package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum PplSortFieldEnum implements KeyValueEnum{
	REGISTER_TIME(1,"registerTime"),//注册时间
	QUARTER_FEE(2,"allowance"),//直接收益
	TOTAL_FEE(3,"childrenAllowance");//间接收益
	PplSortFieldEnum(int key,String value){
		this.key = key;
		this.value = value;
	}
	private int key;
	private String value;
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
}
