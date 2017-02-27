package com.eshop4j.web.service;

import com.eshop4j.api.request.NewsPageListRequest;
import com.eshop4j.core.base.PaginatorSevReq;
import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.SmNews;
 /**
 * 
 * @描述： SmNewsService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmNewsService extends GenericService<SmNews,Long>{

	/**
	 * 查询SmNews列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据id查询资讯记录
	 * @param fid
	 * @return
	 */
	public SmNews findNewsDtl(String fid);

	/**
	 * 查询资讯翻页
	 * @param newsPageListRequest
	 * @param page
	 * @return
	 */
	PaginatorResponse<SmNews> queryNewsPageList(NewsPageListRequest newsPageListRequest, Page<SmNews> page);

	/**
	 * 查询最新的资讯
	 * @param appType
	 * @return
	 */
	SmNews queryNewest(Integer appType);
}
