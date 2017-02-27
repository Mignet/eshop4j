package com.eshop4j.web.enums;

import com.eshop4j.core.base.BaseEnum;

/**
 * 
 * @描述：理财师操作类型
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum InvestorOperationType implements BaseEnum{
	CHANGE_CFPLANNER(1,"绑定理财师"),
	REMOVE_CFPLANNER(2,"解绑理财师"),
	ALLOT_CFPLANNER(3,"系统分配理财师");
	
	InvestorOperationType(int code,String message){
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
