package com.linkwee.web.model.fee;

import java.util.List;

import com.linkwee.core.base.BaseEntity;

public class FeeQueryResp<T> extends BaseEntity {
	private static final long serialVersionUID = -1562986289137314200L;
	private int totalRows; //总记录数
	private List<T> datalist; //数据
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public List<T> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<T> datalist) {
		this.datalist = datalist;
	}
}