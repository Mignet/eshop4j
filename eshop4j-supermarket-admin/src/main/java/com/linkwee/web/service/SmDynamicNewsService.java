package com.linkwee.web.service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmDynamicNews;
import com.linkwee.web.request.DynamicNewsRequest;
import com.linkwee.web.service.SmDynamicNewsService;
 /**
 * 
 * @描述： SmDynamicNewsService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月18日 19:01:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmDynamicNewsService extends GenericService<SmDynamicNews,Long>{

	/**
	 * 查询SmDynamicNews列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	DataTableReturn findNewsList(DynamicNewsRequest dynamicNewsRequest, DataTable dataTable);

	ReturnCode updateDynamicNews(SmDynamicNews convertToDynamicNews);

	ReturnCode SaveDynamicNews(SmDynamicNews convertToDynamicNews);

}
