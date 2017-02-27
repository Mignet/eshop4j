package com.eshop4j.test.api;

import org.junit.Test;

import com.eshop4j.api.request.SysConfigRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.web.model.SysConfig;

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
