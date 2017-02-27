package com.eshop4j.web.model.crm;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年01月03日 17:14:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmShareUserList implements Serializable {
	
	private static final long serialVersionUID = 2040800731057132884L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *用户名
     */
	private String userName;
	
    /**
     *电话号码
     */
	private String mobile;
	
    /**
     *活动类型
     */
	private Integer activityType;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setActivityType(Integer activityType){
		this.activityType = activityType;
	}
	
	public Integer getActivityType(){
		return activityType;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
}

