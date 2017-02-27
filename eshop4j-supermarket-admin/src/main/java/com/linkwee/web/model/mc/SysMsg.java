package com.linkwee.web.model.mc;

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
 * @创建时间：2016年07月20日 15:59:52
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysMsg implements Serializable {
	
	private static final long serialVersionUID = 4526271300002661266L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *
     */
	private String content;
	
	
    /**
     *状态(0发布,1删除)
     */
	private Integer status;
	
	
    /**
     *
     */
	private String userNumber;
	
    /**
     *生效时间
     */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
	
	/**
	 * 读取状态(1已读,0未读)
	 */
	private Integer readStatus;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private Integer appType;
	
    /**
     *
     */
	private String typeName;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
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
	
	
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}
	
	public String getUserNumber(){
		return userNumber;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
}

