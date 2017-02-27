package com.linkwee.web.enums.cim;

import com.linkwee.core.base.KeyValueEnum;

public enum ProductTypeEnums implements KeyValueEnum {
	
	P2P(1,"P2P"),
	信托(2,"信托"),
	资管(3,"资管"),
	基金(4,"基金"),
	公募基金(401,"公募基金"),
	阳光私募(402,"阳光私募"),
	股权基金(403,"股权基金"),
	保险(5,"保险"),
	众筹(6,"众筹"),
	其他(999,"其他");
	
	private ProductTypeEnums(int key, String value) {
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
