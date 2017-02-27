package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum RcTypeEnum implements KeyValueEnum{
	
	WECHAT_RC(1,"微信推荐"),
	UPGRADE_SIGNATURE(2,"内部签名"),
	UPGRADE_ANONYMOUS(3,"内部匿名"),
	MAILLIST(4,"通讯录邀请");
	
	RcTypeEnum(int key,String value){
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
