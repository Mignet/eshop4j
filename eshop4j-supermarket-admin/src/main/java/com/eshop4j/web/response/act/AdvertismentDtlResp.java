package com.eshop4j.web.response.act;


import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

/**
 * 广告详情
 * 
 * @Author chenchy
 * @Date 2015年12月25日 下午5:18:12
 */
public class AdvertismentDtlResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 /**
     *图片显示位置
     */
	private String pageIndex;
	
    /**
     *图片显示位置描述
     */
	//private String pageIndexDesc;
	
    /**
     *图片URL地址
     */
	private String imgUrl;
	
    /**
     *图片URL链接地址
     */
	private String linkUrl;
	/**
	 * 下架时间
	 */
	private Date validEndDate;

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	/*public String getPageIndexDesc() {
		return pageIndexDesc;
	}

	public void setPageIndexDesc(String pageIndexDesc) {
		this.pageIndexDesc = pageIndexDesc;
	}*/

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

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	
	
}
