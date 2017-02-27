package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 操作类型
 */
public enum OptypeEnum implements KeyValueEnum{
	/**
	 * 登录
	 */
	REGISTER(1,"注册"),
	/**
	 * 申购
	 */
	BUY(2,"申购"),
	/**
	 * 赎回
	 */
	BACK(3,"赎回");

	private int key;
	private String value;

	OptypeEnum(int key,String value){
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
