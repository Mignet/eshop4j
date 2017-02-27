package com.eshop4j.web.service;

import com.eshop4j.core.base.ReturnCode;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.SmDynamicNews;
import com.eshop4j.web.request.DynamicNewsRequest;
import com.eshop4j.web.service.SmDynamicNewsService;
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
