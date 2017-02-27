package com.eshop4j.web.enums;


/**
 * 
 * @描述：投资者新手任务
 *
 *
 */
public enum InvestorNewcomerTaskEnum 
{
	INVESTOR_INVITE_CUSTOMER("INVESTOR_INVITE_CUSTOMER","邀请客户"),
	FIRST_INVEST("FIRST_INVEST","首次投资"),
	BIND_CARD("BIND_CARD","绑卡认证");
	
	
	InvestorNewcomerTaskEnum(String code,String message){
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
