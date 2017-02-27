package com.linkwee.web.service;

import com.linkwee.api.request.tc.UnRecordInvestRequest;
import com.linkwee.api.response.tc.CfplannerUnrecordInvestResponse;
import com.linkwee.api.response.tc.CustomerUnrecordInvestResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.cim.CimProductUnrecordInvest;
import com.linkwee.web.request.tc.UnrecordInvestRequest;
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
