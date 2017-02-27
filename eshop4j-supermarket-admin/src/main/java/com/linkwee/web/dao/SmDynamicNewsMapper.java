package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmDynamicNews;
import com.linkwee.web.request.DynamicNewsRequest;
import com.linkwee.web.response.DynamicNewsResponse;

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
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmDynamicNews> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	List<DynamicNewsResponse> findDynamicNewsList(DynamicNewsRequest dynamicNewsRequest, Page<DynamicNewsRequest> page);
}
