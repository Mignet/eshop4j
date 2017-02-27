package com.linkwee.xoss.util;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.RefectUtils;
import com.linkwee.web.enums.PlatformEnum;

public class WebUtil
{
  public static String creatUrl(String url, Map<String, String> params)
  {
	 if(StringUtils.isEmpty(url)){
		 return "";
	 } else {
		    StringBuffer sb = new StringBuffer(url);
		    int i = 0;
		    for (String key : params.keySet()) {
		      String value = (String)params.get(key);
		      if ((!url.contains("?")) && (i == 0))
		        sb.append("?");
		      else {
		        sb.append("&");
		      }
		      sb.append(key).append("=").append(value);
		      i++;
		    }
		    return sb.toString();
	 }
  }

  /**
   * 根据token获取userId
   * @param token
   * @return
   */
/*  public static String getUserId(String token)
  {
    return JsonWebTokenHepler.getUserIdByToken(token);
  }*/

  public static String getSysToken(String userNumber, String userToken)
  {
    String token = userNumber + "_" + userToken;
    return token;
  }

  public static String getWebPath(HttpServletRequest request, String relativePath)
  {
    if (relativePath.charAt(0) == '/') {
      return getWebRootPath(request) + relativePath.substring(1);
    }
    return getWebRootPath(request) + relativePath;
  }

  public static String getWebRootPath(HttpServletRequest request)
  {
    StringBuffer url = request.getRequestURL();
    String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getSession().getServletContext().getContextPath()).append("/").toString();

    return tempContextUrl;
  }

  public static String getDefaultFormat(Object d) {
    if (d == null) {
      return "";
    }
    Class type = d.getClass();
    if ((type == Double.class) || (type == Float.class) || (type == BigDecimal.class)) {
      return NumberUtils.getDefaultFormatHalfDown(d);
    }
    if ((type == String.class) || (type == Character.class) || (type == Byte.class) || (type == Short.class) || (type == Integer.class) || (type == Long.class))
    {
      return d.toString();
    }
    return null;
  }

  public static void initObj(Object target, Object src)
  {
    RefectUtils.copyObj(src, target, new RefectUtils.FieldDecorator()
    {
      public Object doInvoke(String fieldName, Object value) {
        if ((value != null) && (value.getClass() == Boolean.class)) {
          return value;
        }
        return WebUtil.getDefaultFormat(value);
      }
    });
  }

  public static void initObj(Object target, Object src, final String dateFormate) {
    RefectUtils.copyObj(src, target, new RefectUtils.FieldDecorator()
    {
      public Object doInvoke(String fieldName, Object value) {
        if ((StringUtils.isNotBlank(dateFormate)) && ((value instanceof Date))) {
          return DateUtils.format((Date)value, dateFormate);
        }
        if ((value != null) && (value.getClass() == Boolean.class)) {
          return value;
        }
        return WebUtil.getDefaultFormat(value);
      }
    });
  }

  public static int compareVersion(String version1, String version2)
  {
    String[] arr = version1.split("\\.");
    long v1 = 0L;
    long level1 = 1L;
    for (int i = arr.length - 1; i >= 0; i--) {
      v1 += Integer.valueOf(arr[i]).intValue() * level1;
      level1 *= 10000L;
    }
    long v2 = 0L;
    long level2 = 1L;
    String[] arr2 = version2.split("\\.");
    for (int i = arr2.length - 1; i >= 0; i--) {
      v2 += Integer.valueOf(arr2[i]).intValue() * level2;
      level2 *= 10000L;
    }
    if (v1 > v2)
      return 1;
    if (v1 == v2) {
      return 0;
    }
    return -1;
  }

  public static String encryptMobile(String mobile)
  {
    if ((StringUtils.isNotBlank(mobile)) && (mobile.length() == 11)) {
      StringBuilder sb = new StringBuilder();
      sb.append(mobile.substring(0, 3));
      sb.append("****");
      sb.append(mobile.substring(mobile.length() - 4, mobile.length()));
      return sb.toString();
    }
    return mobile;
  }
  
  	/**
	 * 根据appkey 获取平台类型
	 * @param app_key
	 * @return
	 */
	public static PlatformEnum getPlatform(String app_key){
		String type =  app_key.substring(app_key.lastIndexOf("_")+1);
		return (PlatformEnum)EnumUtils.getEnumByValue(type, PlatformEnum.values());
	}
	
}
