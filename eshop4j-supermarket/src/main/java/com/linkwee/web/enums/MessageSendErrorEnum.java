package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum MessageSendErrorEnum implements KeyValueEnum{
	/**
	 * 返回错误编号	错误说明
-1	参数为空。信息、电话号码等有空指针，登陆失败
-12	有异常电话号码
-14	实际号码个数超过100
-999	服务器内部错误
-10001	用户登陆不成功(帐号不存在/停用/密码错误)
-10003	用户余额不足
-10011	信息内容超长
-10029	此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)
-10030	不能发送移动号码
-10031	手机号码(段)非法
-10057	IP受限
-10056	连接数超限 
	 */
	LOGINERROR(-1,"参数为空。信息、电话号码等有空指针，登陆失败"),
	MOBILEERROR(-12,"有异常电话号码"),
	MOBILECOUNTOVERFLOW(-14,"实际号码个数超过100"),
	SERVERINNERERROR(-999,"服务器内部错误"),
	ACCOUNTERROR(-10001,"用户登陆不成功(帐号不存在/停用/密码错误)"),
	OUTOFBALANCEERROR(-10003,"用户余额不足"),
	CONTENTTOLONG(-10011,"信息内容超长"),
	PORTERROR(-10029,"此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)"),
	FOBIDENMOBILEERROR(-10030,"不能发送移动号码"),
	MOBILEILLEGALEERROR(-10031,"手机号码(段)非法"),
	IPLIMITERROR(-10057,"IP受限"),
	CONNECTOUTERROR(-10056,"连接数超限");
	
	
	
	MessageSendErrorEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
