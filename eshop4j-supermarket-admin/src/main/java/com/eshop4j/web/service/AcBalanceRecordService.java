package com.eshop4j.web.service;

import java.util.List;
import java.util.Map;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.service.AcBalanceRecordService;
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
}
