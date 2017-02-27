package com.linkwee.web.model.product;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 产品(渠道管理)
 * 
 * @Author ZhongLing
 * @Date 2015年12月25日 下午5:18:12
 */
public class ProCateSortResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 /**
     *产品id
     */
	private String productId;
	
    /**
     *分类ID
     */
	private String cateId;
	
    /**
     *排序字段
     */
	private Integer sort;
	
    /**
     *推荐描述语
     */
	private String description;
	/**
	 * 类名称
	 */
	private String cateName;
	/**
	 * 是否产品固有类
	 */
	private Integer isPublic;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
}
