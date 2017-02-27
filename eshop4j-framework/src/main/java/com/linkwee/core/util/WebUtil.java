package com.linkwee.core.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.linkwee.core.util.RefectUtils.FieldDecorator;


/**
 * 
 * 描述：web工具类
 *
 * @创建人： Bob
 *
 * @时间：2015年12月2日下午3:15:47
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class WebUtil {
	
	
	/**
	 * 创建url
	 * @param url
	 * @param params
	 * @return
	 */
	public static  String creatUrl(String url,Map<String,String> params){
		StringBuffer sb = new StringBuffer(url);
		int i =0;
		for(String key:params.keySet()){
			String value = params.get(key);
			if(!url.contains("?")&&i==0){
				sb.append("?");
			}else{
				sb.append("&");
			}
			sb.append(key).append("=").append(value);
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * 获取用户中心系统的token
	 * @param token 理财师token
	 * @return
	 */
	public static String getUserToken(String token){
		return token.substring(token.lastIndexOf("_")+1);
	}
	
	/**
	 * 获取用户编码
	 * @param token 理财师token
	 * @return
	 */
	public static String getUserId(String token){
		return token.substring(0,token.lastIndexOf("_"));
	}
	
	/**
	 * 理财师token
	 * @param userNumber 用户编码
	 * @param userToken 用户中心token
	 * @return 理财师token
	 */
	public static String getSysToken(String userNumber,String userToken){
		String token =userNumber+"_"+userToken;
		return token;
	}
	
	
	/**
	 * 获取系统类别
	 * @param app_key
	 * @return
	 */
	public static String getSysType(String app_key){
		return app_key.substring(app_key.lastIndexOf("_")+1);
	}
	
	/**
	 * 根据appkey 获取平台类型
	 * @param app_key
	 * @return
	 */
	/*public static PlatformEnum getPlatform(String app_key){
		String type =  app_key.substring(app_key.lastIndexOf("_")+1);
		return (PlatformEnum)EnumUtils.getEnumByValue(type, PlatformEnum.values());
	}*/
	
	
	/**
	 * http://www.xiaoniu.com/relativePath
	 * 获取根目录模板
	 * @param request
	 * @param relativePath
	 * @return
	 */
	public static String getWebPath(HttpServletRequest request,String relativePath) {
		if(relativePath.charAt(0)=='/'){
			return getWebRootPath(request)+relativePath.substring(1);
		}
		return getWebRootPath(request)+relativePath;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getWebRootPath(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url
				.delete(url.length() - request.getRequestURI().length(),
						url.length())
				.append(request.getSession().getServletContext().getContextPath())
				.append("/").toString();
		return tempContextUrl;
	}
	
	public static String getDefaultFormat(Object d){
		if(d==null){
			return "";
		}
		Class<?> type = d.getClass();
		if(type == Double.class||type== Float.class||type == BigDecimal.class){
			return NumberUtils.getDefaultFormatHalfDown(d);
		}
		if(type == String.class|| type == Character.class 
		   || type == Byte.class || type == Short.class
		   || type == Integer.class || type == Long.class){
			return d.toString();
		}
		return null;
	}
	
	
	
	public static void initObj(Object target,Object src) {
		RefectUtils.copyObj(src, target, new FieldDecorator() {
			@Override
			public Object doInvoke(String fieldName, Object value) {
				if(value!=null&&value.getClass()== Boolean.class){
					return value;
				}
				return getDefaultFormat(value);
			}
		});
	}
	
	public static void initObj(Object target,Object src,final String dateFormate) {
		RefectUtils.copyObj(src, target, new FieldDecorator() {
			@Override
			public Object doInvoke(String fieldName, Object value) {
				if(StringUtils.isNotBlank(dateFormate)&&value instanceof Date){
					return DateUtils.format((Date)value,dateFormate);
				}
				if(value!=null&&value.getClass()== Boolean.class){
					return value;
				}
				return getDefaultFormat(value);
			}
		});
	}
	
	/**
	 * 比较版本号大小
	 * @param version1 版本1
	 * @param version2 版本2
	 * @return version1>version2 返回1 version1=version2 返回0 version1<version2 返回-1
	 */
	public static int compareVersion(String version1,String version2) {
		String[] arr = version1.split("\\.");
		long v1 = 0;
		long level1 = 1;
		for(int i=arr.length-1;i>=0;i--){
			v1+=Integer.valueOf(arr[i])*level1;
			level1=level1*10000;
		}
		long v2= 0;
		long level2 = 1;
		String[] arr2 = version2.split("\\.");
		for(int i=arr2.length-1;i>=0;i--){
			v2+=Integer.valueOf(arr2[i])*level2;
			level2=level2*10000;
		}
		if(v1>v2){
			return 1;
		}else if(v1==v2){
			return 0;
		}else{
			return -1;
		}
	}
	
	/**
	 * 手机号加密
	 * @param mobile 手机号
	 * @return
	 */
	public static String encryptMobile(String mobile){
		if(StringUtils.isNotBlank(mobile)&&mobile.length()==11){
			StringBuilder sb = new StringBuilder();
			sb.append(mobile.substring(0, 3));
			sb.append("****");
			sb.append(mobile.substring(mobile.length()-4, mobile.length()));
			return sb.toString();
		}
		return mobile;
	}
}
