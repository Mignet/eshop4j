package com.linkwee.core.base;

import java.io.Serializable;
import java.util.List;

import com.linkwee.core.orm.paging.Page;

/**
 * 
 * 描述：分页返回类
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午11:04:00
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class PaginatorSevResp<T> implements Serializable{
	
	private static final long serialVersionUID = -5941654069080564384L;
	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 数据总条数
	 */
	private int totalCount;
	/**
	 * 数据
	 */
	private List<T> datas ;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "PaginatorSevResp [pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", pageCount=" + pageCount + ", totalCount="
				+ totalCount + ", datas=" + datas + "]";
	}
	
	public void setValuesByPage(Page<T> page){
		pageIndex = page.getPageNo();
		pageSize = page.getPageSize();
		pageCount = page.getTotalPages();
		totalCount =  page.getTotalCount();
	}
	
}
