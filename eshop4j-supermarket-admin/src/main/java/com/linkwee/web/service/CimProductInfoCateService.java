package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductInfoCate;
import com.linkwee.web.service.CimProductInfoCateService;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInfoCateService extends GenericService<CimProductInfoCate,Long>{

	/**
	 * 查询CimProductInfoCate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 添加产品分类
	 * @param productId  产品id
	 * @param cateId	  分类id  对应产品分类表[tcim_product_cate]分类id[cate_id]
	 * @param sort	  排序
	 */
	void addProductCate(String productId,Integer cateId,Integer sort);
	
	/**
	 * 删除产品分类
	 * @param productId	产品id
	 * @param cateId	分类id	对应产品分类表[tcim_product_cate]分类id[cate_id]  若cateId为null  则删除所有的产品分类
	 */
	void deleProductCate(String productId,Integer cateId);

	/**
	 * 查询产品关联的对应的可用分类
	 * @param productId
	 * @return
	 */
	List<CimProductInfoCate> selectCateListByCondition(CimProductInfoCate cimProductInfoCate);

	/**
	 * 批量插入产品标签
	 * @param newCimProductInfoCateList
	 */
	void insertBatch(List<CimProductInfoCate> newCimProductInfoCateList);
}
