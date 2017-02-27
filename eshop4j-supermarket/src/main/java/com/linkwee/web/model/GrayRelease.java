package com.linkwee.web.model;

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
 * @创建时间：2016年06月27日 15:52:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class GrayRelease implements Serializable {
	
	private static final long serialVersionUID = 1296209372048427889L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *产品id/活动编码
     */
	private String pid;
	
    /**
     *0:产品    1:活动
     */
	private Integer type;
	
    /**
     *0:启用    1:停用
     */
	private Integer status;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *用户名
     */
	private String userName;
	
    /**
     *开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setPid(String pid){
		this.pid = pid;
	}
	
	public String getPid(){
		return pid;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
}

