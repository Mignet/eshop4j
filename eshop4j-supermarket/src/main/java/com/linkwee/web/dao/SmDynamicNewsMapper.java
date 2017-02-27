package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.NetloanNewsResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.JpOption;
import com.linkwee.web.model.JpTaxonomy;
import com.linkwee.web.model.SmDynamicNews;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月18日 19:01:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmDynamicNewsMapper extends GenericDao<SmDynamicNews,Long>{

	List<SmDynamicNews> queryDynamicNews(Page<SmDynamicNews> page, Map<String, Object> conditions);

	SmDynamicNews beforeOneNews(SmDynamicNews smDynamicNews);

	SmDynamicNews nextOneNews(SmDynamicNews smDynamicNews);

	List<NetloanNewsResponse> queryNetloanNewsPageList(Page<NetloanNewsResponse> page, Map<String, Object> conditions);

	List<JpOption> queryAllJpOption();
	
	JpTaxonomy queryTaxonomy(String title);

	NetloanNewsResponse queryNetloanNewsDetail(Map<String, Object> conditions);
	
}
