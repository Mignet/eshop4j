package com.eshop4j.web.model;

import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年11月05日 20:04:58
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class WealthNews implements Serializable {
	
	private static final long serialVersionUID = -1807547505821590642L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *资讯名称
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
     *开始时间
     */
	private Date issueTime;
	
    /**
     *状态:0发布,1删除2初始化类别
     */
	private Integer status;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifiyTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
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
	
	public void setIssueTime(Date issueTime){
		this.issueTime = issueTime;
	}
	
	public Date getIssueTime(){
		return issueTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifiyTime(Date modifiyTime){
		this.modifiyTime = modifiyTime;
	}
	
	public Date getModifiyTime(){
		return modifiyTime;
	}
	
}

