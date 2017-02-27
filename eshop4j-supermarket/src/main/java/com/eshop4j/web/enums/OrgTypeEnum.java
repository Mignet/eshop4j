package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

public enum OrgTypeEnum implements KeyValueEnum{
	
	APP(1,"app"),
	THIRD(2,"third");
	
	OrgTypeEnum(int key,String value){
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
