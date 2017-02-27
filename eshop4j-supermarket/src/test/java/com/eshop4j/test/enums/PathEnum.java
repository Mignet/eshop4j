package com.eshop4j.test.enums;

public enum PathEnum {
	
	/**
	 * 本地
	 */
	LOCALHOST("localhost","http://localhost:8080/rest"),
	
	/**
	 * 开发
	 */
	DEVELOP("develop","http://10.16.0.205:6810/rest"),
	
	/**
	 * 测试
	 * 
	 */
	TEST("test","http://10.16.0.204:8888/rest"),
	
	/**
	 * 自己测试
	 */
	MyTEST("mytest","http://10.16.3.78:8080/rest"),
	
	/**
	 * 预发布
	 */
	PRERELEASE("preRelease","http://120.76.101.179:6810/rest"),
	
	/**
	 * 线上
	 */
	RELEASE("Release","http://market.toobei.com/rest");
	
	
	
	PathEnum(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	private String key;
	private String value;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
