package com.linkwee.web.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.api.PaginatorRequest;

public class AdvRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614746364L;
	private Integer id;
	
	/**
     *图片显示位置
     */
	@NotNull(message="图片显示位置不能为空")
	private String pageLocation;
	
    /**
     *图片显示位置描述
     */
	private String pageIndexDesc;
	
    /**
     *图片URL地址
     */
	@NotNull(message="图片URL地址不能为空")
	private String imgUrl;
	
    /**
     *图片URL链接地址
     */
	private String linkUrl;
	
    /**
     *图片显示排序
     */
	private Integer showIndex;
	
    /**
     *图片状态: 0,显示;-1,不显示
     */
	private Integer status;
	
    /**
     *应用类别1理财师，2投资者
     */
	@NotNull(message="应用类别不能为空")
	private Integer appType;
	/**
	 * 上架开始时间
	 */
	@NotNull(message="上架时间不能为空")
	private Date validBeginDate;
	/**
	 * 下架时间
	 */
	@NotNull(message="下架时间不能为空")
	private Date validEndDate;
	
	 /**
     *分享标题
     */
	private String shareTitle;
	
	 /**
     *分享详情
     */
	private String shareDesc;
	
	 /**
     *分享图标
     */
	private String shareIcon;
	/**
     *分享连接
     */
	private String shareLink;
	
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareDesc() {
		return shareDesc;
	}
	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}
	public String getShareIcon() {
		return shareIcon;
	}
	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}
	public String getShareLink() {
		return shareLink;
	}
	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
	public String getPageIndexDesc() {
		return pageIndexDesc;
	}
	public void setPageIndexDesc(String pageIndexDesc) {
		this.pageIndexDesc = pageIndexDesc;
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
	public Integer getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAppType() {
		return appType;
	}
	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	public Date getValidBeginDate() {
		return validBeginDate;
	}
	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}
	public Date getValidEndDate() {
		return validEndDate;
	}
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	
	
	

}
