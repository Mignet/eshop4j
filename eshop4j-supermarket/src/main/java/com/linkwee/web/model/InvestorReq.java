package com.linkwee.web.model;

import com.linkwee.core.base.BaseEntity;
public class InvestorReq extends BaseEntity {
	private static final long serialVersionUID = -4740428737450779679L;
	private String customer; //客户 名称或是手机号码
	private String regTimeStart; //注册开始时间
	private String regTimeEnd;//注册结束时间
	private int page = 1; //页码
	private int rows = 10; //每页记录数，默认10
	private boolean paging = true;
	
	private int startIndex;
	private int endIndex;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStartIndex() {
		return ((page <= 0 ? 1 : page) -1 ) * (rows <= 0 ? 10 :rows);
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return rows;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getRegTimeStart() {
		return regTimeStart;
	}
	public void setRegTimeStart(String regTimeStart) {
		this.regTimeStart = regTimeStart;
	}
	public String getRegTimeEnd() {
		return regTimeEnd;
	}
	public void setRegTimeEnd(String regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}
	
	public boolean isPaging() {
		return paging;
	}
	public void setPaging(boolean paging) {
		this.paging = paging;
	}
	@Override
	public String toString() {
		return "InvestorReq [customer=" + customer + ", regTimeStart="
				+ regTimeStart + ", regTimeEnd=" + regTimeEnd + ", page="
				+ page + ", rows=" + rows + ", startIndex=" + startIndex
				+ ", endIndex=" + endIndex + "]";
	}
	
	
}
