package com.eshop4j.test.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

public class IOSPushtoSingle {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	 private static String appId = "HQLGzH3xfp7ludDWdv01e2";
	 private static String appKey = "6EYEqFfTgI9gR3uDhADknA";
	 private static String masterSecret = "GBLuNzG0Oc6pbqYcbG8n91";
	 static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
 

   // static String CID = "2b5641dfacefe8a615938efc6b7560a6";
    static String CID = "9c16e2a96bab61f56af732b474a3687d010ed9f586a20ff484f2d0e04046ba72";
  //别名推送方式
   // static String Alias = "";

    public static void main(String[] args) throws Exception {
    	
    	apnpush();
    }
    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        // 配置通知栏图标
       // template.setLogo("icon.png");
        // 配置通知栏网络图标，填写图标URL地址
        template.setLogoUrl("http://www.easyicon.net/api/resizeApi.php?id=510234&size=72");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
    }
    
	 public static NotificationTemplate fillNotificationTemplate(String appId, String appkey,String title,String content,Map<String, Object> urlparam) {
		    NotificationTemplate template = new NotificationTemplate();
		    // 设置APPID与APPKEY
		    template.setAppId(appId);
		    template.setAppkey(appkey);
		    // 设置通知栏标题与内容
		    template.setTitle(title);
		    template.setText(content);
		    // 配置通知栏图标
		    //template.setLogo("icon.png");
		    // 配置通知栏网络图标
		    template.setLogoUrl("http://www.easyicon.net/api/resizeApi.php?id=510234&size=72");
		    // 设置通知是否响铃，震动，或者可清除
		    template.setIsRing(true);
		    template.setIsVibrate(true);
		    template.setIsClearable(true);
		    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		    template.setTransmissionType(2);
		    JSONObject jsonObject = new JSONObject(urlparam);
		    template.setTransmissionContent(jsonObject.toJSONString());
		    // 设置定时展示时间
		    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		    return template;
		}
	 
	 
	 public static void apnpush() throws Exception {
         IGtPush push = new IGtPush(host, appKey, masterSecret);  
         APNTemplate t = new APNTemplate();
         APNPayload apnpayload = new APNPayload();
         apnpayload.setSound("");
         //apn高级推送
         APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
         ////通知文本消息标题
         alertMsg.setTitle("title标题");
         //通知文本消息字符串
         alertMsg.setBody("message body内容");
         //对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
         alertMsg.setTitleLocKey("ccccc");
         //指定执行按钮所使用的Localizable.strings
         alertMsg.setActionLocKey("ddddd");
         apnpayload.setAlertMsg(alertMsg);

         t.setAPNInfo(apnpayload);
         SingleMessage sm = new SingleMessage();
         sm.setData(t);
         IPushResult ret0 = push.pushAPNMessageToSingle(appId, CID, sm);
         System.out.println(ret0.getResponse());

  }

	 
}
