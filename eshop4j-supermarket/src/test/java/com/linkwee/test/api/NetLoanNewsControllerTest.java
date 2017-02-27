package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.news.NetNewsTypeRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;

public class NetLoanNewsControllerTest extends BaseTest{
	
	@Test
	public void testhomeList() throws Exception {
		NetNewsTypeRequest request = new NetNewsTypeRequest();
		request.setType("4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/netLoanNews/homeList",null,Object.class,request);
		logger.debug(baseResponse.toString());
	}
}
