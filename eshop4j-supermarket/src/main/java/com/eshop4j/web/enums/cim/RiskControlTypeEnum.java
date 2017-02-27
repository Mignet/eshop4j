package com.eshop4j.web.enums.cim;

import com.eshop4j.core.base.KeyValueEnum;

public enum RiskControlTypeEnum implements KeyValueEnum {
	
	抵押(1,"抵押"),
	担保(2,"担保"),
	信贷(3,"信贷");
	
	private RiskControlTypeEnum(int key, String value) {
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
