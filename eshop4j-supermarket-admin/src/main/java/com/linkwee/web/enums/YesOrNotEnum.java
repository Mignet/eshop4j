package com.linkwee.web.enums;

import com.linkwee.core.base.BaseEnum;

public enum YesOrNotEnum implements BaseEnum{
	YES(1,"是"),
	NOT(0,"否");
	
	YesOrNotEnum(int code,String message){
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
