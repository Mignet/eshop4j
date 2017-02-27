package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

public enum RegSourceEnum implements KvmEnum{
	QGZ(1,"0001","钱罐子"),
	ZC(2,"0002","投筹"),
	CHANNEL(3,"0003","猎财大师"),
	INVESTOR(4,"0004","T呗");
	
	RegSourceEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	private int key;
	private String value;
	private String msg;
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getMsg() {
		return msg;
	}

}
