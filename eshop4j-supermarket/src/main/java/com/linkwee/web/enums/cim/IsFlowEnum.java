package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum IsFlowEnum implements KeyValueEnum {
	
	固定利率(1,"固定利率"),
	浮动利率(2,"浮动利率");
	
	private IsFlowEnum(int key, String value) {
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
