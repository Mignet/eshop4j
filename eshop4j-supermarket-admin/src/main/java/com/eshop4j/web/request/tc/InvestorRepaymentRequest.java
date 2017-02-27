package com.eshop4j.web.request.tc;

import com.eshop4j.core.datatable.DataTable;

public class InvestorRepaymentRequest extends DataTable{
	/**
	 * 0=用户|1=理财师
	 */
	private Integer type=0;
	private String mobileOrName;
	
	/**
	 * 0=昨天|1=最近3天|2=最近7天|3=最近30天
	 */
	private Integer dateType=1;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMobileOrName() {
		return mobileOrName;
	}
	public void setMobileOrName(String mobileOrName) {
		this.mobileOrName = mobileOrName;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	
	

}
