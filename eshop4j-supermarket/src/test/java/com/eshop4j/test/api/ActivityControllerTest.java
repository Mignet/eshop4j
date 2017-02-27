package com.eshop4j.test.api;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.eshop4j.api.activity.request.BaseActivityRequest;
import com.eshop4j.api.activity.response.ActivityDrawResponse;
import com.eshop4j.api.activity.response.BigWheelDrawResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.web.model.ActWheelWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.request.ActivityPageListRequest;
import com.eshop4j.web.service.CimOrginfoService;

public class ActivityControllerTest extends BaseTest{

	@Resource
	private CimOrginfoService cimOrginfoService;
		
	/**
	 * 精彩活动
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void testActityList() throws Exception {
		//App_investor_android
		ActivityPageListRequest req = new ActivityPageListRequest();
		//req.setAppType("1");//活动类别:0公共，1理财师，2投资者
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/activity/pageList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	//eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0Njg2NjI4NDM3NTYsInN1YiI6IjVlNTQzZDNhZGJjNDRjZTQ4OTFlNTAwOGMzY2I3NTNiIiwiaXNzIjoiaHR0cHM6XC9cL3d3dy5saW5rd2VlLmNvbSJ9.L-IrUa4xrXwBXP9iwtW_gQ52tR543LIks7HEB5rJVpg
	
	@Test
	public void testPlatformActity() throws Exception {
		//App_investor_android
		ActivityPageListRequest req = new ActivityPageListRequest();
		//req.setAppType("1");//活动类别:0公共，1理财师，2投资者
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/activity/platform",this.getToken(),ActivityList.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testPlatformActityPageList() throws Exception {
		//App_investor_android
		ActivityPageListRequest req = new ActivityPageListRequest();
		req.setActivityPlatform("猎财大师");
		//req.setAppType("1");//活动类别:0公共，1理财师，2投资者
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/activity/platform/pageList",this.getToken(),ActivityList.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testPlatformActityList() throws Exception {
		//App_investor_android
		ActivityPageListRequest req = new ActivityPageListRequest();
		req.setActivityPlatform("猎财大师");
		//req.setAppType("1");//活动类别:0公共，1理财师，2投资者
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/activity/platform/list",this.getToken(),ActivityList.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/activity/readed",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 抽奖次数
	 * @throws Exception
	 */
	@Test
	public void testPrizeTimes() throws Exception {
		BaseActivityRequest baseActivityRequest = new BaseActivityRequest();
		baseActivityRequest.setActivityName("大转盘");
		baseActivityRequest.setActivityPlatform("T呗");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/base/prize/times",this.getToken(),Map.class,baseActivityRequest);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 抽一次
	 * @throws Exception
	 */
	@Test
	public void testPrizeOne() throws Exception {
		BaseActivityRequest baseActivityRequest = new BaseActivityRequest();
		baseActivityRequest.setActivityName("龙虎榜");
		baseActivityRequest.setActivityPlatform("T呗");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/base/prize/one",this.getToken(),ActivityDrawResponse.class,baseActivityRequest);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testPrizeOneRecord() throws Exception {
		BaseActivityRequest baseActivityRequest = new BaseActivityRequest();
		baseActivityRequest.setActivityName("大转盘");
		baseActivityRequest.setActivityPlatform("T呗");
		baseActivityRequest.setConditionCase(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/base/prize/one/record",this.getToken(),ActivityDrawResponse.class,baseActivityRequest);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testPrizeTen() throws Exception {
		BaseActivityRequest baseActivityRequest = new BaseActivityRequest();
		baseActivityRequest.setActivityName("大转盘");
		baseActivityRequest.setActivityPlatform("T呗");
		baseActivityRequest.setPrizeStyle(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/base/prize/multi",this.getToken(),ActivityDrawResponse.class,baseActivityRequest);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testPrizeTenRecord() throws Exception {
		BaseActivityRequest baseActivityRequest = new BaseActivityRequest();
		baseActivityRequest.setActivityName("大转盘");
		baseActivityRequest.setActivityPlatform("T呗");
		baseActivityRequest.setConditionCase(2);
		baseActivityRequest.setPrizeStyle(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/base/prize/multi/record",this.getToken(),ActivityDrawResponse.class,baseActivityRequest);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 所有抽奖记录
	 * @throws Exception
	 */
	@Test
	public void testPrizeRecordAll() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/wheel/prize/record/all",this.getToken(),ActWheelWinningRecord.class);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 用户抽奖记录
	 * @throws Exception
	 */
	@Test
	public void testPrizeRecordUser() throws Exception {
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/wheel/prize/record/user",this.getToken(),ActWheelWinningRecord.class,paginatorRequest);		
		logger.debug(baseResponse.toString());
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.parseInt("30")/30);
		System.out.println(Integer.parseInt("10")/30);
		System.out.println(Integer.parseInt("180")/30);
		System.out.println(Integer.parseInt("181")/30);
		System.out.println(Integer.parseInt("365")/30);
		System.out.println(Integer.parseInt("360")/30);
		System.out.println(Integer.parseInt("359")/30);
	}
	
	@Test
	public void testDemo() throws Exception {
		String str = "1,9";
		System.out.println(str.split(",").length);
		System.out.println(str.split(",")[0]);
		System.out.println(str.split(",")[1]);
		System.out.println(str.contains(","));
		System.out.println(str.contains("a"));
		System.out.println(str.contains(" "));
		System.out.println(str.contains(""));
	}
}
