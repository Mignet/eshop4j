package com.eshop4j.web.model;

import com.eshop4j.core.base.BaseEntity;

public class LoginResEntity extends BaseEntity {
	private static final long serialVersionUID = 760144993961282644L;
	private String sessionid; //登录会话
	private String number; //编码
	private String mobile; //手机号码
	private String name; //真实姓名
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
