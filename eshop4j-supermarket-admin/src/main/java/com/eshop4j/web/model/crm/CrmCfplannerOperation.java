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
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 10:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfplannerOperation implements Serializable {
	
	private static final long serialVersionUID = -2941328420849656366L;
	
    /**
     *自增主键
     */
	private Integer id;
	
    /**
     *理财师
     */
	private String cfplanner;
	
    /**
     *操作类型
     */
	private Integer type;
	
    /**
     *上级理财师
     */
	private String parentId;
	
    /**
     *操作人
     */
	private String operationAdmin;
	
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
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
	
	/**
     *上级理财师
     */
	private String parentMobile;
	/**
     *上级理财师
     */
	private String parentName;

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCfplanner(String cfplanner){
		this.cfplanner = cfplanner;
	}
	
	public String getCfplanner(){
		return cfplanner;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return parentId;
	}
	
	public void setOperationAdmin(String operationAdmin){
		this.operationAdmin = operationAdmin;
	}
	
	public String getOperationAdmin(){
		return operationAdmin;
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

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

