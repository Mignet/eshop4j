package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductRef;
import com.linkwee.web.service.CimProductRefService;
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
    /**
     * 批量删除对象
     *
     * @param id 主键
     */
    int deleteByCondition(CimProductRef cimProductRef);
}
