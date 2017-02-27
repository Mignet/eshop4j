package com.eshop4j.web.request.orgInfo;

import com.eshop4j.core.base.BaseEntity;

public class OrgRecommendChooseRequest extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *机构编码
     */
	private String orgCode;
    /**
     *查询内容（姓名或手机号）
     */
	private String searchValue;
	/**
	 * 理财师userId
	 */
	private String userId;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
