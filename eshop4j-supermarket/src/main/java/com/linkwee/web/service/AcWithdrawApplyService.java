package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.acc.AcWithdrawApply;
 /**
 * 
 * @描述： AcWithdrawApplyService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWithdrawApplyService extends GenericService<AcWithdrawApply,Long>{

	/**
	 * 查询AcWithdrawApply列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询AcWithdrawApply列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectDetailByDatatables(DataTable dataTable);


	/**
	 * 提现记录明细
	 * @param page
	 * @return
	 */
	PaginatorResponse<AcWithdrawApply> queryWithdrawLog(Page<AcWithdrawApply> page, Map<String, Object> conditions);
	
	/**
	 * 提现批量审批
	 * @param 
	 * @return
	 */
	Map<String, String> approveWithdraw(List<String> listStr);

	/**
	 * 定时任务同步提现数据状态
	 * @param 
	 * @return
	 */
	List<AcWithdrawApply> queryWithdrawforJob();
}
