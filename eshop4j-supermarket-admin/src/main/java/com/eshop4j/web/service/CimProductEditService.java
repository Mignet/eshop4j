package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimProductEdit;
import com.eshop4j.web.request.CimProductEditDataTableRequest;
import com.eshop4j.web.service.CimProductEditService;
 /**
 * 
 * @描述： CimProductEditService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月28日 13:47:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductEditService extends GenericService<CimProductEdit,Long>{

	/**
	 * 查询CimProductEdit列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据条件查询CimProductEdit列表,
	 * @param cimProductEdit
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findProductEditList(CimProductEditDataTableRequest cimProductEditDataTableRequest);
}
