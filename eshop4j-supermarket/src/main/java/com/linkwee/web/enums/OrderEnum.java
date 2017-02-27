package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum OrderEnum implements KeyValueEnum{
	DESC(1,"desc"),//降序
	ASC(2,"asc");//升序
	
	OrderEnum(int key,String value){
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
