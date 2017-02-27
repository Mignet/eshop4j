package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.act.redpacket.service.RedPacketService;
import com.eshop4j.api.request.crm.InvotationRequest;
import com.eshop4j.api.request.crm.RegisterSevReq;
import com.eshop4j.core.base.ServiceResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.CrmCfplannerMapper;
import com.eshop4j.web.dao.SequenceDao;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.CfpLevelEnum;
import com.eshop4j.web.enums.YesOrNotEnum;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.acc.AcAccountRecharge;
import com.eshop4j.web.model.crm.CrmCfpNewcomerTask;
import com.eshop4j.web.model.crm.InvotateUserListResp;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.CrmCfgLevelService;
import com.eshop4j.web.service.CrmCfpNewcomerTaskService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.util.MD5;
import com.eshop4j.xoss.util.PwdUtil;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfplannerService")
public class CrmCfplannerServiceImpl extends GenericServiceImpl<CrmCfplanner, Long> implements CrmCfplannerService{
	
	private static final Logger logger = LoggerFactory.getLogger(CrmCfplannerServiceImpl.class);
	
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private SequenceDao sequenceDao;
	
	@Resource
	private CrmCfgLevelService crmCfgLevelService;
	
	@Resource
	private AcAccountBindService accountbindService;

	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private RedPacketService redPacketService;
	
	@Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
	
	@Override
    public GenericDao<CrmCfplanner, Long> getDao() {
        return crmCfplannerMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		logger.debug(" -- CrmCfplanner -- 排序和模糊查询 ");
		Page<CrmCfplanner> page = new Page<CrmCfplanner>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfplanner> list = this.crmCfplannerMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    /**
	 * 号码是否已注册
	 * @param mobile
	 * @return
	 */
	@Override
	public boolean isExistsCfplanner(String mobile) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setMobile(mobile);
		bo = this.selectOne(bo);
		if(bo != null){
			return true;
		}
		return false;
	}
	
	public boolean isDisabledLogin90days(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		Date disabledLoginTime = crmCfplanner.getDisabledLoginTime();
		if(disabledLoginTime == null || "".equals(disabledLoginTime)) {
			return false;
		}
		Date newDate = DateUtils.addDay(disabledLoginTime, 90); 
		logger.debug("解禁登录日期: " + DateUtils.format(newDate,DateUtils.FORMAT_SHORT));
		Date newDateFormat = null;
		try {
			newDateFormat = DateUtils.parse(newDate, DateUtils.FORMAT_SHORT);
		} catch (Exception e) {
			logger.error("格式化时间错误 " + e);
		}
		int rlt = DateUtils.compareDate(new Date(), newDateFormat);
		if(rlt == -1) {
			//今天小于解禁登录日期
			return true;
		}
		return false;
	}
	
	@Override
	public String queryDisabledLoginTime(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		Date disabledLoginTime = crmCfplanner.getDisabledLoginTime();
		if(disabledLoginTime == null || "".equals(disabledLoginTime)) {
			return null;
		} else {
			String disabledLoginTimeStr = DateUtils.format(disabledLoginTime,DateUtils.FORMAT_SHORT);
			return disabledLoginTimeStr;
		}
	}

	/**
	 * 根据电话号码获取用户
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByMobile(String mobile) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setMobile(mobile);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}

	/**
	 * 注册理财师
	 * @param registerSevReq
	 * @return
	 */
	@Override
	@Transactional
	public ServiceResponse<Boolean> registerLcs(final RegisterSevReq registerSevReq) {
		CrmCfplanner  crmCfplanner = this.queryCfplannerByMobile(registerSevReq.getMobile());
		if(crmCfplanner!=null){
			 return new ServiceResponse<Boolean>(CrmUserInfoService.Error.REGISTER_ISEXISTS);
		}
		
		String userId = null;
		//基础用户表数据
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(registerSevReq.getMobile());
		if(crmUserInfo == null) {
			userId =  StringUtils.getUUID();
			crmUserInfo = new CrmUserInfo();
			crmUserInfo.setCreateTime(new Date());
			crmUserInfo.setMobile(registerSevReq.getMobile());
			crmUserInfo.setPassword(MD5.crypt(registerSevReq.getPassword()));
			crmUserInfo.setUpdateTime(new Date());
			crmUserInfo.setUserId(userId);
			crmUserInfoService.insert(crmUserInfo);
		}else {
			userId = crmUserInfo.getUserId();
		}
		
		//账户表信息
		accountbindService.initAccountBind(userId, AppTypeEnum.CHANNEL.getKey());
		
		//理财师表数据
		crmCfplanner = new CrmCfplanner();
		if(StringUtils.isNotBlank(registerSevReq.getParentId())){//有推荐人
			crmCfplanner.setParentId(registerSevReq.getParentId());
			crmCfplanner.setSalesOrgId(registerSevReq.getSalesOrgId());
		} else {
			if(StringUtils.isNotBlank(registerSevReq.getSalesOrgId())) {
				if(this.salesOrgIsExist(registerSevReq.getSalesOrgId())) {
					crmCfplanner.setSalesOrgId(registerSevReq.getSalesOrgId());
				}
			}
		}
		crmCfplanner.setCfpLevel(CfpLevelEnum.REGISTERED.getValue());
		crmCfplanner.setMobile(registerSevReq.getMobile());
		crmCfplanner.setCfpRegTime(new Date());
		crmCfplanner.setDelStatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		crmCfplanner.setDepartment("99999999");
		crmCfplanner.setUserId(userId);
		crmCfplanner.setEasemobAcct("cfp" + userId);//环信帐号
		crmCfplanner.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
		crmCfplanner.setCreateTime(new Date());
		crmCfplanner.setUpdateTime(new Date());
		crmCfplanner.setRectVisitTime(new Date());
		if(registerSevReq.getFromUrl() != null && !"".equals(registerSevReq.getFromUrl()) && registerSevReq.getFromUrl().length() > 512) {
			crmCfplanner.setRegisterFromUrl(registerSevReq.getFromUrl().substring(0, 512));
		} else {
			crmCfplanner.setRegisterFromUrl(registerSevReq.getFromUrl());
		}
		if(registerSevReq.getAccessUrl() != null && !"".equals(registerSevReq.getAccessUrl()) && registerSevReq.getAccessUrl().length() > 512) {
			crmCfplanner.setRegisterAccessUrl(registerSevReq.getAccessUrl().substring(0, 512));
		} else {
			crmCfplanner.setRegisterAccessUrl(registerSevReq.getAccessUrl());
		}
		this.insert(crmCfplanner);
		
		//理财师新手任务
		CrmCfpNewcomerTask crmCfpNewcomerTask = new CrmCfpNewcomerTask();
		crmCfpNewcomerTask.setUserId(userId);
		crmCfpNewcomerTask.setCreateTime(new Date());
		crmCfpNewcomerTask.setLastUpdateTime(new Date());
		crmCfpNewcomerTaskService.insert(crmCfpNewcomerTask);
		
		//投资用户信息表
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByMobile(registerSevReq.getMobile());
		if(crmInvestor == null) {
			crmInvestor = new CrmInvestor();
			CrmInvestor refUserCustomer = null;//邀请人
			if(StringUtils.isNotBlank(registerSevReq.getParentId())){
				refUserCustomer = crmInvestorService.queryInvestorByUserId(registerSevReq.getParentId());
			}
			if(null != refUserCustomer){
				crmInvestor.setRefUser(registerSevReq.getParentId());
				//判断邀请人是否为理财师
				CrmCfplanner refCrmCfplanner = this.queryCfplannerByUserId(registerSevReq.getParentId());
				if(null != refCrmCfplanner ){
					crmInvestor.setRefType(new Byte("1"));//理财师邀请
				}else{
					crmInvestor.setRefType(new Byte("2"));//客户邀请
				}
			}
			crmInvestor.setCfplanner(userId);//理财师的理财师是自己
			crmInvestor.setIsFreeCustomer(new Byte("0"));
			crmInvestor.setUserId(userId);
			crmInvestor.setMobile(registerSevReq.getMobile());
			crmInvestor.setCreateTime(new Date());
			crmInvestor.setUpdateTime(new Date());
			crmInvestor.setRectVisitTime(new Date());
			crmInvestor.setEasemobAcct("inv" + userId);//环信帐号
			crmInvestor.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
			crmInvestor.setRegisterFromUrl(registerSevReq.getFromUrl());
			crmInvestor.setRegisterAccessUrl(registerSevReq.getAccessUrl());
			crmInvestorService.insert(crmInvestor);
			
			//注册送5元现金
			String swicth = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_SEND_CASH_SWICTH);
			if("ON".equals(swicth)){
				try {
					AcAccountRecharge recharge = new AcAccountRecharge();
					recharge.setRedpacketId(StringUtils.getUUID());
					recharge.setRemark("注册送5元现金");
					recharge.setTransAmount(BigDecimal.valueOf(5));
					recharge.setTransType(3);//活动奖励
					recharge.setUserId(userId);
					recharge.setUserType(2);
					accountbindService.accountRecharge(recharge);
				} catch (Exception e) {
					logger.error("注册送5元现金失败" , e);
				}
			}
			
		}else {
			CrmInvestor crmInvestorUpdate = new CrmInvestor();
			crmInvestorUpdate.setCfplanner(userId);
			crmInvestorUpdate.setIsFreeCustomer(new Byte("0"));
			crmInvestorUpdate.setId(crmInvestor.getId());
			crmInvestorService.update(crmInvestorUpdate);
		}
		
		//计算注册用户上级理财师的职级
		if(StringUtils.isNotBlank(registerSevReq.getParentId())){
			crmCfgLevelService.rankCalculation(userId);
		}
		return new ServiceResponse<Boolean>(true);
			
	}

	/**
	 * 销售机构是否存在
	 * @param salesOrgId
	 * @return
	 */
	private boolean salesOrgIsExist(String salesOrgId) {
		int cot = crmCfplannerMapper.querySalesOrgCount(salesOrgId);
		if(cot > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据理财师编码号码获取用户
	 * @param number 理财师编码
	 * @return
	 */
	public CrmCfplanner queryCfplannerByNumber(String number) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setNumber(number);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}
	
	/**
	 * 创建理财师编码
	 */
	public String getNumber(){
		String number = sequenceDao.getSequence("tcrm_cfplanner");
		CrmCfplanner oldsaleuserInfo = this.queryCfplannerByNumber(number);
		if(null == oldsaleuserInfo){
			return number;
		}else{
			return getNumber();
		}
	}

	/**
	 * 检测是否能被推荐
	 */
	@Override
	public ServiceResponse<Boolean> checkCfgRecommend(String recommendUserNumber, String mobile) {
		if(isExistsCfplanner(mobile)){
			return new ServiceResponse<Boolean>(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_ISREG);
		}
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByMobile(mobile);
			if(null != crmInvestor && crmInvestor.getIsFreeCustomer() == 1 ){
				//自由用户不保护专属关系
				return new ServiceResponse<Boolean>(true);
			}
			Date curr = DateUtils.addDay(new Date(), -180);
			//处于保护时间内
			if(crmInvestor != null && crmInvestor.getCfplanner() != null &&
					crmInvestor.getRectVisitTime() != null && curr.before(crmInvestor.getRectVisitTime())){
				//是当前理财师客户
				if(recommendUserNumber != null && recommendUserNumber.equals(crmInvestor.getCfplanner())){
					return new ServiceResponse<Boolean>(true);
				}else{
					return new ServiceResponse<Boolean>(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_PROTECTED);
				}
			}
		return new ServiceResponse<Boolean>(true);
	}
	
	/**
	 * 检测用户是否必须需要推荐才能注册
	 * @param mobile 被检测手机号
	 * @return
	 */
	@Override
	public boolean checkCfgNeedRc(String mobile) {
		CrmInvestor customer = crmInvestorService.queryInvestorByMobile(mobile);
		if(null != customer && customer.getIsFreeCustomer() == 1){
			//自由用户不保护专属关系
			return false;
		}
		Date curr = DateUtils.addDay(new Date(), -180);
		//处于保护时间内
		if(customer != null&&StringUtils.isNotBlank(customer.getCfplanner()) &&
			customer.getRectVisitTime() != null && curr.before(customer.getRectVisitTime())){
			return true;
		}
		return false;
	}

	/**
	 * 查投资用户的理财师
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByInvestMobile(String investorMobile) {
		return crmCfplannerMapper.queryCfplannerByInvestMobile(investorMobile);
	}

	/**
	 * 查询手机号码已注册的理财师userId
	 */
	@Override
	public List<String> selectRegCfplanners(String[] mobileArray) {
		return crmCfplannerMapper.selectRegCfplanners(mobileArray);
	}

	/**
	 * 根据userId查理财师信息
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByUserId(String userId) {
		if(StringUtils.isBlank(userId)) {
			return null;
		}
		CrmCfplanner bo = new CrmCfplanner();
		bo.setUserId(userId);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}

	/**
	 * 更新理财师二维码字段
	 */
	@Override
	public void updateCfpQrByUserId(String userId, String qrcode) {
		CrmCfplanner crmCfplanner = new CrmCfplanner();
		crmCfplanner.setUserId(userId);
		crmCfplanner.setQrcode(qrcode);
		crmCfplannerMapper.updateCfpQrByUserId(crmCfplanner);
	}

	/**
	 * 根据环信帐号查理财师信息
	 * @param easemobAcctList
	 * @return
	 */
	@Override
	public List<CrmCfplanner> queryCfplannerByEasemob(List<String> easemobAcctList) {
		return crmCfplannerMapper.queryCfplannerByEasemob(easemobAcctList);
	}

	/**
	 * 查投资者的理财师
	 * @param userId
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByInvestor(String investorUserId) {
		return crmCfplannerMapper.queryCfplannerByInvestor(investorUserId);
	}

	/**
	 * 查理财师的团队人数
	 * @param userId 理财师userId
	 * @return  团队人数
	 */
	@Override
	public int queryTeamMemberCount(String userId) {
		return crmCfplannerMapper.queryTeamMemberCount(userId);
	}

	/**
	 * 更新理财师等级与经验
	 * @param userId 理财师用户编号
	 * @param level 理财师等级
	 * @param experience 理财师增加经验
	 */
	@Override
	public void updateCfplannerRankExperience(String userId, String level,Integer experience) {
		crmCfplannerMapper.updateCfplannerRankExperience(userId, level, experience);
	}

	/**
	 * 修改理财师信息
	 * @param bo
	 * @return
	 */
	@Override
	public int updateByUserId(CrmCfplanner crmCfplanner) {
		return crmCfplannerMapper.updateByUserId(crmCfplanner);
	}

	/**
	 * 查理财师的所有团队成员
	 * @param userId
	 * @return
	 */
	@Override
	public List<CrmCfplanner> queryTeamAllMember(String userId) {
		return crmCfplannerMapper.queryTeamAllMember(userId);
	}

	/**
	 * 符合分配规则的理财师
	 * @return
	 */
	@Override
	public List<String> queryConformAllotRuleCfps() {
		List<String> list = crmCfplannerMapper.queryConformAllotRuleCfps();
		if(list == null || list.size() == 0) {
			list = crmCfplannerMapper.queryLoginInSevenDaysCfp();
		}
		return list;
	}

	@Override
	public CrmCfplanner queryParentByUserId(String userId) {
		CrmCfplanner cfp = this.queryCfplannerByUserId(userId);
		CrmCfplanner parent = null;
		if(cfp != null && cfp.getParentId() != null && !"".equals(cfp.getParentId())) {
			parent = this.queryCfplannerByUserId(cfp.getParentId());
		}
		return parent;
	}

	@Override
	public int queryCustomerCount(String userId) {
		return crmCfplannerMapper.queryCustomerCount(userId);
	}

	@Override
	public boolean isLockedCfplanner(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		if(crmCfplanner != null && crmCfplanner.getIsLocked() != null &&  crmCfplanner.getIsLocked() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> querySpecifiedCfps() {
		return crmCfplannerMapper.querySpecifiedCfps();
	}

	@Override
	public PaginatorResponse<InvotateUserListResp> queryInvitationCfplannerRecord(InvotationRequest req,
			Page<InvotateUserListResp> page) {
		PaginatorResponse<InvotateUserListResp> paginatorResponse = new PaginatorResponse<InvotateUserListResp>();
		paginatorResponse.setDatas(crmCfplannerMapper.queryInvitationCfplannerRecord(req,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryInvitationCfplannerRecordStatistics(String userId) {
		return crmCfplannerMapper.queryInvitationCfplannerRecordStatistics(userId);
	}

}
