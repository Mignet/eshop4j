package com.eshop4j.core.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Net相关
 * 
 */
public final class NetUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NetUtils.class);

	private static final String UNKONWN = "unknown";
	/**
	 * 验证ip是否合法
	 * 
	 * @param text
	 *            ip地址
	 * @return 验证信息
	 */
	public boolean isIpAddress(String text) {
		if (text != null && !text.isEmpty()) {
			// 定义正则表达式
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			// 判断ip地址是否与正则表达式匹配
			return text.matches(regex);
		}
		// 返回判断信息
		return false;
	}

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static final String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		LOGGER.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);

		if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				LOGGER.debug("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				LOGGER.debug("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				LOGGER.debug("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				LOGGER.debug("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
			}
			if (ip == null || ip.length() == 0 || UNKONWN.equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				LOGGER.debug("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!(UNKONWN.equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
	/**
	 * 获取请求路径
	 * @param request
	 * @return
	 */
	public static String  getRequestUrl(HttpServletRequest request,boolean ifWhole){
		String requestUrl = null;
		if(ifWhole){			
			if(StringUtils.isBlank(request.getQueryString())){
				requestUrl = request.getRequestURI();
			} else {
				requestUrl = request.getRequestURI() + "?" + request.getQueryString();
			}
		} else {
			requestUrl = request.getRequestURI();
		}
		return requestUrl;
	}
}