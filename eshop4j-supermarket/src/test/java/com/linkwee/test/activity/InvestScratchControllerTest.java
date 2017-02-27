package com.linkwee.test.activity;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.WechatShareRequest;
import com.linkwee.api.response.activity.ScratchDetailResponse;
import com.linkwee.api.response.crm.InvotateListResponse;
import com.linkwee.api.response.crm.WechatShareResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

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
