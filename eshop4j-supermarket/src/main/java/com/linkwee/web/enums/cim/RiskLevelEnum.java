package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum RiskLevelEnum implements KeyValueEnum {
	
	一般(1,"一般"),
	重要(2,"重要"),
	紧急(3,"紧急"),
	非常紧急(4,"非常紧急");
	
	private RiskLevelEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	private int key;
	private String value;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
