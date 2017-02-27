package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum IsRedemptionEnum implements KeyValueEnum {
	
	不支持赎回和转让(0,"不支持赎回和转让"),
	可赎回(1,"可赎回"),
	可转让(2,"可转让"),
	可赎回且可转让(3,"可赎回且可转让");
	
	private IsRedemptionEnum(int key, String value) {
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
