package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.activity.response.BigWheelDrawResponse;
import com.eshop4j.api.request.FeedbackRequest;
import com.eshop4j.api.request.NewsDetailRequest;
import com.eshop4j.api.request.NewsPageListRequest;
import com.eshop4j.api.request.SysConfigRequest;
import com.eshop4j.api.response.NewsDtlResponse;
import com.eshop4j.api.response.NewsListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.model.ActWheelWinningRecord;
import com.eshop4j.web.model.SysConfig;
import com.eshop4j.web.response.AppVersionResponse;
import com.eshop4j.web.response.DownloadAppListResponse;

public class WheelofFortuneControllerTest extends BaseTest{
	
	/**
	 * 抽奖次数
	 * @throws Exception
	 */
	@Test
	public void testPrizeTimes() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/wheel/prize/times",this.getToken(),Map.class);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 抽一次
	 * @throws Exception
	 */
	@Test
	public void testPrizeOne() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/wheel/prize/one",this.getToken(),BigWheelDrawResponse.class);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 抽十次
	 * @throws Exception
	 */
	@Test
	public void testPrizeTen() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/activity/wheel/prize/ten",this.getToken(),BigWheelDrawResponse.class);		
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
	
}
