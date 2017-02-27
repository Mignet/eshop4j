package com.xiaoniu.mybatis.paginator.domain;

import java.io.Serializable;
import java.util.List;

public class PageResponse implements Serializable{
	
	private static final long serialVersionUID = -5941654069080564384L;
	private int total;
	private List<?> rows ;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
