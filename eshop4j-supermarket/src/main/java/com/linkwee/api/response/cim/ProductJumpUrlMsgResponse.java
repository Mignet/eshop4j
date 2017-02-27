package com.linkwee.api.response.cim;

import com.linkwee.core.base.BaseEntity;



public class ProductJumpUrlMsgResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7488647949540355219L;
	
	private String thirdProductId; //合作机构产品id
	
	private String orgAccount; //合作机构用户账户名
	private String orgNumber; //机构编码
	private String orgKey; //机构明钥
	private String timestamp; //时间戳
	private String sign; //机构签名
	private String orgProductUrl; //机构产品跳转链接
	
	
	
	
	public String getOrgProductUrl() {
		return orgProductUrl;
	}

	public void setOrgProductUrl(String orgProductUrl) {
		this.orgProductUrl = orgProductUrl;
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

	public String getThirdProductId() {
		return thirdProductId;
	}

	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}
}
