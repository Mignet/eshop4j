package com.eshop4j.web.model;

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
 * @创建时间：2016年08月11日 16:04:56
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpUpgradeRecord implements Serializable {
	
	private static final long serialVersionUID = -728351798666585519L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *升级前等级
     */
	private String cfpLevelBefore;
	
    /**
     *升级后等级
     */
	private String cfpLevel;
	
    /**
     *备注
     */
	private String remarks;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


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
	
	public void setCfpLevelBefore(String cfpLevelBefore){
		this.cfpLevelBefore = cfpLevelBefore;
	}
	
	public String getCfpLevelBefore(){
		return cfpLevelBefore;
	}
	
	public void setCfpLevel(String cfpLevel){
		this.cfpLevel = cfpLevel;
	}
	
	public String getCfpLevel(){
		return cfpLevel;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
	public String getRemarks(){
		return remarks;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
}

