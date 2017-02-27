package com.linkwee.web.request;

import com.linkwee.core.base.api.PaginatorRequest;

public class SaleOrgDetailRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614746364L;
	
	
	private String startTimeForSearch;
	private String endTimeForSearch;
	private String nameOrmobile;
	
	private String city;
	private String platform;
	
	private String salesOrgId;
	
	
	
	
	public String getNameOrmobile() {
		return nameOrmobile;
	}
	public void setNameOrmobile(String nameOrmobile) {
		this.nameOrmobile = nameOrmobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getSalesOrgId() {
		return salesOrgId;
	}
	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}
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
	
	
	
	

}
