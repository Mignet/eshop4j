package com.eshop4j.xoss.util;

import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.OrgTypeEnum;
import com.eshop4j.web.enums.PlatformEnum;

/**
 * APP工具类
 * @author Mignet
 *
 */
public class AppUtils {


	/**
	 * 根据orgNumber 获取系统类型	investor,channel
	 * @param orgNumber
	 * @return
	 */
	public static AppTypeEnum getAppType(String orgNumber){
		int start = orgNumber.indexOf('_');
		int end = orgNumber.lastIndexOf('_');
		String type =  orgNumber.substring(start+1,end);
		return (AppTypeEnum)EnumUtils.getEnumByValue(type, AppTypeEnum.values());
	}
	
	/**
	 * 根据orgNumber 获取平台的类型   android,ios,wechat,web,wap
	 * @param orgNumber
	 * @return
	 */
	 public static PlatformEnum getPlatform(String orgNumber)
	  {
	    String type = orgNumber.substring(orgNumber.lastIndexOf('_') + 1);
	    return (PlatformEnum)EnumUtils.getEnumByValue(type, PlatformEnum.values());
	 }
	 
	 /**
	  * 根据appkey 获取机构的类型 app third
	  * @param orgNumber
	  * @return
	  */
	 public static OrgTypeEnum getOrgType(String orgNumber)
	  {
	    String type = orgNumber.substring(0,orgNumber.indexOf('_'));
	    return (OrgTypeEnum)EnumUtils.getEnumByValue(type, OrgTypeEnum.values());
	 }
	
	/**
	 * 是否为理财师客户端
	 * @param orgNumber
	 * @return
	 */
	public static boolean isChannelApp(String orgNumber){
		AppTypeEnum appType = getAppType(orgNumber);
		return AppTypeEnum.CHANNEL.equals(appType);
	}
	
	/**
	 * 是否为投资者客户端
	 * @param orgNumber
	 * @return
	 */
	public static boolean isInvestorApp(String orgNumber){
		AppTypeEnum appType = getAppType(orgNumber);
		return AppTypeEnum.INVESTOR.equals(appType);
	}
	
}
