package com.eshop4j.xoss.api;

public class OpenRequestHead {

	//机构编码,服务端分配
	private String orgNumber;
	//机构公钥,服务端分配
	private String orgKey;
	//时间戳	格式: 2015-09-21 17:06:01
	private String timestamp;
	//数字签名
	private String sign;
	
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
	
	
}
