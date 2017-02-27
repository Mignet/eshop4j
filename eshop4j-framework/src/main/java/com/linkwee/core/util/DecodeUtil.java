package com.linkwee.core.util;

import java.net.URLDecoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.core.security.Base64Utils;

public class DecodeUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DecodeUtil.class);
	private static final String CHARSET = "UTF-8";
	
//	String clientId = "CH201508005";
	private static String clientId = "JDW-CH";
//	String securityCode = "w1Kg4G1y-{3Q;mssffX5L56dghREsP4[gdde=";
	private static String securityCode = "G1y-{3Q;ms[gdde=";
	
	public static void main(String[] args) {
		String params = "clientId=JDW-CH&digest=ZDY2NmQ5MDRjZTVjMjA1NDBjZDY5NmJmOWNhOGU1ZDA%3D&body=%7B+%22soNo%22%3A+%22D1001%22%7D";
		System.out.println("真实参数:"+paramDecode(params));
//		String data = "{\"clientId\":\"JDW-CH\",\"digest\":\"OWFhYjkyNjM5ZWU0YzlkMTk2NjFhMjk0OTg2YzA3YjI=\",\"body\":\"{\\\"ExternalId\\\":\\\"D1001\\\",\\\"OrderNumber\\\":\\\"59-080418-29612345\\\",\\\"OrderTime\\\":\\\"2015-08-05 18:32:30\\\",\\\"ShopName\\\":\\\"深圳家电网\\\",\\\"ProductLine\\\":1,\\\"R3Code\\\":\\\"CH5006260\\\",\\\"Count\\\":1,\\\"UnitPrice\\\":4197.0,\\\"PayAmount\\\":3777.0,\\\"PayAccount\\\":\\\"12345@qq.com\\\",\\\"PayTypeId\\\":2,\\\"Buyer\\\":\\\"测试用户\\\",\\\"BuyerPhone\\\":\\\"13912341234\\\",\\\"PostNumber\\\":\\\"123456\\\",\\\"ProvinceName\\\":\\\"四川省\\\",\\\"CityName\\\":\\\"绵阳市\\\",\\\"DistrictName\\\":\\\"涪城区\\\",\\\"AddressDetail\\\":\\\"高新街道凝祥晓月二期中段9幢2单元602\\\",\\\"Note\\\":\\\"买家留言：  备忘：\\\",\\\"InvoiceType\\\":1,\\\"InvoiceName\\\":\\\"测试用户\\\",\\\"InvoiceAddr\\\":\\\"四川省绵阳市高新区四川省绵阳市高新区凝祥晓月二期中段9幢2单元602\\\"}\"}";
//		System.out.println("post data:"+postEncode(data));
	}
	
	/**
	 * 对url参数进行签名验证并解析[GET]
	 * 
	 * @param params
	 * @return
	 */
    public static String paramDecode(String params) {
    	String[] clientId_ =null;String[] securityCode_ = null;String[] params_ = null;
    	try {
    		String unparams = URLDecoder.decode(params, CHARSET);
    		System.out.println("URL解码:"+unparams);
			String[] list = unparams.split("&");
			clientId_ = list[0].split("=");
			securityCode_ = list[1].split("=");
			params_ = list[2].split("=");
		} catch (Exception e) {
			throw new RuntimeException("{\"success\": false,\"message\": \"验证数据错误！\"}");
		}
    	//1.基础校验
    	if(clientId_[0].equals("clientId")&&clientId_[1].equals(clientId)&&securityCode_[0].equals("digest")&&params_[0].equals("body")){
        	//2.安全码校验
    		String sBefore = params_[1]+securityCode;
    		String sAfter = DigestUtils.md5Hex(sBefore);
    		//md5校验
    		if(Base64Utils.decodeStr(securityCode_[1]).equals(sAfter)){
    			return params_[1];
    		}else{
    			throw new RuntimeException("{\"success\": false,\"message\": \"验证数据错误！\"}");
    		}
    	}else{
    		throw new RuntimeException("{\"success\": false,\"message\": \"验证数据错误！\"}");
    	}
    }
    
    /**
     * 对post参数进行签名验证并解析[POST]
     * 
     * @param data
     * @return
     */
    public static String postEncode(String data){
    	JSONObject jsonObject = new JSONObject(data); 
    	String clientId_ =jsonObject.getString("clientId");
    	String digest =jsonObject.getString("digest");
    	String body = jsonObject.getString("body");
    	String sBefore = body+securityCode;
    	String sAfter = Base64Utils.encodeStr(DigestUtils.md5Hex(sBefore));
    	//校验
    	if(clientId_.equals(clientId)&&digest.equals(sAfter)){
    		return body;
    	}else{
    		throw new RuntimeException("{\"success\": false,\"message\": \"验证数据错误！\"}");
    	}
    }
}
