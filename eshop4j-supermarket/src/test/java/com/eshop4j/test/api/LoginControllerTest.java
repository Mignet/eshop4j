package com.eshop4j.test.api;

import org.apache.axis.encoding.Base64;
import org.junit.Test;

import com.eshop4j.api.request.crm.DeviceInfoRequest;
import com.eshop4j.api.request.crm.JinfuLoginRequest;
import com.eshop4j.api.request.crm.LoginRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.xoss.util.MD5;


public class LoginControllerTest extends BaseTest{
	@Test
	public void testLogin() throws Exception {
		LoginRequest params = new LoginRequest();
		params.setMobile("18111111153");
		params.setPassword("123456");
		params.setAccessUrl("aaa");
		params.setFromUrl("bbb");
		
		DeviceInfoRequest params2 = new DeviceInfoRequest();
		params2.setDeviceId("909E8EDD-59D8-46EA-A80E-62790499BF32");
		params2.setDeviceToken("666666");
		params2.setDeviceModel("iPhone");
		params2.setSystemVersion("9.0");
		params2.setResolution("640x1136");
		BaseResponse baseResponse =  TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/login",null,BaseResponse.class,params,params2);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testLogout() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/logout",this.getToken(),Object.class);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testJinfuLogin() throws Exception {
		JinfuLoginRequest params = new JinfuLoginRequest();
		String mobile = "18576651144";  
		String key = Base64.encode(mobile.getBytes());
		String code = MD5.crypt(MD5.crypt("18576651144") + "linghuikeji@tbei2016");
		params.setKey(key);
		params.setCode(code);
		DeviceInfoRequest params2 = new DeviceInfoRequest();
		params2.setDeviceId("909E8EDD-59D8-46EA-A80E-62790499BF32");
		params2.setDeviceToken("666666");
		params2.setDeviceModel("iPhone");
		params2.setSystemVersion("9.0");
		params2.setResolution("640x1136");
		BaseResponse baseResponse =  TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/jinfuLogin",null,BaseResponse.class,params,params2);
		logger.debug(baseResponse.toString());
	}
	
	
}
