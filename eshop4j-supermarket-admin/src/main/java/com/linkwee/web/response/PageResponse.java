package com.linkwee.web.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linkwee.xoss.util.ErrorCode;

public class PageResponse<T> {
	private ErrorCode errorcode;
	private int total;
	private List<T> rows = new ArrayList<T>();
	private List<Map<String,Object>>  footer= new  ArrayList<Map<String,Object>>(); 

	public ErrorCode getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(ErrorCode errorcode) {
		this.errorcode = errorcode;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<Map<String, Object>> getFooter() {
		return footer;
	}

	public void setFooter(List<Map<String, Object>> footer) {
		this.footer = footer;
	}
	
}