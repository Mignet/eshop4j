package com.eshop4j.web.model.cim;

import java.io.Serializable;

public class CimOrginfoBindSelect implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1692385286691770786L;
	
	private String orgName; //机构名称
	private String orgNumber; //机构编号

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	
}
