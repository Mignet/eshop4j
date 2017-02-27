package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.dao.CustomerMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.CfpCustomerCountResp;
import com.linkwee.web.model.crm.MycustomersResp;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CustomerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;


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

	
	
}
