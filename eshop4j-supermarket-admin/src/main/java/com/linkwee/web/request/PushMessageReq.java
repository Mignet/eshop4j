package com.linkwee.web.request;

import java.util.Map;

import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;

public class PushMessageReq {
	private AppTypeEnum appType;
	private SmsTypeEnum smsType;
	private String deviceToken;
	private String userId;
	private String title;
	private String content;
	private Map<String, Object> urlparam;
	public AppTypeEnum getAppType() {
		return appType;
	}
	public void setAppType(AppTypeEnum appType) {
		this.appType = appType;
	}
	public SmsTypeEnum getSmsType() {
		return smsType;
	}
	public void setSmsType(SmsTypeEnum smsType) {
		this.smsType = smsType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, Object> getUrlparam() {
		return urlparam;
	}
	public void setUrlparam(Map<String, Object> urlparam) {
		this.urlparam = urlparam;
	}
	
	
	

}
