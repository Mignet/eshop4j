package com.eshop4j.web.model.news;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

import java.util.Date;
 /**	
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月12日 14:05:45
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class News extends BaseEntity {
	
	private static final long serialVersionUID = 8582514793750203221L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *应用端口（1：理财师2：投资端）
     */
	private Integer appType;
	
    /**
     *资讯标签
     */
	private String name;
	
    /**
     *类别
     */
	private String typeCode;
	
    /**
     *类别名称
     */
	private String typeName;
	
    /**
     *配图
     */
	private String img;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *摘要
     */
	private String summary;
	
    /**
     *链接
     */
	private String linkUrl;
	
    /**
     *资讯内容
     */
	private String content;
	
    /**
     *状态:0发布,1删除2初始化类别
     */
	private Integer status;
	
    /**
     *发布人
     */
	private String creator;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *显示排序
     */
	private Integer showInx;
	
    /**
     *生效时间
     */
	private String validBegin;
	
    /**
     *结束时间
     */
	private String validEnd;
	
    /**
     *修改时间
     */
	private String modifiyTime;

	 /**
	  * 是否置顶
	  */
	 private int isStick;
	 
	 /**
	  * 分享图标
	  */
	 private String shareIcon;


	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public Integer getAppType() {
		 return appType;
	 }

	 public void setAppType(Integer appType) {
		 this.appType = appType;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getTypeCode() {
		 return typeCode;
	 }

	 public void setTypeCode(String typeCode) {
		 this.typeCode = typeCode;
	 }

	 public String getTypeName() {
		 return typeName;
	 }

	 public void setTypeName(String typeName) {
		 this.typeName = typeName;
	 }

	 public String getImg() {
		 return img;
	 }

	 public void setImg(String img) {
		 this.img = img;
	 }

	 public String getTitle() {
		 return title;
	 }

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 public String getSummary() {
		 return summary;
	 }

	 public void setSummary(String summary) {
		 this.summary = summary;
	 }

	 public String getLinkUrl() {
		 return linkUrl;
	 }

	 public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl;
	 }

	 public String getContent() {
		 return content;
	 }

	 public void setContent(String content) {
		 this.content = content;
	 }

	 public Integer getStatus() {
		 return status;
	 }

	 public void setStatus(Integer status) {
		 this.status = status;
	 }

	 public String getCreator() {
		 return creator;
	 }

	 public void setCreator(String creator) {
		 this.creator = creator;
	 }

	 public Date getCrtTime() {
		 return crtTime;
	 }

	 public void setCrtTime(Date crtTime) {
		 this.crtTime = crtTime;
	 }

	 public Integer getShowInx() {
		 return showInx;
	 }

	 public void setShowInx(Integer showInx) {
		 this.showInx = showInx;
	 }

	 public String getValidBegin() {
		 return validBegin;
	 }

	 public void setValidBegin(String validBegin) {
		 this.validBegin = validBegin;
	 }

	 public String getValidEnd() {
		 return validEnd;
	 }

	 public void setValidEnd(String validEnd) {
		 this.validEnd = validEnd;
	 }

	 public String getModifiyTime() {
		 return modifiyTime;
	 }

	 public void setModifiyTime(String modifiyTime) {
		 this.modifiyTime = modifiyTime;
	 }

	 public int getIsStick() {
		 return isStick;
	 }

	 public void setIsStick(int isStick) {
		 this.isStick = isStick;
	 }
	 	 
	 public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
 }

