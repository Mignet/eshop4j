package com.eshop4j.web.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.SmAppVersion;

/**
 * 
 * @描述：产品库-推荐精选
 *
 * @author 何源
 * @时间 2015年8月6日上午10:23:39
 *
 */
public class DownloadAppListResponse extends BaseEntity {
	private static final long serialVersionUID = -5495927906939029196L;

	public DownloadAppListResponse() {

	}

	public DownloadAppListResponse(SmAppVersion smAppVersion) {
		WebUtil.initObj(this, smAppVersion,DateUtils.FORMAT_LONG);
	}

	/**
	 * 版本名称
	 */
	private String name;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 平台:ios,android
	 */
	private String platform;

	/**
	 * 下载地址
	 */
	private String downloadUrl;

	/**
	 * 发布时间 2015-12-12 14:15:12
	 */
	private String issueTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
