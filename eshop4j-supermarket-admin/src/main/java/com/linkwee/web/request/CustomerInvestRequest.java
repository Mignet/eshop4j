package com.linkwee.web.request;

import com.linkwee.core.datatable.DataTable;

public class CustomerInvestRequest extends DataTable{
	
	private String nameOrMobile;
	
	private String platfrom;
	
	private String userId;
	

	public String getNameOrMobile() {
		return nameOrMobile;
	}

	public void setNameOrMobile(String nameOrMobile) {
		this.nameOrMobile = nameOrMobile;
	}

	public String getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
