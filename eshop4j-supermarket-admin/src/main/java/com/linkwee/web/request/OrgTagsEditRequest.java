package com.linkwee.web.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.linkwee.web.model.CimProductInfoCate;

public class OrgTagsEditRequest {

	/**
	 * 机构编码
	 */
	@NotBlank
	private String orgNumber;
	/**
	 * 产品分类关联
	 */
	@NotEmpty
	private List<CimProductInfoCate> cateListArray;
		
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public List<CimProductInfoCate> getCateListArray() {
		return cateListArray;
	}
	public void setCateListArray(List<CimProductInfoCate> cateListArray) {
		this.cateListArray = cateListArray;
	}
	
}
