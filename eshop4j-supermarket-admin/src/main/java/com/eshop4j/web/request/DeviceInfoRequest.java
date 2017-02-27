package com.eshop4j.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class DeviceInfoRequest {
	
	/**
	 * 设备唯一标识
	 */
	private String deviceId; 
	
	/**
	 * 设备唯一标识 如iPhone 、HTC 603e
	 */
	private String deviceModel; 
	
	/**
	 * 系统版本，如8.1
	 */
	private String systemVersion;
	
	/**
	 * 设备唯一标识
	 */
	private String deviceToken;
	
	/**
	 * 设备分辨率，如1242*2208
	 */
	private String resolution;
	
	/**
	 * 微信关联号(不是通过微信访问，不用填)
	 */
	private String unionid;
	
	/**
	 * 微信授权票据(不是通过微信访问，不用填)
	 */
	private String accessToken;
	
	/**
	 * 微信用户普通标识(不是通过微信访问，不用填)
	 */
	private String openid;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
