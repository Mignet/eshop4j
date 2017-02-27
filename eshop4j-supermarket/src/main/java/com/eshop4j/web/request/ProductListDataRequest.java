package com.eshop4j.web.request;

import com.eshop4j.core.datatable.DataTable;

public class ProductListDataRequest extends DataTable {
	
	/**
     * 产品名称
     */
	private String productName;
	/**
	 *机构编码-固定50位编码，不重复字段
	 */
	private String orgNumber;
    /**
     * 产品类型
     */
	private Integer productType;
    /**
     * 产品状态
     */
	private Integer status;
    /**
     *审核状态(1=审核通过(上架) 2-待审核 3-审核未通过(下架))
     */
	private Integer auditStatus;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
