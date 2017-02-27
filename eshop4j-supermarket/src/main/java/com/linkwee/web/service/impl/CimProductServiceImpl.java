package com.linkwee.web.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.cim.HotProductRequest;
import com.linkwee.api.request.cim.ProductCateForShowRequest;
import com.linkwee.api.request.cim.ProductClassifyPageListRequest;
import com.linkwee.api.request.cim.ProductDetailRequest;
import com.linkwee.api.request.cim.ProductInvestRequest;
import com.linkwee.api.request.cim.ProductPageListClassifyRequest;
import com.linkwee.api.request.cim.ProductPageListRecommendRequest;
import com.linkwee.api.request.cim.ProductPageListRequest;
import com.linkwee.api.request.cim.ProductRecommendByChooseRequest;
import com.linkwee.api.request.cim.ProductRecommendChooseRequest;
import com.linkwee.api.request.cim.ProductStatisticsRequest;
import com.linkwee.api.request.cim.ScreenProductPageListRequest;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductInvestResponse;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.api.response.cim.ProductStatisticsPreferenceResponse;
import com.linkwee.api.response.cim.ProductStatisticsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.openapi.request.ProductPushRequest;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.web.dao.CimProductMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CimProductCate;
import com.linkwee.web.model.CimProductEdit;
import com.linkwee.web.model.CimProductInfoCate;
import com.linkwee.web.model.CimProductRef;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorRecommend;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.mc.SysMsg;
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
import com.linkwee.web.response.orgInfo.OrgSaleProductResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductCateService;
import com.linkwee.web.service.CimProductEditService;
import com.linkwee.web.service.CimProductInfoCateService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductRefService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.AppUtils;


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
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;
	@Resource
	private CimProductRefService cimProductRefService;
	@Resource
	private SysGrayReleaseService sysGrayReleaseService;
	@Resource
	private PushMessageHelper pushMessageHelper;
    @Resource
    private CrmCfplannerService crmCfplannerService;
	@Resource
	private CimProductCateService cimProductCateService;
	@Resource
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private CimProductEditService cimProductEditService;
	@Resource
	private TCFeeService cimFeeService;
	@Resource
	private RedPacketService redPacketService;
	@Resource
	private SysMsgService sysMsgService;
	@Resource
	private ConfigHelper configHelper;

		
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
	public PaginatorResponse<ProductPageListResponse> queryHotProduct(HotProductRequest hotProductRequest,Page<ProductPageListResponse> page) {
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		List<ProductPageListResponse> productPageListResponseList = cimProductMapper.queryHotProduct(hotProductRequest,page);
		
		//添加产品分类标签
		addProductTags(productPageListResponseList,hotProductRequest.getAppKind(),hotProductRequest.getUserId());
		paginatorResponse.setDatas(productPageListResponseList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<ProductPageListResponse> queryProductPageList(ProductPageListRequest productPageListRequest,Page<ProductPageListResponse> page) {
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		List<ProductPageListResponse> productPageListResponses = cimProductMapper.queryProductPageList(productPageListRequest,page);
		
		//添加产品分类标签
		addProductTags(productPageListResponses,productPageListRequest.getAppKind(),productPageListRequest.getUserId());
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	@Override
	public PaginatorResponse<OrgSaleProductResponse> queryOrgSaleProducts(String orgNumber, Page<OrgSaleProductResponse> page) {
		PaginatorResponse<OrgSaleProductResponse> paginatorResponse = new PaginatorResponse<OrgSaleProductResponse>();
		paginatorResponse.setDatas(cimProductMapper.queryOrgSaleProducts(orgNumber, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public ProductDetailResponse queryProductDetail(ProductDetailRequest productDetailRequest) {
		return queryProductDetail(productDetailRequest.getProductId(),productDetailRequest.getAppKind());
	}

	@Override
	public ProductDetailResponse queryProductDetail(String productId) {
		return queryProductDetail(productId,null);
	}
	
	@Override
	public ProductDetailResponse queryProductDetail(String productId,String appKind) {
		
		ProductDetailResponse productDetailResponse = new ProductDetailResponse();
		if(StringUtils.isBlank(productId)){
			LOGGER.debug("查询产品详情时产品id不能为空");
		} else {
			//查询产品信息
			ProductDetailRequest productDetailRequest = new ProductDetailRequest();
			productDetailRequest.setProductId(productId);
			productDetailResponse = cimProductMapper.queryProductDetail(productDetailRequest);
			
			if(productDetailResponse != null){
				//添加产品分类标签
				ProductCateForShowRequest productCateForShowRequest = new ProductCateForShowRequest();
				BeanUtils.copyProperties(productDetailResponse, productCateForShowRequest);
				productCateForShowRequest.setAppKind(appKind);
				ArrayList<String> productCateShowList  = getProductCateShowList(productCateForShowRequest);
				productDetailResponse.setTagList(productCateShowList);
				ArrayList<String> productCateShowListRightNewer = addProductRightTagsNewer(productCateForShowRequest.getProductId());
				productDetailResponse.setTagListRightNewer(productCateShowListRightNewer);
				
				//查询产品对应的平台信息
				if(StringUtils.isNotEmpty(productDetailResponse.getOrgNumber())){	
					OrgInfo orgInfoResponse = cimOrginfoService.findOrgInfo(productDetailResponse.getOrgNumber());
					productDetailResponse.setOrgInfoResponse(orgInfoResponse);
					
					//右上角添加标签
					ArrayList<String> tagListRight = new ArrayList<String>();
					if(orgInfoResponse.getOrgFeeType() !=null && orgInfoResponse.getOrgFeeType() == 1){
						tagListRight.add("首投");
					} else if(orgInfoResponse.getOrgFeeType() !=null && orgInfoResponse.getOrgFeeType() == 2){
						tagListRight.add("复投");
					}
					productDetailResponse.setTagListRight(tagListRight);
				}
				try {
					String productProfitDesc = cimFeeService.productProfitCalculate(productDetailResponse);
					productDetailResponse.setProductProfitDesc(productProfitDesc);
				} catch (Exception e) {
					LOGGER.warn("获取产品佣金计算描述失败",e);
				}
			}
			LOGGER.debug("根据产品id查询产品详情:productDetailResponse={}",JSONObject.toJSONString(productDetailResponse));
		}
		
		return productDetailResponse;
	}

	@Override
	public PaginatorResponse<ProductPageListResponse> queryRecdProductPageList(ProductPageListRecommendRequest productPageListRecommendRequest,Page<ProductPageListResponse> page) {
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		paginatorResponse.setDatas(cimProductMapper.queryRecdProductPageList(productPageListRecommendRequest,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<CimProduct> getFlowProducts() {
		return cimProductMapper.getFlowProducts();
	}

	@Override
	public PaginatorResponse<ProductPageListResponse> queryProductScreenPageList(ScreenProductPageListRequest productPageListRequest, Page<ProductPageListResponse> page) {
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		List<ProductPageListResponse> productPageListResponses = cimProductMapper.queryProductScreenPageList(productPageListRequest,page);
		//添加产品分类标签
		addProductTags(productPageListResponses,productPageListRequest.getAppKind(),productPageListRequest.getUserId());
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
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
	public PaginatorResponse<ProductInvestResponse> getProductInvestList(ProductInvestRequest productInvestRequest,Page<ProductInvestResponse> page) {
		PaginatorResponse<ProductInvestResponse> paginatorResponse = new PaginatorResponse<ProductInvestResponse>();
		paginatorResponse.setDatas(cimProductMapper.getProductInvestList(productInvestRequest.getProductId(), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	/**
	 * 查询产品分类统计信息	
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品 (中长期)801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * PC端:  		产品类型有 1-热门产品 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期) 801-理财师推荐产品 
	 * 其中  801-理财师推荐产品   901-首投标  902-复投标 为特殊产品分类 在分类表（tcim_product_cate）不存在
	 * 默认查询所有  可单独查询
	 */
	@Override
	public List<ProductStatisticsResponse> productClassifyStatistics(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest) {
		
		LOGGER.info("查询产品分类统计,AppRequestHead={},ProductStatisticsRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productStatisticsRequest));
		
		List<ProductStatisticsResponse> productStatisticsResponselist =  new ArrayList<ProductStatisticsResponse>();
		ProductStatisticsResponse productStatisticsResponse = new ProductStatisticsResponse();
		String cateIdList = productStatisticsRequest.getCateIdList();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		String  platform = AppUtils.getPlatform(appRequestHead.getOrgNumber()).getValue();//获取平台的类型   android,ios,wechat,web,wap
		productStatisticsRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		/**
		 * 如果所传的cateIdList为空，则根据不同的请求appType查询所有的产品分类  默认查询所有  可单独查询
		 */
		if("channel".equalsIgnoreCase(appType)){//猎才大师
			if(StringUtils.isBlank(cateIdList)){
				cateIdList = "2,901,902";
				productStatisticsRequest.setCateIdList(cateIdList);
			}
			productStatisticsResponselist = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
			String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
			//猎财大师先显示首投标  再显示复投标
			if(ArrayUtils.contains(cateIdArray, "901")){//901-首投标 
				productStatisticsRequest.setCateIdList("901");
				productStatisticsResponse = cimProductMapper.queryProductCateExtendsStatistics(productStatisticsRequest);
				productStatisticsResponselist.add(productStatisticsResponse);
			}
			if(ArrayUtils.contains(cateIdArray, "902")){//902-复投标 
				productStatisticsRequest.setCateIdList("902");
				productStatisticsResponse = cimProductMapper.queryProductCateExtendsStatistics(productStatisticsRequest);
				productStatisticsResponselist.add(productStatisticsResponse);
			}
		} else if("investor".equalsIgnoreCase(appType)){//投呗
			/**
			 * PC端	单独处理
			 * 产品类型有    801-理财师推荐产品  1-热门产品  2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期)
			 * 默认查询所有  可单独查询
			 */
			if(platform.equals("web")){
				if(StringUtils.isBlank(cateIdList)){
					cateIdList = "2,3,4,5,801";
					productStatisticsRequest.setCateIdList(cateIdList);
				}
				String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
				//PC端先展示理财师推荐
				if(ArrayUtils.contains(cateIdArray, "801")){
					/**
					 * 查询理财师userId
					 * token失效的时候  理财师推荐不显示
					 */
					if("token_valid_success".equals(jsonWebTokenHepler.checkToken(appRequestHead.getToken()).getCode())){
						CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
						productStatisticsRequest.setCfplannerUserId(crmInvestor.getCfplanner());
						productStatisticsResponse = cimProductMapper.queryRecdProductStatistics(productStatisticsRequest);
						productStatisticsResponselist.add(productStatisticsResponse);
					}
				}
				List<ProductStatisticsResponse>  productStatisticsResponses = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
				productStatisticsResponselist.addAll(productStatisticsResponses);
				
				//对于每一个分类，查询出一个在售的产品
				
				for(ProductStatisticsResponse productStatcsResponse : productStatisticsResponselist){
					
					List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
					
					/**
					 * 封装page和productPageListClassifyRequest请求对象
					 */
					ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
					productPageListClassifyRequest.setAppKind(appType);
					productPageListClassifyRequest.setOrder(1);
					productPageListClassifyRequest.setSort(1);
					productPageListClassifyRequest.setCateId(productStatcsResponse.getCateId());
					productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
					Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(1,10);
					
					Integer cateId = productStatcsResponse.getCateId();
					if(cateId == 801){//理财师推荐产品
						ProductPageListRecommendRequest productPageListRecommendRequest =  new ProductPageListRecommendRequest();
						BeanUtils.copyProperties(productPageListClassifyRequest, productPageListRecommendRequest);
						productPageListRecommendRequest.setUserId(userId);
						//查询投资用户对应的理财师信息
						CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
						productPageListRecommendRequest.setCfplannerUserId(crmInvestor.getCfplanner());
						LOGGER.info("查询理财师推荐产品列表, userId={},CrmInvestor={}",userId,JSONObject.toJSONString(crmInvestor));
						productPageListResponses = cimProductMapper.queryRecdProductPageList(productPageListRecommendRequest,page);
					}else {
						productPageListResponses = cimProductMapper.queryProductCatePageList(productPageListClassifyRequest,page);
					}
					
					if(productPageListResponses != null && productPageListResponses.size() > 0){
						productStatcsResponse.setProductPageListResponse(productPageListResponses.get(0));
					}
				}
			} else {
				/**
				 *  投呗:
				 *  产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品 (中长期)801-理财师推荐产品 
				 *  默认查询所有  可单独查询
				 */
				if(StringUtils.isBlank(cateIdList)){
					cateIdList = "2,3,4,5,801";
					productStatisticsRequest.setCateIdList(cateIdList);
				}
				productStatisticsResponselist = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
				String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
				if(ArrayUtils.contains(cateIdArray, "801")){
					/**
					 * 查询理财师userId
					 */
					if("token_valid_success".equals(jsonWebTokenHepler.checkToken(appRequestHead.getToken()).getCode())){
						CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()));
						productStatisticsRequest.setCfplannerUserId(crmInvestor.getCfplanner());
						productStatisticsResponse = cimProductMapper.queryRecdProductStatistics(productStatisticsRequest);
						productStatisticsResponselist.add(productStatisticsResponse);
					}
				}
			}
		}
		
		return productStatisticsResponselist;
	}

	/**
	 * 理财-产品分类列表 带排序
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * PC端(T呗):  	产品类型有 1-热门产品 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期) 801-理财师推荐产品 
	 */
	@Override
	public PaginatorResponse<ProductPageListResponse> queryProductClassifyPageList(AppRequestHead appRequestHead,ProductClassifyPageListRequest productCfyPgListRequest) {
		
		/**
		 * 封装page和productPageListClassifyRequest请求对象
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		if("channel".equalsIgnoreCase(appType)){
			productPageListClassifyRequest.setUserId(userId);
		}
		productPageListClassifyRequest.setAppKind(appType);
		productPageListClassifyRequest.setOrgNumber(productCfyPgListRequest.getOrgCode());
		productPageListClassifyRequest.setOrder(productCfyPgListRequest.getOrder());
		productPageListClassifyRequest.setSort(productCfyPgListRequest.getSort());
		productPageListClassifyRequest.setCateId(productCfyPgListRequest.getCateId());
		productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(productCfyPgListRequest.getPageIndex(), productCfyPgListRequest.getPageSize());
		
		LOGGER.info("查询产品分类列表, AppRequestHead={},productPageListClassifyRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productPageListClassifyRequest));
		
		/**
		 * 获取返回结果List<ProductPageListResponse>返回对象
		 */
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		
		List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
		Integer cateId = productPageListClassifyRequest.getCateId();
		/**
		 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
		 * 	       投呗:  	产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
		 */
		if("channel".equalsIgnoreCase(appType)){//猎才大师
			if(cateId == 901 || cateId == 902) {// 901-首投标  902-复投标     
				productPageListResponses = cimProductMapper.queryProductCateExtendsPageList(productPageListClassifyRequest,page);
			} else {
				productPageListResponses = cimProductMapper.queryProductCatePageList(productPageListClassifyRequest,page);
			}
		} else if("investor".equalsIgnoreCase(appType)){//投呗和PC端
			if(cateId == 801){//理财师推荐产品
				ProductPageListRecommendRequest productPageListRecommendRequest =  new ProductPageListRecommendRequest();
				BeanUtils.copyProperties(productPageListClassifyRequest, productPageListRecommendRequest);
				productPageListRecommendRequest.setUserId(userId);
				//查询投资用户对应的理财师信息
				CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
				if(crmInvestor != null){				
					productPageListRecommendRequest.setCfplannerUserId(crmInvestor.getCfplanner());
					LOGGER.info("查询理财师推荐产品列表, userId={},CrmInvestor={}",userId,JSONObject.toJSONString(crmInvestor));
					productPageListResponses = cimProductMapper.queryRecdProductPageList(productPageListRecommendRequest,page);
				}
			}else {
				productPageListResponses = cimProductMapper.queryProductCatePageList(productPageListClassifyRequest,page);
			}
		}
		
		//添加产品标签
		addProductTags(productPageListResponses,appType,userId);
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	/**
	 * 根据推送的产品信息对该产品自动进行产品分类
	 * 1: 新手标
	 * 2：短期产品   [90天以内]  期限按照最小的判断
	 * 3: 高收益产品 	   [年化收益10%以上（含）]  收益率按照最大的判断
	 * 4：中长期产品    [90天以上,含]
	 * 备注：新手标需从其他产品类别剔除
	 */
	@Override
	public void autoHandleProductCateForPush(String productId,ProductPushRequest productPushRequest) {
		try {
			/**
			 * 1: 新手标
			 * 备注：新手标需从其他产品类别剔除
			 */
			if(productPushRequest.getIfRookie() != null && productPushRequest.getIfRookie() == 1){
				cimProductInfoCateService.addProductCate(productId, 2, null);
			} else {
				/**
				 * 短期产品   [90天以内]
				 */
				if(productPushRequest.getDeadLineMinValue() < 90){
					cimProductInfoCateService.addProductCate(productId, 3, null);
				}
				/**
				 * 高收益产品   [年化收益10%以上（含）]
				 */
				if(productPushRequest.getFlowMaxRate().compareTo(new BigDecimal(10)) == 1){
					cimProductInfoCateService.addProductCate(productId, 4, null);
				}
				/**
				 * 中长期产品    [90天以上,含]
				 */
				if(productPushRequest.getDeadLineMinValue() >= 90){
					cimProductInfoCateService.addProductCate(productId, 5, null);
				}
			}

		} catch (Exception e) {
			LOGGER.error("根据推送的产品信息对该产品自动进行产品分类异常:productId={}", productId);
			e.printStackTrace();
		}
	}

	@Override
	public int updateFeeRatioByOrgNumber(String orgNumber, BigDecimal feeRatio) {
		return cimProductMapper.updateFeeRatioByOrgNumber(orgNumber,feeRatio);
	}

	@Override
	public ProductRecommendChooseResponse recommendChooseList(AppRequestHead head,ProductRecommendChooseRequest productRecommendChooseRequest) {
		ProductRecommendChooseResponse productRecommendChooseResponse = new ProductRecommendChooseResponse();
		List<CrmInvestorRecommend> allFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> haveFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> notHaveFeeList = new ArrayList<CrmInvestorRecommend>();
		
		/**
		 * 获取理财师id
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		productRecommendChooseRequest.setUserId(userId);
		/**
		 * 根据条件筛选理财师所有客户 含是否推荐该产品信息
		 */
		allFeeList = crmInvestorService.selectCrmInvestorRecommend(productRecommendChooseRequest);
		/**
		 * 查询产品详情 
		 */
		ProductDetailRequest productDetailRequest = new ProductDetailRequest();
		productDetailRequest.setProductId(productRecommendChooseRequest.getProductId());
		ProductDetailResponse productDetailResponse = queryProductDetail(productDetailRequest);
		
		/**
		 * 推荐
		 * 1：未进行对接    直接列出所有用户
		 * 2：已技术对接
		 * 2.1 有佣金的用户   2.11 CPS  我们带过去的新用户            2.12  CPA 我们带过去的新用户未投资
		 * 2.2 无佣金的用户   2.21 CPS  不是我们带过去的用户       2.22  CPA 不是我们带过去用户 或者是我们带过去的用户（已投资）
		 */
		if(productDetailResponse.getOrgInfoResponse().getOrgIsstaticproduct() == 1){ //未进行对接
			productRecommendChooseResponse.setOrgIsstaticproduct(1);
		} else {//已技术对接
			productRecommendChooseResponse.setOrgIsstaticproduct(0);
			
			/** 收费类型   1:cpa-按投资人数量进行收费	2:cps-按投资金额进行收费 */
			Integer orgFeeType = productDetailResponse.getOrgInfoResponse().getOrgFeeType();
			
			/**
			 * 遍历所有客户进行是否拥有佣金分类
			 */
			for(CrmInvestorRecommend crmInvestorRecommend:allFeeList){
				CrmOrgAcctRel crmOrgAcctRel =  new CrmOrgAcctRel();
				crmOrgAcctRel.setOrgNumber(productDetailResponse.getOrgNumber());
				crmOrgAcctRel.setUserId(crmInvestorRecommend.getUserId());
				crmOrgAcctRel = crmOrgAcctRelService.selectOne(crmOrgAcctRel);
				if(crmOrgAcctRel == null){//未绑定平台用户
					haveFeeList.add(crmInvestorRecommend);
				} else if(crmOrgAcctRel.getIsNewUser() == 1){//平台新用户
					if(orgFeeType == 1){//CPA
						/**
						 * 判断该用户是否投资过该机构,若投资过,则没有佣金，否则有佣金
						 */
						CimProductInvestRecord cimProductInvestRecord = new CimProductInvestRecord();
						cimProductInvestRecord.setUserId(crmInvestorRecommend.getUserId());
						cimProductInvestRecord.setPlatfrom(productDetailResponse.getOrgNumber());
						List<CimProductInvestRecord>  cimProductInvestRecordList= cimProductInvestRecordService.selectListByCondition(cimProductInvestRecord);
						if(cimProductInvestRecordList != null && cimProductInvestRecordList.size() > 0){
							notHaveFeeList.add(crmInvestorRecommend);
						} else {
							haveFeeList.add(crmInvestorRecommend);
						}
					} else if(orgFeeType == 2){//CPS
						haveFeeList.add(crmInvestorRecommend);
					}
				} else {//平台老用户
					notHaveFeeList.add(crmInvestorRecommend);
				}
			}
		}
		productRecommendChooseResponse.setAllFeeList(allFeeList);
		productRecommendChooseResponse.setHaveFeeList(haveFeeList);
		productRecommendChooseResponse.setNotHaveFeeList(notHaveFeeList);
		
		return productRecommendChooseResponse;
	}

	/**
	 * 查询产品标签   下栏
	 * 标签顺序    1：可赎回可转让   2：产品自定义标签   2：机构产品自定义标签
	 * 每款产品最多显示三个标签
	 * @param productCateForShowRequest
	 * @return
	 */
	private ArrayList<String> getProductCateShowList(ProductCateForShowRequest productCateForShowRequest){
		String appKind = productCateForShowRequest.getAppKind();
		ArrayList<String> productCateShowList =  new ArrayList<String>();
		ArrayList<String> productCateShowListFinally =  new ArrayList<String>();
		//添加产品可转让可赎回信息
		if(productCateForShowRequest.getIsRedemption() == 1){
			productCateShowList.add(productCateForShowRequest.getRedemptionTime()+"天后可赎回");
		} else if(productCateForShowRequest.getIsRedemption() == 2){
			productCateShowList.add(productCateForShowRequest.getAssignmentTime()+"天后可转让");
		} else if(productCateForShowRequest.getIsRedemption() == 3){
			productCateShowList.add(productCateForShowRequest.getRedemptionTime()+"天后可赎回"+productCateForShowRequest.getAssignmentTime()+"天后可转让");
		}
		//添加产品自定义标签
		if(StringUtils.isNotBlank(appKind) && StringUtils.isNotBlank(productCateForShowRequest.getProductId())){		
			ArrayList<String> productCateList  = cimProductMapper.queryProductCateForShow(productCateForShowRequest);
			if(productCateList != null && productCateList.size() > 0){
				productCateShowList.addAll(productCateList);
			}
		}
		//添加机构产品自定义标签
		CimOrginfo cimOrginfo = cimOrginfoService.queryCimOrginfoByProductid(productCateForShowRequest.getProductId());
		String orgProductTag = "";
		if("channel".equalsIgnoreCase(appKind)){//猎财大师
			orgProductTag = cimOrginfo.getOrgPlannerProductTag();
		} else if("investor".equalsIgnoreCase(appKind)){//投呗
			orgProductTag = cimOrginfo.getOrgProductTag();
		}
		if(StringUtils.isNotBlank(orgProductTag)){
			orgProductTag = orgProductTag.trim();
			String[] orgProductTagArray = null;
			//兼容中英文标点
			if(orgProductTag.indexOf(",") > 0){
				orgProductTagArray = orgProductTag.split(",");
			} else if(orgProductTag.indexOf("，") > 0){
				orgProductTagArray = orgProductTag.split("，");
			} else {
				productCateShowList.add(orgProductTag);
			}
			if(orgProductTagArray != null){		
				for(String orgProdTag:orgProductTagArray){		
					productCateShowList.add(orgProdTag);
				}
			}
		}
		//每款产品最多显示三个标签
		if(productCateShowList.size() <= 3){
			productCateShowListFinally.addAll(productCateShowList);
		} else {
			for(int i=0;i<=2;i++){
				productCateShowListFinally.add(productCateShowList.get(i));
			}
		}
		return productCateShowListFinally;
	}

	@Override
	public void recommendByChoose(AppRequestHead head,ProductRecommendByChooseRequest productRecommendByChooseRequest) {

		//获取理财师id
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		final String productId = productRecommendByChooseRequest.getProductId();
		String userIdString = productRecommendByChooseRequest.getUserIdString();
		//批量删除原有推荐信息  
		CimProductRef cimProductRefNew =  new CimProductRef();
		cimProductRefNew.setProductId(productId);
		cimProductRefNew.setSaleUserId(userId);
		cimProductRefService.deleteByCondition(cimProductRefNew);
		
		//推送个人消息列表
		final  List<SysMsg> msgList = Lists.newArrayList();
		final  List<String> userIds = Lists.newArrayList();
		//推荐产品
		CimProduct product = new CimProduct();
		product.setProductId(productId);
		product = selectOne(product);
		if(product == null) return;
		//产品所属机构信息
		CimOrginfo orgInfo = cimOrginfoService.queryOrgInfoByOrgNumber(product.getOrgNumber());
		final String orgName = orgInfo == null ? "" : orgInfo.getName();
		
		//理财师信息
		 CrmCfplanner crmCfplanner =  crmCfplannerService.queryCfplannerByInvestor(userId);
		//推送消息内容
		 String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_RECOMEND_PRODUCT_INV); 
		final String  content = contentTemp == null ? null : String.format(contentTemp,crmCfplanner == null ? "" : crmCfplanner.getUserName() == null ? "" :crmCfplanner.getUserName()+crmCfplanner.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),product == null ? "" :product.getProductName());

		 
		 //微信推荐成功通知
		 final List<WeiXinMsgRequest> wxList = Lists.newArrayList();
		 
		//插入理财师推荐信息
		if(StringUtils.isNotBlank(userIdString)){
			String[] investorArray = productRecommendByChooseRequest.getUserIdString().split(",");
			for(String investorUserId : investorArray){
				//如果该投资人不存在  直接跳出当前循环进入下一次循环
				CrmInvestor crmInvestor = new CrmInvestor();
				crmInvestor.setUserId(investorUserId);
				crmInvestor = crmInvestorService.selectOne(crmInvestor);
				if(crmInvestor == null) continue;
				
				CimProductRef cimProductRef = new CimProductRef();
				cimProductRef.setProductId(productId);
				cimProductRef.setSaleUserId(userId);
				cimProductRef.setInvestorUserId(investorUserId);
				cimProductRef.setRecommendTime(new Date());
				cimProductRef.setRemarks("理财师选择推荐产品");
				cimProductRefService.insert(cimProductRef);
				
				SysMsg msg = new SysMsg();				
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(investorUserId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
				msg.setTypeName(PersonalMsgTypeEnum.PROJECTINVEST_INV.getValue());
				msg.setStartTime(new Date());
				msg.setCrtTime(new Date());
				msg.setModifyTime(new Date());
				msg.setLinkBtnTxt("点击立即查看");
				msg.setLinkUrlKey(PersonalMsgTypeEnum.RECOMMEND_PRODUCT.getMsg());
				msgList.add(msg);
				userIds.add(investorUserId);
				
				//微信推荐成功通知
				WeiXinMsgRequest weixinreq = new WeiXinMsgRequest();
				weixinreq.setUseId(crmInvestor.getUserId());
				weixinreq.setTemkey(SysConfigConstant.RECOMMEND_SUCCESS);//推荐成功通知
				weixinreq.setRecommendPerson(crmCfplanner.getUserName());//推荐人
				weixinreq.setRecommendedPerson(crmInvestor.getUserName());//被推荐人
				weixinreq.setProductName(product.getProductName());
				wxList.add(weixinreq);
			}
		}
		//给推荐成功的投资客户发个人消息
		if(content != null && msgList.size() > 0){
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					sysMsgService.addMsgs(msgList);
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("productDetailUrl", new StringBuffer(configHelper.getValue(SysConfigConstant.RECOMEND_DETAIL_URL_INV)).append("?productId=").append(productId).toString());
					urlparam.put("productId",productId);
					urlparam.put("orgName",orgName);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.PRODUCTDTL_INC, userIds, "产品推荐", content, urlparam, false, PersonalMsgTypeEnum.RECOMMEND_PRODUCT);
					
				}
			});
		}
		
		//微信推荐成功通知
		if(wxList.size() > 0){
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					weiXinMsgService.sendWeiXinMsgListCommon(wxList);
				}
			});
		}
	}

	/**
	 * 查询产品分类统计信息	
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品 (中长期)801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品 (中长期)
	 * PC端:  		产品类型有 1-热门产品 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期) 801-理财师推荐产品 
	 * 其中  801-理财师推荐产品   901-首投标  902-复投标 为特殊产品分类 在分类表（tcim_product_cate）不存在
	 * 默认查询所有  可单独查询
	 */
	@Override
	public List<ProductStatisticsResponse> productClassifyStatistics201(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest) {

		LOGGER.info("查询产品分类统计,AppRequestHead={},ProductStatisticsRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productStatisticsRequest));
		
		List<ProductStatisticsResponse> productStatisticsResponselist =  new ArrayList<ProductStatisticsResponse>();
		ProductStatisticsResponse productStatisticsResponse = new ProductStatisticsResponse();
		String cateIdList = productStatisticsRequest.getCateIdList();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		String  platform = AppUtils.getPlatform(appRequestHead.getOrgNumber()).getValue();//获取平台的类型   android,ios,wechat,web,wap
		productStatisticsRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		/**
		 * 如果所传的cateIdList为空，则根据不同的请求appType查询所有的产品分类  默认查询所有  可单独查询
		 */
		if("channel".equalsIgnoreCase(appType)){//猎才大师		
			if(StringUtils.isBlank(cateIdList)){
				cateIdList = "2,3,4,5";
				productStatisticsRequest.setCateIdList(cateIdList);
			}
			productStatisticsResponselist = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
		} else if("investor".equalsIgnoreCase(appType)){//投呗
			/**
			 * PC端	单独处理
			 * 产品类型有    801-理财师推荐产品    2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期)
			 * 默认查询所有  可单独查询
			 */
			if(platform.equals("web")){
				if(StringUtils.isBlank(cateIdList)){
					cateIdList = "2,3,4,5,801";
					productStatisticsRequest.setCateIdList(cateIdList);
				}
				String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
				//PC端先展示理财师推荐
				if(ArrayUtils.contains(cateIdArray, "801")){
					/**
					 * 查询理财师userId
					 * token失效的时候  理财师推荐不显示
					 */
					if("token_valid_success".equals(jsonWebTokenHepler.checkToken(appRequestHead.getToken()).getCode())){
						CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
						productStatisticsRequest.setCfplannerUserId(crmInvestor.getCfplanner());
						productStatisticsResponse = cimProductMapper.queryRecdProductStatistics(productStatisticsRequest);
						productStatisticsResponselist.add(productStatisticsResponse);
					}
				}
				List<ProductStatisticsResponse>  productStatisticsResponses = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
				productStatisticsResponselist.addAll(productStatisticsResponses);
				
				//对于每一个分类，查询出一个优选的产品
				List<ProductStatisticsPreferenceResponse> productStatisticsPreferenceResponseList = getProductStatisticsResponselist(userId, appType, productStatisticsRequest);
				for(ProductStatisticsResponse productStatcsResponse : productStatisticsResponselist){
					for(ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse:productStatisticsPreferenceResponseList){
						if(productStatcsResponse.getCateId() == productStatisticsPreferenceResponse.getCateId()){
							productStatcsResponse.setProductPageListResponse(productStatisticsPreferenceResponse.getProductPageListResponse());
						}						
					}					
				}
			} else {
				/**
				 *  投呗:
				 *  产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品 (中长期)801-理财师推荐产品 
				 *  默认查询所有  可单独查询
				 */
				if(StringUtils.isBlank(cateIdList)){
					cateIdList = "2,3,4,5,801";
					productStatisticsRequest.setCateIdList(cateIdList);
				}
				productStatisticsResponselist = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
				String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
				if(ArrayUtils.contains(cateIdArray, "801")){
					/**
					 * 查询理财师userId
					 */
					if("token_valid_success".equals(jsonWebTokenHepler.checkToken(appRequestHead.getToken()).getCode())){
						CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()));
						productStatisticsRequest.setCfplannerUserId(crmInvestor.getCfplanner());
						productStatisticsResponse = cimProductMapper.queryRecdProductStatistics(productStatisticsRequest);
						productStatisticsResponselist.add(productStatisticsResponse);
					}
				}
			}
		}
		
		return productStatisticsResponselist;
	}
	
	/**
	 * 理财-产品分类列表 带排序
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标 3-短期产品  4-高收益产品  5-稳健收益产品（中长期） 802-热推产品
	 * PC端(T呗):  	产品类型有 1-热门产品 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品(中长期) 801-理财师推荐产品 802-热推产品
	 */
	@Override
	public PaginatorResponse<ProductPageListResponse> queryProductClassifyPageList201(AppRequestHead appRequestHead,ProductClassifyPageListRequest productCfyPgListRequest) {
		
		/**
		 * 封装page和productPageListClassifyRequest请求对象
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		if("channel".equalsIgnoreCase(appType)){
			productPageListClassifyRequest.setUserId(userId);
		}
		productPageListClassifyRequest.setAppKind(appType);
		productPageListClassifyRequest.setOrgNumber(productCfyPgListRequest.getOrgCode());
		productPageListClassifyRequest.setOrder(productCfyPgListRequest.getOrder());
		productPageListClassifyRequest.setSort(productCfyPgListRequest.getSort());
		productPageListClassifyRequest.setCateId(productCfyPgListRequest.getCateId());
		productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(productCfyPgListRequest.getPageIndex(), productCfyPgListRequest.getPageSize());
		
		LOGGER.info("查询产品分类列表, AppRequestHead={},productPageListClassifyRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productPageListClassifyRequest));
		
		/**
		 * 获取返回结果List<ProductPageListResponse>返回对象
		 */
		PaginatorResponse<ProductPageListResponse> paginatorResponse = new PaginatorResponse<ProductPageListResponse>();
		
		List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
		Integer cateId = productPageListClassifyRequest.getCateId();
		/**
		 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标 3-短期产品  4-高收益产品  5-稳健收益产品（中长期） 802-热推产品
		 * 	       投呗:  	产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
		 */
		if("channel".equalsIgnoreCase(appType)){//猎才大师
			if(cateId == 901 || cateId == 902) {// 901-首投标  902-复投标     
				productPageListResponses = cimProductMapper.queryProductCateExtendsPageList(productPageListClassifyRequest,page);
			} else if(cateId == 802){
				productPageListResponses = cimProductMapper.queryHotRecommendPageList(productPageListClassifyRequest,page);
			}else {
				productPageListResponses = cimProductMapper.queryProductCatePageList(productPageListClassifyRequest,page);
			}
		} else if("investor".equalsIgnoreCase(appType)){//投呗和PC端
			if(cateId == 801){//理财师推荐产品
				ProductPageListRecommendRequest productPageListRecommendRequest =  new ProductPageListRecommendRequest();
				BeanUtils.copyProperties(productPageListClassifyRequest, productPageListRecommendRequest);
				productPageListRecommendRequest.setUserId(userId);
				//查询投资用户对应的理财师信息
				CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
				if(crmInvestor != null){				
					productPageListRecommendRequest.setCfplannerUserId(crmInvestor.getCfplanner());
					LOGGER.info("查询理财师推荐产品列表, userId={},CrmInvestor={}",userId,JSONObject.toJSONString(crmInvestor));
					productPageListResponses = cimProductMapper.queryRecdProductPageList(productPageListRecommendRequest,page);
				}
			}else if(cateId == 802){
				productPageListResponses = cimProductMapper.queryHotRecommendPageList(productPageListClassifyRequest,page);
			}else {
				productPageListResponses = cimProductMapper.queryProductCatePageList(productPageListClassifyRequest,page);
			}
		}
		
		//添加产品标签
		addProductTags(productPageListResponses,appType,userId);
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<ProductStatisticsPreferenceResponse> productClassifyPreference(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest) {

		LOGGER.info("查询产品分类优选,AppRequestHead={},ProductStatisticsRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productStatisticsRequest));
		
		String cateIdList = productStatisticsRequest.getCateIdList();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		productStatisticsRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		
		if(StringUtils.isBlank(cateIdList)){
			if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
				cateIdList = "2,3,4,5,801";
			}else {
				cateIdList = "2,3,4,5,802";
			}			
			productStatisticsRequest.setCateIdList(cateIdList);
		}
		
		return getProductStatisticsResponselist(userId, appType, productStatisticsRequest);
	}
	
	
	private List<ProductStatisticsPreferenceResponse> getProductStatisticsResponselist(String userId,String  appType,ProductStatisticsRequest productStatisticsRequest){
		
		List<ProductStatisticsPreferenceResponse> productStatisticsResponselist =  new ArrayList<ProductStatisticsPreferenceResponse>();
		/**
		 * 每一个分类查询一个优选的产品 
		 * 默认顺序
		 */
		String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
		if(ArrayUtils.contains(cateIdArray, "801")){
			
			ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse = new ProductStatisticsPreferenceResponse();			
			//添加分类id
			productStatisticsPreferenceResponse.setCateId(801);
			productStatisticsPreferenceResponse.setCateName("我的理财师推荐");
			
			ProductPageListResponse productPageListResponse = null;
			ProductPageListRecommendRequest productPageListRecommendRequest =  new ProductPageListRecommendRequest();
			productPageListRecommendRequest.setUserId(userId);
			productPageListRecommendRequest.setAppKind(appType);
			//查询投资用户对应的理财师信息
			CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
			if(crmInvestor != null){				
				productPageListRecommendRequest.setCfplannerUserId(crmInvestor.getCfplanner());
				LOGGER.info("查询理财师最新推荐产品, userId={},CrmInvestor={}",userId,JSONObject.toJSONString(crmInvestor));
				productPageListRecommendRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
				productPageListResponse = cimProductMapper.queryNewestRecdProduct(productPageListRecommendRequest);
			}
			if(productPageListResponse != null){				
				productStatisticsPreferenceResponse.setProductPageListResponse(productPageListResponse);
			}else{
				//802
				//添加分类id
				productStatisticsPreferenceResponse.setCateId(802);
				productStatisticsPreferenceResponse.setCateName("理财师们热推");
				
				List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
				Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(1,10);
				ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
				productPageListClassifyRequest.setAppKind(appType);
				productPageListClassifyRequest.setUserId(userId);
				productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
				productPageListResponses = cimProductMapper.queryHotRecommendPageList(productPageListClassifyRequest,page);
				
				if(productPageListResponses != null && productPageListResponses.size() > 0){
					productPageListResponse = productPageListResponses.get(0);
				}
				
				if(productPageListResponse != null){				
					productStatisticsPreferenceResponse.setProductPageListResponse(productPageListResponse);
				}
			}
			if(productPageListResponse != null){
				List<ProductPageListResponse> tempPageListResponses = new ArrayList<ProductPageListResponse>();
				tempPageListResponses.add(productPageListResponse);
				addProductTags(tempPageListResponses,appType,userId);
				productStatisticsResponselist.add(productStatisticsPreferenceResponse);
			}		
		}
		if(ArrayUtils.contains(cateIdArray, "802")){
			
			ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse = new ProductStatisticsPreferenceResponse();			
			//添加分类id
			productStatisticsPreferenceResponse.setCateId(802);
			productStatisticsPreferenceResponse.setCateName("理财师们热推");
			
			List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
			Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(1,10);
			ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
			productPageListClassifyRequest.setAppKind(appType);
			productPageListClassifyRequest.setUserId(userId);
			productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
			productPageListResponses = cimProductMapper.queryHotRecommendPageList(productPageListClassifyRequest,page);
			ProductPageListResponse productPageListResponse = null;
			
			if(productPageListResponses != null && productPageListResponses.size() > 0){
				productPageListResponse = productPageListResponses.get(0);
			}
			
			if(productPageListResponse != null){
				List<ProductPageListResponse> tempPageListResponses = new ArrayList<ProductPageListResponse>();
				tempPageListResponses.add(productPageListResponse);
				addProductTags(tempPageListResponses,appType,userId);
				productStatisticsPreferenceResponse.setProductPageListResponse(productPageListResponse);
				productStatisticsResponselist.add(productStatisticsPreferenceResponse);
			}		
		}
		
		if(ArrayUtils.contains(cateIdArray, "2")){
			ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse = new ProductStatisticsPreferenceResponse();
			
			setCateIdAndNameRtProductCate(productStatisticsPreferenceResponse, 2);
			
			ProductPageListResponse productPageListResponse = null;
			
			if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
				ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
				productPageListClassifyRequest.setUserId(userId);
				productPageListClassifyRequest.setAppKind(appType);
				productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
				productPageListResponse = cimProductMapper.queryNotInvestPlatformNewerProduct(productPageListClassifyRequest);
				
				if(productPageListResponse != null){	
					List<ProductPageListResponse> tempPageListResponses = new ArrayList<ProductPageListResponse>();
					tempPageListResponses.add(productPageListResponse);
					addProductTags(tempPageListResponses,appType,userId);
					productStatisticsPreferenceResponse.setProductPageListResponse(productPageListResponse);
					productStatisticsResponselist.add(productStatisticsPreferenceResponse);					
				}
			}else {
				productselfDefinedTypePreference(productStatisticsResponselist,2,appType,userId);
			}
			
		}
		if(ArrayUtils.contains(cateIdArray, "3")){
			productselfDefinedTypePreference(productStatisticsResponselist,3,appType,userId);
		}
		if(ArrayUtils.contains(cateIdArray, "5")){
			productselfDefinedTypePreference(productStatisticsResponselist,5,appType,userId);
		}
		if(ArrayUtils.contains(cateIdArray, "4")){
			productselfDefinedTypePreference(productStatisticsResponselist,4,appType,userId);
		}
		
		return productStatisticsResponselist;
	}
	
	/**
	 * 设置分类名称和分类ID 返回产品分类信息
	 * @param productStatisticsPreferenceResponse
	 * @param cateId
	 * @return
	 */
	private CimProductCate setCateIdAndNameRtProductCate(ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse,Integer cateId){
		String cateName = null;
		CimProductCate cimProductCate  = new CimProductCate();
		
		cimProductCate.setCateId(cateId);
		cimProductCate = cimProductCateService.selectOne(cimProductCate);
		cateName = cimProductCate == null? "" :cimProductCate.getDescription();
		
		//添加分类id
		productStatisticsPreferenceResponse.setCateId(cateId);
		//设置分类名称
		productStatisticsPreferenceResponse.setCateName(cateName);
		
		return cimProductCate;
	}

	/**
	 * 产品自定义优选分类
	 * @param productStatisticsResponselist
	 */
	private void productselfDefinedTypePreference(List<ProductStatisticsPreferenceResponse> productStatisticsResponselist,Integer cateId, String appType,String userId){
		ProductStatisticsPreferenceResponse productStatisticsPreferenceResponse = new ProductStatisticsPreferenceResponse();
		
		//设置分类名称和分类ID 返回产品分类信息
		CimProductCate cimProductCate  = setCateIdAndNameRtProductCate(productStatisticsPreferenceResponse, cateId);
		ProductPageListResponse productPageListResponse = null;
		
		ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
		productPageListClassifyRequest.setCateId(cateId);
		productPageListClassifyRequest.setAppKind(appType);
		productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		//有主推平台
		if(StringUtils.isNotBlank(cimProductCate.getMajorRecommendationPlatform())){							
			productPageListClassifyRequest.setOrgNumber(cimProductCate.getMajorRecommendationPlatform());
			productPageListResponse = cimProductMapper.queryProductCateList(productPageListClassifyRequest);
		}
		//有主推平台且主推平台该类型的产品不存在或者不存在主推平台
		if(productPageListResponse == null){
			productPageListClassifyRequest.setOrgNumber(null);
			productPageListResponse = cimProductMapper.queryProductCateList(productPageListClassifyRequest);
		}
			
		if(productPageListResponse != null){	
			List<ProductPageListResponse> tempPageListResponses = new ArrayList<ProductPageListResponse>();
			tempPageListResponses.add(productPageListResponse);
			addProductTags(tempPageListResponses,appType,userId);
			productStatisticsPreferenceResponse.setProductPageListResponse(productPageListResponse);
			productStatisticsResponselist.add(productStatisticsPreferenceResponse);
		}		
	}
	
	/**
	 * 根据产品分页列表返回   设置下方标签  右上角标签
	 * @param productPageListResponse
	 * @param appType
	 */
	private void addProductTags(List<ProductPageListResponse> productPageListResponseList,String appType,String userId){
		for(ProductPageListResponse productPageListResponse:productPageListResponseList){
			ProductCateForShowRequest productCateForShowRequest = new ProductCateForShowRequest();
			BeanUtils.copyProperties(productPageListResponse, productCateForShowRequest);
			productCateForShowRequest.setAppKind(appType);
			//底部
			ArrayList<String> productCateShowList  = getProductCateShowList(productCateForShowRequest);
			productPageListResponse.setTagList(productCateShowList);
			//右上角
			ArrayList<String> tagListRight = new ArrayList<String>();
			if(productPageListResponse.getOrgFeeType() !=null && productPageListResponse.getOrgFeeType() == 1){
				tagListRight.add("首投");
			} else if(productPageListResponse.getOrgFeeType() !=null && productPageListResponse.getOrgFeeType() == 2){
				tagListRight.add("复投");
			}
			productPageListResponse.setTagListRight(tagListRight);
			//右上角 -新
			ArrayList<String> productCateShowListRightNewer = addProductRightTagsNewer(productPageListResponse.getProductId());
			productPageListResponse.setTagListRightNewer(productCateShowListRightNewer);
		}
		
		if(StringUtils.isBlank(userId) || "undefined".equals(userId)){
			for(ProductPageListResponse productPageListResponse:productPageListResponseList){
				productPageListResponse.setHasRedPacket(false);
			}		
		}else {
			redPacketService.productRedPacketTag(productPageListResponseList, userId);
		}
	}

	/**
	 * 全部标的
	 */
	@Override
	public List<ProductStatisticsResponse> productTypeList(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest) {

		LOGGER.info("全部标的,AppRequestHead={},ProductStatisticsRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productStatisticsRequest));
		
		List<ProductStatisticsResponse> productStatisticsResponselist =  new ArrayList<ProductStatisticsResponse>();
		ProductStatisticsResponse productStatisticsResponse = new ProductStatisticsResponse();
		String cateIdList = productStatisticsRequest.getCateIdList();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		productStatisticsRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		/**
		 * 如果所传的cateIdList为空，则根据不同的请求appType查询所有的产品分类  默认查询所有  可单独查询
		 */
		if("channel".equalsIgnoreCase(appType)){//猎才大师
			if(StringUtils.isBlank(cateIdList)){
				cateIdList = "2,901,902";
				productStatisticsRequest.setCateIdList(cateIdList);
			}
			productStatisticsResponselist = cimProductMapper.productClassifyStatistics(productStatisticsRequest);
			String[] cateIdArray = productStatisticsRequest.getCateIdList().split(",");
			//猎财大师先显示首投标  再显示复投标
			if(ArrayUtils.contains(cateIdArray, "901")){//901-首投标 
				productStatisticsRequest.setCateIdList("901");
				productStatisticsResponse = cimProductMapper.queryProductCateExtendsStatistics(productStatisticsRequest);
				productStatisticsResponselist.add(productStatisticsResponse);
			}
			if(ArrayUtils.contains(cateIdArray, "902")){//902-复投标 
				productStatisticsRequest.setCateIdList("902");
				productStatisticsResponse = cimProductMapper.queryProductCateExtendsStatistics(productStatisticsRequest);
				productStatisticsResponselist.add(productStatisticsResponse);
			}
		} 
		return productStatisticsResponselist;
	}

	@Override
	public void autoProductEdit(CimProduct cimProductNew) {
		/**
		 * 根据机构编码查询产品的编辑信息
		 * 设置产品描述为管理员设置的产品描述
		 */
		CimProductEdit cimProductEdit = new CimProductEdit();
		cimProductEdit.setOrgNumber(cimProductNew.getOrgNumber());
		List<CimProductEdit> cimProductEditList= cimProductEditService.selectListByCondition(cimProductEdit);
		for(CimProductEdit cimProductEditNew:cimProductEditList){
			if(cimProductNew.getProductName().indexOf(cimProductEditNew.getProductName()) != -1){
				cimProductNew.setProductDesc(cimProductEditNew.getProductDesc());
				break;
			}
		}
	}

	/**
	 * 热推产品列表TOP10
	 */
	@Override
	public List<ProductPageListResponse> queryProductClassifyPageListTop(AppRequestHead appRequestHead) {
		List<ProductPageListResponse> productPageListResponses = new ArrayList<ProductPageListResponse>();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//当前用户
		ProductPageListClassifyRequest productPageListClassifyRequest =  new ProductPageListClassifyRequest();
		String  appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue();//查询appType
		if("channel".equalsIgnoreCase(appType)){
			productPageListClassifyRequest.setUserId(userId);
		}
		productPageListClassifyRequest.setAppKind(appType);
		productPageListClassifyRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		productPageListResponses = cimProductMapper.queryHotRecommendPageListTop(productPageListClassifyRequest);
		//添加产品标记
		addProductTags(productPageListResponses,appType,userId);
		return productPageListResponses;
	}
	
	/**
	 * 根据产品分页列表返回   设置右上角的新手标标签
	 * @param productPageListResponse
	 * @param appType
	 */
	private ArrayList<String> addProductRightTagsNewer(String productId){
		ArrayList<String> tagListRightNewer = new ArrayList<String>();		
		CimProductInfoCate cimProductInfoCate = new CimProductInfoCate();
		cimProductInfoCate.setProductId(productId);
		cimProductInfoCate.setCateId(2);
		CimProductInfoCate cimProductInfoCateTemp = cimProductInfoCateService.selectOne(cimProductInfoCate); 
		if(cimProductInfoCateTemp != null){
			tagListRightNewer.add("新手专享");
		}
		return tagListRightNewer;
	}
}
