package com.eshop4j.test.api;

import org.junit.Test;

import com.eshop4j.api.request.news.NetNewsTypeRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;

public class NetLoanNewsControllerTest extends BaseTest{
	
	@Test
	public void testhomeList() throws Exception {
		NetNewsTypeRequest request = new NetNewsTypeRequest();
		request.setType("4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/netLoanNews/homeList",null,Object.class,request);
		logger.debug(baseResponse.toString());
	}
}
