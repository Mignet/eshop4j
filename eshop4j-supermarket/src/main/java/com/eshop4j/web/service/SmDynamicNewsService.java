package com.eshop4j.web.service;

import java.util.Map;

import com.eshop4j.api.response.NetloanNewsResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.SmDynamicNews;
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

	PaginatorResponse<SmDynamicNews> queryDynamicNews(Page<SmDynamicNews> page, Map<String, Object> conditions);

	Map<String, Object> findNearNews(SmDynamicNews smDynamicNews);

	PaginatorResponse<NetloanNewsResponse> queryNetloanNews(Page<NetloanNewsResponse> page, Map<String, Object> conditions);

	NetloanNewsResponse queryNetloanNewsDetail(Map<String, Object> conditions);

}
