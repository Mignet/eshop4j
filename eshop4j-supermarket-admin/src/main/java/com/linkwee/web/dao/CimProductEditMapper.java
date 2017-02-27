package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProductEdit;
import com.linkwee.web.request.CimProductEditDataTableRequest;
import com.linkwee.web.response.CimProductEditResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月28日 13:47:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductEditMapper extends GenericDao<CimProductEdit,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProductEdit> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据条件查询CimProductEdit列表
	 * @param cimProductEdit
	 * @param page
	 * @return
	 */
	List<CimProductEditResponse> findProductEditList(CimProductEditDataTableRequest dt,RowBounds page);
}
