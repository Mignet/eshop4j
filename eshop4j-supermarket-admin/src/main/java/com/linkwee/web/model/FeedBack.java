package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月21日 15:17:17
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class FeedBack extends BaseEntity {
	
	private static final long serialVersionUID = 8679570654603445159L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *内容
     */
	private String content;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *会话
     */
	private String token;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *应用类别1理财师，2投资者
     */
	private Integer appType;
	


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
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
}

