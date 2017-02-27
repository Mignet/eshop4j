package com.linkwee.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：app来源
 *
 * @author Bob
 * @时间  2015年10月16日下午4:04:48
 *
 */
public class AppSource extends BaseEntity{

	private static final long serialVersionUID = -8079343375826948242L;
	
	/**
	 * app版本号
	 */
	private String appVersion;

	/**
	 * 来源类型
	 */
	private String sourceType;

	/**
	 * 系统类型
	 */
	private String systemType;

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
