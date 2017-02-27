package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgRef;
import com.linkwee.web.service.CimOrgRefService;
 /**
 * 
 * @描述： CimOrgRefService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月17日 15:26:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRefService extends GenericService<CimOrgRef,Long>{

	/**
	 * 查询CimOrgRef列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 批量删除对象
	 * @param cimOrgRefNew
	 */
	int deleteByCondition(CimOrgRef cimOrgRefNew);
}
