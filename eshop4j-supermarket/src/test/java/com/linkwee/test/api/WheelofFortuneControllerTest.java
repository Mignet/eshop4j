package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.api.request.FeedbackRequest;
import com.linkwee.api.request.NewsDetailRequest;
import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.api.request.SysConfigRequest;
import com.linkwee.api.response.NewsDtlResponse;
import com.linkwee.api.response.NewsListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.response.AppVersionResponse;
import com.linkwee.web.response.DownloadAppListResponse;

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
