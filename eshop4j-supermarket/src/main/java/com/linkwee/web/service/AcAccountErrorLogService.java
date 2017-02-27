package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.acc.AcAccountErrorLog;
import com.linkwee.web.service.AcAccountErrorLogService;
 /**
 * 
 * @描述： AcAccountErrorLogService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月30日 18:03:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcAccountErrorLogService extends GenericService<AcAccountErrorLog,Long>{

	/**
	 * 查询AcAccountErrorLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
