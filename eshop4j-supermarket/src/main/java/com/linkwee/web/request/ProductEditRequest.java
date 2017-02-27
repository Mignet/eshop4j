package com.linkwee.web.request;

import java.util.List;

import com.linkwee.web.model.CimProductInfoCate;

public class ProductEditRequest {

	/**
	 * 产品列表排序
	 */
	private Integer showIndex;
	/**
	 * 产品id
	 */
	private String productIdForEdit;
	/**
	 * 产品表主键id
	 */
	private Integer productTableId;
	/**
	 * 初始产品列表排序
	 */
	private Integer originalShowIndex;
	/**
	 * 产品分类关联
	 */
	private List<CimProductInfoCate> cateListArray;
	
	public Integer getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}
	public String getProductIdForEdit() {
		return productIdForEdit;
	}
	public void setProductIdForEdit(String productIdForEdit) {
		this.productIdForEdit = productIdForEdit;
	}
	public Integer getProductTableId() {
		return productTableId;
	}
	public void setProductTableId(Integer productTableId) {
		this.productTableId = productTableId;
	}
	public Integer getOriginalShowIndex() {
		return originalShowIndex;
	}
	public void setOriginalShowIndex(Integer originalShowIndex) {
		this.originalShowIndex = originalShowIndex;
	}
	public List<CimProductInfoCate> getCateListArray() {
		return cateListArray;
	}
	public void setCateListArray(List<CimProductInfoCate> cateListArray) {
		this.cateListArray = cateListArray;
	}
	
}
