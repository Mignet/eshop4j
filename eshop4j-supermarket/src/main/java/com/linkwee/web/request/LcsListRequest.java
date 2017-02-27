package com.linkwee.web.request;

import com.linkwee.core.base.api.PaginatorRequest;

public class LcsListRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614746364L;
	
	
	private String startDate;
	private String endDate;
	private String nameOrmobile;
	
	private String name;
	private String mobile;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getNameOrmobile() {
		return nameOrmobile;
	}
	public void setNameOrmobile(String nameOrmobile) {
		this.nameOrmobile = nameOrmobile;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
