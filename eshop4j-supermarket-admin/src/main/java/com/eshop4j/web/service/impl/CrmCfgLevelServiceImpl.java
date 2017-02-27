package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.tc.fee.common.CalculateTools;
import com.eshop4j.web.dao.CrmCfgLevelMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.CfpLevelEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.CrmCfpUpgradeRecord;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.CrmCfgLevel;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.model.weixin.WeiXinMsgRequest;
import com.eshop4j.web.service.CrmCfgLevelService;
import com.eshop4j.web.service.CrmCfpUpgradeRecordService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.web.service.WeiXinMsgService;
import com.eshop4j.xoss.helper.ConfigHelper;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.util.Operation;
import com.eshop4j.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述：CrmCfgLevelService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月26日 17:13:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfgLevelService")
public class CrmCfgLevelServiceImpl extends GenericServiceImpl<CrmCfgLevel, Long> implements CrmCfgLevelService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfgLevelServiceImpl.class);
	
	@Resource
	private CrmCfgLevelMapper crmCfgLevelMapper;
	
	@Autowired
	protected CrmCfplannerService cfplannerService;
	
	@Autowired
	protected CrmInvestorService investorService;
	
	
	@Autowired 
	private SysMsgService sysMsgService;
	
	@Autowired
	private PushMessageHelper pushMessageHelper;
	
	@Autowired
	private CrmCfpUpgradeRecordService cfpUpgradeRecordService;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Override
    public GenericDao<CrmCfgLevel, Long> getDao() {
        return crmCfgLevelMapper;
    }
	
	
	@Override
	public void rankCalculation(String userId){
		if(StringUtils.isBlank(userId))return;
		//查询理财师
		CrmCfplanner cfplanner =  cfplannerService.queryCfplannerByUserId(userId);
		if(cfplanner==null){
			LOGGER.warn("calculate rankCalculation Cfplanner do not exist investor id ={}", userId);
			return;
		}
		//计算理财师团队业绩
		calculationTeamPerformance(cfplanner);
		
		
		//查询上级理财师
		if(StringUtils.isBlank(cfplanner.getParentId()))return;
	    cfplanner =  cfplannerService.queryCfplannerByUserId(cfplanner.getParentId());
		if(cfplanner==null){
			LOGGER.warn("calculate rankCalculation Cfplanner do not exist investor id ={}", userId);
			return;
		}
		//计算上级理财师团队业绩
		calculationTeamPerformance(cfplanner);
		//查询上上理财师
		if(StringUtils.isBlank(cfplanner.getParentId()))return;
	    cfplanner =  cfplannerService.queryCfplannerByUserId(cfplanner.getParentId());
	   
		if(cfplanner==null){
			LOGGER.warn("calculate rankCalculation Cfplanner do not exist investor id ={}", userId);
			return;
		}
		//计算上上理财师团队业绩
		calculationTeamPerformance(cfplanner);
		
		
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		try{
			rankCalculation(investRecord);
		}catch(Exception e){
			throw e;
		}
	}
    
	
	/**
	 * 计算职级
	 * @param investRecord
	 */
	protected void rankCalculation(InvestRecordWrapper investRecord) throws Exception{
		try{
			
			//查询用户
			CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
			
			
			if(StringUtils.isBlank(investor.getCfplanner())){
				LOGGER.warn("calculate rankCalculation Cfplanner do not exist investor id ={}", investRecord.getUserId());
				return;
			}
			//查询理财师
			CrmCfplanner cfplanner =  cfplannerService.queryCfplannerByUserId(investor.getCfplanner());
			
			Validate.notNull(cfplanner, "Cfplanner do not exist Cfplanner id = "+ investor.getCfplanner());
		
			//计算经验
			BigDecimal experience = CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), investRecord.getProductDays());
						
			//获取理财师下一级
			CrmCfgLevel nextLevel = getNextLevel(cfplanner);
			
			//计算个人业绩并确定是否升级
			boolean isUpgrade = false;
			//需要升级到下一级 则计算 不需要升级 则只加经验值
			if(nextLevel != null ){
				isUpgrade =	calculationIndividualPerformance(cfplanner,experience, nextLevel);
			}
	
			//获取级别,如果升级则获取下一级级别名称 否则获取当前级别名称
			String  curLevel = isUpgrade ? nextLevel.getCode():cfplanner.getCfpLevel();
			
			//更新数据
			cfplannerService.updateCfplannerRankExperience(cfplanner.getUserId(), curLevel, experience.intValue());
			
			
			//升级发送消息
			if(isUpgrade){
				sendMsg(cfplanner.getUserId(),cfplanner.getCfpLevel(),nextLevel.getCode());
			}else if(!isUpgrade && nextLevel != null ){//个人业绩不升级,则计算团队业绩
				rankCalculation(cfplanner.getUserId());
			}
			
			
		}catch(Exception e){
			LOGGER.warn("rankCalculation exception investRecord={},exception={}", investRecord,e);
		}
	}
	
	/**
	 * 获取理财师下一级
	 * @param cfplanner
	 * @return
	 */
	protected CrmCfgLevel getNextLevel(CrmCfplanner cfplanner){
		
		CfpLevelEnum levelEnum = CfpLevelEnum.valueOf(cfplanner.getCfpLevel());
		
		//判断是否升级 
		boolean isUpgrade = !ObjectUtils.equals(levelEnum, CfpLevelEnum.CHIEF);
		//获取下一级升级条件 首席理财师不升级
		CfpLevelEnum nextLevelEnum = isUpgrade ? CfpLevelEnum.getLevelByKey(levelEnum.getKey()+1) : CfpLevelEnum.CHIEF;
		
		CrmCfgLevel nextLevel = null;
		//获取下一级条件并判断
		if(isUpgrade){
			nextLevel = new CrmCfgLevel();
			nextLevel.setCode(nextLevelEnum.getValue());
			return  crmCfgLevelMapper.selectOneByCondition(nextLevel);
		}
		
		return null;
	}
	
	
	/**
	 * 计算个人业绩并确定是否升级
	 * @param cfplanner
	 * @param nextLevel
	 * @return 是否升级 true=是|false=否
	 */
	protected boolean calculationIndividualPerformance(CrmCfplanner cfplanner,BigDecimal experience,CrmCfgLevel nextLevel){
		//理财师个人业绩是否大于等于下一级的个人业绩升级条件
		return (experience.add(new BigDecimal(cfplanner.getLevelExperience()))).compareTo(nextLevel.getIndividualPerformance())>-1;
	}
	
	/**
	 * 计算团队业绩并确定是否升级
	 * @param cfplanner
	 * @param nextLevel
	 * @return 是否升级 true=是|false=否
	 */
	protected boolean calculationTeamPerformance(CrmCfplanner cfplanner,CrmCfgLevel nextLevel){
		
		//获取团队成员
		List<CrmCfplanner> teamMembers = cfplannerService.queryTeamAllMember(cfplanner.getUserId());
		
		if(teamMembers==null || teamMembers.isEmpty())return false;
		
		//计算团队成员业绩
		Integer teamPerformance =0;
		for (CrmCfplanner crmCfplanner : teamMembers) {
			teamPerformance = teamPerformance + crmCfplanner.getLevelExperience();
		}
		return Operation.GE.compare(new Integer(teamMembers.size()).doubleValue(), nextLevel.getTeamCount().doubleValue()) && Operation.GE.compare(teamPerformance.doubleValue(),nextLevel.getTeamPerformance().doubleValue());
		
	}
	/**
	 * 团队业绩计算
	 * @param userId
	 */
	protected void calculationTeamPerformance(CrmCfplanner cfplanner){
		CrmCfgLevel nextLevel = getNextLevel(cfplanner);
		if(nextLevel==null)return;
		//升级发送消息
		if(calculationTeamPerformance(cfplanner, nextLevel)){
			sendMsg(cfplanner.getUserId(),cfplanner.getCfpLevel(), CfpLevelEnum.valueOf(nextLevel.getCode()).getMsg());
		    sendWeiXinMsg(cfplanner,CfpLevelEnum.valueOf(nextLevel.getCode()).getMsg());
		}
		
		
	}
	
	protected void sendWeiXinMsg(CrmCfplanner cfplanner,String cfpLevel){
		//推送微信消息 
		final WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
		wxreq.setUseId(cfplanner.getUserId());
		wxreq.setTemkey(SysConfigConstant.GRADE_CHANGE);
		wxreq.setUserName(cfplanner.getUserName()+RandomTextCreator.encrypTion(cfplanner.getMobile()));//用户名
		wxreq.setProductName("晋级为"+cfpLevel);//变更类型
		wxreq.setUseType("1");
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				weiXinMsgService.sendWeiXinMsgCommon(wxreq);
			}
		});
	}
	
	protected void sendMsg(String userId,String beforeLevel,String cfpLevel){
		String msg = CfpLevelEnum.valueOf(cfpLevel).getMsg();
		Date date =new Date();
		CrmCfpUpgradeRecord cfpUpgradeRecord = new CrmCfpUpgradeRecord();
		cfpUpgradeRecord.setUserId(userId);
		cfpUpgradeRecord.setCfpLevel(cfpLevel);
		cfpUpgradeRecord.setCfpLevelBefore(beforeLevel);
		cfpUpgradeRecord.setRemarks(msg);
		cfpUpgradeRecord.setLastUpdateTime(date);
		cfpUpgradeRecord.setCreateTime(date);
		cfpUpgradeRecordService.insert(cfpUpgradeRecord);
		pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LSJAPPCS, userId, "晋级通知", String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSUPGRADE),msg), null, true);
		
	}
	
	

}
