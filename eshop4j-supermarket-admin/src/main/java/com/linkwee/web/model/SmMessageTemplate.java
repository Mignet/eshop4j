package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Byte;
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
 * @创建时间：2016年07月19日 16:25:17
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmMessageTemplate implements Serializable {
	
	private static final long serialVersionUID = -2185069742990670482L;
	
    /**
     *模板ID
     */
	private Long id;
	
    /**
     *类型1系统2个人
     */
	private Integer type;
	
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private Integer appType;
	
    /**
     *系统模块
     */
	private String moduleId;
	
    /**
     *
     */
	private String sendTo;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *
     */
	private String content;
	
    /**
     *1可用0停用
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *创建人
     */
	private String createPerson;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
    /**
     *修改人
     */
	private String lastUpdatePerson;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *消息作用区分类型，1功能类(默认)，2营销类，3告警类
     */
	private Byte msgType;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setModuleId(String moduleId){
		this.moduleId = moduleId;
	}
	
	public String getModuleId(){
		return moduleId;
	}
	
	public void setSendTo(String sendTo){
		this.sendTo = sendTo;
	}
	
	public String getSendTo(){
		return sendTo;
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
	
	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}
	
	public String getCreatePerson(){
		return createPerson;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
	public void setLastUpdatePerson(String lastUpdatePerson){
		this.lastUpdatePerson = lastUpdatePerson;
	}
	
	public String getLastUpdatePerson(){
		return lastUpdatePerson;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setMsgType(Byte msgType){
		this.msgType = msgType;
	}
	
	public Byte getMsgType(){
		return msgType;
	}
	
}

