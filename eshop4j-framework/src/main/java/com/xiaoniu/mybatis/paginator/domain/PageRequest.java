package com.xiaoniu.mybatis.paginator.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	private String orderExpr;
	private Map<String, Object> query = new HashMap<String, Object>();

	private boolean containsTotalCount = true;
	private Boolean asyncTotalCount;

	public String getOrderExpr() {
		return orderExpr;
	}

	public void setOrderExpr(String orderExpr) {
		this.orderExpr = orderExpr;
	}

	public boolean isContainsTotalCount() {
		return containsTotalCount;
	}

	public void setContainsTotalCount(boolean containsTotalCount) {
		this.containsTotalCount = containsTotalCount;
	}

	public Boolean getAsyncTotalCount() {
		return asyncTotalCount;
	}

	public void setAsyncTotalCount(Boolean asyncTotalCount) {
		this.asyncTotalCount = asyncTotalCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

}
