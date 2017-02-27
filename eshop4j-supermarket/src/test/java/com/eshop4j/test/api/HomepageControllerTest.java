package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.act.AdvPageListRequest;
import com.eshop4j.api.request.act.AdvertisementPageListRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.model.news.SmAdvertisement;

public class HomepageControllerTest extends BaseTest{

	/**
	 * 首页广告条
	 * @throws Exception
	 */
	@Test
	public void testBanners() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/banners",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testOpeningAdv() throws Exception {
		AdvertisementPageListRequest req = new AdvertisementPageListRequest();
		req.setAdvType("app_opening");
		req.setAppType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/opening",this.getToken(),SmAdvertisement.class);
		logger.debug(baseResponse.toString());
	}
	/**
	 * 根据端口（理财师，投资端）和广告位置查询配置广告
	 * @throws Exception
	 */
	@Test
	public void testAdvs() throws Exception {
		AdvPageListRequest req = new AdvPageListRequest();
		req.setAdvPlacement("pc_idx_middle");
		req.setAppType(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/advs",null,SmAdvertisement.class,req);
		logger.debug(baseResponse.toString());
	}
	
}
