package com.eshop4j.plugins.huafu.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eshop4j.plugins.huafu.entity.AcVerifyIsCredit;
import com.eshop4j.xoss.util.HttpClientUtil;

public class AcVerifyIsCreditHttp {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
   
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        //发送 GET 请求
//        String str=AcVerifyIsCreditHttp.sendGet("http://detectionBankCard.api.juhe.cn/bankCard", "cardid=4563511200011342343&key=a62b2c168050e09ef77c61a1729e5e73");
    	String str= HttpClientUtil.httpsGet("http://detectionBankCard.api.juhe.cn/bankCard?cardid=6226300221257446&key=a62b2c168050e09ef77c61a1729e5e73");
    	System.out.println(str);
//        System.out.println(JSONArray.toJSONString(s).toString());
        JSONObject json = JSONObject.parseObject(str);
		@SuppressWarnings("static-access")
		AcVerifyIsCredit ver = json.toJavaObject(json, AcVerifyIsCredit.class);
		System.out.println(JSONArray.toJSONString(ver).toString());
    	
    	
//        String asCII="\U4F01\U4E1A";  
//        SYSTEM.OUT.println(ascii);  
        
        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
    }
}