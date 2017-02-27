package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.dao.SequenceDao;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CrmCfgLevelService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;


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
	public void updatesSalesOrgId(String salesOrgId, String mobile) {
		crmCfplannerMapper.updatesSalesOrgId(salesOrgId, mobile);
	}

	@Override
	public String queryWeiXinOpenId(String useId) {
		return crmCfplannerMapper.queryWeiXinOpenId(useId);
	}

}
