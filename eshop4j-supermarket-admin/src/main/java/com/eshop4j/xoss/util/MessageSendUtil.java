package com.eshop4j.xoss.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSendUtil {
	public final static Map<Long,String> errorInfoMap = new HashMap<Long,String>();
	static{
		errorInfoMap.put(Long.valueOf("-1"), "参数为空。信息、电话号码等有空指针，登陆失败");
		errorInfoMap.put(Long.valueOf("-12"), "有异常电话号码");
		errorInfoMap.put(Long.valueOf("-14"), "实际号码个数超过100");
		errorInfoMap.put(Long.valueOf("-999"), "服务器内部错误");
		errorInfoMap.put(Long.valueOf("-10001"), "用户登陆不成功(帐号不存在/停用/密码错误)");
		errorInfoMap.put(Long.valueOf("-10003"), "用户余额不足");
		errorInfoMap.put(Long.valueOf("-10011"), "信息内容超长");
		errorInfoMap.put(Long.valueOf("-10029"), "此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)");
		errorInfoMap.put(Long.valueOf("-10030"), "不能发送移动号码");
		errorInfoMap.put(Long.valueOf("-10031"), "手机号码(段)非法");
		errorInfoMap.put(Long.valueOf("-10057"), "IP受限");
		errorInfoMap.put(Long.valueOf("-10056"), "连接数超限");
	}

	public static String RegexString(String content){
		Pattern p = Pattern.compile("<string[^>]*>(.*?)</string>"); 
		Matcher ms = p.matcher(content); 
		if(ms.find()) { 
				return ms.group(1);
		}else
		{
			return content;
		}
	}
	public static boolean isSuccessReturn(long element){
		/*long[] errorCodeArry = {-1, -12, -14, -999,-10001,-10003,-10011,-10029,-1003,-10031,-10057,-10056};
		boolean ret = true;
		for (int i = 0; i < errorCodeArry.length; i++)
		{
			if (element == errorCodeArry[i])
			{
				ret = false;
				break;
			}
		}
		return ret;*/
		return errorInfoMap.get(element) == null ? true : false;
	
	}
	
	
}
