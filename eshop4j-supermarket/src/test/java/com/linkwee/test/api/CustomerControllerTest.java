package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.crm.CustomerDetailRequest;
import com.linkwee.api.request.crm.MycustomersRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;

public class CustomerControllerTest extends BaseTest{

	
	/**
	 * 客户列表
	 */
	@Test
	public void testMycustomers() throws Exception {
		MycustomersRequest req = new MycustomersRequest();
		req.setSort(3);
		req.setOrder(1);
		req.setPageSize(10);
		req.setPageIndex(1);
		//req.setCustomerType("1");
		//req.setName("18576651144");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/mycustomers/pageList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 客户首页
	 */
	@Test
	public void testHomePage() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/homepage",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 客户详情
	 */
	@Test
	public void testCustomerDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("83889096d52a4d0d9c6ca388d6c12ff4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/detail",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 设置重要客户
	 */
	@Test
	public void testaddImportantDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("0891f28a9886436d9313ea0af073c7b8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/important/add",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	/**
	 * 设置重要客户
	 */
	@Test
	public void testRemoveImportantDetail() throws Exception {
		CustomerDetailRequest req = new CustomerDetailRequest();
		req.setUserId("0891f28a9886436d9313ea0af073c7b8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/customer/important/remove",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
}
