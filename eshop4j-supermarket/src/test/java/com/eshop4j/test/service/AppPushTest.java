package com.eshop4j.test.service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * APP 推送测试
 * @author Mignet
 *
 */
public class AppPushTest {
/**
 * 个推参数：
 * app名称：投呗测试
 * AppID: ziJjEoucCg9otW1PXpNe51
 * AppKey: BlsdorcH028m9LCF8ELiZ
 * AppSecret：7FWBPvefXuAtt1X4fuYz62
 * MasterSecret: Kcs2aGCNTdAlcBpjfz5Ef
 */
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "HQLGzH3xfp7ludDWdv01e2";
    private static String appKey = "6EYEqFfTgI9gR3uDhADknA";
    private static String masterSecret = "GBLuNzG0Oc6pbqYcbG8n91";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    

    public static void main(String[] args) throws IOException {

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("欢迎使用个推!");
        template.setText("这是一条推送消息~");
        template.setUrl("http://www.baidu.com");

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}


