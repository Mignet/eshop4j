package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.api.response.NetloanNewsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmDynamicNews;
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

	PaginatorResponse<SmDynamicNews> queryDynamicNews(Page<SmDynamicNews> page, Map<String, Object> conditions);

	Map<String, Object> findNearNews(SmDynamicNews smDynamicNews);

	PaginatorResponse<NetloanNewsResponse> queryNetloanNews(Page<NetloanNewsResponse> page, Map<String, Object> conditions);

	NetloanNewsResponse queryNetloanNewsDetail(Map<String, Object> conditions);

}
