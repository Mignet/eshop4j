package com.linkwee.api.response.mc;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.mc.Classroom;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ClassroomPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -4607986069584094794L;
	
	public ClassroomPageListResponse(){
			
	}
	
	public ClassroomPageListResponse(Classroom obj){
		WebUtil.initObj(this,obj);
		this.setImg(obj.getImg());
		this.setTitle(obj.getTitle());
		this.setContent(obj.getContent());
		this.setStatus(obj.getStatus());
		this.setLabel(obj.getLabel());
		this.setCreateTime(obj.getCreateTime());
		this.setShowInx(obj.getShowInx());
		this.setLinkUrl(obj.getLinkUrl());
		this.setSummary(obj.getSummary());
		this.setShareTitle(obj.getShareTitle());
		this.setShareIcon(obj.getShareIcon());
		this.setShareLink(obj.getShareLink());
		this.setShareDesc(obj.getShareDesc());
	}
	
    /**
     *自增长ID
     */
	private Integer id;
	
    /**
     *应用端口(1:猎财大师2:投呗）
     */
	private Integer appType;
	
    /**
     *图片
     */
	private String img;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *课堂内容
     */
	private String content;
	
    /**
     *状态:0发布,1删除2初始化类别
     */
	private Integer status;
	
    /**
     *创建者
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *显示排序
     */
	private Integer showInx;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBegin;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEnd;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifiyTime;
	
    /**
     *标签
     */
	private String label;
	
    /**
     *默认为0，0=不置顶,1=置顶
     */
	private Short isStick;
	
	/**
     *跳转地址
     */
	private String linkUrl;
	
	 /**
     *简介
     */
	private String summary;
	
    /**
     *分享标题
     */
	private String shareTitle;
	
    /**
     *分享图片链接
     */
	private String shareLink;
	
    /**
     *分享描述
     */
	private String shareDesc;
	
    /**
     *分享图标地址
     */
	private String shareIcon;
	

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
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

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	
	public String getImg(){
		return img;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setShowInx(Integer showInx){
		this.showInx = showInx;
	}
	
	public Integer getShowInx(){
		return showInx;
	}
	
	public void setValidBegin(Date validBegin){
		this.validBegin = validBegin;
	}
	
	public Date getValidBegin(){
		return validBegin;
	}
	
	public void setValidEnd(Date validEnd){
		this.validEnd = validEnd;
	}
	
	public Date getValidEnd(){
		return validEnd;
	}
	
	public void setModifiyTime(Date modifiyTime){
		this.modifiyTime = modifiyTime;
	}
	
	public Date getModifiyTime(){
		return modifiyTime;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setIsStick(Short isStick){
		this.isStick = isStick;
	}
	
	public Short getIsStick(){
		return isStick;
	}
	
}

