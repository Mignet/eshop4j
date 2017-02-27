package com.eshop4j.web.enums;

import com.eshop4j.core.base.KvmEnum;

public enum ActicityRedPacketEnum implements KvmEnum{
	NEWYEAR_WHEEL_68(3,"NEWYEAR_WHEEL_68","68元投资返现红包"),//春节大转盘68元投资返现红包
	NEWYEAR_WHEEL_88(4,"NEWYEAR_WHEEL_88","88元投资返现红包");//春节大转盘88元投资返现红包
	
	private int key;
	private String value;
	private String msg;

	ActicityRedPacketEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

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