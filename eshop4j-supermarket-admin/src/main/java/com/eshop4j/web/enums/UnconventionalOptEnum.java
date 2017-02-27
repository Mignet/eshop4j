package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

public enum UnconventionalOptEnum implements KeyValueEnum {
	/**
	 * 登录
	 */
	LCSROLLBACK(1, "理财师回退业务员"),
	/**
	 * 申购
	 */
	SALESMANREGISTER(2, "业务员注册");

	private int key;
	private String value;

	UnconventionalOptEnum(int key, String value) {
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
