package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 10:43:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmFeedback implements Serializable {
	
	private static final long serialVersionUID = -2277117880668095255L;
	
    /**
     *
     */
	private Long id;
	
    /**
     *反馈内容
     */
	private String content;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createtime;
	
    /**
     *
     */
	private Integer appType;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	
	public Date getCreatetime(){
		return createtime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
}

