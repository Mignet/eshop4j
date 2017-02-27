package com.eshop4j.web.model.fee;

import java.util.List;

import com.eshop4j.core.base.BaseEntity;

public class FeePayResultQueryReq extends BaseEntity {
	private static final long serialVersionUID = -4835828552999921479L;
	public static enum QueryTpye {
		ALL, SUCCESS, FAIL, INIT, SENDING
	}
	private String month; //年月
	private List<String> saleuserlist; //业务员列表
	private int queryType =-1;
	private int page; //页码
	private int rowperpage; //每页记录数
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<String> getSaleuserlist() {
		return saleuserlist;
	}
	public void setSaleuserlist(List<String> saleuserlist) {
		this.saleuserlist = saleuserlist;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowperpage() {
		return rowperpage;
	}
	public void setRowperpage(int rowperpage) {
		this.rowperpage = rowperpage;
	}
	public int getQueryType() {
		return queryType;
	}
	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}
}