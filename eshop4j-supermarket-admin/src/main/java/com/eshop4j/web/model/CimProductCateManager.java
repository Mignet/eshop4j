package com.eshop4j.web.model;

public class CimProductCateManager extends CimProductCate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图片服务器url
	 */
	private String imgServerUrl;
	
    /**
     *机构名称
     */
	private String orgName;

	public String getImgServerUrl() {
		return imgServerUrl;
	}

	public void setImgServerUrl(String imgServerUrl) {
		this.imgServerUrl = imgServerUrl;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
