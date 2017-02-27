package com.linkwee.web.model.fee;

import java.util.List;

import com.linkwee.core.base.BaseEntity;

public class FeePayResultQueryResp extends BaseEntity {
	private static final long serialVersionUID = -2798940443879802782L;
	private List<FeePay> resultlist;
	private int totalRows; //总记录
	public List<FeePay> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<FeePay> resultlist) {
		this.resultlist = resultlist;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
}