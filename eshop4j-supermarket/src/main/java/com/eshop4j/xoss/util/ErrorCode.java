package com.eshop4j.xoss.util;

public class ErrorCode {
	private int code; //错误码
	private String desc; //描述
	public ErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	public static final ErrorCode OP_SUCCESS = new ErrorCode(0, "成功");
}