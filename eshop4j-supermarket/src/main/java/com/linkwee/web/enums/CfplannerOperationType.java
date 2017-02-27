package com.linkwee.web.enums;

import com.linkwee.core.base.BaseEnum;

/**
 * 
 * @描述：理财师操作类型
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum CfplannerOperationType implements BaseEnum{
	QUIT_CFPLANNER(1,"退出理财师"),//1退出理财师
	CHANGE_PARENT(2,"绑定上级"),
	REMOVE_PARENT(3,"解绑上级");
	
	CfplannerOperationType(int code,String message){
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
