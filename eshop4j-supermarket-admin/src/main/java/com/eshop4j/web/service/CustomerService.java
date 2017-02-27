package com.eshop4j.web.service;

import java.util.Map;

import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.crm.CfpCustomerCountResp;
import com.eshop4j.web.model.crm.MycustomersResp;
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
public interface CustomerService {

	
	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	PaginatorSevResp<MycustomersResp> queryMycustomerList(Map<String, Object> query, Page<MycustomersResp> page);

	/**
	 * 客户累计投资统计
	 * @param userId
	 * @return
	 */
	Double queryTotalInvestAmt(String userId);
	
	/**
	 * 客户本月投资统计
	 * @param userId
	 * @return
	 */
	Double queryMonthInvestAmt(String userId);
	
	/**
	 * 客户今日投资统计
	 * @param userId
	 * @return
	 */
	Double queryDayInvestAmt(String userId);

	/**
	 * 查理财师某客户提供的佣金收益
	 * @param userId
	 * @param userId2
	 * @return
	 */
	Double queryFeeAmtByCfpAndInvestor(String cfplanner, String investor);

	
	
}
