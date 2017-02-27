 package com.eshop4j.web.request;

import com.eshop4j.core.base.api.PaginatorRequest;

public class ListDetailRequest extends PaginatorRequest {

	private static final long serialVersionUID = -4977432770614746364L;
	
    /**
     * 查询开始时间
     */
	private String startTimeForSearch;
	
    /**
     * 查询结束时间
     */
	private String endTimeForSearch;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 产品名称
	 */
	private String name;
	
	/**
	 * 状态
	 */
	private String status;

	public String getStartTimeForSearch() {
		return startTimeForSearch;
	}

	public void setStartTimeForSearch(String startTimeForSearch) {
		this.startTimeForSearch = startTimeForSearch;
	}

	public String getEndTimeForSearch() {
		return endTimeForSearch;
	}

	public void setEndTimeForSearch(String endTimeForSearch) {
		this.endTimeForSearch = endTimeForSearch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	
}
