package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.Short;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class SmNews implements Serializable {
	
	private static final long serialVersionUID = 7382059079234344278L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *应用端口（1：理财师2：投资端）
     */
	private Integer appType;
	
    /**
     *
     */
	private String name;
	
    /**
     *
     */
	private String typeCode;
	
    /**
     *
     */
	private String typeName;
	
    /**
     *
     */
	private String img;
	
    /**
     *
     */
	private String title;
	
    /**
     *
     */
	private String summary;
	
    /**
     *
     */
	private String linkUrl;
	
    /**
     *
     */
	private String content;
	
    /**
     *状态:0发布,1删除2初始化类别
     */
	private Integer status;
	
    /**
     *
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
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
     *默认为0，0=不置顶,1=置顶
     */
	private Short isStick;
	


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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setTypeCode(String typeCode){
		this.typeCode = typeCode;
	}
	
	public String getTypeCode(){
		return typeCode;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
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
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	
	public String getLinkUrl(){
		return linkUrl;
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
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
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
	
	public void setIsStick(Short isStick){
		this.isStick = isStick;
	}
	
	public Short getIsStick(){
		return isStick;
	}
	
}

