package com.eshop4j.web.enums;

import com.eshop4j.core.base.KvmEnum;

/**
 * 
 * @描述：合伙人等级
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum PartnerLevelEnum implements KvmEnum{
	JUNIOR(1,"JUNIOR","合伙人"),
	HIGH(2,"HIGH","高级合伙人"),
	SENIOR(3,"SENIOR","资深合伙人");
	
	PartnerLevelEnum(int key,String value,String msg){
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
