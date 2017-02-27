package com.eshop4j.web.enums.cim;

import com.eshop4j.core.base.KeyValueEnum;

public enum InterestWayEnum implements KeyValueEnum {
	
	购买当日(1,"购买当日"),
	购买次日(2,"购买次日"),
	募集完成当日(3,"募集完成当日"),
	募集完成次日(4,"募集完成次日"),
	指定日期(5,"指定日期");
	
	private InterestWayEnum(int key, String value) {
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
