package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.response.AppVersionResponse;
import com.linkwee.web.response.DownloadAppListResponse;

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
