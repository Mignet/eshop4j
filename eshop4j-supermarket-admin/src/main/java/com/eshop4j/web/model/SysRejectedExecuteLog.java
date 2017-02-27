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
 * @创建时间：2016年08月17日 02:51:21
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class SysRejectedExecuteLog implements Serializable {
	
	private static final long serialVersionUID = -5299759079400160729L;
	
    /**
     *
     */
	private Integer id;
	
	private String executeId;
	
    /**
     *服务名称
     */
	private String serviceName;
	
    /**
     *服务方法
     */
	private String serviceMethod;
	
    /**
     *参数
     */
	private String serviceParm;
	
    /**
     *拒绝描述
     */
	private String rejectedRemark;
	
	
    /**
     *失败时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date faillTime;
	
    /**
     *状态 : 1=未处理|2=处理中|3=完成|4=成功
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	
	
	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}

	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	
	public String getServiceName(){
		return serviceName;
	}
	
	public void setServiceMethod(String serviceMethod){
		this.serviceMethod = serviceMethod;
	}
	
	public String getServiceMethod(){
		return serviceMethod;
	}
	
	public void setServiceParm(String serviceParm){
		this.serviceParm = serviceParm;
	}
	
	public String getServiceParm(){
		return serviceParm;
	}
	
	public void setRejectedRemark(String rejectedRemark){
		this.rejectedRemark = rejectedRemark;
	}
	
	public String getRejectedRemark(){
		return rejectedRemark;
	}


	public void setFaillTime(Date faillTime){
		this.faillTime = faillTime;
	}
	
	public Date getFaillTime(){
		return faillTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	

	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
}

