package com.linkwee.web.request;

import com.linkwee.core.base.api.PaginatorRequest;

public class LcsCustomerRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013729355954043856L;
	
	private String lcsNumber;
	private String nameOrmobile;
	private String startDate;
	private String endDate;
	public String getLcsNumber() {
		return lcsNumber;
	}
	public void setLcsNumber(String lcsNumber) {
		this.lcsNumber = lcsNumber;
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


	public String getNameOrmobile() {
		return nameOrmobile;
	}

	public void setNameOrmobile(String nameOrmobile) {
		this.nameOrmobile = nameOrmobile;
	}
}
