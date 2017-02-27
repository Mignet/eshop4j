package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 
 * @描述：消息类别
 *
 * @author Bob
 * @时间  2015年10月27日上午10:27:00
 *
 */
public enum MsgTypeEnum implements KeyValueEnum{
	SYS(0,"系统消息"),
	PERSON(1,"个人消息");
	
	private int key;
	private String value;

	MsgTypeEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}


	public String getValue() {
		return value;
	}

	
}
