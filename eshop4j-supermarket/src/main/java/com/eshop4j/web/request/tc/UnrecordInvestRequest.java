package com.eshop4j.web.request.tc;

import com.eshop4j.core.datatable.DataTable;

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
