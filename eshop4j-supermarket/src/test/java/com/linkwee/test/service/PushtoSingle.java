package com.linkwee.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

public class PushtoSingle {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	 private static String appId = "HQLGzH3xfp7ludDWdv01e2";
	 private static String appKey = "6EYEqFfTgI9gR3uDhADknA";
	 private static String masterSecret = "GBLuNzG0Oc6pbqYcbG8n91";
	 static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
 

   // static String CID = "2b5641dfacefe8a615938efc6b7560a6";
   static String CID = "fec8f7419cf47c2855bfc542ae21e05c";
  //别名推送方式
   // static String Alias = "";
    static String osType = "android";//ios,android

    public static void main(String[] args) throws Exception {
       /* IGtPush push = new IGtPush(host, appKey, masterSecret);
        //LinkTemplate template = linkTemplateDemo();
        Map<String,Object> urlMap = new HashMap<String,Object>();
        urlMap.put("customerId", "");
        NotificationTemplate template = fillNotificationTemplate(appId,appKey,"透传模板测试标题","透传模板测试内容",urlMap);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        	
            ret = push.pushMessageToSingle(message, target);
        	
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
           if(ret.getResponse().containsKey("result")){
        	   System.out.println(ret.getResponse().get("result"));
           }
        } else {
            System.out.println("服务器响应异常");
        }*/
    	//应用标签设置
    	setTag();
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
		    //template.setLogoUrl("http://www.easyicon.net/api/resizeApi.php?id=510234&size=72");
		    // 设置通知是否响铃，震动，或者可清除
		    template.setIsRing(true);
		    template.setIsVibrate(true);
		    template.setIsClearable(true);
		    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		    template.setTransmissionType(2);
		    JSONObject jsonObject = new JSONObject(urlparam);
		    template.setTransmissionContent(jsonObject.toJSONString());
		    /*if(osType.equals("ios")){
		    	 APNPayload payload = new APNPayload();
		    	    payload.setContentAvailable(1);
		    	    payload.setSound("default");
		    	    payload.setCategory("$由客户端定义");
		    	    //简单模式APNPayload.SimpleMsg 
		    	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
		    	    //字典模式使用下者
		    	    //payload.setAlertMsg(getDictionaryAlertMsg());
		    	    template.setAPNInfo(payload);
		    }*/
		    // 设置定时展示时间
		    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		    return template;
		}
	 /**
	  * 应用标签设置
	  */
	 public static void setTag() {
	        IGtPush push = new IGtPush(host, appKey, masterSecret);
	        List<String> tagList = new ArrayList<String>();
	                tagList.add(String.valueOf("18-20"));
	                tagList.add("深圳");
	                tagList.add("双创周");
	        IQueryResult ret = push.setClientTag(appId, CID, tagList);
	        System.out.println(ret.getResponse().toString());
	    }
	 
}
