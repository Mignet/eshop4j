package com.linkwee.test.api.crm.invitation;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.WechatShareRequest;
import com.linkwee.api.response.crm.InvotateListResponse;
import com.linkwee.api.response.crm.WechatShareResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.request.DataStatisticsRequest;

public class InvitationControllerTest extends BaseTest{
	
	@Test
	public void testCustomerHomepage() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/customer/homepage",this.getToken(),Map.class);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testCfpHomepage() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/cfp/homepage",this.getToken(),Map.class);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testMailList() throws Exception{
		InvotationRequest invotationRequest = new InvotationRequest();
		invotationRequest.setMobiles("18682243486,18111111128,15071392245");
		invotationRequest.setNames("z张三,历史,zhaoliu");
		invotationRequest.setType(1);
		//invotationRequest.setType(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/invitation/maillist",this.getToken(),Map.class,invotationRequest);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testInvestorStatistics() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/investor/statistics",this.getToken(),Map.class);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testInvestorPageList() throws Exception{
		InvotationRequest invotationRequest = new InvotationRequest();
		invotationRequest.setPageSize(5);
		invotationRequest.setPageIndex(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/investor/pageList",this.getToken(),InvotateListResponse.class,invotationRequest);		
		logger.debug(baseResponse.toString());
	}
		
	@Test
	public void testWechatShare() throws Exception{
		WechatShareRequest wechatShareRequest = new WechatShareRequest();
		wechatShareRequest.setUrl("http://www.baidu.com");		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/wechat/share",this.getToken(),WechatShareResponse.class,wechatShareRequest);		
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void dataStatistics() throws Exception{
		DataStatisticsRequest dataStatisticsRequest = new DataStatisticsRequest();
		//dataStatisticsRequest.setTag();
		dataStatisticsRequest.setStartTime(DateUtils.parse("2016-09-01 00:00:00"));
		dataStatisticsRequest.setEndTime(DateUtils.parse("2016-09-20 23:00:00"));		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/data/statistics",this.getToken(),Map.class,dataStatisticsRequest);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 邀请客户记录
	 */
	@Test
	public void testCustomerRecord() throws Exception{
		InvotationRequest req = new InvotationRequest();
		req.setPageIndex(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/customerRecord",this.getToken(),Map.class,req);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 邀请理财师记录
	 */
	@Test
	public void testCfplannerRecord() throws Exception{
		InvotationRequest req = new InvotationRequest();
		req.setPageIndex(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/cfplannerRecord",this.getToken(),Map.class,req);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 邀请理财师记录
	 */
	@Test
	public void testcustomerRecordStatistics() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/customerRecordStatistics",this.getToken(),null);		
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 邀请理财师记录
	 */
	@Test
	public void testcfplannerRecordStatistics() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/invitation/cfplannerRecordStatistics",this.getToken(),null);		
		logger.debug(baseResponse.toString());
	}
	
	
	
}
