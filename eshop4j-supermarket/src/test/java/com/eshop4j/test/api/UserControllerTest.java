package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.acc.TwoPwdRequest;
import com.eshop4j.api.request.crm.CheckMobileRequest;
import com.eshop4j.api.request.crm.EasemobIdRequest;
import com.eshop4j.api.request.crm.PwdRequest;
import com.eshop4j.api.request.crm.RegisterRequest;
import com.eshop4j.api.request.crm.ResetLoginPwdRequest;
import com.eshop4j.api.response.crm.CheckMobileResponse;
import com.eshop4j.api.response.crm.LoginResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.request.DeviceInfoRequest;
import com.eshop4j.web.request.SendVcodeRequest;

public class UserControllerTest extends BaseTest{

	/**
	 * 发送验证码
	 */
	@Test
	public void testSendVcode() throws Exception {
		SendVcodeRequest params = new SendVcodeRequest();
		params.setMobile("18111111213");
		params.setType(MsgModuleEnum.REGISTER.getKey());
		params.setVcode("g5px");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/sendVcode",null,null,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 注册
	 */
	@Test
	public void testRegister() throws Exception {
		RegisterRequest params = new RegisterRequest();
		params.setMobile("18111111213");
		params.setPassword("123456");
		params.setRecommendCode("18111111212");
		params.setVcode("3457");
		params.setFromUrl("aaa");
		params.setAccessUrl("bbb");
		params.setSaleOrgCode("6cf4a086b66348a5b49b7a1b4b5c9011");
		DeviceInfoRequest params2 = new DeviceInfoRequest();
		params2.setDeviceId("909E8EDD-59D8-46EA-A80E-62790499BF32");
		params2.setDeviceModel("iPhone");
		params2.setSystemVersion("9.0");
		params2.setResolution("640x1136");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/user/register",null,LoginResponse.class,params,params2);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 获取用户信息
	 */
	@Test
	public void testGetUserInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/getUserInfo",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 根据环信帐号获取用户信息
	 */
	@Test
	public void testGetUserInfoByEasemob() throws Exception {
		EasemobIdRequest req = new EasemobIdRequest();
		req.setEasemobAcct("cfpb99c4c62fe0a4abe879d69b651d3045f");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/getUserInfoByEasemob",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 检查号码是否可以注册
	 */
	@Test
	public void testCheckMobile() throws Exception {
		CheckMobileRequest params = new CheckMobileRequest();
		params.setMobile("18111111176");
		params.setRecommendCode("18111111120");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/checkMobile",null,CheckMobileResponse.class,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 修改登录密码
	 */
	@Test
	public void testModifyLoginPwd() throws Exception {
		TwoPwdRequest params = new TwoPwdRequest();
		params.setOldPwd("654321");
		params.setNewPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/modifyLoginPwd",this.getToken(),null,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 重置登录密码
	 */
	@Test
	public void testResetLoginPwd() throws Exception {
		ResetLoginPwdRequest params = new ResetLoginPwdRequest();
		params.setMobile("18111111120");
		params.setVcode("0027");
		params.setNewPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/resetLoginPwd",null,null,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 校验登录密码
	 */
	@Test
	public void testVerifyLoginPwd() throws Exception {
		PwdRequest params = new PwdRequest();
		params.setPwd("12345622");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/verifyLoginPwd",this.getToken(),Map.class,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 我的理财师
	 * @throws Exception
	 */
	@Test
	public void testMycfp() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/mycfp",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	
}
