package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductListDataRequest;
import com.linkwee.web.request.ProductSaleDetailRequest;
import com.linkwee.web.request.ProductSaleListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailForManageResponse;
import com.linkwee.web.response.ProductSaleDetailResponse;
import com.linkwee.web.response.ProductsSalesStatisticsResponse;
import com.linkwee.web.response.act.ProductPageResponse;
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
public interface CimProductService extends GenericService<CimProduct,Long>{

	/**
	 * 查询CimProduct列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 获取浮动产品
	 * @return
	 */
	List<CimProduct> getFlowProducts();
	/**
	 * 根据条件查询产品
	 * @param orgNumber
	 * @param proName
	 * @param page
	 * @return
	 */
	
	List<ProductPageResponse> queryProductByProductName(String orgNumber,String proName, Page<ProductPageResponse> page);
	
	/**
	 * 根据条件查询产品
	 * @param pids
	 * @return
	 */
	List<ProductPageResponse> queryProductByProductIds(String[] pids);
	

	/**
	 * 后台管理系统查询产品列表
	 * @param productListDataRequest
	 * @return
	 */
	DataTableReturn selectProductListForManage(ProductListDataRequest productListDataRequest);
	
	/**
	 * 根据产品id查询产品详情  管理后台
	 * @param productId
	 * @return
	 */
	ProductDetailForManageResponse queryProductDetailForManerge(String productId);

   /**
    * 产品审核
    * @param auditType  审核类型  partAudit-部分审核  allAudit-全部审核
    * @param auditCode  审核code  0-审核通过  1-审核未通过
    * @param productTableIdList  待审核的产品表主键id列   格式 1,2,3,4
    * @return
    */
	void productAudit(String auditType, Integer auditCode,String productTableIdList);

	/**
	 * 后台管理系统-查询产品销售列表
	 * @param productSaleListRequest
	 * @return
	 */
    DataTableReturn selectProductSaleListForManage(ProductSaleListRequest productSaleListRequest);

    /**
     * 后台管理系统-查询产品销售详情
     * @param productSaleDetailRequest
     * @return
     */
	List<ProductSaleDetailResponse> selectProductSaleDetail(ProductSaleDetailRequest productSaleDetailRequest);
	/**
	 * 后台管理系统-查询产品销售统计  为data-tables封装
	 * @param productsSalesStatisticsRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
	
	/**
	 * 查询产品销售统计
	 * @param productsSalesStatisticsRequest
	 * @param page
	 * @return
	 */
	List<ProductsSalesStatisticsResponse> selectSalesStatistics(ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
	
	/**
	 * 根据机构代码更改产品的佣金
	 * @param orgNumber 机构代码
	 * @param feeRatio 佣金
	 * @return
	 */
    int updateFeeRatioByOrgNumber(String orgNumber,BigDecimal feeRatio);
    
    /**
	 * 查询机构所有在售产品
	 * @author yalin 
	 * @date 2017年1月16日 上午11:24:47  
	 * @param orgNumber
	 * @return
	 */
	public List<CimProduct> queryOrgAllOnSellProducts(String orgNumber);
	
}
