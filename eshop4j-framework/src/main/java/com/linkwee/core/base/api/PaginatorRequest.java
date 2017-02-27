package com.linkwee.core.base.api;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.base.PaginatorSevReq;



/**
 * 
 * @描述：api分页请求参数
 *
 * @author Bob
 * @时间  2015年7月31日上午10:15:22
 *
 */
public class PaginatorRequest extends BaseEntity{

	private static final long serialVersionUID = 3184528312384118459L;

	/**
	 * 第几页
	 */
	private Integer pageIndex =1;
	
	/**
	 * 页面大小
	 */
	private Integer pageSize = 10;
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 顺序
	 */
	private Integer order;
	
	public PaginatorSevReq toPaginatorSevReq(){
		PaginatorSevReq ret = new PaginatorSevReq();
		ret.setPageIndex(pageIndex);
		ret.setPageSize(pageSize);
		ret.setOrder(order);
		ret.setSort(sort);
		return ret;
	}

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

	@Override
	public String toString() {
		return "PaginatorRequest [pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", sort=" + sort + ", order=" + order + "]";
	}

}
