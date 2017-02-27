package com.linkwee.web.request;

import com.linkwee.core.datatable.DataTable;

public class ProductSaleListRequest extends DataTable {
	
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
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
