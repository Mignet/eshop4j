package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Boolean;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 16:57:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JpContent implements Serializable {
	
	private static final long serialVersionUID = 3519691770315547384L;
	
    /**
     *主键ID
     */
	private Long id;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *内容
     */
	private String text;
	
    /**
     *摘要
     */
	private String summary;
	
    /**
     *连接到(常用于谋文章只是一个连接)
     */
	private String linkTo;
	
    /**
     *是否启用markdown
     */
	private Boolean markdownEnable;
	
    /**
     *缩略图
     */
	private String thumbnail;
	
    /**
     *模型
     */
	private String module;
	
    /**
     *样式
     */
	private String style;
	
    /**
     *用户ID
     */
	private Long userId;
	
    /**
     *匿名稿的用户
     */
	private String author;
	
    /**
     *匿名稿的邮箱
     */
	private String userEmail;
	
    /**
     *IP地址
     */
	private String userIp;
	
    /**
     *发布浏览agent
     */
	private String userAgent;
	
    /**
     *父级内容ID
     */
	private Long parentId;
	
    /**
     *关联的对象ID
     */
	private Long objectId;
	
    /**
     *排序编号
     */
	private Integer orderNumber;
	
    /**
     *状态
     */
	private String status;
	
    /**
     *支持人数
     */
	private Integer voteUp;
	
    /**
     *反对人数
     */
	private Integer voteDown;
	
    /**
     *评分分数
     */
	private Integer rate;
	
    /**
     *评分次数
     */
	private Integer rateCount;
	
    /**
     *价格
     */
	private BigDecimal price;
	
    /**
     *评论状态
     */
	private String commentStatus;
	
    /**
     *评论总数
     */
	private Integer commentCount;
	
    /**
     *最后评论时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date commentTime;
	
    /**
     *访问量
     */
	private Integer viewCount;
	
    /**
     *创建日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date created;
	
    /**
     *最后更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modified;
	
    /**
     *slug
     */
	private String slug;
	
    /**
     *标识
     */
	private String flag;
	
    /**
     *经度
     */
	private BigDecimal lng;
	
    /**
     *纬度
     */
	private BigDecimal lat;
	
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
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public void setLinkTo(String linkTo){
		this.linkTo = linkTo;
	}
	
	public String getLinkTo(){
		return linkTo;
	}
	
	public void setMarkdownEnable(Boolean markdownEnable){
		this.markdownEnable = markdownEnable;
	}
	
	public Boolean isMarkdownEnable(){
		return markdownEnable;
	}
	
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}
	
	public String getThumbnail(){
		return thumbnail;
	}
	
	public void setModule(String module){
		this.module = module;
	}
	
	public String getModule(){
		return module;
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
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	
	public String getUserEmail(){
		return userEmail;
	}
	
	public void setUserIp(String userIp){
		this.userIp = userIp;
	}
	
	public String getUserIp(){
		return userIp;
	}
	
	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}
	
	public String getUserAgent(){
		return userAgent;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setObjectId(Long objectId){
		this.objectId = objectId;
	}
	
	public Long getObjectId(){
		return objectId;
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
	
	public void setVoteUp(Integer voteUp){
		this.voteUp = voteUp;
	}
	
	public Integer getVoteUp(){
		return voteUp;
	}
	
	public void setVoteDown(Integer voteDown){
		this.voteDown = voteDown;
	}
	
	public Integer getVoteDown(){
		return voteDown;
	}
	
	public void setRate(Integer rate){
		this.rate = rate;
	}
	
	public Integer getRate(){
		return rate;
	}
	
	public void setRateCount(Integer rateCount){
		this.rateCount = rateCount;
	}
	
	public Integer getRateCount(){
		return rateCount;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public void setCommentStatus(String commentStatus){
		this.commentStatus = commentStatus;
	}
	
	public String getCommentStatus(){
		return commentStatus;
	}
	
	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}
	
	public Integer getCommentCount(){
		return commentCount;
	}
	
	public void setCommentTime(Date commentTime){
		this.commentTime = commentTime;
	}
	
	public Date getCommentTime(){
		return commentTime;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	
	public Integer getViewCount(){
		return viewCount;
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
	
	public void setSlug(String slug){
		this.slug = slug;
	}
	
	public String getSlug(){
		return slug;
	}
	
	public void setFlag(String flag){
		this.flag = flag;
	}
	
	public String getFlag(){
		return flag;
	}
	
	public void setLng(BigDecimal lng){
		this.lng = lng;
	}
	
	public BigDecimal getLng(){
		return lng;
	}
	
	public void setLat(BigDecimal lat){
		this.lat = lat;
	}
	
	public BigDecimal getLat(){
		return lat;
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
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

