package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.SmNewsClassify;
import com.eshop4j.web.request.NewsRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 13:45:44
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmNewsClassifyMapper extends GenericDao<SmNewsClassify,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmNewsClassify> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	List<SmNewsClassify> findNewsClassifyList(NewsRequest newsRequest,Page<NewsRequest> page);
}
