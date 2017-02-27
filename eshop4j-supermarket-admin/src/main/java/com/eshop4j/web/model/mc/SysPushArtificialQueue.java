package com.eshop4j.web.model.mc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月10日 15:50:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysPushArtificialQueue implements Serializable {
	
	private static final long serialVersionUID = 654006045256938218L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *推送对象(1理财师,2投资者)
     */
	private Integer appType;
	
    /**
     *推送内容
     */
	private String content;
	
    /**
     *推送跳转链接
     */
	private String link;
	
    /**
     *状态(0待发送,1已经发送,2已经撤销)
     */
	private Integer status;
	
    /**
     *状态(0即时,1定时)
     */
	private Integer startType;
	
    /**
     *定时发送时间
     */
	private Date startTime;
	
    /**
     *发送对象,全部or指定用户
     */
	private Integer sendObjectType;
	
    /**
     *发送对象,选择全站发送时该列为空
     */
	private String sendObject;
	
	/**
	 * 公告推送or系统推送
	 */	
	private Integer sendType;
	
	/**
	 * 备注
	 */
	private String remark;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
	/**
	 * userIds
	 * @param id
	 */
	private String userIds;
	/**
	 * 手机号
	 */
	private String mobiles;
	
	private List<String> mobileList;


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setStartType(Integer startType){
		this.startType = startType;
	}
	
	public Integer getStartType(){
		return startType;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setSendObjectType(Integer sendObjectType){
		this.sendObjectType = sendObjectType;
	}
	
	public Integer getSendObjectType(){
		return sendObjectType;
	}
	
	public void setSendObject(String sendObject){
		this.sendObject = sendObject;
	}
	
	public String getSendObject(){
		return sendObject;
	}
	
	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	

	public List<String> getMobileList() {
		return mobileList;
	}

	public void setMobileList(List<String> mobileList) {
		this.mobileList = mobileList;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

