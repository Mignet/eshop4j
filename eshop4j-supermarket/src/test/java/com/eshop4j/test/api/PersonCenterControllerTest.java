package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.crm.IconRequest;
import com.eshop4j.api.request.crm.PartnerDetailRequest;
import com.eshop4j.api.request.crm.PartnerMonthSaleRequest;
import com.eshop4j.api.request.crm.PartnerPageListRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;

public class PersonCenterControllerTest extends BaseTest{

	
	/**
	 * 团队列表
	 */
	@Test
	public void testPartnerPageList() throws Exception {
		PartnerPageListRequest req = new PartnerPageListRequest();
		//req.setName("18576651144");
		req.setSort(2);
		req.setOrder(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/pageList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 团队统计
	 */
	@Test
	public void testPartner() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	
	/**
	 * 个人中心首页
	 */
	@Test
	public void testPersoncenterHome() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/homepage",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 团队成员详情
	 */
	@Test
	public void testPartnerDetail() throws Exception {
		PartnerDetailRequest req = new PartnerDetailRequest();
		req.setUserId("dfaedfbbf40b44e0a6ad2659471569b4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/detail",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 团队成员销售记录
	 */
	@Test
	public void testPartnerSalesRecordPageList() throws Exception {
		PartnerDetailRequest req = new PartnerDetailRequest();
		req.setUserId("fcede0bfc4774865bd34f57ca275f6c8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/salesRecordList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 上传头像
	 * @throws Exception
	 */
	@Test
	public void testUploadIcon() throws Exception{
		IconRequest iconRequest = new IconRequest();
		iconRequest.setImage("4cb3bb1dd4a689c9f843e417d7d683a7");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/personcenter/icon",this.getToken(),Object.class,iconRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售统计
	 */
	@Test
	public void testpartnerMonthSaleStatistics() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2016-12-01");
		req.setDateType("4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleStatistics",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售列表
	 */
	@Test
	public void testpartnerMonthSaleList() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2016-12-01");
		req.setDateType("4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
}
