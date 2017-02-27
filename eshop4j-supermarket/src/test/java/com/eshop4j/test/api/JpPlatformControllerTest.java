package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.web.request.jpressPlatform.PlatformRequest;

public class JpPlatformControllerTest extends BaseTest{

	
	/**
	 * 网贷平台 查询条件
	 */
	@Test
	public void testPlatformSearchCondition() throws Exception {
		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/jpress/jpplatform/platformHead",null,Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 网贷平台列表
	 */
	@Test
	public void testPlatformPageList() throws Exception {
		PlatformRequest req = new PlatformRequest();
		req.setPlatformName("");
		req.setPageSize(10);
		req.setPageIndex(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/jpress/jpplatform/pageList",null,Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 客户详情
	 *//*
	@Test
	public void testCustomerDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("83889096d52a4d0d9c6ca388d6c12ff4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/detail",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	*//**
	 * 设置重要客户
	 *//*
	@Test
	public void testaddImportantDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("0891f28a9886436d9313ea0af073c7b8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/important/add",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	*//**
	 * 设置重要客户
	 *//*
	@Test
	public void testRemoveImportantDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("0891f28a9886436d9313ea0af073c7b8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/important/remove",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}*/
	
	
}
