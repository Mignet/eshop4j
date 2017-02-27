package com.eshop4j.web.request.tc;

import com.eshop4j.core.datatable.DataTable;

public class FeeDetailRequest extends DataTable{
	
	private String mobile;
	
	private String customerMobile;
	
	private String month;
	
	private String time;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
