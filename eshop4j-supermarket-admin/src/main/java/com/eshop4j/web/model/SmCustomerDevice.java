package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月14日 15:08:18
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class SmCustomerDevice implements Serializable {
	
	private static final long serialVersionUID = -7038992555486797660L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *设备id
     */
	private String deviceId;
	/**
	 * 设备唯一标识
	 */
	private String deviceToken;
	
    /**
     *
     */
	private String deviceType;
	
    /**
     *设备详情
     */
	private String deviceDetail;
	
    /**
     *设备分辨率
     */
	private String deviceResolution;
	
    /**
     *手机系统版本号
     */
	private String systemVersion;
	
    /**
     *app版本号
     */
	private String appversion;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	
    /**
     *最新登录时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date loginLasttime;
	
    /**
     *app类型(1=领会理财|2=领会投资端)
     */
	private Byte appType;
	
    /**
     *是否推送通知栏消息:0-推送  1-不推送
     */
	private Byte isSendnotice;
	


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
	
	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}
	
	public String getDeviceId(){
		return deviceId;
	}
	
	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}
	
	public String getDeviceType(){
		return deviceType;
	}
	
	public void setDeviceDetail(String deviceDetail){
		this.deviceDetail = deviceDetail;
	}
	
	public String getDeviceDetail(){
		return deviceDetail;
	}
	
	public void setDeviceResolution(String deviceResolution){
		this.deviceResolution = deviceResolution;
	}
	
	public String getDeviceResolution(){
		return deviceResolution;
	}
	
	public void setSystemVersion(String systemVersion){
		this.systemVersion = systemVersion;
	}
	
	public String getSystemVersion(){
		return systemVersion;
	}
	
	public void setAppversion(String appversion){
		this.appversion = appversion;
	}
	
	public String getAppversion(){
		return appversion;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setLoginLasttime(Date loginLasttime){
		this.loginLasttime = loginLasttime;
	}
	
	public Date getLoginLasttime(){
		return loginLasttime;
	}
	
	public void setAppType(Byte appType){
		this.appType = appType;
	}
	
	public Byte getAppType(){
		return appType;
	}
	
	public void setIsSendnotice(Byte isSendnotice){
		this.isSendnotice = isSendnotice;
	}
	
	public Byte getIsSendnotice(){
		return isSendnotice;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
}

