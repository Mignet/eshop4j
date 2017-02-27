package com.eshop4j.test.api;

import org.junit.Test;

import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.response.AppVersionResponse;
import com.eshop4j.web.response.DownloadAppListResponse;

public class SystemControllerTest extends BaseTest{
		
	/**
	 * 查询版本情况
	 * @throws Exception
	 */
	@Test
	public void testAppversion() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/system/appVersion", null, AppVersionResponse.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 查询最新可供下载的版本
	 * @throws Exception
	 */
	@Test
	public void downloadAppList() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/system/downloadAppList", null, DownloadAppListResponse.class);
		logger.debug(baseResponse.toString());
	}
}
