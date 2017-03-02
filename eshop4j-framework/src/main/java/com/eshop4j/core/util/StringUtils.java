package com.eshop4j.core.util;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
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
	/**
	 * 将字符串中大写字母转换成下划线小写，比如roleName->role_name
	 * @param str
	 * @return string
	 */
	public static String convertColumn(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c:str.toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append('_').append(Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将数字中文分割,中间加分隔符,且末尾去掉分隔符
	 * @param str	待分割的字符串
	 * @param separateSign  分隔标示
	 * @return
	 */
	public static String separateNumberChinese(String str,String separateSign){
	    Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
	    Matcher m = p.matcher(str);
	    StringBuffer stringBuffer = new StringBuffer();
	    while (m.find()) {
	        stringBuffer.append(m.group()+separateSign);
	    }
	    //如果第二个字符串和第四个字符串相等，则将第二个字符串设置为""
	    String[] stringArray = stringBuffer.substring(0,stringBuffer.length()-1).split(separateSign);
	    if(stringArray.length == 4 && stringArray[1].equals(stringArray[3])){
	    	stringArray[1] = "";
	    }
	    String returnString = Arrays.toString(stringArray);
	    return  returnString.substring(1, returnString.length()-1);
	}
	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
