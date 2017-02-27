package com.linkwee.web.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.api.PaginatorRequest;

public class ActivityListRequest extends PaginatorRequest{

	private static final long serialVersionUID = -4977432770614746364L;
	 /**
     *流水号
     */
	private Integer id;
	
    /**
     *活动名称
     */
	//@NotNull(message="名称不能为空")
	private String activityName;
	
    /**
     *活动图标
     */
	private String activityImg;
	
    /**
     *活动链接
     */
	private String linkUrl;
	
    /**
     *开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startDate;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endDate;
	
    /**
     *活动状态:0:进行中,1:结束
     */
	private Integer status;
	
    /**
     *活动类别:0:钱罐子活动,1理财师活动
     */
	private Integer activityType;
	
    /**
     *活动编码
     */
	private String activityCode;
	
    /**
     *活动类别:0公共，1理财师，2投资者
     */
	private Integer appType;
	
    /**
     *活动结束图片URL地址
     */
	private String activityEndImg;
	
    /**
     *分享标题
     */
	private String shareTitle;
	
    /**
     *分享描述
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
	
	/**
	 * 活动平台
	 */
	private String activityPlatform;
	
	/**
	 * 活动状态
	 */
	private Integer activityStatus;
	
	/**
	 * 平台信息页中间位置的活动图
	 */
	private String platformImg;
	
	/**
	 * 活动点评
	 */
	private String activityDesc;
	
	/**
	 * 是否设置为封面
	 */
	private Integer isCover;
	
	/**
	 * 显示排序
	 */
	private Integer showIndex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityImg() {
		return activityImg;
	}

	public void setActivityImg(String activityImg) {
		this.activityImg = activityImg;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getActivityEndImg() {
		return activityEndImg;
	}

	public void setActivityEndImg(String activityEndImg) {
		this.activityEndImg = activityEndImg;
	}

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

	public String getActivityPlatform() {
		return activityPlatform;
	}

	public void setActivityPlatform(String activityPlatform) {
		this.activityPlatform = activityPlatform;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
		
	public String getPlatformImg() {
		return platformImg;
	}

	public void setPlatformImg(String platformImg) {
		this.platformImg = platformImg;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Integer getIsCover() {
		return isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
