package com.eshop4j.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.response.CommonTCSResult;
import com.eshop4j.web.service.SysPushMessageService;
import com.eshop4j.xoss.helper.PushMessageHelper;

public class PushMessageHelperTest extends TestSupport {

    @Resource
    private PushMessageHelper pushMessageHelper;
    @Resource
    private SysPushMessageService sysPushMessageService;

 
   
    @Test
    public void testPushByAppId() throws Exception {
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("msgUrl", "http://preliecai.tophlc.com/pages/notice/message_detail.html?msgId=95");
    	pushMessageHelper.pushByAppId(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSSYSNOTICERELEASE, "公告发送通知1504", "猎财大师APP上线啦1504", customermap);
        end();
    }
    
    
   //@Test
    public void testorgInfoPage() throws Exception {
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("customerId", "444444444444444");
		// 36c00b082d514adaac42a5745d61399f  4391493f2d484b13bf034b023980f9fe  c467410eb66e45efbd1dcc547135462a   4391493f2d484b13bf034b023980f9fe 995e2df93fb3451186088158d95f3268
		//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL,SmsTypeEnum.LCUSTOMERREGIST,"36c00b082d514adaac42a5745d61399f", "提示短暂消失", "异步推送短暂消失，同步推送正常", customermap);
		SysPushMessage pushMessage = new SysPushMessage();
		pushMessage.setModuleId(SmsTypeEnum.LCUSTOMERREGIST.getValue());
		customermap.put("customUrlKey", SmsTypeEnum.LCUSTOMERREGIST.getMsg());
		pushMessage.setUserId("995e2df93fb3451186088158d95f3268");
		pushMessage.setDeviceToken("d998b2a7f9b5b817b425e7b2fc28908d");
		pushMessage.setOsType("ios");
		pushMessage.setTitle("提示短暂消失");
		pushMessage.setContent("异步推送短暂消失，同步推送正常");
		JSONObject jsonObject = new JSONObject(customermap);
		pushMessage.setExtend1(jsonObject.toJSONString());
		pushMessage.setAppType(AppTypeEnum.CHANNEL.getKey());
		CommonTCSResult rlt = pushMessageHelper.pushMessage(pushMessage);
		System.out.println(rlt.getMessage());
        end();
    }
    
    @Test
    public void testAnyPushMsg() throws Exception {
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("customerId", "444444444444444");
		// 4ae43985a736463a892f3db7b998ecd6 c1add0a7c3e2438f853ae270e975d958 1d06a0942eb54adfa6eafabf2f959033
		CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL,SmsTypeEnum.LFEERECEIVED,"1d06a0942eb54adfa6eafabf2f959033", "批量单推标题", "批量单推内容", null,true,PersonalMsgTypeEnum.INVITATIONINV);
		System.out.println(rlt.getMessage());
        end();
    }
    
    
   // @Test
    public void testRenewBatch() throws Exception{
    	start();
    	Page<SysPushMessage> page = new Page<SysPushMessage>(1, 100); // 分批执行
		Map<String, Object> conditions = new HashMap<String, Object>();
		PaginatorResponse<SysPushMessage> msgPage = sysPushMessageService.querySysPushMessageList(page, conditions);
		List<SysPushMessage> list=  msgPage.getDatas();
		
		if(list.size()>0){
			sysPushMessageService.renewBatch(list);
		}
    	
        end();
    }
    
    @Test
    public void testPushMessageListAsyn() throws Exception{
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("customerId", "444444444444444");
		//List<String> userIds = new ArrayList<String>();
		Set<String> userIds = Sets.newHashSet();
		userIds.add("1d06a0942eb54adfa6eafabf2f959033");
		userIds.add("bdaccd0b09f2477e9b1bc005aeeba803");
    	System.out.println(pushMessageHelper.pushMessageListAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSRECEIVESYSREDPAPER,userIds, "批量推送测试标题(理财师收到系统红包)", "亲，金融超市给您发了一批红包", null,true,PersonalMsgTypeEnum.PROJECTINVEST_INV).getMessage());
    	
        end();
    }
    
    @Test
    public void testPushMessageList() throws Exception{
    	start();    	
    	System.out.println(pushMessageHelper.pushMessageList("2b3c310e281e4cec93ad7b5ff2f2cba9android").getMessage());    	
        end();
    }
    
    //@Test
    public void testBatchSavePushMsg() throws Exception{
    	start();

		List<SysPushMessage> list=  new ArrayList<SysPushMessage>();
		SysPushMessage  temp = new SysPushMessage();
		temp.setTitle("test");
		temp.setContent("test");
		temp.setUserId("id");				
		temp.setDeviceToken("deviceToken");
		temp.setAppType(1);
		temp.setOsType("ios");
		temp.setModuleId("register");
		temp.setHandle(1);// 1 已处理 0 未处理
		temp.setStatus(1);// 已发送
		temp.setCreateTime(new Date());
		temp.setUpdateTime(new Date());
		SysPushMessage  temp1 = new SysPushMessage();
		temp1.setTitle("test1");
		temp1.setContent("test1");
		temp1.setUserId("id1");				
		temp1.setDeviceToken("deviceToken1");
		temp1.setAppType(1);
		temp1.setOsType("ios");
		temp1.setModuleId("register1");
		temp1.setHandle(1);// 1 已处理 0 未处理
		temp1.setStatus(1);// 已发送
		temp1.setCreateTime(new Date());
		temp1.setUpdateTime(new Date());
		list.add(temp);
		list.add(temp1);
		sysPushMessageService.saveBatch(list);
		
    	
        end();
    }
    
    @Test
    public void testBatchsinglePush() throws Exception{
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("customerId", "444444444444444");
		//List<String> userIds = new ArrayList<String>();
		Set<String> userIds = Sets.newHashSet();
		userIds.add("1d06a0942eb54adfa6eafabf2f959033");
		userIds.add("bdaccd0b09f2477e9b1bc005aeeba803");
    	System.out.println(pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSRECEIVESYSREDPAPER,userIds, "批量单推测试标题(理财师收到系统红包)", "亲，金融超市给您发了一批红包", null,true,PersonalMsgTypeEnum.PROJECTINVEST_INV).getMessage());
    	
        end();
    }
    
    @Test
    public void testBatchsinglePush2() throws Exception{
    	start();    	
		List<SysPushMessage> list=  new ArrayList<SysPushMessage>();
		SysPushMessage  temp = new SysPushMessage("1d06a0942eb54adfa6eafabf2f959033","活动奖励发放","恭喜您，您的T呗账户收到200.00活动奖励，请及时登录并查看");
		SysPushMessage  temp1 = new SysPushMessage("5e73e9b14d33454f853b51950153701b","活动奖励发放","恭喜您，您的T呗账户收到500.00活动奖励，请及时登录并查看");
		list.add(temp);
		list.add(temp1);
    	System.out.println(pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL,SmsTypeEnum.LFEERECEIVED,null,list,null));
    	
        end();
    }
    
    @Test
    public void testAdvancePaymentReminder() throws Exception{
    	start();    	
    	pushMessageHelper.advancePaymentReminder();    	
        end();
    }
}
