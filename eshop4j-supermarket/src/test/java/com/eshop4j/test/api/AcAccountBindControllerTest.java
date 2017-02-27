package com.eshop4j.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.acc.InitPayPwdRequest;
import com.eshop4j.api.request.acc.InputVcodeRequest;
import com.eshop4j.api.request.acc.MonthProfixDetailRequest;
import com.eshop4j.api.request.acc.MyAccountPageListRequest;
import com.eshop4j.api.request.acc.ResetPayPwdRequest;
import com.eshop4j.api.request.acc.UserWithdrawRequest;
import com.eshop4j.api.response.acc.AcAccountBindReponse;
import com.eshop4j.api.response.acc.AcAccountTypeReponse;
import com.eshop4j.api.response.acc.MyAccount;
import com.eshop4j.api.response.acc.MyAccountPageListResponse;
import com.eshop4j.api.response.acc.ProvinceInfoResponse;
import com.eshop4j.api.response.acc.WithdrawApplyPageListResponse;
import com.eshop4j.api.response.acc.WithdrawBankCardResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.model.acc.AcCityList;
import com.eshop4j.web.model.acc.AcProvinceList;

public class AcAccountBindControllerTest extends BaseTest{
	
	
	
	@Test//重置支付密码
	public void resetPayPwd() throws Exception {
		ResetPayPwdRequest ver = new ResetPayPwdRequest();
		ver.setResetPayPwdToken("y546q90h6rk3nflx");
		ver.setPwd("888888");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/resetPayPwd",this.getToken(),AcAccountTypeReponse.class,ver);
		logger.debug(baseResponse.toString());
	}
	@Test//重置支付密码-输入手机验证(inputVcode)
	public void inputVcode() throws Exception {
		InputVcodeRequest ver = new InputVcodeRequest();
		ver.setVcode("3110");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/inputVcode",this.getToken(),AcAccountTypeReponse.class,ver);
		logger.debug(baseResponse.toString());
	}
	
	@Test//重置支付密码-点击手机发送验证码(sendVcode)
	public void sendVcode() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/sendVcode",this.getToken(),AcAccountTypeReponse.class,null);
		logger.debug(baseResponse.toString());
	}
	
	@Test//累计提现金额
	public void getWithdrawSummary() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getWithdrawSummary",this.getToken(),AcAccountTypeReponse.class,null);
		logger.debug(baseResponse.toString());
	}
	
	@Test//账户类型 
	public void queryAccountType() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryAccountType",this.getToken(),AcAccountTypeReponse.class,null);
		logger.debug(baseResponse.toString());
	}
	
	@Test//提现记录明细  
	public void queryWithdrawLog() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/queryWithdrawLog",this.getToken(),WithdrawApplyPageListResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
	@Test//提现请求  
	public void testuserWithdrawRequest() throws Exception {
		UserWithdrawRequest req = new UserWithdrawRequest();
		req.setUserName("陈佳良");
		req.setIdCard("44142219870510263X");
		req.setAmount("0.1");
		req.setBankCard("6226097805198232");
		req.setBankCode("CMB");
		req.setBankName("招商银行");
		req.setCity("深圳");
		req.setKaihuhang("南山科技园支行");
		req.setUserType(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/userWithdrawRequest",this.getToken(),WithdrawBankCardResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test//提现查询银行卡信息
	public void testgetWithdrawBankCard() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/getWithdrawBankCard",this.getToken(),WithdrawBankCardResponse.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	@Test//账户明细  
	public void testMyaccountDetail() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setUserType("2");
//		req.setTypeValue("2");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/myaccountDetail/pageList",this.getToken(),MyAccountPageListResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test//我的账户
	public void testMyAccount() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/myaccount",this.getToken(),MyAccount.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	@Test//查询绑卡信息
	public void testUserBindCard() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getUserBindCard",this.getToken(),AcAccountBindReponse.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	
	@Test//检查用户是否设置支付密码
	public void verifyPayPwdState() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/verifyPayPwdState",this.getToken(),Map.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	
	
	@Test//检查用户是否设置支付密码
	public void initPayPwd() throws Exception {
		InitPayPwdRequest req = new InitPayPwdRequest();
		req.setPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/initPayPwd",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
	@Test//是否已经绑卡(个人设置)
	public void testPersoncenterSetting() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/personcenter/setting",this.getToken(),AcProvinceList.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	@Test//查省份
	public void testQueryAllProvince() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryAllProvince",this.getToken(),ProvinceInfoResponse.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	@Test//查城市
	public void testQueryCityByProvince() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("provinceId", "53");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/queryCityByProvince",this.getToken(),AcCityList.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	@Test//查询支付机构支持的银行
	public void testQueryAllBank() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/queryAllBank",this.getToken(),ProvinceInfoResponse.class,new Object[]{});
		logger.debug(baseResponse.toString());
	}
	
	@Test//账户余额
	public void testAccountBalance() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/accountBalance",this.getToken(),null);
		logger.debug(baseResponse.toString());
	}
	
	@Test// 账户余额月份收益总计列表
	public void testmonthProfixTotalList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixTotalList",this.getToken(),null);
		logger.debug(baseResponse.toString());
	}
	
	@Test//月度收益统计
	public void testmonthProfixStatistics() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2016-11");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixStatistics",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test//月度收益明细列表
	public void testmonthProfixDetailList() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2016-12");
		req.setProfixType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixDetailList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
}
