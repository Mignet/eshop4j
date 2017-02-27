package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum IsHaveProgressEnum implements KeyValueEnum {
	
	有(0,"有"),
	没有(1,"没有");
	
	private IsHaveProgressEnum(int key, String value) {
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
