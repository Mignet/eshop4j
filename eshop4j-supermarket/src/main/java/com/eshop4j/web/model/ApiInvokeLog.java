package com.eshop4j.web.model;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月21日 15:31:49
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ApiInvokeLog extends BaseEntity {
	
	private static final long serialVersionUID = 8149591296776171154L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
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

