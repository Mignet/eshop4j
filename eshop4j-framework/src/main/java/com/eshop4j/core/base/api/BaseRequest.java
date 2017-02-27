package com.eshop4j.core.base.api;

import com.eshop4j.core.base.BaseEntity;


/**
 * 
 * @描述：api请求数据
 *
 * @author Bob
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class BaseRequest extends BaseEntity{

	private static final long serialVersionUID = 2339018794015002032L;
	/**
	 * 版本号
	 */
	private String apiVersion;
	/**
	 * 会话
	 */
	private String token;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 设备id
	 */
	private String deviceId;

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "BaseRequest [apiVersion=" + apiVersion + ", token=" + token
				+ ", userId=" + userId + ", deviceId=" + deviceId + "]";
	}
}
