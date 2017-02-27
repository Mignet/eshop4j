package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.crm.ShareUserRequest;
import com.eshop4j.api.request.crm.WeiXinMsgRequest;
import com.eshop4j.api.response.mc.ClassroomPageListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;

public class WeiXinMsgControllerTest extends BaseTest{
	
	@Test
	public void testWeiXin() throws Exception {
		WeiXinMsgRequest req = new WeiXinMsgRequest();
		req.setUseId("2895bf4b85b74a6db14a5ef9d010a174");
		req.setUseType(1);
		
		//报单审核未通过
		req.setTemkey(SysConfigConstant.AUDIT_NOT_PASSED);
		req.setAuditTime("2016-12-02 10:29:40");
		req.setReason("报单重复");//未通过原因
//		req.setReason("成功");//通过结果
		req.setProductName("张三购买100元的《安心牛D20160913-42539》");
		
		//等级变更通知
//		req.setTemkey(SysConfigConstant.GRADE_CHANGE);
//		req.setUserName("巡航三");//用户姓名
//		req.setChangeType("晋升为V2金牌理财师");
		
//		req.setTemkey(SysConfigConstant.OPEN_THIRD_ACCOUNT_SUCCESS);
//		req.setPlatformName("小牛在线");//平台名称
//		req.setOpenTime("2016-12-02 10:29:40");
		//开设时间
		
//		req.setTemkey(SysConfigConstant.INVITATION_REGISTER_SUCCESS);//注册成功通知
//		req.setRecommendMobile("13871111256");
		
//		req.setTemkey("register_success");//注册成功通知
		/*req.setTemkey("investment_success");//投资成功通知
		req.setProductName("月月牛2016-11-23期");
		req.setReturnRate("12.12%");
		req.setTerm("120天");
		req.setAmount("10000.00元");*/
		
		//CimProductRepaymentRecordServiceImpl
//		req.setTemkey(SysConfigConstant.PAYMENT_REMINDER_ACTIVE);//回款提醒(产品剩余3天到期回款) or 回款提醒
//		req.setPaymentNumber("S8XT120161202102940324");
//		req.setPaymentDate("2016-12-02 10:29:40");
//		req.setCustomer("新闻里");//（姓名+手机尾号xxxx）
//		req.setAmount("10088.0元");
//		req.setPlatformName("五星财富");
//		req.setProductName("天尊一号");
		
//		req.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_LEADER);//到账提醒 猎财
//		req.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION);//到账提醒 佣金
//		req.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_RECOMMEND);//到账提醒 推荐
//		req.setArrivalTime("2016-12-02 10:29:40");//到账时间
//		req.setArrivalAmount("10088.0元");//到账金额
//		req.setArrivalDetail("2016年12月到账佣金");//到账详情
		
		
		
//		req.setTemkey("recommend_success");//推荐成功通知
//		req.setRecommendPerson("张三");
//		req.setRecommendedPerson("李四");
//		req.setProductName("月月牛12012");
//		weiXinMsgService.sendWeiXinMsg(req);
		
//		req.setTemkey(SysConfigConstant.WITHDRAWALS_ACCOUNT);//提现到账通知
//		req.setWithdrawTime("2016-12-01 10:46:28");
//		req.setNoticeTime("2016-12-01 15:22:40");
//		req.setAccount("招商银行尾号8232");
//		req.setWithdrawAmount("10000元");
		
		/*req.setTemkey(SysConfigConstant.ACTIVITY_COMPLETE);//活动完成通知
		req.setActivityName("元旦乐乐欢");
		req.setActivityAmount("10000元");*/
		
		/*req.setTemkey(SysConfigConstant.APPLY_WITHDRAWALS_ACCOUNT);//提现申请通知
		req.setNickName("王聚四");
		req.setWithdrawTime("2016-12-01 15:22:40");
		req.setWithdrawAmount("10000元");
		req.setWithdrawType("T呗");*/
		
		
/*		req.setTemkey(SysConfigConstant.BIND_SUCCESS);//绑定成功通知bind_success
		req.setBindName("王聚四");//绑定姓名
		req.setBindAccount("18603027024");//绑定账户
		req.setBindTime("2016-12-01 15:22:40");//绑定时间
*/		
	/*	req.setTemkey(SysConfigConstant.RELEASE_RELATIONSHIP);//解绑通知
		req.setUserName("王聚四");//用户名
		req.setUserType("理财师");//用户类型
		req.setReleaseTime("2016-12-01 15:22:40");//解绑时间
*/		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/weixin/sendWeiXinMsgCommon",this.getToken(),ClassroomPageListResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
	/**
	 * 珠海活动分享用户
	 */
	@Test
	public void testSaveShareUser() throws Exception {
		ShareUserRequest params = new ShareUserRequest();
		params.setUserName("张三");
		params.setActivityType("1");
		params.setMobile("18603027024");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/user/saveShareUser",this.getToken(),Map.class,params);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 保存用户微信openId
	 */
	@Test
	public void testSaveWeiXinOpenId() throws Exception {
		WeiXinMsgRequest params = new WeiXinMsgRequest();
		params.setUserName("张三");
		params.setIsPush("2");
		params.setCode("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/user/saveWeiXinOpenId",this.getToken(),Map.class,params);
		logger.debug(baseResponse.toString());
	}
//	
//	/**
//	 * 获取微信应用ID和应用密钥
//	 */
//	@Test
//	public void getAppIdandAppSecret() throws Exception {
//		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.PRERELEASE),"/api/user/getAppIdandAppSecret",this.getToken(),Map.class,null);
//		logger.debug(baseResponse.toString());
//	}

}
