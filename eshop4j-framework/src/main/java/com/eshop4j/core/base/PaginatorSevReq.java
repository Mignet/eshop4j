package com.eshop4j.core.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 描述：分页请求参数
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午11:04:21
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class PaginatorSevReq extends ServiceRequest {

	private static final long serialVersionUID = -2028976417907224605L;
	
	/**
	 * 页码
	 */
	private Integer pageIndex = 1;
	/**
	 * 页面大小
	 */
	private Integer pageSize = 10;
	/**
	 * 排序字段
	 */
	private Integer sort;
	/**
	 * 排序方向
	 */
	private Integer order;
	/**
	 * 查询条件
	 */
	private Map<String, Object> queryConditions = new HashMap<String, Object>();

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Map<String, Object> getQueryConditions() {
		return queryConditions;
	}

	public void setQueryConditions(Map<String, Object> queryConditions) {
		this.queryConditions = queryConditions;
	}

	@Override
	public String toString() {
		return "PaginatorRequest [pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + "]";
	}

}
