package com.linkwee.api.request.cim;

import com.linkwee.core.base.BaseEntity;

public class HotProductRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构编码
	 */
	private String orgNumber;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;
	
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
	public Boolean getIfHaveGray() {
		return ifHaveGray;
	}
	public void setIfHaveGray(Boolean ifHaveGray) {
		this.ifHaveGray = ifHaveGray;
	}
}
