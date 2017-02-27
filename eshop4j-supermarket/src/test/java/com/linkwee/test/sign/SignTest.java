package com.linkwee.test.sign;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.SignUtils;

public class SignTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
//		//签名计算1
//		String str = "81443174b70145e9a97daa1f45fb58b7mobile15036248519orgKey1AFBD1D079464D4892E06F091FA8A578orgNumberOPEN_LUJINSUO_WEBpassword123564timestamp2015-09-21 16:08:0681443174b70145e9a97daa1f45fb58b7";
//		System.out.println(SignUtils.signByString(str));
//		
//		//签名计算2
//		String str2 = "orgAccount=18898766534&thirdProductId=270&orgKey=660E1F6111614123A351B5618E713BFC&orgNumber=OPEN_TOUCHOU_WEB&sign=6F0A899FE8D85A60706B83D2FA80999B&timestamp=2016-08-18+19%3A25%3A33&txId=1d49e793712f4a5f93ee809625162a23";
//		String[] strings = str2.split("&");
//		Map<String,String> paramsMap = new HashMap<String,String>();
//		for(String st:strings){
//			String[] strings2 = st.split("=");
//			paramsMap.put(strings2[0], URLDecoder.decode(strings2[1], "UTF-8"));
//			System.out.println("key="+strings2[0]+"		value="+strings2[1]);
//		}
//		System.out.println("原来签名为："+paramsMap.get("sign"));
//		paramsMap.remove("sign");
//		System.out.println(JSONObject.toJSONString(paramsMap));
//		String sign = SignUtils.sign(paramsMap,"CC7E84D282964BA8913B05510827020C");//机构私钥
//		System.out.println("现在签名为："+sign);
		
//		//签名计算3
//		Map<String,String> paramsMap2 = new HashMap<String,String>();
//		paramsMap2.put("password", "123564");
//		paramsMap2.put("mobile", "15036248519");
//		paramsMap2.put("orgNumber", "OPEN_LUJINSUO_WEB");//机构编码
//		paramsMap2.put("orgKey","1AFBD1D079464D4892E06F091FA8A578");//机构明钥
//		paramsMap2.put("timestamp","2015-09-21 16:08:06");
////		paramsMap2.put("txId",null);
//		String sign2 = SignUtils.sign(paramsMap2,"81443174b70145e9a97daa1f45fb58b7");//机构私钥
//		System.out.println("现在签名为："+sign2);
//	    
		
		//生成机构key
//	    ApplicationUtils.randomUUID(true,true);
		System.out.println("生成机构key");
	    System.out.println(ApplicationUtils.randomUUID(true,true));
	    System.out.println(ApplicationUtils.randomUUID(true,true));
	    System.out.println(ApplicationUtils.randomUUID(true,true));
	    System.out.println(ApplicationUtils.randomUUID(true,true));
	}
}
