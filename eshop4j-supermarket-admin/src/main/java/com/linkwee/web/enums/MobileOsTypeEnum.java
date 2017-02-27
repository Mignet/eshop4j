package com.linkwee.web.enums;

public enum MobileOsTypeEnum {
	
	ANDROID("android","安卓系统"),
	IOS("ios","ios系统");
	
	MobileOsTypeEnum(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	

}
