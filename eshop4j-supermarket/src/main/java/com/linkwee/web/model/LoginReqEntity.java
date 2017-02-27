package com.linkwee.web.model;

import com.linkwee.core.base.BaseEntity;

public class LoginReqEntity extends BaseEntity {
	private static final long serialVersionUID = -3376448639872011553L;
	private String user;
	private String pwd;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
