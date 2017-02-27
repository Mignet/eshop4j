package com.eshop4j.openapi.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.openapi.demo.utils.Cipher3DES;
import com.eshop4j.openapi.demo.utils.HttpRequestClient;
import com.eshop4j.openapi.demo.utils.RsaSignCoder;


public class TestOpenApi {
	
	/**
	 * 商户测试签名私钥,公钥交给平台
	 */
	private static String signPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA1mrPJF9dlbVmrmojIccwRR4dQxDHlzcmN69RxuwuZ8Qae57CyC9g4lTSYnkasJSg3TVZaUTv6ooIHm5D+SMbaQIDAQABAkACfWEEYhsbFu0nT1IzHTYBR3U9hx9aMSi/csi3T5zGVaoovrRf/6QDG3IomqJZZ+teZdcSDMbjtrYqTr+vH/G1AiEA+z18V1GaDtD8JMhGFUOf1y6SgFMrevwdT4V9RlgmDE8CIQDaertw8glf8eg7Na+BNQYFHkAgGoYiWTKQjzb28YNWxwIgHliy6CknHpsHmbnHHVJpwCgT8gQZwGUrdSt+kjybe+kCIQCo8AZIi59cJT4hqp2PF2UUSBPlvxzBKp/C0Dr8MDwSuQIgXCXis3v1lyvSBLr2t7/gEkkn0kes101VEThU/tqbqQY=";
	
	/**
	 * 固定的平台测试签名公钥,私钥在平台
	 */
	private static String signPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJMsCUctQJyh9dfvG3QIgsjwEWNSydOGB3SoFxsBr46wXYjyAYwJcP/d5a+zLaAWiW3Xiz1TToWjhUDNrvKex9sCAwEAAQ==";
	
	/**
	 * 接入前置条件(以下为测试相关参数)：
	 * 1、平台分配的接入秘钥   例如：l4mdofLTvHkyONpdlyXBiaTv
	 * 2、机构编号：TEST10001，即在平台注册账号的编号
	 * 3、应用编号：TESTAPP10001 即在平台创建应用的编号
	 * 4、加密偏移量，用户自己随机任意生成的8位数字或者字母 
	 * 5、用户自己生成的签名密钥对：GetKey.getSignKeys();  私钥自己留存，公钥上传
	 * 6、绑定IP，用户可自行在平台个人中心上传公钥的地方绑定出口IP，或将自己的出口IP提供给平台绑定
	 */
	public static void test1() throws Exception{
		//1、组装请求报文
		JSONObject headerJson = new JSONObject();
		JSONObject conditionJson = new JSONObject();
		JSONObject productPushRequest = new JSONObject();
		headerJson.put("qryBatchNo", "20160525151642123");  //验证批次号  用户生成的唯一编号
		headerJson.put("orgNumber", "TEST10001");   //商户编号，即用户编号
		headerJson.put("sysCode", "TESTAPP10001"); //应用编号
		headerJson.put("qryReason", "信贷");                 //原因
		headerJson.put("qryDate", "20160704");//格式：yyyyMMdd
		headerJson.put("qryTime", "101059");//格式：hhmmss
		productPushRequest.put("productName", "产品1");
		productPushRequest.put("thirdProductId", "43122419800000000000711020001X");
		productPushRequest.put("productDesc", "产品1");
		conditionJson.put("productPushRequest", productPushRequest);
		JSONObject allJson = new JSONObject();
		allJson.put("header", headerJson);
		allJson.put("condition", conditionJson);
		System.out.println(allJson.toString());
		String data = allJson.toString();
		System.out.println("请求报文："+data);
		
		String privateKey = "l4mdofLTvHkyONpdlyXBiaTv";//平台提供的加密秘钥
		String vector = "02154782"; //用户自己生成的 随机8位数字或者字母
		//2、加密请求报文
		String encrData = Cipher3DES.encrypt(data, privateKey, vector);
		System.out.println("加密报文："+encrData);
		
		//3、对请求报文进行签名
		String signature=RsaSignCoder.sign(encrData, signPrivateKey); 
		System.out.println("签名值："+signature);
		
		/**
		 * 组装请求参数 调用实名认证接口
		 */
		//4、请求API对应的接口
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("condition", encrData));
		parameters.add(new BasicNameValuePair("orgNumber", "TEST10001"));
		parameters.add(new BasicNameValuePair("signature", signature));
		parameters.add(new BasicNameValuePair("vector", vector));//随机生成的8位数偏移量
		//四要素URL:http://api.hfdatas.com/superapi/super/auth/smrz4
		String reslult = HttpRequestClient.invokeHttp("http://10.16.1.87:8080/rest/openapi/test", parameters, "UTF-8");
		System.out.println("返回报文："+reslult);
		
		/**
		 * 解析平台返回报文
		 */
		//5、获取接口返回报文
		Map<String, String> resultMap = new HashMap<String, String>();
	    // 将json字符串转换成jsonObject  
		JSONObject requestJson = JSONObject.parseObject(reslult);
		Set<String> it = requestJson.keySet();  
		// 遍历jsonObject数据，添加到Map对象  
		for(String key:it){
           String value = String.valueOf(requestJson.get(key));  
           resultMap.put(key, value);  
        }  
        String sign = resultMap.get("signature");
        String datas = resultMap.get("contents");
		
		/**
		 * 验签及解密报文
		 */
		//6、验证加密内容报文的签名
		boolean isTrue=RsaSignCoder.verify(datas, signPublicKey, sign);
		if(isTrue){//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
			//7、验签通过则进行解密返回的加密报文
			String str = Cipher3DES.decrypt(datas, privateKey, vector);
			System.out.println("解密结果："+str);
		}
	}

	public static void main(String[] args) {
		try {
			TestOpenApi.test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
