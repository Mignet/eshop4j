package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.ListDetailRequest;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface CfplannerSaleService {


	/**
	 * 销售收益列表数据
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn selectCfplannerSaleList(DataTable dataTable, ListDetailRequest req);
	
	/**
	 * 佣金明细列表数据
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn selectFeeDetailList(DataTable dataTable, ListDetailRequest req);

	/**
	 * 推荐收益明细
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn selectAllowanceDetailList(DataTable dataTable, ListDetailRequest req);

	/**
	 * 活动奖励明细
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn selectActivityRewardList(DataTable dataTable, ListDetailRequest req);

	/**
	 * 客户投资明细
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn selectCustomerInvestList(DataTable dataTable, ListDetailRequest req);
	
	
}
