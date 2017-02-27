package com.eshop4j.web.response;

import java.util.List;

import com.eshop4j.web.model.InRecordLogEntry;

public class QueryInRecordLogResponse {
	private int pageIndex; //第几页
	private int pageSize;  //页面记录数
	private int pageCount; //页数
	private int totalCount; //总记录数
	private List<InRecordLogEntry> datas; //充值记录
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
	public List<InRecordLogEntry> getDatas() {
		return datas;
	}
	public void setDatas(List<InRecordLogEntry> datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "QueryInRecordLogResponse [pageIndex=" + pageIndex
				+ ", pageSize=" + pageSize + ", pageCount=" + pageCount
				+ ", totalCount=" + totalCount + ", datas=" + datas + "]";
	}
}