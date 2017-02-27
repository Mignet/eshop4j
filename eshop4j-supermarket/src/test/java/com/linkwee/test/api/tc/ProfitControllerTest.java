package com.linkwee.test.api.tc;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.tc.CfplannerProfitRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;

public class ProfitControllerTest extends BaseTest{

	
	@Test
	public void cfplannerProfitTotalTest() throws Exception{
		
		CfplannerProfitRequest profitRequest =  new CfplannerProfitRequest();
		//profitRequest.setDateType(1);
		//profitRequest.setDate(new Date());
		
		System.out.println(JSON.toJSONString(profitRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/profit/cfplannerProfitTotal",this.getToken(),BaseResponse.class,profitRequest);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void cfplannerProfitItemTest() throws Exception{
		String token = this.getToken();
		CfplannerProfitRequest profitRequest =  new CfplannerProfitRequest();
		profitRequest.setDateType(1);
		profitRequest.setDate("2016-07-29");
		profitRequest.setProfitType(1001);
		
		System.out.println(JSON.toJSONString(profitRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/profit/cfplannerProfitItem",token,BaseResponse.class,profitRequest);
		logger.debug(baseResponse.toString());
	/*	
		baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/profit/cfplannerProfitItemTotal",token,BaseResponse.class,profitRequest);
		logger.debug(baseResponse.toString());
		
		
		baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/profit/cfplannerProfitTypes",token,BaseResponse.class,null);
		logger.debug(baseResponse.toString());*/
	}
	
	
	
}
