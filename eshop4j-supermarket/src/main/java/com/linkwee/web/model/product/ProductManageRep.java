package com.linkwee.web.model.product;

import com.linkwee.core.base.BaseEntity;

/**
 * 产品(渠道管理)
 * 
 * @Author ZhongLing
 * @Date 2015年12月25日 下午5:18:12
 */
public class ProductManageRep extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private Integer productTypeId;
	private Integer status;
	private String sort;
	private String order;
	private String collectBeginTimeStart;
	private String collectBeginTimeEnd;
	private String saleStatus; //销售状态
	private String proCateId; //类型ID
	private String buyUserId;//投资用户ID
	private int page = 1; //页码
	private int rows = 20; //每页记录数，默认20
	private boolean paging = true;
	
	private int startIndex;
	private int endIndex;
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getCollectBeginTimeStart() {
		return collectBeginTimeStart;
	}
	public void setCollectBeginTimeStart(String collectBeginTimeStart) {
		this.collectBeginTimeStart = collectBeginTimeStart;
	}
	public String getCollectBeginTimeEnd() {
		return collectBeginTimeEnd;
	}
	public void setCollectBeginTimeEnd(String collectBeginTimeEnd) {
		this.collectBeginTimeEnd = collectBeginTimeEnd;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStartIndex() {
		return ((page <= 0 ? 1 : page) -1 ) * (rows <= 0 ? 20 :rows);
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return rows;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	public boolean isPaging() {
		return paging;
	}
	public void setPaging(boolean paging) {
		this.paging = paging;
	}
	
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getProCateId() {
		return proCateId;
	}
	public void setProCateId(String proCateId) {
		this.proCateId = proCateId;
	}
	
	public String getBuyUserId() {
		return buyUserId;
	}
	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}
	@Override
	public String toString() {
		return "ProductManageRep [productName=" + productName
				+ ", productTypeId=" + productTypeId + ", status=" + status
				+ ", sort=" + sort + ", order=" + order
				+ ", collectBeginTimeStart=" + collectBeginTimeStart
				+ ", collectBeginTimeEnd=" + collectBeginTimeEnd + ", page="
				+ page + ", rows=" + rows + ", startIndex=" + startIndex
				+ ", endIndex=" + endIndex + "]";
	}
	

}
