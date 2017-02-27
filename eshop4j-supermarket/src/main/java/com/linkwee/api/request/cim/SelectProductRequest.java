package com.linkwee.api.request.cim;

import com.linkwee.core.base.BaseEntity;

public class SelectProductRequest extends BaseEntity {

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
}
