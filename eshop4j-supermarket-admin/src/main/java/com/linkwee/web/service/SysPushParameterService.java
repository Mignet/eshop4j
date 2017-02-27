package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.mc.SysPushParameter;
import com.linkwee.web.service.SysPushParameterService;
 /**
 * 
 * @描述： SysPushParameterService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 10:43:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysPushParameterService extends GenericService<SysPushParameter,Long>{

	/**
	 * 查询SysPushParameter列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
