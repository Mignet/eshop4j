package com.linkwee.xoss.util;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;

import com.alibaba.fastjson.JSONObject;


public class AccessParamRecord  {
	private static final Class<RequestLogging> REQUEST_LOGGING_CLASS = RequestLogging.class;

	private final static Charset UTF8     = Charset.forName("UTF-8");
	 
    /**
     * CR.
     */
	private static final byte CR = (byte) '\r';


    /**
     * LF.
     */
	private static final byte LF = (byte) '\n';

	
	/**
	 * 生成访问参数
	 * 
	 * @param Parameter
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String paramInfo(MethodParameter[] Parameter,HttpServletRequest request) {
		String param=null;
		StringBuilder sb = new StringBuilder();
		for (MethodParameter m : Parameter) {
			param=null;
			Class clazz = m.getParameterType();
			
			if(ServletRequest.class.isAssignableFrom(clazz)|| ServletResponse.class.isAssignableFrom(clazz)){
				continue;
			}
			String type = clazz.getName();
			String parameterName = m.getParameterName();
			
			
			if(clazz==String.class){
				param = getParameterValues(request,parameterName);
			}else if(clazz.isPrimitive()){
				param = getParameterValues(request,parameterName);
			}else{
				param = getObjectFieldValue(clazz,request);
			}
			

			sb.append("|paramType:  ");
			sb.append(type);
			sb.append("|paramName:");
			sb.append(parameterName);
			sb.append("|param: ");
			sb.append(param);
		}
		return sb.toString();
	}
	
	
	/**
	 * 生成对象参数描述
	 * @param clazz
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  static String getObjectFieldValue(Class clazz,HttpServletRequest request){
			Map<String, String> map= new HashMap<String, String>(clazz.getDeclaredFields().length);
			for(Field field :clazz.getDeclaredFields()){
				RequestLogging fieldAnnotation = field.getAnnotation(REQUEST_LOGGING_CLASS);
				if(fieldAnnotation==null){
					String fieldName=field.getName();
					String value = getParameterValues(request,fieldName);
					map.put(fieldName, value);	
				}
			}
			return JSONObject.toJSONString(map);
	}
	
	/**
	 * 获取参数
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static String getParameterValues(HttpServletRequest request,String parameterName){
		String[] parameterValues = request.getParameterValues(parameterName);
		if (parameterValues != null && parameterValues.length>0) {
			return  parameterValues[0];
			
		}
		return null;
	}	

}
