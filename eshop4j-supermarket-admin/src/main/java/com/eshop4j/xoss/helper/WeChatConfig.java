package com.eshop4j.xoss.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class WeChatConfig {
	
	/**
	 * 微信公众号appId
	 */
	private String appid;
	
	/**
	 * 微信公众号secret
	 */
	private String appSecret;
	
	/**
	 * 微信获取accessToken接口
	 */
	private String urlAccessToken;
	
	/**
	 * 微信获取用户信息接口
	 */
	private String urlUserInfo;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getUrlAccessToken() {
		return urlAccessToken;
	}
	public void setUrlAccessToken(String urlAccessToken) {
		this.urlAccessToken = urlAccessToken;
	}
	public String getUrlUserInfo() {
		return urlUserInfo;
	}
	public void setUrlUserInfo(String urlUserInfo) {
		this.urlUserInfo = urlUserInfo;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
