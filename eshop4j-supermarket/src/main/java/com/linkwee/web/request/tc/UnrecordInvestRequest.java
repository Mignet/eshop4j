package com.linkwee.web.request.tc;

import com.linkwee.core.datatable.DataTable;

public class UnrecordInvestRequest extends DataTable{
	
	private Integer status;
	private String mobile;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
