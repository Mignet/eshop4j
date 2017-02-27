package com.linkwee.web.request.orgInfo;

import com.linkwee.core.base.BaseEntity;

public class OrgRecommendByChooseRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *机构编码
     */
	private String orgCode;
	
    /**
     *用户userIdString  多个 用,分割
     */
	private String userIdString;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserIdString() {
		return userIdString;
	}

	public void setUserIdString(String userIdString) {
		this.userIdString = userIdString;
	}
}
