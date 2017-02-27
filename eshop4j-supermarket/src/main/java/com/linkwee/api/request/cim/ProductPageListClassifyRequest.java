package com.linkwee.api.request.cim;


public class ProductPageListClassifyRequest extends ProductPageListRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *产品分类id
     */
	private Integer cateId;
	
	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
}
