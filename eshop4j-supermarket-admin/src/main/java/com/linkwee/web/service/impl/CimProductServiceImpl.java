package com.linkwee.web.service.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimProductMapper;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductListDataRequest;
import com.linkwee.web.request.ProductSaleDetailRequest;
import com.linkwee.web.request.ProductSaleListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailForManageResponse;
import com.linkwee.web.response.ProductListForManageResponse;
import com.linkwee.web.response.ProductSaleDetailResponse;
import com.linkwee.web.response.ProductSaleListResponse;
import com.linkwee.web.response.ProductsSalesStatisticsResponse;
import com.linkwee.web.response.act.ProductPageResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInfoCateService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.helper.JsonWebTokenHepler;


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
@Service("cimProductService")
public class CimProductServiceImpl extends GenericServiceImpl<CimProduct, Long> implements CimProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductServiceImpl.class);
	
	@Resource
	private CimProductMapper cimProductMapper;
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
    private JsonWebTokenHepler jsonWebTokenHepler;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private CimProductInfoCateService cimProductInfoCateService;
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<CimProduct, Long> getDao() {
        return cimProductMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProduct -- 排序和模糊查询 ");
		Page<CimProduct> page = new Page<CimProduct>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProduct> list = cimProductMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}



	@Override
	public List<CimProduct> getFlowProducts() {
		return cimProductMapper.getFlowProducts();
	}


	@Override
	public List<ProductPageResponse> queryProductByProductName(String orgNumber,String proName, Page<ProductPageResponse> page) {
		if(StringUtils.isBlank(proName))throw new ServiceException("产品名称不能为空");
		return cimProductMapper.queryProductByProductName(orgNumber, proName, page);
	}

	@Override
	public List<ProductPageResponse> queryProductByProductIds(String[] pids) {
		return cimProductMapper.queryProductByProductIds(pids);
	}



	@Override
	public DataTableReturn selectProductListForManage(ProductListDataRequest productListDataRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(productListDataRequest.getDraw()+1);
		LOGGER.debug(" -- 后台查询产品列表 -- 排序和模糊查询 ");
		Page<ProductListForManageResponse> page = new Page<ProductListForManageResponse>(productListDataRequest.getStart()/productListDataRequest.getLength()+1,productListDataRequest.getLength());
		List<ProductListForManageResponse> list = cimProductMapper.selectProductListForManage(productListDataRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public ProductDetailForManageResponse queryProductDetailForManerge(String productId) {
		ProductDetailForManageResponse productDetailForManageResponse = new ProductDetailForManageResponse();
		//查询产品信息
		productDetailForManageResponse = cimProductMapper.queryProductDetailForManerge(productId);
		LOGGER.debug("后台管理-根据产品id查询产品详情:productDetailResponse={}",JSONObject.toJSONString(productDetailForManageResponse));
		return productDetailForManageResponse;
	}

	@Override
	public void productAudit(String auditType, Integer auditCode,String productTableIdList) {
		cimProductMapper.productAudit(auditType,auditCode,productTableIdList);
	}

	@Override
	public DataTableReturn selectProductSaleListForManage(ProductSaleListRequest productSaleListRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(productSaleListRequest.getDraw()+1);
		LOGGER.debug(" -- 后台查询产品列表 -- 排序和模糊查询 ");
		Page<ProductSaleListResponse> page = new Page<ProductSaleListResponse>(productSaleListRequest.getStart()/productSaleListRequest.getLength()+1,productSaleListRequest.getLength());
		List<ProductSaleListResponse> list = cimProductMapper.selectProductSaleListForManage(productSaleListRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<ProductSaleDetailResponse> selectProductSaleDetail(ProductSaleDetailRequest productSaleDetailRequest) {
		List<ProductSaleDetailResponse> productSaleListResponseList = cimProductMapper.selectProductSaleDetail(productSaleDetailRequest);
		return productSaleListResponseList;
	}
	
	@Override
	public DataTableReturn selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest prodSSRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(prodSSRequest.getDraw()+1);
		LOGGER.debug(" -- 查询产品销售统计 -- 排序和模糊查询 ");
		Page<ProductsSalesStatisticsResponse> page = new Page<ProductsSalesStatisticsResponse>(prodSSRequest.getStart()/prodSSRequest.getLength()+1,prodSSRequest.getLength());
		List<ProductsSalesStatisticsResponse> list = cimProductMapper.selectSalesStatisticsByDatatables(prodSSRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	
	@Override
	public List<ProductsSalesStatisticsResponse> selectSalesStatistics(ProductsSalesStatisticsRequest productsSalesStatisticsRequest) {
		List<ProductsSalesStatisticsResponse> list = cimProductMapper.selectSalesStatisticsByDatatables(productsSalesStatisticsRequest);
		return list;
	}

	@Override
	public int updateFeeRatioByOrgNumber(String orgNumber, BigDecimal feeRatio) {
		return cimProductMapper.updateFeeRatioByOrgNumber(orgNumber,feeRatio);
	}

	@Override
	public List<CimProduct> queryOrgAllOnSellProducts(String orgNumber) {
		return cimProductMapper.queryOrgAllOnSellProducts(orgNumber);
	}
	

}
