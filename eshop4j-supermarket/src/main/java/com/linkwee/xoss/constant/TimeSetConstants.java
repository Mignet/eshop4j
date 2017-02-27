package com.linkwee.xoss.constant;

public class TimeSetConstants {

	/**
	 * token有效期	2天	2*24*60*60*1000
	 */
	public static final long TOKEN_VALID_DATE = 2*24*60*60*1000;
	
	/**
	 * 短信验证码有效期	有效期15分钟	5*60*1000
	 */
	public static final long MSGVERIFYCODE_VALID_DATE = 15*60*1000;
	/**
	 * 超过三次短信验证码 获取时间间隔	 2小时	2*60*60*1000
	 */
	public static final long MSGGAIN_WAIT_TIME = 2*60*60*1000;
	/**
	 * 验证银行名称 
	 */
	public static final long BANKCODE_VALID_DATE = 1*60*1000;
	/**
	 * orgSecret有效期	7天	7*24*60*60*1000
	 */
	public static final long ORGSECRET_VALID_DATE = 7*24*60*60*1000;
}
