package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

public class WeiXinMsgResponse extends BaseEntity {
	
	private static final long serialVersionUID = -1816148675364562127L;

	/**
	 *  access_token
	 */
	private String	access_token;
	
	/**
	 * 实效
	 */
	private String expires_in;
	
	/**
	 *  refresh_token
	 */
	private String refresh_token;
	
	/**
	 *  用户微信openId
	 */
	private String openid;
	
	/**
	 *  scope
	 */
	private String scope;
	
	/**
	 *  错误CODE
	 */
	private String errcode;
	
	/**
	 *  错误信息
	 */
	private String errmsg;
	
	
	/**
	 *  发送消息后返回的ID
	 */
	private String msgid;


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


	public String getRefresh_token() {
		return refresh_token;
	}


	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getScope() {
		return scope;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


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


	public String getMsgid() {
		return msgid;
	}


	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}




}
