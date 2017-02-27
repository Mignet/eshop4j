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
 * @创建时间：2016年07月26日 20:22:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmAppVersion implements Serializable {
	
	private static final long serialVersionUID = 310392765446594169L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *
     */
	private String name;
	
    /**
     *
     */
	private String version;
	
    /**
     *
     */
	private String minVersion;
	
    /**
     *
     */
	private String platform;
	
    /**
     *
     */
	private String downloadUrl;
	
    /**
     *
     */
	private String regHints;
	
    /**
     *
     */
	private String updateHints;
	
    /**
     *是否开放注册(0开放,1不开放)
     */
	private Integer openReg;
	
    /**
     *状态(0可用,1不可用)
     */
	private Integer status;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date issueTime;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifyTime;
	
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return version;
	}
	
	public void setMinVersion(String minVersion){
		this.minVersion = minVersion;
	}
	
	public String getMinVersion(){
		return minVersion;
	}
	
	public void setPlatform(String platform){
		this.platform = platform;
	}
	
	public String getPlatform(){
		return platform;
	}
	
	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}
	
	public String getDownloadUrl(){
		return downloadUrl;
	}
	
	public void setRegHints(String regHints){
		this.regHints = regHints;
	}
	
	public String getRegHints(){
		return regHints;
	}
	
	public void setUpdateHints(String updateHints){
		this.updateHints = updateHints;
	}
	
	public String getUpdateHints(){
		return updateHints;
	}
	
	public void setOpenReg(Integer openReg){
		this.openReg = openReg;
	}
	
	public Integer getOpenReg(){
		return openReg;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setIssueTime(Date issueTime){
		this.issueTime = issueTime;
	}
	
	public Date getIssueTime(){
		return issueTime;
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
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
}

