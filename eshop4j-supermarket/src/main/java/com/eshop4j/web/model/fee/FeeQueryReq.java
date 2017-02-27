package com.eshop4j.web.model.fee;

import com.eshop4j.core.base.BaseEntity;

public class FeeQueryReq extends BaseEntity {
	private static final long serialVersionUID = -2032335658133004657L;
	private String saleuser; //业务员
	private int page; //页码
	private int rowperpage; //每页记录数，默认25
	public String getSaleuser() {
		return saleuser;
	}
	public void setSaleuser(String saleuser) {
		this.saleuser = saleuser;
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
}