package com.eshop4j.web.enums.cim;

import com.eshop4j.core.base.KeyValueEnum;

public enum MoneyTypeEnum implements KeyValueEnum {
	
	RMB(1,"RMB"),
	港币(2,"港币"),
	美元(3,"美元");
	
	private MoneyTypeEnum(int key, String value) {
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
