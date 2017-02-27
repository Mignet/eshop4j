package com.eshop4j.web.model.cim;

import java.util.Date;

import com.eshop4j.core.datatable.DataTable;

public class CimOrgDynamicDataTable extends DataTable {
	
	private String orgName;
	private Date startTime;
	private Date endTime;
	private String orgTitle;
	
	

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrgTitle() {
		return orgTitle;
	}

	public void setOrgTitle(String orgTitle) {
		this.orgTitle = orgTitle;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	
	
}
