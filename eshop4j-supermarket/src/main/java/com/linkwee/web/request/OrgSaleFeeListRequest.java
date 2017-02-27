package com.linkwee.web.request;


public class OrgSaleFeeListRequest {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -4977432770614746364L;
	
	
    /**
     *活动图标
     */
	private String startDate;
	
    /**
     *活动链接
     */
	private String endDate;
    /**
     *机构编码-固定50位编码，不重复字段
     */
	private String orgNumber;

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

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
