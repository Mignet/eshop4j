package com.linkwee.api.controller.cim;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.cim.HotProductRequest;
import com.linkwee.api.request.cim.ProductClassifyPageListRequest;
import com.linkwee.api.request.cim.ProductDetailRequest;
import com.linkwee.api.request.cim.ProductInvestRequest;
import com.linkwee.api.request.cim.ProductPageListRequest;
import com.linkwee.api.request.cim.ProductRecommendByChooseRequest;
import com.linkwee.api.request.cim.ProductRecommendChooseRequest;
import com.linkwee.api.request.cim.ProductScreenRequest;
import com.linkwee.api.request.cim.ProductStatisticsRequest;
import com.linkwee.api.request.cim.ScreenProductPageListRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductInvestResponse;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.api.response.cim.ProductStatisticsPreferenceResponse;
import com.linkwee.api.response.cim.ProductStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProductRef;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.model.share.ShareMessage;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductExtendsService;
import com.linkwee.web.service.CimProductRefService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.WebUtil;

@Controller
@RequestMapping(value = "/api/product")
@RequestLogging("产品")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private CimProductService cimProductService;
	@Resource
	private CimProductExtendsService cimProductExtendsService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	@Resource
	private CimProductRefService cimProductRefService;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CimOrginfoService cimOrgInfoService;
	@Resource
	private SysGrayReleaseService sysGrayReleaseService;
	@Resource
	private RedPacketService redPacketService;
	
    /**
     * 理财产品-热门产品列表-分页
     * @param req
     * @param head
     * @return
     */
    @ResponseBody
    @RequestMapping("/hotProduct")
    @RequestLogging("热门产品列表")
	public BaseResponse queryHotProduct(AppRequestHead head,PaginatorRequest pageRequest,String orgCode){
    	HotProductRequest hotProductRequest =  new HotProductRequest();
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
    	hotProductRequest.setUserId(userId);
    	hotProductRequest.setOrgNumber(orgCode);
    	hotProductRequest.setAppKind(AppUtils.getAppType(head.getOrgNumber()).getValue());
    	hotProductRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
    	Page<ProductPageListResponse> page  = new Page<ProductPageListResponse>(pageRequest.getPageIndex(),pageRequest.getPageSize());
    	LOGGER.info("查询热门产品列表, hotProductRequest={},page={}",JSONObject.toJSONString(hotProductRequest),JSONObject.toJSONString(page));
		PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryHotProduct(hotProductRequest,page);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
     
    /**
     * 理财-产品列表-分页排序(所有产品)
     * @param head
     * @param pageRequest
     * @return
     */
	@ResponseBody
	@RequestLogging("产品列表")
	@RequestMapping("/productPageList")
	public BaseResponse productPageList(AppRequestHead head,PaginatorRequest pageRequest,String orgCode){
		LOGGER.info("查询理财产品列表[所有], pageRequest={}", JSONObject.toJSONString(pageRequest));
		ProductPageListRequest productPageListRequest =  new ProductPageListRequest();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
		String  appType = AppUtils.getAppType(head.getOrgNumber()).getValue();//查询appType
		/*if("channel".equalsIgnoreCase(appType)){
			productPageListRequest.setUserId(userId);
		}*/
		//默认排序改成年化收益降序
		if(pageRequest.getSort() == null || pageRequest.getSort() == 1){
			pageRequest.setSort(2);
			pageRequest.setOrder(1);
		}
		productPageListRequest.setUserId(userId);
		productPageListRequest.setAppKind(appType);
		productPageListRequest.setOrgNumber(orgCode);
		productPageListRequest.setOrder(pageRequest.getOrder());
		productPageListRequest.setSort(pageRequest.getSort());
		productPageListRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(pageRequest.getPageIndex(), pageRequest.getPageSize());
		PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryProductPageList(productPageListRequest,page);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
    /**
     * 理财-产品分类列表-分页排序
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
     * @param appRequestHead
     * @param pageRequest
     * @return
     */
	@ResponseBody
	@RequestLogging("产品分类列表")
	@RequestMapping("/productClassifyPageList")
	public BaseResponse productClassifyPageList(AppRequestHead appRequestHead,ProductClassifyPageListRequest productCfyPgListRequest){
		LOGGER.info("查询理财产品分类列表, ProductClassifyPageListRequest={}", JSONObject.toJSONString(productCfyPgListRequest));
		//默认排序改成年化收益降序
		if(productCfyPgListRequest.getSort() == null || productCfyPgListRequest.getSort() == 1){
			productCfyPgListRequest.setSort(2);
			productCfyPgListRequest.setOrder(1);
		}
		PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryProductClassifyPageList(appRequestHead,productCfyPgListRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 产品分类统计(根据产品分类表进行产品分类统计)
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * @param productStatisticsRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("产品分类统计")
	@RequestMapping("/productClassifyStatistics")
	public BaseResponse productClassifyStatistics(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest){
		List<ProductStatisticsResponse> productStatisticsResponses= cimProductService.productClassifyStatistics(appRequestHead,productStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(productStatisticsResponses);
	}
	
	@ResponseBody
	@RequestLogging("产品分类优选")
	@RequestMapping("/productClassifyPreference/2.0.1")
	public BaseResponse productClassifyPreference(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest){
		List<ProductStatisticsPreferenceResponse> productStatisticsResponses= cimProductService.productClassifyPreference(appRequestHead,productStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(productStatisticsResponses);
	}
	
    /**
     * 理财-产品详情
     * @param head
     * @param pageRequest
     * @return
     */
	@ResponseBody
	@RequestLogging("产品详情")
	@RequestMapping("/productDetail")
	public BaseResponse productDetail(AppRequestHead head,String productId){
		LOGGER.info("根据产品id查询产品详情, productId={}", productId);
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		ProductDetailRequest productDetailRequest = new ProductDetailRequest();
		String  appType = AppUtils.getAppType(head.getOrgNumber()).getValue();//查询appType
		if("channel".equalsIgnoreCase(appType)){
			productDetailRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		}
		productDetailRequest.setAppKind(appType);
		productDetailRequest.setProductId(productId);
		ProductDetailResponse productDetailResponse = new ProductDetailResponse();
		productDetailResponse = cimProductService.queryProductDetail(productDetailRequest);
		//可使用的红包数量
		if(productDetailResponse != null && StringUtils.isNotBlank(userId) && !"undefined".equals(userId)){
			Integer couldUseRedPacketCounts = redPacketService.productRedPacketCount(productDetailResponse,userId);
			productDetailResponse.setCouldUseRedPacketCounts(couldUseRedPacketCounts);
		}
		return AppResponseUtil.getSuccessResponse(productDetailResponse);
	}
	
	/**
	 * 产品分享
	 * @param productId  产品id
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("产品分享")
	@RequestMapping("/share")
	public BaseResponse productShare(String productId,AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		ShareContent shareContent = cimProductExtendsService.selectShareContentByProductId(productId);
		if(null == shareContent){
			ProductDetailResponse productDetailResponse = cimProductService.queryProductDetail(productId);
			shareContent = new ShareContent();		
			shareContent.setShareDesc("预期收益："+productDetailResponse.getProductRateText()+ "		产品期限：" +productDetailResponse.getDeadLineValueNewText());
			shareContent.setShareImgurl(sysConfigService.querySysConfigByKey("product_config", "product_shareImgurl", 0).getConfigValue());
			shareContent.setShareLink(sysConfigService.querySysConfigByKey("product_config", "product_shareLink", 0).getConfigValue());
			shareContent.setShareTitle(productDetailResponse.getProductName());
		}
		//分享链接 添加分享人联系方式
		Map<String, String> params = new HashMap<String, String>();
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		params.put("recommendCode",crmUserInfo.getMobile());
		params.put("productId",productId);
		shareContent.setShareLink(WebUtil.creatUrl(shareContent.getShareLink(), params));
		LOGGER.info("产品分享,productId={},cimProductInfoShare={}", productId,JSONObject.toJSONString(shareContent));
		return AppResponseUtil.getSuccessResponse(new ShareMessage(shareContent));
	}
	
	/**
	 * 产品推荐
	 * @param productId	产品id
	 * @param head
	 * @return
	 * 老版本20161011之前 已做兼容处理
	 */
	@ResponseBody
	@RequestLogging("产品推荐")
	@RequestMapping("/recommend")
	public BaseResponse productRecommend(String productId,AppRequestHead head){
		if(StringUtils.isBlank(productId)){
			return AppResponseUtil.getErrorBusi("productId_isNull", "产品ID为null,产品推荐失败");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		/**
		 * 先删除所有的已推荐产品的客户
		 */
		CimProductRef cimProductRef = new CimProductRef();
		cimProductRef.setProductId(productId);
		cimProductRef.setSaleUserId(userId);
		List<CimProductRef> cimProductRefList = cimProductRefService.selectListByCondition(cimProductRef);
		LOGGER.info("产品推荐, productId={},UserId={}", productId,userId);
		if(null != cimProductRefList && cimProductRefList.size() > 0){
			for(CimProductRef cProductRef : cimProductRefList){			
				cimProductRefService.delete((long)cProductRef.getId());
			}
		}
		
		/**
		 * 查询理财师所有的客户，并推荐该产品
		 * 根据客户条件筛选理财师所有客户
		 */
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setCfplanner(userId);
		List<CrmInvestor>  crmInvestorList = crmInvestorService.selectListByCondition(crmInvestor);
		for(CrmInvestor cInvestor:crmInvestorList){
			CimProductRef cimProductRefNew = new CimProductRef();
			cimProductRefNew.setProductId(productId);
			cimProductRefNew.setSaleUserId(userId);
			cimProductRefNew.setInvestorUserId(cInvestor.getUserId());
			CimProductRef cimProductRefSelect = cimProductRefService.selectOne(cimProductRefNew);
			if(null == cimProductRefSelect){
				cimProductRefNew.setRecommendTime(new Date());
				cimProductRefNew.setRemarks("理财师推荐全部产品");
				cimProductRefService.insert(cimProductRefNew);
			}
		}
		
		return AppResponseUtil.getSuccessResponse();
	}

	/**
	 * 产品推荐取消
	 * @param productId
	 * @param head
	 * @return
	 * 老版本20161011之前   已做兼容处理
	 */
	@ResponseBody
	@RequestLogging("产品推荐取消")
	@RequestMapping("/cancelRecommend")
	public BaseResponse productCancelRecommend(String productId,AppRequestHead head){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CimProductRef cimProductRef = new CimProductRef();
		cimProductRef.setProductId(productId);
		cimProductRef.setSaleUserId(userId);
		List<CimProductRef> cimProductRefList = cimProductRefService.selectListByCondition(cimProductRef);
		LOGGER.info("产品推荐取消, productId={},UserId={}", productId,userId);
		if(null != cimProductRefList && cimProductRefList.size() > 0){
			for(CimProductRef cProductRef : cimProductRefList){			
				cimProductRefService.delete((long)cProductRef.getId());
			}
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 产品推荐选择列表
	 * @param productRecommendChooseRequest
	 * @param head
	 * @return
	 * 新版本20161011之后
	 */
	@ResponseBody
	@RequestLogging("产品推荐选择列表")
	@RequestMapping("/recommendChooseList")
	public BaseResponse recommendChooseList(AppRequestHead head,ProductRecommendChooseRequest productRecommendChooseRequest){
		if(StringUtils.isBlank(productRecommendChooseRequest.getProductId())){
			return AppResponseUtil.getErrorBusi("productId_isNull", "产品ID为null,产品推荐失败");
		}
		LOGGER.info("产品推荐选择列表,productRecommendChooseRequest={}",JSONObject.toJSONString(productRecommendChooseRequest));
		ProductRecommendChooseResponse productRecommendChooseResponse = cimProductService.recommendChooseList(head,productRecommendChooseRequest);
		return AppResponseUtil.getSuccessResponse(productRecommendChooseResponse);
	}
	
	/**
	 * 产品选择推荐
	 * @param ProductRecommendByChooseRequest
	 * @param head
	 * @return
	 * 新版本20161011之后
	 */
	@ResponseBody
	@RequestLogging("产品选择推荐")
	@RequestMapping("/recommendByChoose")
	public BaseResponse recommendByChoose(AppRequestHead head,ProductRecommendByChooseRequest productRecommendByChooseRequest){
		if(StringUtils.isBlank(productRecommendByChooseRequest.getProductId())){
			return AppResponseUtil.getErrorBusi("productId_isNull", "产品ID为null,产品推荐失败");
		}
		LOGGER.info("产品选择推荐,productRecommendChooseRequest={}",JSONObject.toJSONString(productRecommendByChooseRequest));
		cimProductService.recommendByChoose(head,productRecommendByChooseRequest);
		return AppResponseUtil.getSuccessResponse();
	}
	
	
	/**
	 * 理财师推荐的产品列表
	 * @param userId  理财师用户id
	 * @param pageRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("理财师推荐的产品列表")
	@RequestMapping("/recdProductPageList")
	public BaseResponse recdProductPageList(AppRequestHead appRequestHead,ProductClassifyPageListRequest productClassifyPageListRequest){
		LOGGER.info("查询理财师推荐产品列表, AppRequestHead={},ProductClassifyPageListRequest={}",JSONObject.toJSONString(appRequestHead),JSONObject.toJSONString(productClassifyPageListRequest));
		if(StringUtils.isBlank(appRequestHead.getToken())){
			return AppResponseUtil.getErrorBusi("query_recdProductList_fail", "查询理财师推荐的产品列表失败。token不能为空");
		} else {
			productClassifyPageListRequest.setCateId(801);
			PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryProductClassifyPageList(appRequestHead, productClassifyPageListRequest);
			return AppResponseUtil.getSuccessResponse(rlt);
		}
	}
		
	/**
	 * 产品筛选条件
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/productHead")
	@ResponseBody
	@RequestLogging("产品筛选条件")
	public BaseResponse productHead(AppRequestHead head,ProductScreenRequest req) throws Exception {
    	LOGGER.info("productHead|head:"+head);
    	Map<String,Object> rlt = new HashMap<String,Object>();
    	
    	List<SysConfig> sysList = sysConfigService.querySysConfigByName("产品筛选条件-");
    	List<Map<String,Object>> profitList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> deadlineList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> backGroundList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> platfromList = new ArrayList<Map<String,Object>>();
    	for(SysConfig item:sysList){
    		Map<String,Object> data = new HashMap<String,Object>();
    		data.put("key", item.getConfigKey());
    		data.put("value", item.getConfigValue());
    		if(item.getConfigType().endsWith("profit")){
    			profitList.add(data);
    		}else if(item.getConfigType().endsWith("days")){
    			deadlineList.add(data);
    		}else if(item.getConfigType().endsWith("background")){
    			backGroundList.add(data);
    		}
    	}
    	
    	List<SysConfig> orgSelectList = sysConfigService.querySysConfigByName("机构筛选条件-");
    	List<Map<String,Object>> orgLevelList = new ArrayList<Map<String,Object>>(); //机构评级
    	for(SysConfig item:orgSelectList){
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("key", item.getConfigKey());
			data.put("value", item.getConfigValue());
			if(item.getConfigType().endsWith("grade")){ //评级
				orgLevelList.add(data);
			}
		}
    	
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
    	Boolean ifHaveGray = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1");
    	
    	List<CimOrginfo> orgList = cimOrgInfoService.selectListByGrade(req.getSecurityLevel(),ifHaveGray);
    	for(CimOrginfo orgInfo : orgList){
    		Map<String,Object> data = new HashMap<String,Object>();
    		data.put("key", orgInfo.getName());
    		data.put("value", orgInfo.getOrgNumber());
    		platfromList.add(data);
    	}
    	
    	rlt.put("profit", profitList);
    	rlt.put("deadline", deadlineList);
    	rlt.put("background", backGroundList);
    	rlt.put("platfrom", platfromList);
    	rlt.put("orgLevel", orgLevelList);
				
    	LOGGER.info("productHead| query success!");
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
	 * 产品筛选-分页(PC端)
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/screenPageList")
	@ResponseBody
	@RequestLogging("产品筛选-分页")
	public BaseResponse productScreenPageList(@Valid ProductScreenRequest req,BindingResult result,AppRequestHead head){
    	
    	LOGGER.info("产品筛选请求参数 ProductScreenRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
    	
		//默认排序改成年化收益降序
		if(req.getSort() == null || req.getSort() == 1){
			req.setSort(2);
			req.setOrder(1);
		}
		
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
    	
    	ScreenProductPageListRequest productPageListRequest =  new ScreenProductPageListRequest();
		productPageListRequest.setUserId(userId);
		productPageListRequest.setOrgNumber(req.getPlatform());
		productPageListRequest.setAppKind(AppUtils.getAppType(head.getOrgNumber()).getValue());
		productPageListRequest.setPlatformContext(req.getBackground());
		productPageListRequest.setSecurityLevel(req.getSecurityLevel());
		productPageListRequest.setOrder(req.getOrder());
		productPageListRequest.setSort(req.getSort());
		productPageListRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(userId, "0,1"));
		
		//产品期限
    	if(StringUtils.isNotBlank(req.getProductDeadLine()) && req.getProductDeadLine().contains(",")){
    		String[] deadLine = req.getProductDeadLine().split(",");
    		if(deadLine.length == 2){
    			String minDeadLine = deadLine[0];
    			String maxDeadLine = deadLine[1];
    			productPageListRequest.setMinDeadLine(minDeadLine);
    			productPageListRequest.setMaxDeadLine(maxDeadLine);
    		}
    	}
    	//年化收益
    	if(StringUtils.isNotBlank(req.getYearProfit()) && req.getYearProfit().contains(",")){
    		String[] yearProfit = req.getYearProfit().split(",");
    		if(yearProfit.length == 2){
    			String minYearProfit = yearProfit[0];
    			String maxYearProfit = yearProfit[1];
    			productPageListRequest.setMinYearProfit(minYearProfit);
    			productPageListRequest.setMaxYearProfit(maxYearProfit);
    		}
    	}  	
		
		Page<ProductPageListResponse> page = new Page<ProductPageListResponse>(req.getPageIndex(), req.getPageSize());		
		PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryProductScreenPageList(productPageListRequest,page); //		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    
    /**
	 * 产品投资记录(PC端)
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="productInvestList")
	@ResponseBody
	@RequestLogging("产品投资记录")
	public BaseResponse productInvestList(@Valid ProductInvestRequest req,BindingResult result,AppRequestHead head){
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
    	Page<ProductInvestResponse> page = new Page<ProductInvestResponse>(req.getPageIndex(), req.getPageSize()>10?10: req.getPageSize());
    	PaginatorResponse<ProductInvestResponse> rlt = cimProductService.getProductInvestList(req, page);
    	return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
     * 全部标的（猎财大师显示）
     * @param head
     * @return
     */
    @RequestMapping("/productTypeList/2.0.1")
    @ResponseBody
    @RequestLogging("全部标的列表")
    public BaseResponse productTypeList(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest){
    	List<ProductStatisticsResponse> productStatisticsResponses= cimProductService.productTypeList(appRequestHead,productStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(productStatisticsResponses);
    }
    
    /**
	 * 产品分类统计(根据产品分类表进行产品分类统计)
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 3-短期产品  4-高收益产品  5-中长期产品
	 * @param productStatisticsRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("产品分类统计2.0.1")
	@RequestMapping("/productClassifyStatistics/2.0.1")
	public BaseResponse productClassifyStatistics201(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest){
		List<ProductStatisticsResponse> productStatisticsResponses= cimProductService.productClassifyStatistics201(appRequestHead,productStatisticsRequest);
		return AppResponseUtil.getSuccessResponse(productStatisticsResponses);
	}
	
	/**
     * 理财-产品分类列表-分页排序
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品  802-理财师热推产品
	 * 猎才大师:		产品类型有 2-新手产品  901-首投标  902-复投标  3-短期产品  4-高收益产品  5-稳健收益产品(中长期) 802-理财师热推产品
     * @param appRequestHead
     * @param pageRequest
     * @return
     */
	@ResponseBody
	@RequestLogging("产品分类列表2.0.1")
	@RequestMapping("/productClassifyPageList/2.0.1")
	public BaseResponse productClassifyPageList201(AppRequestHead appRequestHead,ProductClassifyPageListRequest productCfyPgListRequest){
		LOGGER.info("查询理财产品分类列表2.0.1, ProductClassifyPageListRequest={}", JSONObject.toJSONString(productCfyPgListRequest));
		//默认排序改成年化收益降序
		if(productCfyPgListRequest.getSort() == null || productCfyPgListRequest.getSort() == 1){
			productCfyPgListRequest.setSort(2);
			productCfyPgListRequest.setOrder(1);
		}
		PaginatorResponse<ProductPageListResponse> rlt = cimProductService.queryProductClassifyPageList201(appRequestHead,productCfyPgListRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 热推产品列表TOP10
	 * @param appRequestHead
	 * @return
	 */
	@ResponseBody
	@RequestLogging("热推产品列表TOP10")
	@RequestMapping("/hotRecommendProductListTop/2.0.1")
	public BaseResponse hotRecommendProductListTop(AppRequestHead appRequestHead){
		List<ProductPageListResponse> rlt = cimProductService.queryProductClassifyPageListTop(appRequestHead);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
}
