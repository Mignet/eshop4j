package com.eshop4j.web.enums.cim;

import com.eshop4j.core.base.KeyValueEnum;

public enum AuditStatusEnum implements KeyValueEnum {
	
	审核通过(1,"审核通过"),
	待审核(2,"待审核"),
	审核未通过(3,"审核未通过");
	
	private AuditStatusEnum(int key, String value) {
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
