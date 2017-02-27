package com.linkwee.web.model.news;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class AdvertisementListResp extends BaseEntity {

	private static final long serialVersionUID = 5753516043009306844L;

	 /**
     *流水号
     */
	private Integer id;
    /**
     *图片URL地址
     */
	private String imgUrl;
    /**
     *图片URL链接地址
     */
	private String linkUrl;
	
    /**
     *应用类别1理财师，2投资者
     */
	private Integer appType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	
}
