package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.core.base.BaseDao;
import com.eshop4j.web.model.news.HomepageNetNewsListResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface NetLoanNewsMapper  extends BaseDao {

	
	public List<HomepageNetNewsListResp> queryHomepageNetNewsList(@Param("type") String type);
	
	
	
	
}
