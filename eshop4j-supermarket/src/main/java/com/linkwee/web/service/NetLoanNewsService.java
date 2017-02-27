package com.linkwee.web.service;

import java.util.List;

import com.linkwee.web.model.news.HomepageNetNewsListResp;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface NetLoanNewsService {


	List<HomepageNetNewsListResp> queryHomepageNetNewsList(String type);

	
	
}
