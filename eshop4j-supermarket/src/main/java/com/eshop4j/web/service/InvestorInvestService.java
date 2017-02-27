package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.request.ListDetailRequest;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface InvestorInvestService {


	/**
	 * 用户投资与收益
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public DataTableReturn selectInvestorInvest(DataTable dataTable, ListDetailRequest req);
	
	/**
	 * 用户投资记录
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public DataTableReturn selectInvestRecord(DataTable dataTable, ListDetailRequest req);
	
	
}
