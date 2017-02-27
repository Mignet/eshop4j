package com.linkwee.web.request;

import com.linkwee.core.datatable.DataTable;

public class ProductCateDataRequest extends DataTable {
	
    /**
     *分类名称
     */
	private String cateName;
	
    /**
     *机构编码-不重复字段
     */
	private String orgNumber;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
