package com.linkwee.web.model;

 import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人：ZhongLing
 * 
 * @创建时间：2015年12月17日 09:49:11
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Easemob extends BaseEntity{
	private static final long serialVersionUID = -3395870495767544870L;
	private Integer id;
	/**
	 * 用户id
	 */
	private String customerId;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 环信密码
	 */
	private String easemobPassword;
	/**
	 * 是否注册成功；成功1、失败0
	 */
	private int status;
	/**
	 * 是否设置实名昵称；成功、失败0
	 */
	private int nickNameStatus;
	public int getNickNameStatus() {
		return nickNameStatus;
	}
	public void setNickNameStatus(int nickNameStatus) {
		this.nickNameStatus = nickNameStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEasemobAcct() {
		return easemobAcct;
	}
	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}
	public String getEasemobPassword() {
		return easemobPassword;
	}
	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
}

