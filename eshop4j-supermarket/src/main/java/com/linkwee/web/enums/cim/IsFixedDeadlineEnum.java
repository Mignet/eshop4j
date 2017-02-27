package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum IsFixedDeadlineEnum implements KeyValueEnum {
	
	固定期限(1,"固定期限"),
	浮动期限(2,"浮动期限");
	
	private IsFixedDeadlineEnum(int key, String value) {
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
