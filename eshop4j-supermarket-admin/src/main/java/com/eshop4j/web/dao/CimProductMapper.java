package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.request.ProductListDataRequest;
import com.eshop4j.web.request.ProductSaleDetailRequest;
import com.eshop4j.web.request.ProductSaleListRequest;
import com.eshop4j.web.request.ProductsSalesStatisticsRequest;
import com.eshop4j.web.response.ProductDetailForManageResponse;
import com.eshop4j.web.response.ProductListForManageResponse;
import com.eshop4j.web.response.ProductSaleDetailResponse;
import com.eshop4j.web.response.ProductSaleListResponse;
import com.eshop4j.web.response.ProductsSalesStatisticsResponse;
import com.eshop4j.web.response.act.ProductPageResponse;
import com.eshop4j.web.response.orgInfo.OrgSaleProductResponse;


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
public interface CimProductMapper extends GenericDao<CimProduct,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProduct> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询机构在售产品列表
	 * @param orgNumber 机构编码
	 * @param page 分页信息
	 * @return
	 */
	List<OrgSaleProductResponse> queryOrgSaleProducts(@Param("orgNumber")String orgNumber,RowBounds page);
	
	/**
	 * 获取浮动期限产品 -
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
	List<ProductPageResponse> queryProductByProductName(@Param("orgNumber")String orgNumber,@Param("proName")String proName,RowBounds page);
	/**
	 * 根据条件查询产品
	 * @param pids
	 * @param page
	 * @return
	 */
	List<ProductPageResponse> queryProductByProductIds(@Param("pids")String[] pids);
	
	/**
	 * 管理后台查询产品列表
	 * @param productListDataRequest
	 * @param page
	 * @return
	 */
	List<ProductListForManageResponse> selectProductListForManage(@Param("query")ProductListDataRequest productListDataRequest,Page<ProductListForManageResponse> page);
	
	/**
	 * 后台管理-根据产品id查询产品详情
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
	void productAudit(@Param("auditType")String auditType,@Param("auditCode")Integer auditCode,@Param("productTableIdList")String productTableIdList);

	/**
	 * 后台管理系统-查询产品销售列表
	 * @param productSaleListRequest
	 * @param page
	 * @return
	 */
	List<ProductSaleListResponse> selectProductSaleListForManage(@Param("query")ProductSaleListRequest productSaleListRequest,Page<ProductSaleListResponse> page);

	/**
	 *  后台管理系统-查询产品销售详情
	 * @param productSaleDetailRequest
	 * @return
	 */
	List<ProductSaleDetailResponse> selectProductSaleDetail(@Param("query")ProductSaleDetailRequest productSaleDetailRequest);
	
	/**
	 * 查询产品销售统计  为data-tables封装
	 * @param productsSalesStatisticsRequest
	 * @param page
	 * @return
	 */
	List<ProductsSalesStatisticsResponse> selectSalesStatisticsByDatatables(@Param("query")ProductsSalesStatisticsRequest productsSalesStatisticsRequest,Page<ProductsSalesStatisticsResponse> page);
	/**
	 * 查询产品销售统计
	 * @param productsSalesStatisticsRequest
	 * @param page
	 * @return
	 */
	List<ProductsSalesStatisticsResponse> selectSalesStatisticsByDatatables(@Param("query")ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
	
	/**
	 * 根据机构代码更改产品的佣金
	 * @param orgNumber 机构代码
	 * @param feeRatio 佣金
	 * @return
	 */
	int updateFeeRatioByOrgNumber(@Param("orgNumber")String orgNumber, @Param("feeRatio")BigDecimal feeRatio);
	
	
	/**
	 * 查询机构所有在售产品
	 * @author yalin 
	 * @date 2017年1月16日 上午11:24:47  
	 * @param orgNumber
	 * @return
	 */
	public List<CimProduct> queryOrgAllOnSellProducts(String orgNumber);

}
