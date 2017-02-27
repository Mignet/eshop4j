package com.eshop4j.web.model.mc;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @创建时间：2016年08月04日 17:09:02
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysNoticeReadLog implements Serializable {
	
	private static final long serialVersionUID = -4159886834270572613L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *公告ID
     */
	private Integer noticeId;
	
    /**
     *接口名称
     */
	private String apiName;
	
    /**
     *访问次数
     */
	private Integer accessCount;
	
    /**
     *更新时间
     */
	private Date chgTime;
	
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
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setNoticeId(Integer noticeId){
		this.noticeId = noticeId;
	}
	
	public Integer getNoticeId(){
		return noticeId;
	}
	
	public void setApiName(String apiName){
		this.apiName = apiName;
	}
	
	public String getApiName(){
		return apiName;
	}
	
	public void setAccessCount(Integer accessCount){
		this.accessCount = accessCount;
	}
	
	public Integer getAccessCount(){
		return accessCount;
	}
	
	public void setChgTime(Date chgTime){
		this.chgTime = chgTime;
	}
	
	public Date getChgTime(){
		return chgTime;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
}

