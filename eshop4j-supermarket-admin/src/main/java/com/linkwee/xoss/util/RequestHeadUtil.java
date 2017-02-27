package com.linkwee.xoss.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.OpenRequestHead;

public class RequestHeadUtil {
	
	private static final Logger LOGGER = Logger.getLogger(RequestHeadUtil.class);

	/**
	 * HTTP请求参数转化
	 * @param request
	 * @return
	 */
	public static HashMap<String, String> getRequestParams(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String[]> srcParamMap = request.getParameterMap();
		HashMap<String, String> destParamMap = new HashMap<String, String>();
		for (String obj : srcParamMap.keySet()) {
			String[] values = srcParamMap.get(obj);
			if (values != null && values.length > 0) {
				destParamMap.put(obj, values[0]);
			} else {
				destParamMap.put(obj, null);
			}
		}
		return destParamMap;
	}
	
	/**
	 * 转化成APP请求头
	 * @param request
	 * @return
	 */
	public static AppRequestHead convertToAppRequestHead(HttpServletRequest request){
		AppRequestHead head = new AppRequestHead();
		head.setOrgNumber(request.getParameter("orgNumber"));
		head.setAppKind(request.getParameter("appKind"));
		head.setAppClient(request.getParameter("appClient"));
		head.setAppVersion(request.getParameter("appVersion"));
		head.setSign(request.getParameter("sign"));
		head.setTimestamp(request.getParameter("timestamp"));
		head.setToken(request.getParameter("token"));
		head.setV(request.getParameter("v"));
		return head;
	}
	
	
	/**
	 * 转化成第三方请求头
	 * @param request
	 * @return
	 */
	public static OpenRequestHead convertToOpenRequestHead(HttpServletRequest request){
		OpenRequestHead head = new OpenRequestHead();
		head.setOrgNumber(request.getParameter("orgNumber"));
		head.setOrgKey(request.getParameter("orgKey"));
		head.setTimestamp(request.getParameter("timestamp"));
		head.setSign(request.getParameter("sign"));
		return head;
	}
	
	/**
	 * 调用response输出相关的内容
	 * @param response
	 * @param obj
	 */
	public static  void responseOutWithJson(HttpServletResponse response,Object obj) {  
	    //将实体对象转换为JSON Object转换  
	    String ret = JSONObject.toJSONString(obj);  
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(ret);  
	        LOGGER.debug("调用response输出:"+ret+"\n");  
	    } catch (IOException e) {  
	    	LOGGER.error("调用response输出:"+ret+"错误",e);  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }
	} 
}
