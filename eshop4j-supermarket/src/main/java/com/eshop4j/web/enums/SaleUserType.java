package com.eshop4j.web.enums;

import com.eshop4j.core.base.BaseEnum;

/**
 * 
 * @描述：用户类别
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:44
 *
 */
public enum SaleUserType implements BaseEnum{
	SALE(0,"业务员"),
	SYS(1,"系统预置用户");
	
	SaleUserType(int code,String message){
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