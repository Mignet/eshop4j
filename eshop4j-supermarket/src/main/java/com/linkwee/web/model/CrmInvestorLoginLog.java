package com.linkwee.web.model;

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
 * @创建时间：2016年08月17日 19:11:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmInvestorLoginLog implements Serializable {
	
	private static final long serialVersionUID = -808611027875626196L;
	
    /**
     *自增ID
     */
	private Integer id;
	/**
     * UUID
     */
	private String logId;
    /**
     *用户编码
     */
	private String userId;
	
    /**
     *设备id
     */
	private String deviceId;
	
    /**
     *设备类型
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
	private String appVersion;
	
    /**
     *来源地址
     */
	private String fromUrl;
	
    /**
     *受访地址
     */
	private String accessUrl;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后更新时间
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
	
	public void setAppVersion(String appVersion){
		this.appVersion = appVersion;
	}
	
	public String getAppVersion(){
		return appVersion;
	}
	
	public void setFromUrl(String fromUrl){
		this.fromUrl = fromUrl;
	}
	
	public String getFromUrl(){
		return fromUrl;
	}
	
	public void setAccessUrl(String accessUrl){
		this.accessUrl = accessUrl;
	}
	
	public String getAccessUrl(){
		return accessUrl;
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

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
	
}

