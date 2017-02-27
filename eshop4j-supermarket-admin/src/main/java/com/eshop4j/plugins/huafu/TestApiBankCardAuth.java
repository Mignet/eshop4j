package com.eshop4j.plugins.huafu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.plugins.huafu.entity.AcVerifyRealName;
import com.eshop4j.plugins.huafu.utils.Cipher3DES;
import com.eshop4j.plugins.huafu.utils.HttpRequestClient;
import com.eshop4j.plugins.huafu.utils.RsaSignCoder;
import com.eshop4j.xoss.helper.DateUtils;

public class TestApiBankCardAuth {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestApiBankCardAuth.class);
	
	/**
	 * 商户测试签名私钥
	 */
//	private static String signPrivateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAqiK+jmlvJfxQSkuV44T8Pwwk5FGeYRi/7UQAQfp/9IQ0O3ad0h/ex+ABDPqUbiVYnU2LDmx+UtmvVwzF/AfRRwIDAQABAkEAoLejlPoqYhLAcf6HAF9+vbwmGXy0hXqQy3yiVbFiMEMn7Svb8h/fQ7ZLBvS+8OGpZOwIt6e6T3pGto/upppKsQIhAPNyfoNqPRtdpVEvWwtwNode1NXAm0Z16ycZ5U1Jez8PAiEAsuiJk1ZAFjr4k3gMq01qUb1pyT+02f/e9nXvSoXPykkCIQC7HkTZs53WW+tGdHSxXQW8lQpYZZuz08z0F/Zkqlc9xwIgNrIo/UZtKV62CD+3f9eXHY5O/Rvg6pTzUV4U3i+yqyECIQCNLE4bY0zvOJGv7hrPmcVZStmbL+cg9FrGKEu2n6z4MQ==";
	private static String signPrivateKey ="MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA2RDOXzEbQQd2FYSa2rmXderdKUmTJ5kfsXSGlqYi96jwSM8SHR8TdfqW9XYllGqP5vzKbBM02cXbvCBIUBblFQIDAQABAkEAviUTsp1ws2FW1ninsgw7mmbXv3AdAOYItklOEK+OGAT/GJmehjzu8cbfyQaLfGSIIrprOahgHlFJUL89WjECIQIhAO6vtJBmXTx1QsjL8eQCoxDTEnn4DjAdV46VMlQwngZ9AiEA6M+dTMLbhQ6K27SO9eD1EmRMo/lpDa2dkP/GZTPhZHkCIQCZsVo7gS6YN4mBcpGXmHsX4lILZ8b7BGnUzg1SWNvooQIhAItVjkgdqvIEVRjPBGyl2SbLvo64u6YDDXV1jxCMr03JAiBYohViEYqcWNQhrXAtrO4Rij7si/Saa/KXnVu2ciV6JA==";
	/**
	 * 固定的平台测试签名公钥
	 */
//	private static String signPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJZbLUlvQtyyGfFJOJnLs98Cv0nbHseAJ7kGIrFhVHf16Ewfa8asBmAToUM67Uspr7P5p/zxVTffN4YMrP+5400CAwEAAQ==";
	private static String signPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJJY/kusrKic6XAhHMakIX06nEnMnvceRV5m8dLIKQcz0RNkDXiEQ/EIV0hZQNQlTIyB5f6OzQeDjJlDUGgkyD8CAwEAAQ==";
	/**
	 * 接入前置条件(以下为测试相关参数)：
	 * 1、平台分配的接入秘钥   例如：l4mdofLTvHkyONpdlyXBiaTv
	 * 2、商户编号：TEST10001，即在平台注册账号的编号
	 * 3、应用编号：TESTAPP10001 即在平台创建应用的编号
	 * 4、加密偏移量，用户自己随机任意生成的8位数字或者字母 
	 * 5、用户自己生成的签名密钥对：GetKey.getSignKeys();  私钥自己留存，公钥上传
	 * 6、绑定IP，用户可自行在平台个人中心上传公钥的地方绑定出口IP，或将自己的出口IP提供给平台绑定
	 */
	public static void test1() throws Exception{
		//1、组装请求报文
		JSONObject headerJson = new JSONObject();
		JSONObject conditionJson = new JSONObject();
		//查询批次号 验证批次号  用户生成的唯一编号 唯一，不超过20位
		headerJson.put("qryBatchNo", "20160525151642123");
		//平台分配的商户编号
		headerJson.put("userCode", "TEST10001");
		//应用编号 平台创建应用分配的唯一编号
		headerJson.put("sysCode", "TESTAPP10001");
		//查询原因  简单说明调用原由，可为空
		headerJson.put("qryReason", "信贷");
		//查询日期 格式：yyyyMMdd，可为空
		headerJson.put("qryDate", "20160704");
		//查询时间 格式：hhmmss，可为空
		headerJson.put("qryTime", "101059");//格式：hhmmss
		
		//姓名 不超过20位
		conditionJson.put("realName", "吴二文学000000000000000000000000");
		//身份证号码 必须符合身份证标准规范
		conditionJson.put("idCard", "43122419871102001X");
		//银行卡号码 银行卡不能为空且符合银行卡规范
		conditionJson.put("bankCard", "6217582000023632670");
		conditionJson.put("mobile", "18675529654");
		
		JSONObject allJson = new JSONObject();
		allJson.put("header", headerJson);
		allJson.put("condition", conditionJson);
		
		System.out.println(allJson.toString());
		
		String data = allJson.toString();
		System.out.println("请求报文："+data);
		
		String privateKey = "l4mdofLTvHkyONpdlyXBiaTv";//平台提供的加密秘钥
		String vector = "12345678"; //用户自己生成的 随机8位数字或者字母
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
		//请求条件的加密报文
		parameters.add(new BasicNameValuePair("condition", encrData));
		//平台分配的用户编号
		parameters.add(new BasicNameValuePair("userCode", "TEST10001"));
		//加密请求报文的签名值
		parameters.add(new BasicNameValuePair("signature", signature));
		//加密偏移量，用户随机生成8位随机数字或字母
		parameters.add(new BasicNameValuePair("vector", vector));
		
		//四要素URL:http://api.hfdatas.com/superapi/super/auth/smrz4
		String reslult = HttpRequestClient.invokeHttp("http://test.api.hfdatas.com/superapi/super/auth/smrz4", parameters, "UTF-8");
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
		for (String key:it){  
			 String value = String.valueOf(requestJson.get(key));  
             resultMap.put(key, value); 
        }  
		//加密请求报文的签名值
        String sign = resultMap.get("signature");
        System.out.println("加密请求报文的签名值:"+sign);
        //返回数据的加密报文
        String datas = resultMap.get("contents");
        System.out.println("返回数据的加密报文:"+datas);
        
		/**
		 * 验签及解密报文
		 */
		//6、验证加密内容报文的签名
		boolean isTrue=RsaSignCoder.verify(datas, signPublicKey, sign);
		if(isTrue){//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
			//7、验签通过则进行解密返回的加密报文
			String str = Cipher3DES.decrypt(datas, privateKey, vector);
			System.out.println("解密结果："+str);
//			String errorMsg = "{\"msg\":{\"codeDesc\": \"身份证号码有误\",\"code\": \"02\"}}";
			JSONObject json = JSONObject.parseObject(str);
			try {
				AcVerifyRealName ver = json.toJavaObject(json, AcVerifyRealName.class);
				if(ver!=null&&ver.getData()!=null&&ver.getData().get(0).getRecord()!=null&&"00".equals(ver.getData().get(0).getRecord().get(0).getResCode())){
					System.out.println("实名验证成功");
				}else if(ver!=null&&ver.getMsg()!=null){
					System.out.println("错误代码:"+ver.getMsg().getCode()+"                错误代码描述:"+ver.getMsg().getCodeDesc());
				}
			} catch (Exception e) {
			
			}
		}
		

	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void test2(String bankCard) throws Exception{
		
		//1、组装请求报文
		JSONObject headerJson = new JSONObject();
		JSONObject conditionJson = new JSONObject();
		headerJson.put("qryBatchNo", "20160525151642123");  //验证批次号  用户生成的唯一编号
		headerJson.put("userCode", "HFJK20160811000004");   //商户编号，即用户编号
		headerJson.put("sysCode", "HFJKAPP20160811000003"); //应用编号
		headerJson.put("qryReason", "领会银行卡属性查询");                 //原因
		headerJson.put("qryDate", DateUtils.getNow("yyyyMMdd"));//格式：yyyyMMdd
		headerJson.put("qryTime", DateUtils.getNow("hhmmss"));//格式：hhmmss
		conditionJson.put("bankCard", bankCard);
		JSONObject allJson = new JSONObject();
		allJson.put("header", headerJson);
		allJson.put("condition", conditionJson);
		System.out.println(allJson.toString());
		String data = allJson.toString();
		System.out.println("请求报文："+data);
		
		String privateKey ="1rYfBx/42X+hc1vBO25UvEZd";// "wr/lfJgIMWt/BOUO5ioyrVvL";//平台提供的加密秘钥
		String vector = "65412387"; //用户自己生成的 随机8位数字或者字母
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
		parameters.add(new BasicNameValuePair("userCode", "HFJK20160811000004"));
		parameters.add(new BasicNameValuePair("signature", signature));
		parameters.add(new BasicNameValuePair("vector", vector));//随机生成的8位数偏移量
		//四要素URL:http://api.hfdatas.com/superapi/super/auth/smrz4
		String reslult = HttpRequestClient.invokeHttp("http://api.hfdatas.com/superapi/super/bankcard/nature", parameters, "UTF-8");
		System.out.println("返回报文："+reslult);
		
		Map<String, String> returnStr = new HashMap<String, String>();
		/**
		 * 解析平台返回报文
		 */
		//5、获取接口返回报文
		Map<String, String> resultMap = new HashMap<String, String>();
	    // 将json字符串转换成jsonObject  
		JSONObject requestJson = JSONObject.parseObject(reslult);
		Set<String> it = requestJson.keySet();  
        // 遍历jsonObject数据，添加到Map对象  
		for (String key:it){  
			 String value = String.valueOf(requestJson.get(key));  
             resultMap.put(key, value); 
        }  
		//加密请求报文的签名值
        String sign = resultMap.get("signature");
        LOGGER.info("加密请求报文的签名值:"+sign);
        //返回数据的加密报文
        String datas = resultMap.get("contents");
        LOGGER.info("返回数据的加密报文:"+datas);
        
        //6、验证加密内容报文的签名
        boolean isTrue= true;
        String str = null;
       
        if(sign!=null&&datas!=null){
        	//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
        	isTrue=RsaSignCoder.verify(datas, signPublicKey, sign);
        	 /**
			 * 验签及解密报文
			 */
        	if(isTrue){
        		str = Cipher3DES.decrypt(datas, privateKey, vector);
        		//7、验签通过则进行解密返回的加密报文
				LOGGER.info("解密结果："+str);
        	}else{
				returnStr.put("code","999");
			}
        }else{
        	str = reslult;
        }
        
		JSONObject json = JSONObject.parseObject(str);
		@SuppressWarnings("static-access")
		AcVerifyRealName ver = json.toJavaObject(json, AcVerifyRealName.class);
		if(ver!=null&&ver.getMsg()!=null){
			LOGGER.info("实名验证错误代码=【{}】,错误代码描述【{}】",ver.getMsg().getCode(),ver.getMsg().getCodeDesc());
		}else if(ver!=null&&ver.getData()!=null&&ver.getData().get(0).getRecord()!=null){
			
			String bankName = ver.getData().get(0).getRecord().get(0).getBankName();
			if("Discover Financial ServicesI".equals(bankName)){
				LOGGER.info("实名验证{}",true);
//				return true;
			}
			boolean flag = "中国民生银行".contains(bankName);
			LOGGER.info("实名验证{}",flag);
		} 
	}

	public static void main(String[] args) {
		try {
			TestApiBankCardAuth.test2("6217003320029879130");//6226190303411650 特殊  Discover Financial ServicesI
//			http://detectionBankCard.api.juhe.cn/bankCard?key=f686f3eee51d0d8c50827dbff361e744&cardid=6226220113414063
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block  Discover Financial ServicesI
			e.printStackTrace();
			
		}
	}
}
