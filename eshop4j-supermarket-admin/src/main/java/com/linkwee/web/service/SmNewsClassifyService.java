package com.linkwee.web.service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmNewsClassify;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.SmNewsClassifyService;
 /**
 * 
 * @描述： SmNewsClassifyService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 13:45:44
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmNewsClassifyService extends GenericService<SmNewsClassify,Long>{

	/**
	 * 查询SmNewsClassify列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 资讯分类列表
	 * @param newsRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn findNewsClassifyList(NewsRequest newsRequest,DataTable dataTable);

	/**
	 * 更新资讯分类
	 * @param classify
	 * @return
	 */
	ReturnCode updateNewsClassify(SmNewsClassify classify);

	/**
	 * 添加资讯分类
	 * @param classify
	 * @return
	 */
	ReturnCode saveNewsClassify(SmNewsClassify classify);

	/**
	 * 删除资讯分类
	 * @param parseInt
	 * @return
	 */
	ReturnCode DeleteNewsClassify(int parseInt);
}
