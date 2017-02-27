package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.dao.CustomerMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.CfpCustomerCountResp;
import com.linkwee.web.model.crm.MycustomersResp;
import com.linkwee.web.model.crm.OrgSimpleResp;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CustomerService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.util.WebUtil;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Resource
	private CrmUserInfoMapper crmUserInfoMapper;
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Resource
	private SysMsgService msgService;
	
	@Resource
    private CimProductInvestRecordService investRecordService;
	
	@Resource
    private CimProductInvestRecordService cimProductInvestRecordService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
    private PartnerService partnerService;
	
	/**
	 * 客户首页统计信息
	 * @param userId
	 * @return
	 */
	@Override
	public CfpCustomerCountResp queryCfpCustomerCountResp(String userId) {
		CfpCustomerCountResp rlt = new CfpCustomerCountResp();
		rlt.setDayInvestAmt(this.queryDayInvestAmt(userId));//今日销售
		rlt.setMonthInvestAmt(this.queryMonthInvestAmt(userId));//本月销售
		rlt.setTotalInvestAmt(this.queryTotalInvestAmt(userId));//历史销售
		rlt.setMinTime(customerMapper.queryInvestMinTime(userId));
		//未读赎回记录
		rlt.setNewBacktradeCount(investRecordService.queryCustomerRepaymentTradeMsgCount(userId));
		//未读购买记录
		rlt.setNewBuytradeCount(investRecordService.queryCustomerInvestTradeMsgCount(userId));
		//未读客户数量
		SysApiInvokeLog apiInvokeLogCustomer = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_MYCUSTOMERS_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		Date readCustomerDate = null;
		if(apiInvokeLogCustomer!=null){
			readCustomerDate = apiInvokeLogCustomer.getChgTime();
		}else{
			readCustomerDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		rlt.setNewCustomerCount(this.queryNewCustomerCount(userId, readCustomerDate));
		//未读动态数量
		rlt.setNewMsgCount(cimProductInvestRecordService.queryCfpNewlyDynamicUnReadCount(userId));
		
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		String level = WebUtil.getDefaultFormat(EnumUtils.getKeyByKvmEnumValue(cfp.getCfpLevel(), CfpLevelEnum.values()));
		//职级
		rlt.setLevel(level);
		//团队人数
		rlt.setTeamCount(partnerService.queryMyRcCount(userId) + partnerService.queryChildrenRcCount(userId));
		//本月推荐奖励
		rlt.setThisMonthAllowance(partnerService.queryThisMonthAllowance(userId));
		//本月佣金
		rlt.setThisMonthFee(customerMapper.queryThisMonthFee(userId));
		//本月团队销售额
		rlt.setThisMonthTeamSaleAmount(partnerService.queryThisMonthTeamSaleAmount(userId));
		rlt.setHasCustomer(1);
		if(rlt.getTeamCount() > 0) {
			rlt.setHasTeamMembers(1);
		} else {
			rlt.setHasTeamMembers(0);
		}
		
		rlt.setHasAdvertisement(0);
		rlt.setAdvertisementImageUrl("");
		rlt.setAdvertisementLinkUrl("");
		return rlt;
	}

	/**
	 * 未读客户数量
	 * @param userId
	 * @param date
	 * @return
	 */
	private int queryNewCustomerCount(String userId, Date date) {
		return customerMapper.queryNewCustomerCount(userId, date);
	}

	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<MycustomersResp> queryMycustomerList(Map<String, Object> query, Page<MycustomersResp> page){
		PaginatorSevResp<MycustomersResp> paginatorResponse = new PaginatorSevResp<MycustomersResp>();
		paginatorResponse.setDatas(customerMapper.queryMycustomerList(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	
	public Double queryTotalInvestAmt(String userId) {
		return customerMapper.queryTotalInvestAmt(userId);
	}

	public Double queryMonthInvestAmt(String userId) {
		return customerMapper.queryMonthInvestAmt(userId);
	}

	public Double queryDayInvestAmt(String userId) {
		return customerMapper.queryDayInvestAmt(userId);
	}

	@Override
	public Double queryFeeAmtByCfpAndInvestor(String cfplanner, String investor) {
		return customerMapper.queryFeeAmtByCfpAndInvestor(cfplanner, investor);
	}

	@Override
	public List<OrgSimpleResp> queryRegisteredOrgList(String userId) {
		return customerMapper.queryRegisteredOrgList(userId);
	}

	
	
}
