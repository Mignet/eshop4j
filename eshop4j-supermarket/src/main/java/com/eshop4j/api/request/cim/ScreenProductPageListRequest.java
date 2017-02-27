package com.eshop4j.api.request.cim;

import com.eshop4j.core.base.BaseEntity;

public class ScreenProductPageListRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构编码
	 */
	private String orgNumber;
	/**
	 * APP类型  investor,channel
	 */
	private String appKind;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 产品最小期限
	 */
	private String minDeadLine;
	/**
	 * 产品最大期限
	 */
	private String maxDeadLine;
	/**
	 * 最小年化收益
	 */
	private String minYearProfit;
	/**
	 * 最大年化收益
	 */
	private String maxYearProfit;
	/**
	 * 平台背景
	 */
	private String platformContext;
	/**
	 * 排序
	 */
	private Integer sort;	
	/**
	 * 顺序
	 */
	private Integer order;
	/**
	 * 是否拥有灰度权限 true=拥有灰度权限  false=没有灰度权限
	 */
	private Boolean ifHaveGray;
	/**
	 * 安全等级
	 */
	private String securityLevel;
	
	public String getMinDeadLine() {
		return minDeadLine;
	}

	public void setMinDeadLine(String minDeadLine) {
		this.minDeadLine = minDeadLine;
	}

	public String getMaxDeadLine() {
		return maxDeadLine;
	}

	public void setMaxDeadLine(String maxDeadLine) {
		this.maxDeadLine = maxDeadLine;
	}

	public String getMinYearProfit() {
		return minYearProfit;
	}

	public void setMinYearProfit(String minYearProfit) {
		this.minYearProfit = minYearProfit;
	}

	public String getMaxYearProfit() {
		return maxYearProfit;
	}

	public void setMaxYearProfit(String maxYearProfit) {
		this.maxYearProfit = maxYearProfit;
	}

	public String getPlatformContext() {
		return platformContext;
	}

	public void setPlatformContext(String platformContext) {
		this.platformContext = platformContext;
	}

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
}
