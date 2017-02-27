package com.linkwee.web.request.tc;


public class TcFeeDistributionRequest {

	private String orgInfo;
	private String month;
	private Integer type = 0;
	
	private String start;
	private String end;
	
	public String getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(String orgInfo) {
		this.orgInfo = orgInfo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
}
