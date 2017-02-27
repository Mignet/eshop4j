package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

public enum SmsChannelTypeEnum implements KeyValueEnum{
	/**
	 * 1功能类(默认)，2营销类，3告警类
	 */
	VERIFICATION(1,"验证码"),
	PROMOTION(2,"营销短信"),
	WARNING(3,"警告类");
	
	SmsChannelTypeEnum(int key,String value){
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
