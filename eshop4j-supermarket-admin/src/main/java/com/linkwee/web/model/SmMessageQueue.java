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
 * @创建时间：2016年07月18日 11:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmMessageQueue implements Serializable {
	
	private static final long serialVersionUID = 4686693192894299875L;
	
    /**
     *消息ID
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
	 * 模块
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
     *状态1编辑2发送成功3发送失败4删除 
     */
	private Integer status;
	
    /**
     *发送时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date sendTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
   
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
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	
	public Date getSendTime(){
		return sendTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
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

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	
	
}

