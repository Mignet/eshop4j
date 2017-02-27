package com.linkwee.web.enums;

public enum RequestTypeEnums {

	GET("GET"),
	POST("POST");
	
	RequestTypeEnums(String value){
		this.value = value;
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
