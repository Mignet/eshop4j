package com.linkwee.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月21日 15:17:17
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Advertisement extends BaseEntity {
	
	private static final long serialVersionUID = 7802726303471670019L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *图片显示位置
     */
	private String pageIndex;
	
    /**
     *图片显示位置描述
     */
	private String pageIndexDesc;
	
    /**
     *图片URL地址
     */
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
	private Integer appType;
	
	/**
	 * 上架开始时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone="GMT+8") 
	private Date validBeginDate;
	/**
	 * 下架时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone="GMT+8") 
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

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setPageIndex(String pageIndex){
		this.pageIndex = pageIndex;
	}
	
	public String getPageIndex(){
		return pageIndex;
	}
	
	public void setPageIndexDesc(String pageIndexDesc){
		this.pageIndexDesc = pageIndexDesc;
	}
	
	public String getPageIndexDesc(){
		return pageIndexDesc;
	}
	
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	
	public String getImgUrl(){
		return imgUrl;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	
	public String getLinkUrl(){
		return linkUrl;
	}
	
	public void setShowIndex(Integer showIndex){
		this.showIndex = showIndex;
	}
	
	public Integer getShowIndex(){
		return showIndex;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
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
	
}

