package com.linkwee.web.model.mc;

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
 * @创建时间：2016年07月25日 16:17:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysPushMessage implements Serializable {
	
	private static final long serialVersionUID = 2881450110757361477L;
	
    /**
     *主键id
     */
	private Long id;
	
    /**
     *模块id(注册，提现，购买，赎回)
     */
	private String moduleId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *设备标识
     */
	private String deviceToken;
	
    /**
     *操作系统类型(分为安卓，ios)
     */
	private String osType;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *内容
     */
	private String content;
	
    /**
     *是否已处理
     */
	private Integer handle;
	
    /**
     *发送状态 (1成功0失败)
     */
	private Integer status;
	/**
	 * appType
	 */
	private  Integer appType;
	
    /**
     *发送状态描述
     */
	private String statusDesc;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间（推送时间）
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *扩展字段1
     */
	private String extend1;
	
    /**
     *扩展字段2
     */
	private String extend2;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setModuleId(String moduleId){
		this.moduleId = moduleId;
	}
	
	public String getModuleId(){
		return moduleId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setDeviceToken(String deviceToken){
		this.deviceToken = deviceToken;
	}
	
	public String getDeviceToken(){
		return deviceToken;
	}
	
	public void setOsType(String osType){
		this.osType = osType;
	}
	
	public String getOsType(){
		return osType;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setHandle(Integer handle){
		this.handle = handle;
	}
	
	public Integer getHandle(){
		return handle;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setStatusDesc(String statusDesc){
		this.statusDesc = statusDesc;
	}
	
	public String getStatusDesc(){
		return statusDesc;
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
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setExtend1(String extend1){
		this.extend1 = extend1;
	}
	
	public String getExtend1(){
		return extend1;
	}
	
	public void setExtend2(String extend2){
		this.extend2 = extend2;
	}
	
	public String getExtend2(){
		return extend2;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	
}

