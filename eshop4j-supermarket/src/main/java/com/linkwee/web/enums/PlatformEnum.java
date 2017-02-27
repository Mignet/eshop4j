package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum PlatformEnum implements KeyValueEnum{
	/**
	 * 本站
	 */
	LOCAL(0,"local"),
	/**
	 * 微信
	 */
	WECHAT(1,"wechat"),
	/**
	 * 安卓
	 */
	ANDROID(2,"android"),
	/**
	 * IOS
	 */
	IOS(3,"ios"),
	/**
	 * wap
	 */
	WAP(4,"wap"),
	/**
	 * web
	 */
	WEB(5,"web");

	private int key;
	private String value;

	PlatformEnum(int key,String value){
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
