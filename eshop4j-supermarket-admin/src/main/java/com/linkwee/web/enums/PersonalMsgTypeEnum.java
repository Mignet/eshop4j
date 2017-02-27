package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

/**
 * 站内消息-个人消息类型
 * 
 * @author chenchy
 * @Date 2016年10月19日 下午3:26:52
 */
public enum PersonalMsgTypeEnum implements KvmEnum{
	/**
	 * 投资端  注册成功
	 */
	CREGISTER(1," 注册成功",""),
	/**
	 * 投资端  邀请用户
	 */
	INVITATIONINV(2,"邀请用户",""),
	/**
	 * 投资端 项目回款
	 */
	PAYMENTRETURN(3,"项目回款",""),
	/**
	 * 投资端 提现
	 */
	WITHDRAWALS_INV(4,"提现",""),
	/**
	 * 投资端  解绑理财师
	 */
	UNBUNDINGLCS_INV(5,"解绑理财师",""),
	
	/**
	 * 投资端   重绑理财师
	 */
	REBUNDINGLCS_INV(6,"重绑理财师",""),
	/**
	 * 投资端   红包(收到系统、理财师红包;红包过期)
	 */
	REDPACKET_INV(7,"红包",""),
	/**
	 * 投资端   项目投资
	 */
	PROJECTINVEST_INV(8,"项目投资",""),
	/**
	 * 投资端  平台绑定
	 */
	BUNDINGORG_INV(9,"平台绑定","");
	
	private int key;
	private String value;
	private String msg;//app跳转页url

	PersonalMsgTypeEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

	

	
	
}
