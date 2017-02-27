package com.linkwee.web.model.jpressPlatform;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 11:31:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JpPlatform implements Serializable {
	
	private static final long serialVersionUID = -154730384715973121L;
	
    /**
     *主键ID
     */
	private Long id;
	
    /**
     *机构名称
     */
	private String name;
	
    /**
     *摘要
     */
	private String summary;
	
    /**
     *注册资本
     */
	private String capital;
	
    /**
     *上线时间
     */
	private Date upTime;
	
    /**
     *所在城市
     */
	private String city;
	
    /**
     *平台背景
     */
	private String context;
	
    /**
     *资金托管
     */
	private String trusteeship;
	
    /**
     *保障模式
     */
	private String securityModel;
	
    /**
     *投标保障
     */
	private String bidSecurity;
	
    /**
     *债券转让0不支持；1支持
     */
	private Boolean isTransfer;
	
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private Integer grade;
	
    /**
     *最小年化收益
     */
	private BigDecimal minProfit;
	
    /**
     *最大年化收益
     */
	private BigDecimal maxProfit;
	
    /**
     *最小产品期限
     */
	private Integer minDeadLine;
	
    /**
     *最大产品期限
     */
	private Integer maxDeadLine;
	/**
	 * 产品期限自定义显示
	 */
	private String deadLineSelfDefined;
	
    /**
     *平台详情图片
     */
	private String platformDetailImg;
	
    /**
     *简介
     */
	private String briefIntroduction;
	
    /**
     *团队介绍
     */
	private String teamIntroduction;
	
    /**
     *网站备案
     */
	private String siteRecord;
	
    /**
     *联系我们
     */
	private String contactUs;
	
    /**
     *图片资料
     */
	private String picInfo;
	
    /**
     *样式
     */
	private String style;
	
    /**
     *用户
     */
	private Long userId;
	
    /**
     *排序编号
     */
	private Integer orderNumber;
	
    /**
     *状态
     */
	private String status;
	
    /**
     *创建日期
     */
	private Date created;
	
    /**
     *最后更新日期
     */
	private Date modified;
	
    /**
     *访问量
     */
	private Integer viewCount;
	
    /**
     *slug
     */
	private String slug;
	
    /**
     *SEO关键字
     */
	private String metaKeywords;
	
    /**
     *SEO描述信息
     */
	private String metaDescription;
	
    /**
     *备注信息
     */
	private String remarks;
	/**
	 * 详情页链接地址
	 */
	private String dtlLinkUrl;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public void setCapital(String capital){
		this.capital = capital;
	}
	
	public String getCapital(){
		return capital;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setTrusteeship(String trusteeship){
		this.trusteeship = trusteeship;
	}
	
	public String getTrusteeship(){
		return trusteeship;
	}
	
	public void setSecurityModel(String securityModel){
		this.securityModel = securityModel;
	}
	
	public String getSecurityModel(){
		return securityModel;
	}
	
	public void setBidSecurity(String bidSecurity){
		this.bidSecurity = bidSecurity;
	}
	
	public String getBidSecurity(){
		return bidSecurity;
	}
	
	public void setIsTransfer(Boolean isTransfer){
		this.isTransfer = isTransfer;
	}
	
	public Boolean isIsTransfer(){
		return isTransfer;
	}
	
	public void setGrade(Integer grade){
		this.grade = grade;
	}
	
	public Integer getGrade(){
		return grade;
	}
	
	public void setMinProfit(BigDecimal minProfit){
		this.minProfit = minProfit;
	}
	
	public BigDecimal getMinProfit(){
		return minProfit;
	}
	
	public void setMaxProfit(BigDecimal maxProfit){
		this.maxProfit = maxProfit;
	}
	
	public BigDecimal getMaxProfit(){
		return maxProfit;
	}
	
	public void setMinDeadLine(Integer minDeadLine){
		this.minDeadLine = minDeadLine;
	}
	
	public Integer getMinDeadLine(){
		return minDeadLine;
	}
	
	public void setMaxDeadLine(Integer maxDeadLine){
		this.maxDeadLine = maxDeadLine;
	}
	
	public Integer getMaxDeadLine(){
		return maxDeadLine;
	}
	
	public void setPlatformDetailImg(String platformDetailImg){
		this.platformDetailImg = platformDetailImg;
	}
	
	public String getPlatformDetailImg(){
		return platformDetailImg;
	}
	
	public void setBriefIntroduction(String briefIntroduction){
		this.briefIntroduction = briefIntroduction;
	}
	
	public String getBriefIntroduction(){
		return briefIntroduction;
	}
	
	public void setTeamIntroduction(String teamIntroduction){
		this.teamIntroduction = teamIntroduction;
	}
	
	public String getTeamIntroduction(){
		return teamIntroduction;
	}
	
	public void setSiteRecord(String siteRecord){
		this.siteRecord = siteRecord;
	}
	
	public String getSiteRecord(){
		return siteRecord;
	}
	
	public void setContactUs(String contactUs){
		this.contactUs = contactUs;
	}
	
	public String getContactUs(){
		return contactUs;
	}
	
	public void setPicInfo(String picInfo){
		this.picInfo = picInfo;
	}
	
	public String getPicInfo(){
		return picInfo;
	}
	
	public void setStyle(String style){
		this.style = style;
	}
	
	public String getStyle(){
		return style;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setOrderNumber(Integer orderNumber){
		this.orderNumber = orderNumber;
	}
	
	public Integer getOrderNumber(){
		return orderNumber;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setCreated(Date created){
		this.created = created;
	}
	
	public Date getCreated(){
		return created;
	}
	
	public void setModified(Date modified){
		this.modified = modified;
	}
	
	public Date getModified(){
		return modified;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	
	public Integer getViewCount(){
		return viewCount;
	}
	
	public void setSlug(String slug){
		this.slug = slug;
	}
	
	public String getSlug(){
		return slug;
	}
	
	public void setMetaKeywords(String metaKeywords){
		this.metaKeywords = metaKeywords;
	}
	
	public String getMetaKeywords(){
		return metaKeywords;
	}
	
	public void setMetaDescription(String metaDescription){
		this.metaDescription = metaDescription;
	}
	
	public String getMetaDescription(){
		return metaDescription;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
	public String getRemarks(){
		return remarks;
	}

	public String getDeadLineSelfDefined() {
		return deadLineSelfDefined;
	}

	public void setDeadLineSelfDefined(String deadLineSelfDefined) {
		this.deadLineSelfDefined = deadLineSelfDefined;
	}
	

	public String getDtlLinkUrl() {
		return dtlLinkUrl;
	}

	public void setDtlLinkUrl(String dtlLinkUrl) {
		this.dtlLinkUrl = dtlLinkUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

