package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimProductRef;
import com.eshop4j.web.service.CimProductRefService;
 /**
 * 
 * @描述： CimProductRefService服务接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 18:21:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRefService extends GenericService<CimProductRef,Long>{

	/**
	 * 查询CimProductRef列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
