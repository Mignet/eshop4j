package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum IsQuotaEnum implements KeyValueEnum {
	
	限额(1,"限额"),
	不限额(2,"不限额");
	
	private IsQuotaEnum(int key, String value) {
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
