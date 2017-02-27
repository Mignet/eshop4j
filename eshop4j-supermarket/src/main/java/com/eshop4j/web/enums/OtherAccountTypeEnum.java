package com.eshop4j.web.enums;

import com.eshop4j.core.base.BaseEnum;
/**
 * 
 * @描述：第三方登录类别
 *
 * @author Bob
 * @时间  2015年8月20日下午1:55:39
 *
 */
public enum OtherAccountTypeEnum implements BaseEnum{
	WECHAT(1,"微信");
	
	OtherAccountTypeEnum(int code,String message){
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
