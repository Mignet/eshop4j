package com.eshop4j.web.model.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 15:19:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmAdvertisement implements Serializable {
	
	private static final long serialVersionUID = 8880364960175295895L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *
     */
	private String pageIndex;
	
    /**
     *广告url
     */
	private String imgUrl;
	
    /**
     *跳转链接url
     */
	private String linkUrl;
	
    /**
     *图片显示排序
     */
	private Integer showIndex;
	
	  /**
     *图片显示位置描述
     */
	private String pageIndexDesc;
	
    public String getPageIndexDesc() {
		return pageIndexDesc;
	}

	public void setPageIndexDesc(String pageIndexDesc) {
		this.pageIndexDesc = pageIndexDesc;
	}

	/**
     *图片状态: 0,显示;-1,不显示
     */
	private Integer status;
	
    /**
     *应用类别1理财师，2投资者
     */
	private Integer appType;
	
    /**
     *上架时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBeginDate;
	
    /**
     *下架时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEndDate;
	
    /**
     *
     */
	private String shareTitle;
	
    /**
     *
     */
	private String shareDesc;
	
    /**
     *
     */
	private String shareIcon;
	
    /**
     *
     */
	private String shareLink;
	


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
	
	public void setValidBeginDate(Date validBeginDate){
		this.validBeginDate = validBeginDate;
	}
	
	public Date getValidBeginDate(){
		return validBeginDate;
	}
	
	public void setValidEndDate(Date validEndDate){
		this.validEndDate = validEndDate;
	}
	
	public Date getValidEndDate(){
		return validEndDate;
	}
	
	public void setShareTitle(String shareTitle){
		this.shareTitle = shareTitle;
	}
	
	public String getShareTitle(){
		return shareTitle;
	}
	
	public void setShareDesc(String shareDesc){
		this.shareDesc = shareDesc;
	}
	
	public String getShareDesc(){
		return shareDesc;
	}
	
	public void setShareIcon(String shareIcon){
		this.shareIcon = shareIcon;
	}
	
	public String getShareIcon(){
		return shareIcon;
	}
	
	public void setShareLink(String shareLink){
		this.shareLink = shareLink;
	}
	
	public String getShareLink(){
		return shareLink;
	}
	
}

