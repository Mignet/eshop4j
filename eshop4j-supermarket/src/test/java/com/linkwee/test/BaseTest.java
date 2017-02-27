package com.linkwee.test;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.api.request.crm.DeviceInfoRequest;
import com.linkwee.api.request.crm.LoginRequest;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;


public class BaseTest {
	protected final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static String baseUrl = "http://localhost:8080/rest";
	//private static String baseUrl = "http://10.16.0.205:6810/rest";
	//private static String baseUrl = "http://premarket.toobei.com/rest/";
	//private static String baseUrl = "http://market.toobei.com/rest";
	
	public String getUrl() throws Exception{
		return baseUrl;
	}
	
	public String getUrl(PathEnum pathEnum) throws Exception{
		baseUrl = pathEnum.getValue();
		return pathEnum.getValue();
	}
	
	@SuppressWarnings("unchecked")
	public String getToken() throws Exception{
		//调用登录创建token
		LoginRequest params = new LoginRequest();
//		//正式环境账号
		params.setMobile("15220203390");
		params.setPassword("123456.");
//		params.setMobile("15019447487");
//		params.setPassword("123456.");
		
		DeviceInfoRequest params2 = new DeviceInfoRequest();
		params2.setDeviceId("909E8EDD-59D8-46EA-A80E-62790499BF32");
		params2.setDeviceModel("iPhone");
		params2.setSystemVersion("9.0");
		params2.setResolution("640x1136");
		
		SuccessResponse<Map<String ,String >> baseResponse = (SuccessResponse<Map<String ,String >>) TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/login",null,Map.class,params,params2);
		logger.debug(baseResponse.toString());
		Map<String ,String > map = baseResponse.getData();
		System.out.println("创建TOKEN:"+map.get("token"));
		return map.get("token");
	}
}