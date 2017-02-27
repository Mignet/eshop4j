package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.request.cim.HotProductRequest;
import com.eshop4j.api.request.cim.ProductCateForShowRequest;
import com.eshop4j.api.request.cim.ProductDetailRequest;
import com.eshop4j.api.request.cim.ProductPageListClassifyRequest;
import com.eshop4j.api.request.cim.ProductPageListRecommendRequest;
import com.eshop4j.api.request.cim.ProductPageListRequest;
import com.eshop4j.api.request.cim.ProductStatisticsRequest;
import com.eshop4j.api.request.cim.ScreenProductPageListRequest;
import com.eshop4j.api.response.cim.ProductDetailResponse;
import com.eshop4j.api.response.cim.ProductInvestResponse;
import com.eshop4j.api.response.cim.ProductPageListResponse;
import com.eshop4j.api.response.cim.ProductStatisticsResponse;
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
	 * 查询热门产品
	 * @param userId
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryHotProduct(HotProductRequest hotProductRequest,RowBounds page);

	/**
	 * 理财-产品列表
	 * @param userId
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryProductPageList(ProductPageListRequest productPageListRequest,Page<ProductPageListResponse> page);
	
	/**
	 * 理财-理财师推荐产品列表 带排序
	 * @param userId
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryRecdProductPageList(ProductPageListRecommendRequest productPageListRecommendRequest,Page<ProductPageListResponse> page);
	
	/**
	 * 查询机构在售产品列表
	 * @param orgNumber 机构编码
	 * @param page 分页信息
	 * @return
	 */
	List<OrgSaleProductResponse> queryOrgSaleProducts(@Param("orgNumber")String orgNumber,RowBounds page);
	
	/**
	 * 查询产品详情
	 * @param productDetailRequest
	 * @return
	 */
	ProductDetailResponse queryProductDetail(ProductDetailRequest productDetailRequest);
	
	/**
	 * 获取浮动期限产品 -
	 * @return
	 */
	List<CimProduct> getFlowProducts();

	/**
	 * 查询筛选产品
	 * @param productPageListRequest
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryProductScreenPageList(ScreenProductPageListRequest productPageListRequest, Page<ProductPageListResponse> page);
	
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
	 * 查询询产品销售详情(PC)
	 * @param productId
	 * @param page
	 * @return
	 */
	List<ProductInvestResponse> getProductInvestList(@Param("productId")String productId,RowBounds page);

	/**
	 * 查询产品分类统计
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatisticsResponse> productClassifyStatistics(ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 理财师推荐产品 统计
	 * @param productStatisticsExtendRequest
	 * @return
	 */
	ProductStatisticsResponse queryRecdProductStatistics(ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 根据产品分类查询产品分类列表  翻页
	 * @param productPageListClassifyRequest
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryProductCatePageList(ProductPageListClassifyRequest productPageListClassifyRequest,Page<ProductPageListResponse> page);
	
	/**
	 * 扩展产品分类查询产品分类列表  翻页
	 * @param productPageListClassifyRequest
	 * @param page
	 * @return
	 */
	List<ProductPageListResponse> queryProductCateExtendsPageList(ProductPageListClassifyRequest productPageListClassifyRequest,Page<ProductPageListResponse> page);

	/**
	 * 扩展产品分类 产品 统计
	 * 901-首投标   902-复投标 
	 * @param productStatisticsRequest
	 * @return
	 */
	ProductStatisticsResponse queryProductCateExtendsStatistics(ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 查询产品可展示的标签列表
	 * @param productId
	 * @return
	 */
	ArrayList<String> queryProductCateForShow(ProductCateForShowRequest productCateForShowRequest);
	
	/**
	 * 根据机构代码更改产品的佣金
	 * @param orgNumber 机构代码
	 * @param feeRatio 佣金
	 * @return
	 */
    int updateFeeRatioByOrgNumber(@Param("orgNumber")String orgNumber, @Param("feeRatio")BigDecimal feeRatio);

    /**
     * 热推产品列表分页
     * @param productPageListClassifyRequest
     * @param page
     * @return
     */
	List<ProductPageListResponse> queryHotRecommendPageList(ProductPageListClassifyRequest productPageListClassifyRequest,Page<ProductPageListResponse> page);

	/**
	 * 理财师最新推荐的产品
	 * @param productPageListRecommendRequest
	 * @return
	 */
	ProductPageListResponse queryNewestRecdProduct(ProductPageListRecommendRequest productPageListRecommendRequest);

	/**
	 * 没有投资记录的平台的新手标
	 * @param productPageListClassifyRequest
	 * @return
	 */
	ProductPageListResponse queryNotInvestPlatformNewerProduct(ProductPageListClassifyRequest productPageListClassifyRequest);

	/**
	 * 
	 * @param productPageListClassifyRequest
	 * @return
	 */
	ProductPageListResponse queryProductCateList(ProductPageListClassifyRequest productPageListClassifyRequest);

	/**
     * 热推产品列表(TOP10)
     * @param productPageListClassifyRequest
     * @param page
     * @return
     */
	List<ProductPageListResponse> queryHotRecommendPageListTop(ProductPageListClassifyRequest productPageListClassifyRequest);
	
}
