package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimProductInfoCate;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInfoCateMapper extends GenericDao<CimProductInfoCate,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProductInfoCate> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据产品id删除该产品所有分类
	 * @param productId
	 */
	void deleteAllByProductId(String productId);

	/**
	 * 查询产品关联的对应的可用分类
	 * @param cimProductInfoCate
	 * @return
	 */
	List<CimProductInfoCate> selectCateListByCondition(CimProductInfoCate cimProductInfoCate);

	/**
	 * 批量插入产品标签
	 * @param newCimProductInfoCateList
	 */
	void insertBatch(List<CimProductInfoCate> newCimProductInfoCateList);
}
