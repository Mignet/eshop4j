package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class ProductClassifyPageListRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构编码
	 */
	private String orgCode;
    /**
     *分类id
     */
	private Integer cateId;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

}
