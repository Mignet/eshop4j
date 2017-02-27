package com.eshop4j.api.response.cim;

import com.eshop4j.core.base.BaseEntity;



public class UserCenterJumpUrlMsgResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5344346780226808957L;
	
	
	/**
	 * 平台用户中心跳转地址
	 */
	private String orgUsercenterUrl;
	/**
	 * 用户第三方机构用户账号
	 */
	private String orgAccount; 
	
	private String orgNumber; //机构编码
	private String orgKey; //机构明钥
	private String timestamp; //时间戳
	private String sign; //机构签名
	
	
	
	
	

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrgUsercenterUrl() {
		return orgUsercenterUrl;
	}

	public void setOrgUsercenterUrl(String orgUsercenterUrl) {
		this.orgUsercenterUrl = orgUsercenterUrl;
	}

	
	
}
