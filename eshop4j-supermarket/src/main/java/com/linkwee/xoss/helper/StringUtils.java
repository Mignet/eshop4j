package com.linkwee.xoss.helper;

public class StringUtils {
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static String upperCaseFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String lowerCaseFirstChar(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 字符串是否为数字
	 * @Auther ZhongLing
	 * @Date 2016年2月22日
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}
