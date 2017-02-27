package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.api.request.acc.MonthProfixDetailRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.MonthProfixDetailListResp;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;
 /**
 * 
 * @描述： AcBalanceRecordService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcBalanceRecordService extends GenericService<AcBalanceRecord,Long>{

	/**
	 * 查询AcBalanceRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 我的账户明细
	 * @param page
	 * @return
	 */
	PaginatorResponse<AcBalanceRecord> queryMyAccountDetails(Page<AcBalanceRecord> page,
			Map<String, Object> conditions);
	
	
	/**
	 * 我的账户明细
	 * @param page
	 * @return
	 */
	PaginatorResponse<AcBalanceRecord> queryMyAccountDetails2(Page<AcBalanceRecord> page,
			Map<String, Object> conditions);

	/**
	 * 发放奖励
	 * @param balanList
	 * @return
	 */
	void grantProfit(List<AcBalanceRecord> balanList);

	/**
	 * 查询发放奖励
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectGrantByDatatables(DataTable dataTable);
	
	/**
	 * 检查当天是否有重复发放的数据
	 * @param serialNumber
	 * @return
	 */
	List<AcBalanceRecord> checkSameSerialNumber(String serialNumber);

	/**
	 * 月度收益明细列表
	 * @param req
	 * @param page
	 * @return
	 */
	PaginatorResponse<MonthProfixDetailListResp> queryMonthProfixDetailList(MonthProfixDetailRequest req,
			Page<MonthProfixDetailListResp> page);

	/**
	 * 月份收益总计列表
	 * @param req
	 * @param page
	 * @return
	 */
	PaginatorResponse<MonthProfixTotalListResp> queryProfixTotalList(MonthProfixDetailRequest req,
			Page<MonthProfixTotalListResp> page);

	/**
     * 累计收益
     */
	Double queryTotalIncome(String userId);
	
	
}
