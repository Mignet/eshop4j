package com.linkwee.web.request;

import java.util.Date;

import com.linkwee.core.datatable.DataTable;

public class ProductsSalesStatisticsRequest extends DataTable{

	/**
     * 产品名称
     */
	private String productName;
	
    /**
     * 产品状态
     */
	private Integer status;
	/**
	 * 上架开始时间
	 */
	private Date auditStartTime;
	/**
	 * 上架结束时间
	 */
	private Date auditEndTime;
    /**
     *机构编码-固定50位编码，不重复字段
     */
	private String orgNumber;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getAuditStartTime() {
		return auditStartTime;
	}
	public void setAuditStartTime(Date auditStartTime) {
		this.auditStartTime = auditStartTime;
	}
	public Date getAuditEndTime() {
		return auditEndTime;
	}
	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
