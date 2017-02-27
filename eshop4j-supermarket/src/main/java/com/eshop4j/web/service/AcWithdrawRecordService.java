package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.acc.AcWithdrawRecord;
import com.eshop4j.web.service.AcWithdrawRecordService;
 /**
 * 
 * @描述： AcWithdrawRecordService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWithdrawRecordService extends GenericService<AcWithdrawRecord,Long>{

	/**
	 * 查询AcWithdrawRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
