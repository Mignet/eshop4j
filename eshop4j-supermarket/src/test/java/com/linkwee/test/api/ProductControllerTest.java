package com.linkwee.test.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.cim.ProductClassifyPageListRequest;
import com.linkwee.api.request.cim.ProductInvestRequest;
import com.linkwee.api.request.cim.ProductRecommendByChooseRequest;
import com.linkwee.api.request.cim.ProductScreenRequest;
import com.linkwee.api.request.cim.ProductStatisticsRequest;
import com.linkwee.api.request.tc.ProfitCalculateRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.api.response.cim.ProductStatisticsPreferenceResponse;
import com.linkwee.api.response.cim.ProductStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.CimProductExtends;

public class ProductControllerTest extends BaseTest{
	
	/**
	 * 首页-热门产品
	 * @throws Exception
	 */
	@Test
	public void testHotProduct() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(100);
		Map<String, String> parameterMap = new HashMap<String, String>();
//		parameterMap.put("orgCode", "7777777");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/hotProduct",this.getToken(),ProductPageListResponse.class,pageRequest,parameterMap);
		logger.debug(baseResponse.toString());
	}
		
	@Test
	public void profitCalculate() throws Exception {
		ProfitCalculateRequest calculateRequest =  new ProfitCalculateRequest();
		calculateRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		calculateRequest.setAmount(50000d);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/profitCalculate",this.getToken(),BaseResponse.class,calculateRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品列表
	 * @throws Exception
	 */
	@Test
	public void testProductPageList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(20);
		pageRequest.setOrder(0);
		pageRequest.setSort(4);
		Map<String, String> parameterMap = new HashMap<String, String>();
//		parameterMap.put("orgCode", "OPEN_XIAONIUZAIXIAN_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productPageList",this.getToken(),ProductPageListResponse.class,pageRequest,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品分类列表
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * @throws Exception
	 */
	@Test
	public void testproductClassifyPageList() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPageList",this.getToken(),ProductPageListResponse.class,productCfyPgListRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 产品分类统计（根据产品分类表分类 ）
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * @throws Exception
	 */
	@Test
	public void testproductClassifyStatistics() throws Exception {
		ProductStatisticsRequest productStatisticsRequest = new ProductStatisticsRequest();
//		productStatisticsRequest.setCateIdList("801");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyStatistics",this.getToken(),ProductStatisticsResponse.class,productStatisticsRequest);
		logger.info(baseResponse.toString());
	}
	
	/**
	 * 理财-产品详情
	 * @throws Exception
	 */
	@Test
	public void testProductDetail() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "BD77BF079DEE477D806C63A7BC5B6E0D");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productDetail",this.getToken(),ProductDetailResponse.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品分享
	 * @throws Exception
	 */
	@Test
	public void testProductShare() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/share",this.getToken(),CimProductExtends.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐
	 * @throws Exception
	 */
	@Test
	public void testProductRecommend() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommend",this.getToken(),null,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐取消
	 * @throws Exception
	 */
	@Test
	public void testProductCancelRecommend() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/cancelRecommend",this.getToken(),null,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐选择列表
	 * @throws Exception
	 */
	@Test
	public void testrecommendChooseList() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
//		parameterMap.put("searchValue", "成国峰");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommendChooseList",this.getToken(),ProductRecommendChooseResponse.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品选择推荐
	 * @throws Exception
	 */
	@Test
	public void testrecommendByChoose() throws Exception {
		ProductRecommendByChooseRequest productRecommendByChooseRequest =  new ProductRecommendByChooseRequest();
		productRecommendByChooseRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		productRecommendByChooseRequest.setUserIdString("822b71784d6f497cb891626fac538a14,5c32ce9e27ef4750b5828ef45c5a8d64");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommendByChoose",this.getToken(),null,productRecommendByChooseRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财师推荐的产品列表
	 * @throws Exception
	 */
	@Test
	public void testRecdProductPageList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(5);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recdProductPageList",this.getToken(),ProductPageListResponse.class,pageRequest);
		logger.info(baseResponse.toString());
	}
	
	/**
	 * 产品筛选条件
	 * @throws Exception
	 */
	@Test
	public void testproductHead() throws Exception {
		ProductScreenRequest productScreenRequest = new ProductScreenRequest();
		//productScreenRequest.setSecurityLevel(">=4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productHead",this.getToken(),Map.class,productScreenRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 查询筛选产品
	 * @throws Exception
	 */
	@Test
	public void testProductScreenPageList() throws Exception {
		ProductScreenRequest pageRequest = new ProductScreenRequest();
		/*pageRequest.setBackground("民营");
		pageRequest.setPlatform("OPEN_XIAONIUZAIXIAN_WEB");
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(20);
		pageRequest.setProductDeadLine("30,60");
		pageRequest.setYearProfit("3,9");*/
		//pageRequest.setSecurityLevel(">=4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/screenPageList",this.getToken(),ProductPageListResponse.class,pageRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 产品投资列表
	 * @throws Exception
	 */
	@Test
	public void testProductInvestList() throws Exception {
		ProductInvestRequest pageRequest =new ProductInvestRequest();
		pageRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productInvestList",this.getToken(),ProductPageListResponse.class,pageRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 全部标的
	 * @throws Exception
	 */
	@Test
	public void testProductTypeList() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productTypeList/2.0.1",this.getToken(),ProductStatisticsResponse.class,productCfyPgListRequest);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyStatistics201() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyStatistics/2.0.1",this.getToken(),ProductStatisticsResponse.class,productCfyPgListRequest);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyPageList201() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(3);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPageList/2.0.1",this.getToken(),ProductPageListResponse.class,productCfyPgListRequest);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyPreference() throws Exception {
		ProductStatisticsRequest productStatisticsRequest = new ProductStatisticsRequest();
		//productStatisticsRequest.setCateIdList("3,4,5");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPreference/2.0.1",this.getToken(),ProductStatisticsPreferenceResponse.class,productStatisticsRequest);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testHotRecommendProductListTop() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/hotRecommendProductListTop/2.0.1",this.getToken(),new ArrayList<ProductPageListResponse>().getClass());
		logger.debug(baseResponse.toString());
	}
	
}
