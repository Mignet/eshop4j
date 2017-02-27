package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CrmCfpLoginLog;
import com.eshop4j.web.service.CrmCfpLoginLogService;
 /**
 * 
 * @描述： CrmCfpLoginLogService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 18:57:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLoginLogService extends GenericService<CrmCfpLoginLog,Long>{

	/**
	 * 查询CrmCfpLoginLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
