package com.eshop4j.test.activity;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.crm.InvotationRequest;
import com.eshop4j.api.request.crm.WechatShareRequest;
import com.eshop4j.api.response.activity.ScratchDetailResponse;
import com.eshop4j.api.response.crm.InvotateListResponse;
import com.eshop4j.api.response.crm.WechatShareResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;

public class InvestScratchControllerTest extends BaseTest{
	
	@Test
	public void testWinningRecord() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/scratch/records",this.getToken(),Map.class);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testScratchDetail() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/scratch/detail",this.getToken(),ScratchDetailResponse.class);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testScratchWinning() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/scratch/winning",this.getToken(),BaseResponse.class);		
		logger.debug(baseResponse.toString());
	}
	
}
