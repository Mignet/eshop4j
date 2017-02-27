package com.eshop4j.web.model;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

/**
 * 用户设备信息
 * 
 * @author xuzhao
 * @Date 2016年3月10日 下午5:12:35
 */
public class CustomerDevice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String memberNo;
	private String token;
	private String deviceId;
	private String deviceType;
	private String deviceDetail;
	private String deviceResolution;
	private String systemVersion;
	private String appversion;
	private Date createDate;
	private Date loginLasttime;
	private Integer appType;
	private Integer isSendplatformnotice;
	private Integer isSendinteractnotice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceResolution() {
		return deviceResolution;
	}

	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginLasttime() {
		return loginLasttime;
	}

	public void setLoginLasttime(Date loginLasttime) {
		this.loginLasttime = loginLasttime;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}

	public Integer getIsSendplatformnotice() {
		return isSendplatformnotice;
	}

	public void setIsSendplatformnotice(Integer isSendplatformnotice) {
		this.isSendplatformnotice = isSendplatformnotice;
	}

	public Integer getIsSendinteractnotice() {
		return isSendinteractnotice;
	}

	public void setIsSendinteractnotice(Integer isSendinteractnotice) {
		this.isSendinteractnotice = isSendinteractnotice;
	}

	
}
