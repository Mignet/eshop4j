package com.eshop4j.api.request.crm;

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
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
