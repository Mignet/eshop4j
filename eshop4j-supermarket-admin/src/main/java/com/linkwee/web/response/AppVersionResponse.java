package com.linkwee.web.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.SmAppVersion;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述 客户端appversion
 *
 * @author hxb
 * @时间 
 *
 */
public class AppVersionResponse extends BaseEntity {
	private static final long serialVersionUID = -5495927906939029196L;

	public AppVersionResponse() {

	}

	public AppVersionResponse(SmAppVersion smAppVersion) {
		WebUtil.initObj(this, smAppVersion,DateUtils.FORMAT_LONG);
		this.setOpenReg(smAppVersion.getOpenReg() == null
				|| smAppVersion.getOpenReg() == 0);
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
	 * 支持的最低版本
	 */
	private String minVersion;

	/**
	 * 下载地址
	 */
	private String downloadUrl;

	/**
	 * 注册提示
	 */
	private String regHints;

	/**
	 * 更新内容(多条内容用;号分割)
	 */
	private String updateHints;

	/**
	 * 是否开放注册(0开放,1不开放)
	 */
	private Boolean openReg = true;

	/**
	 * 升级(0不需要升级,1提示升级,2强制升级)
	 */
	private String upgrade;

	/**
	 * 发布时间 2015-12-12 14:15:12
	 */
	private String issueTime;

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

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

	public String getMinVersion() {
		return minVersion;
	}

	public void setMinVersion(String minVersion) {
		this.minVersion = minVersion;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getRegHints() {
		return regHints;
	}

	public void setRegHints(String regHints) {
		this.regHints = regHints;
	}

	public String getUpdateHints() {
		return updateHints;
	}

	public void setUpdateHints(String updateHints) {
		this.updateHints = updateHints;
	}

	public Boolean getOpenReg() {
		return openReg;
	}

	public void setOpenReg(Boolean openReg) {
		this.openReg = openReg;
	}

	public String getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
