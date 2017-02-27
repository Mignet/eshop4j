package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.SysConfigRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.web.model.SysConfig;

public class SysConfigControllerTest extends BaseTest{
		
	/**
	 * 根据应用类别查询系统配置
	 * @throws Exception
	 */
	@Test
	public void testSelectByAppType() throws Exception {
		SysConfigRequest sysConfigRequest = new SysConfigRequest();
		sysConfigRequest.setAppType(2);
		sysConfigRequest.setPageIndex(1);
		sysConfigRequest.setPageSize(20);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(), "/sysconfig/listByAppType", this.getToken(), SysConfig.class, sysConfigRequest);
		logger.debug(baseResponse.toString());
	}
}
