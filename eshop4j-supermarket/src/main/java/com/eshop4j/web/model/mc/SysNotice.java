package com.eshop4j.web.model.mc;

import java.io.Serializable;
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
public class SysNotice implements Serializable {
	
	private static final long serialVersionUID = 4526271300002661266L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *
     */
	private String link;
	
    /**
     *状态(0发布,1删除)
     */
	private Integer status;
	
    /**
     *生效时间
     */
	private Date startTime;
	
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
	private String message;
	/**
	 * 展示平台wechat:1,android:2 ,ios:3,wap:4,web:5
	 */
	private Integer platform;
	/**
	 * 公告内容
	 */
	private String content;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	
	public void setLink(String link){
		this.link = link;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
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
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}

