package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.cim.ProductRecommendChooseRequest;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmInvestorMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.InvestorNewcomerTaskEnum;
import com.linkwee.web.enums.InvestorOperationType;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.enums.YesOrNotEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorOperation;
import com.linkwee.web.model.CrmInvestorRecommend;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.InvestorBindPlatformDatable;
import com.linkwee.web.model.crm.InvestorBindPlatformListResp;
import com.linkwee.web.model.crm.InvotateUserListResp;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorOperationService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.PwdUtil;
import com.linkwee.xoss.util.RejectedExecuteRetry;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmInvestorService")
public class CrmInvestorServiceImpl extends GenericServiceImpl<CrmInvestor, Long> implements CrmInvestorService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmInvestorServiceImpl.class);
	
	@Resource
	private CrmInvestorMapper crmInvestorMapper;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private CrmInvestorOperationService crmInvestorOperationService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
	private CimOrginfoService CimOrgInfoService;
	@Resource
	private RedPacketService redPacketService;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Override
    public GenericDao<CrmInvestor, Long> getDao() {
        return crmInvestorMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmInvestor -- 排序和模糊查询 ");
		Page<CrmInvestor> page = new Page<CrmInvestor>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmInvestor> list = this.crmInvestorMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    /**
	 * 投资用户是否存在
	 * @param mobile
	 * @return
	 */
	@Override
	public boolean isExistsInvestor(String mobile) {
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setDelStatus(YesOrNotEnum.NOT.getCode());
		crmInvestor.setMobile(mobile);
		crmInvestor = this.selectOne(crmInvestor);
		if(crmInvestor != null){
			return true;
		}
		return false;
	}

	/**
	 * 根据电话号码查投资用户
	 * @param recommendCode
	 * @return
	 */
	@Override
	public CrmInvestor queryInvestorByMobile(String mobile) {
		CrmInvestor bo = new CrmInvestor();
		bo.setDelStatus(YesOrNotEnum.NOT.getCode());
		bo.setMobile(mobile);
		CrmInvestor crmInvestor = crmInvestorMapper.selectOneByCondition(bo);
		return crmInvestor;
	}

	/**
	 * 投资者注册
	 */
	@Override
	@Transactional
	public String registerInvestor(String mobile, String password, String refUserId, String fromUrl, String accessUrl) {
		String userId = StringUtils.getUUID();
		//保存用户基础数据
		CrmUserInfo crmUserInfo = new CrmUserInfo();
		crmUserInfo.setCreateTime(new Date());
		crmUserInfo.setMobile(mobile);
		crmUserInfo.setPassword(MD5.crypt(password));
		crmUserInfo.setUpdateTime(new Date());
		crmUserInfo.setUserId(userId);
		crmUserInfoService.insert(crmUserInfo);
		
		//账户表信息
		accountbindService.initAccountBind(userId, AppTypeEnum.INVESTOR.getKey());
		
		//保存投资者表数据
		CrmInvestor crmInvestor = new CrmInvestor();
		CrmInvestor refUserCustomer = null;//邀请人
		if(StringUtils.isNotBlank(refUserId)){
			refUserCustomer = this.queryInvestorByUserId(refUserId);
		}
		if(null != refUserCustomer){
			//如果邀请人不为空,将邀请人的理财师设置为当前注册用户的理财师
			crmInvestor.setCfplanner(refUserCustomer.getCfplanner());
			crmInvestor.setRefUser(refUserId);
			crmInvestor.setIsFreeCustomer(refUserCustomer.getIsFreeCustomer());//根据邀请人是否自由客户身份来设置注册人身份
			//判断邀请人是否为理财师
			CrmCfplanner refCrmCfplanner = crmCfplannerService.queryCfplannerByUserId(refUserId);
			if(null != refCrmCfplanner ){
				crmInvestor.setRefType(new Byte("1"));//理财师邀请
			}else{
				crmInvestor.setRefType(new Byte("2"));//客户邀请
			}
		} else {
			crmInvestor.setIsFreeCustomer(new Byte("1"));//自由客户
		}
		crmInvestor.setUserId(userId);
		crmInvestor.setMobile(mobile);
		crmInvestor.setCreateTime(new Date());
		crmInvestor.setUpdateTime(new Date());
		crmInvestor.setRectVisitTime(new Date());
		crmInvestor.setEasemobAcct("inv" + userId);//环信帐号
		crmInvestor.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
		if(fromUrl != null && !"".equals(fromUrl) && fromUrl.length() > 512) {
			crmInvestor.setRegisterFromUrl(fromUrl.substring(0, 512));
		} else {
			crmInvestor.setRegisterFromUrl(fromUrl);
		}
		if(accessUrl != null && !"".equals(accessUrl) && accessUrl.length() > 512) {
			crmInvestor.setRegisterAccessUrl(accessUrl.substring(0, 512));
		} else {
			crmInvestor.setRegisterAccessUrl(accessUrl);
		}
		this.insert(crmInvestor);
		
		//分配理财师
		if(refUserId == null) {
			allotCfplanner(userId, mobile);
		}
		return userId;
	}

	/**
	 * 分配理财师
	 * @param userId
	 */
	public void allotCfplanner(String userId,String mobile) {
		/*List<String> cfplannerList = crmCfplannerService.queryConformAllotRuleCfps();*/
		List<String> cfplannerList = crmCfplannerService.querySpecifiedCfps();
		if(cfplannerList != null && cfplannerList.size() >0) {
			Random rand = new Random();
			int i = rand.nextInt(cfplannerList.size()); 
			String cfplanner = cfplannerList.get(i);
			CrmInvestor crmInvestor = new CrmInvestor();
			crmInvestor.setCfplanner(cfplanner);
			crmInvestor.setUserId(userId);
			this.updateByUserId(crmInvestor);
			
			//日志
			CrmInvestorOperation operation = new CrmInvestorOperation();
			operation.setCfplanner(cfplanner);
			operation.setCreateTime(new Date());
			operation.setInvestor(userId);
			operation.setLastUpdateTime(new Date());
			operation.setOperationAdmin("system");
			operation.setRemarks(InvestorOperationType.ALLOT_CFPLANNER.getMessage());
			operation.setType(InvestorOperationType.ALLOT_CFPLANNER.getCode());
			crmInvestorOperationService.insert(operation);
			
			//消息
			String content = String.format(sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_LASSIGNCUSTOMER,AppTypeEnum.CHANNEL.getKey()), "*" + mobile.substring(mobile.length() - 4));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", userId);
			try {
				pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LASSIGNCUSTOMER, cfplanner, "平台分配客户", content,map, true);
			} catch (Exception e) {
				LOGGER.error("分配理财师消息推送异常" + e);
			}
		}
	}

	/**
	 * 保存投资者表数据
	 * @param mobile
	 * @param password
	 * @param refUserId
	 * @param type
	 */
	@Override
	public void saveInvestor(String mobile, String userId, String refUserId, AppTypeEnum type) {
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setMobile(mobile);
		crmInvestor = this.queryInvestorByMobile(mobile);
		if(crmInvestor == null) {
			crmInvestor = new CrmInvestor();
			CrmInvestor refUserCustomer = null;//邀请人
			if(StringUtils.isNotBlank(refUserId)){
				refUserCustomer = new CrmInvestor();
				refUserCustomer.setUserId(refUserId);
				refUserCustomer = this.selectOne(refUserCustomer);
			}
			
			if(null != refUserCustomer){
				//如果邀请人不为空,将邀请人的理财师设置为当前注册用户的理财师
				crmInvestor.setCfplanner(refUserCustomer.getCfplanner());
				crmInvestor.setRefUser(refUserId);
				crmInvestor.setIsFreeCustomer(refUserCustomer.getIsFreeCustomer());//根据邀请人是否自由客户身份来设置注册人身份
				//判断邀请人是否为理财师
				CrmCfplanner refCrmCfplanner = new CrmCfplanner();
				refCrmCfplanner.setUserId(refUserId);
				refCrmCfplanner = crmCfplannerService.selectOne(refCrmCfplanner);
				if(null != refCrmCfplanner ){
					crmInvestor.setRefType(new Byte("1"));//理财师邀请
				}else{
					crmInvestor.setRefType(new Byte("2"));//客户邀请
				}
				
			} else {
				crmInvestor.setIsFreeCustomer(new Byte("1"));//自由客户
			}
			if(type.getKey() == AppTypeEnum.CHANNEL.getKey()){
				//理财师注册
				CrmCfplanner saleUserInfo = new CrmCfplanner();
				saleUserInfo.setUserId(userId);
				saleUserInfo = crmCfplannerService.selectOne(saleUserInfo);
				if(null != saleUserInfo ){
					crmInvestor.setCfplanner(saleUserInfo.getUserId());
					crmInvestor.setIsFreeCustomer(new Byte("0"));
				}
			}
			crmInvestor.setUserId(userId);
			crmInvestor.setMobile(mobile);
			crmInvestor.setCreateTime(new Date());
			crmInvestor.setUpdateTime(new Date());
			crmInvestor.setEasemobAcct("inv" + userId);//环信帐号
			crmInvestor.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
			this.insert(crmInvestor);
		}else {
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(userId);
			crmCfplanner = crmCfplannerService.selectOne(crmCfplanner);
			if(null != crmCfplanner){
				//如果注册用户是理财师
				CrmInvestor crmInvestorUpdate = new CrmInvestor();
				crmInvestorUpdate.setCfplanner(crmCfplanner.getUserId());
				crmInvestorUpdate.setId(crmInvestor.getId());
				crmInvestorUpdate.setIsFreeCustomer(new Byte("0"));
				this.update(crmInvestorUpdate);
			}
		}
		
		
	}
	
	/**
	 * 查投资用户信息
	 * @param userId
	 * @return
	 */
	@Override
	public CrmInvestor queryInvestorByUserId(String userId) {
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setUserId(userId);
		crmInvestor = this.selectOne(crmInvestor);
		return crmInvestor;
	}

	/**
	 * 更新投资用户二维码
	 */
	@Override
	public void updateInvQrByUserId(String userId, String qrcode) {
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setUserId(userId);
		crmInvestor.setQrcode(qrcode);
		crmInvestorMapper.updateInvQrByUserId(crmInvestor);
	}

	/**
	 * 查询手机号码已注册的投资用户userId
	 */
	@Override
	public List<String> selectRegInvestors(String[] mobileArray) {
		return crmInvestorMapper.selectRegInvestors(mobileArray);
	}

	/**
	 * 在投资用户表中查询被该用户推荐的用户userId
	 */
	@Override
	public List<String> refRegInvestors(String userId) {
		return crmInvestorMapper.selectRefRegInvestors(userId);
	}

	/**
	 * 根据环信帐号查投资用户信息
	 * @param userId
	 * @return
	 */
	@Override
	public List<CrmInvestor> queryInvestorByEasemob(List<String> easemobAcctList) {
		return crmInvestorMapper.queryInvestorByEasemob(easemobAcctList);
	}

	@Override
	public Map<String, Object> queryEaseToken() {
		return crmInvestorMapper.queryEaseToken();
	}

	@Override
	public void updateEaseToken(String token) {
		crmInvestorMapper.updateEaseToken(token);
	}

	/**
	 * 根据userId更新投资者信息
	 */
	@Override
	public int updateByUserId(CrmInvestor crmInvestor) {
		return crmInvestorMapper.updateByUserId(crmInvestor);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@RejectedExecuteRetry
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		try{
			//更新用户投资信息
			CrmInvestor crmInvestor= new CrmInvestor();
			crmInvestor.setUserId(investRecord.getUserId());
			crmInvestor.setRectInvestTime(investRecord.getInvestTime());
			crmInvestor.setUpdateTime(new Date());
			if(investRecord.isFirstInvest()) {
				crmInvestor.setFirstInvestTime(investRecord.getInvestTime());
				//TODO 首投新手红包
				CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(investRecord.getUserId());
				try {
					LOGGER.info("新手福利首次投资红包：  userInfo={}" ,userInfo);
					actRedpacketService.customerTaskRedPacekt(InvestorNewcomerTaskEnum.FIRST_INVEST, userInfo);
				} catch (Exception e) {
					LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
				}
			}
			updateByUserId(crmInvestor);
			
			//更新第三方用户是否投资
			CimOrgInfoService.updateOrgAcctRelInvested(investRecord.getUserId(), investRecord.getProductOrgId());
			
		}catch(Exception e){
			LOGGER.warn("Update RectInvestTime Exception investRecord={},Exception={}", investRecord,e);
			throw e;
		}
	}

	@Override
	public Date queryFirstRcpDate(String userId) {
		return crmInvestorMapper.queryFirstRcpDate(userId);
	}

	@Override
	public void removeCfplanner(String userId) {
		crmInvestorMapper.removeCfplanner(userId);
	}

	@Override
	public boolean isLockedInventor(String mobile) {
		CrmInvestor crmInvestor = this.queryInvestorByMobile(mobile);
		if(crmInvestor != null && crmInvestor.getIsLocked() != null &&  crmInvestor.getIsLocked() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public DataTableReturn queryInvestorBindPlatformList(InvestorBindPlatformDatable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<InvestorBindPlatformListResp> page = new Page<InvestorBindPlatformListResp>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<InvestorBindPlatformListResp> list = this.crmInvestorMapper.queryInvestorBindPlatformList(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CrmInvestor> selectListByConditionNew(CrmInvestor t) {
		return crmInvestorMapper.selectListByConditionNew(t);
	}

	@Override
	public CrmInvestor queryPlannerByInvestUserId(String investUserId) {
		return crmInvestorMapper.queryPlannerByInvestUserId(investUserId);
	}

	@Override
	public List<CrmInvestorRecommend> selectCrmInvestorRecommend(ProductRecommendChooseRequest productRecommendChooseRequest) {
		return crmInvestorMapper.selectCrmInvestorRecommend(productRecommendChooseRequest);
	}

	@Override
	public List<CrmInvestorRecommend> selectCrmInvestorRecommendOrg(OrgRecommendChooseRequest orgRecommendChooseRequest) {
		return crmInvestorMapper.selectCrmInvestorRecommendOrg(orgRecommendChooseRequest);
	}

	/**
	 * 生成投资用户
	 * @param mobile
	 * @param cfplaner
	 * @return
	 * @throws Exception 
	 */
	@Override
	public CrmInvestor generateInvestor(String mobile, String cfplaner) throws Exception {
		if(StringUtils.isBlank(mobile) || mobile.length() != 11) {
			 throw new Exception("mobile is error!");
		}
		if(StringUtils.isBlank(cfplaner)) {
			 throw new Exception("cfplaner can not be null!");
		}
		String userId = StringUtils.getUUID();
		//保存用户基础数据
		CrmUserInfo crmUserInfo = new CrmUserInfo();
		crmUserInfo.setCreateTime(new Date());
		crmUserInfo.setMobile(mobile);
		crmUserInfo.setPassword(MD5.crypt(mobile.substring(mobile.length()-6, mobile.length())));
		crmUserInfo.setUpdateTime(new Date());
		crmUserInfo.setUserId(userId);
		crmUserInfoService.insert(crmUserInfo);
		
		//账户表信息
		accountbindService.initAccountBind(userId, AppTypeEnum.INVESTOR.getKey());
		
		//保存投资者表数据
		CrmInvestor crmInvestor = new CrmInvestor();
		CrmInvestor refUserCustomer = null;//邀请人
		if(StringUtils.isNotBlank(cfplaner)){
			refUserCustomer = this.queryInvestorByUserId(cfplaner);
		}
		if(null != refUserCustomer){
			//如果邀请人不为空,将邀请人的理财师设置为当前注册用户的理财师
			crmInvestor.setCfplanner(refUserCustomer.getCfplanner());
			crmInvestor.setRefUser(cfplaner);
			crmInvestor.setIsFreeCustomer(refUserCustomer.getIsFreeCustomer());//根据邀请人是否自由客户身份来设置注册人身份
			//判断邀请人是否为理财师
			CrmCfplanner refCrmCfplanner = crmCfplannerService.queryCfplannerByUserId(cfplaner);
			if(null != refCrmCfplanner ){
				crmInvestor.setRefType(new Byte("1"));//理财师邀请
			}else{
				crmInvestor.setRefType(new Byte("2"));//客户邀请
			}
		} else {
			crmInvestor.setIsFreeCustomer(new Byte("1"));//自由客户
		}
		crmInvestor.setUserId(userId);
		crmInvestor.setMobile(mobile);
		crmInvestor.setCreateTime(new Date());
		crmInvestor.setUpdateTime(new Date());
		crmInvestor.setRectVisitTime(new Date());
		crmInvestor.setEasemobAcct("inv" + userId);//环信帐号
		crmInvestor.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
		this.insert(crmInvestor);
		
		try {
			//红包
			CrmUserInfo userInfo =  new CrmUserInfo();
			userInfo.setUserId(userId);
			userInfo.setMobile(mobile);
			redPacketService.customerRegisterRedPacekt(userInfo);
		} catch (Exception e) {
			LOGGER.warn("registerRedPacekt exception userInfo={}" , e.getMessage());
		}
		return crmInvestor;
		
	}

	@Override
	public PaginatorResponse<InvotateUserListResp> queryInvitationCustomerRecord(InvotationRequest req,
			Page<InvotateUserListResp> page) {
		PaginatorResponse<InvotateUserListResp> paginatorResponse = new PaginatorResponse<InvotateUserListResp>();
		paginatorResponse.setDatas(crmInvestorMapper.queryInvitationCustomerRecord(req,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Map<String, Integer> queryInvitationCustomerRecordStatistics(String userId) {
		Map<String, Integer> map = crmInvestorMapper.queryInvitationCustomerRecordStatistics(userId);
		return map;
	}

	@Override
	public int queryInvitationCount(String userId) {
		List<CrmInvestor> invList = new ArrayList<CrmInvestor>();
		CrmInvestor inv = new CrmInvestor();
		inv.setRefUser(userId);
		invList = this.selectListByCondition(inv);
		if(invList != null && invList.size() >0 ) {
			 return invList.size();
		}
		return 0;
	}
}
