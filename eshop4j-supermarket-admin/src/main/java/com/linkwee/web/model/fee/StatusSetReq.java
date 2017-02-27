package com.linkwee.web.model.fee;

import java.util.List;

import com.linkwee.core.base.BaseEntity;

public class StatusSetReq extends BaseEntity {
	private static final long serialVersionUID = -2527073836870755672L;
	private String month; //年月
	private List<String> saleuserlist; //业务员列表
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
}