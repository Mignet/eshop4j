package com.eshop4j.web.service;

import com.eshop4j.api.request.tc.UnRecordInvestRequest;
import com.eshop4j.api.response.tc.CfplannerUnrecordInvestResponse;
import com.eshop4j.api.response.tc.CustomerUnrecordInvestResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.cim.CimProductUnrecordInvest;
import com.eshop4j.web.request.tc.UnrecordInvestRequest;
 /**
 * 
 * @描述： CimProductUnrecordInvestService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestService extends GenericService<CimProductUnrecordInvest,Long>{
	
	/**
	 * 报单数量
	 * @param userId
	 * @return
	 */
	int getCfplannerUnrecordInvestCount(String userId);


	/**
	 * 获客户报单
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<CustomerUnrecordInvestResponse> getCustomerUnrecordInvest(String userId,Page<CustomerUnrecordInvestResponse> page);
	
	/**
	 * 获取理财师报单
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<CfplannerUnrecordInvestResponse> getCfplannerUnrecordInvest(String userId,Page<CfplannerUnrecordInvestResponse> page);
	
	
	BaseResponse inserUnrecordInvest(UnRecordInvestRequest req,String cfpId);
	
	DataTableReturn getUnrecordInvestList(UnrecordInvestRequest req); 
}
