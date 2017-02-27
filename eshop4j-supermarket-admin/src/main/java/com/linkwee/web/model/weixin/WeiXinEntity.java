package com.linkwee.web.model.weixin;

import java.io.Serializable;

public class WeiXinEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//微信accToken
	private String access_token;
	//微信accToken时效(7200秒)
	private String expires_in;
	//错误code
	private String errcode;
	//错误信息
	private String errmsg;
	

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
	
}
