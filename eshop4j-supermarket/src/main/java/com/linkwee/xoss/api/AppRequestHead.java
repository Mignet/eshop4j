package com.linkwee.xoss.api;

public class AppRequestHead {
	
	//app标示  服务端分配  根据不同的app分配不同的密钥(App_investor_web)
	private String orgNumber;
	//APP类型  investor,channel
	private String appKind;
	//APP客户端 android,ios,wechat,wap,web...
	private String appClient;
	//app版本号
	private String appVersion;
	//api版本号
	private String v;
	//时间戳		格式: 2015-09-21 17:06:01
	private String timestamp;
	//数字签名
	private String sign;
	//App token  某些方法需要，非必须
	private String token;
	
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
	public String getAppClient() {
		return appClient;
	}
	public void setAppClient(String appClient) {
		this.appClient = appClient;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
