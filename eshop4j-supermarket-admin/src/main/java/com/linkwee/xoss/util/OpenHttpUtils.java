package com.linkwee.xoss.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.util.SignUtils;
import com.linkwee.web.enums.RequestTypeEnums;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.xoss.crypto.HttpRequestClient;

public class OpenHttpUtils {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(OpenHttpUtils.class);
	
	/**
	 * POST请求获取结果
	 * @param sysThirdkeyConfig  请求来源机构接口配置对象
	 * @param requestTypeEnums  请求方法枚举   POST GET
	 * @param requestUrl  请求路径
	 * @param objectParams 请求参数,不定长参数，可以为多个
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(SysThirdkeyConfig sysThirdkeyConfig,RequestTypeEnums requestTypeEnums,String requestUrl,Object... objectParams){
		
		List<NameValuePair> formparams = changeRequest2NameValuePairForSign(sysThirdkeyConfig,objectParams);
		
        /**
         * 请求得到结果
         */
		String reslult = null;
		try {
			LOGGER.info("请求参数：requestUrl={},formparams={}",requestUrl,JSONObject.toJSONString(formparams));
			if("get".equalsIgnoreCase(requestTypeEnums.getValue())){
				reslult = HttpRequestClient.invokeGetHttp(requestUrl, formparams, "UTF-8");
			} else {
				reslult = HttpRequestClient.invokePostHttp(requestUrl, formparams, "UTF-8");
			}	
		} catch (Exception e) {
			LOGGER.info("请求得到结果异常：{}",e);
			e.printStackTrace();
		}
		
		LOGGER.info("请求返回报文：{}",reslult);
		return reslult;
	}
	
	/**
	 * 根据请求URL和请求参数生成签名后的URL
	 * @param sysThirdkeyConfig	请求来源机构接口配置对象
	 * @param reqURL	请求路径
	 * @param objectParams	请求参数,不定长参数，可以为多个
	 * @return
	 * @throws Exception 
	 */
	public static String generateSignUrl(SysThirdkeyConfig sysThirdkeyConfig,String reqURL,Object... objectParams){
		
		List<NameValuePair> formparams = changeRequest2NameValuePairForSign(sysThirdkeyConfig,objectParams);
        
		//生成完整的请求URL
		StringBuilder requestURL = new StringBuilder();
		try {
	        if (!reqURL.contains("?")) {
	        	requestURL.append(reqURL).append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(formparams)));
	        } else {
	        	requestURL.append(reqURL).append(EntityUtils.toString(new UrlEncodedFormEntity(formparams)));
	        }
		} catch (Exception e) {
			LOGGER.info("根据请求URL和请求参数生成签名后的URL异常:{}",e);
			e.printStackTrace();
		}

		return requestURL.toString();
	}
	
	/**
	 * 将请求的参数添加签名并转化成NameValuePair对象
	 * @param orgEnum	机构类型
	 * @param objectParams	参数
	 * @return
	 */
	private static List<NameValuePair>  changeRequest2NameValuePairForSign(SysThirdkeyConfig sysThirdkeyConfig,Object... objectParams){
		/**
		 * 将请求的参数转化成Map<String,String> 对象
		 */
		Map<String,String> paramsMap = new HashMap<String,String>();
		if(objectParams!=null && objectParams.length>0){
			for(Object obj:objectParams){
				Map<String,String> map = obj2Map(obj);
				paramsMap.putAll(map);
			}
		}
		
        /**
         * 添加签名
         */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", sysThirdkeyConfig.getOrgNumber());//机构编码
		paramsMap.put("orgKey",sysThirdkeyConfig.getOrgKey());//机构明钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
        String sign = SignUtils.sign(paramsMap,sysThirdkeyConfig.getOrgSecret());//机构私钥
        paramsMap.put("sign", sign);
        
        /**
         * 将所有的参数转化成post请求 所需要的对象
         */
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(String key:paramsMap.keySet()){
        	formparams.add(new BasicNameValuePair(key,paramsMap.get(key)));  
        }
        
        return formparams;
	}
	
	/**
	 * 将object对象转化成Map<String,String>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,String> obj2Map(Object obj){
		 Map<String,String> ret = new HashMap<String,String>();
		 if(obj==null){
			return ret;
		 }
		 if(obj instanceof Map){
			 return (Map<String,String>)obj;
		 }
		 Class<?> clazz = obj.getClass();
		 while(clazz!=null){
			 Field[] fields = clazz.getDeclaredFields();
			 for(Field field:fields){
				 String name = field.getName();
				 field.setAccessible(true);
				 Object value = null;
				try {
					value = field.get(obj);
				} catch (Exception e) {
					LOGGER.info("将object对象转化成Map<String,String>异常：{}",e);
					e.printStackTrace();
				}
				 field.setAccessible(false);
				 if(value!=null){
					 ret.put(name, value.toString());
				 }
			 }
			 clazz = clazz.getSuperclass();
		 }
		 return ret;
	}
}
