package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月21日 15:17:17
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class AppVersion extends BaseEntity {
	
	private static final long serialVersionUID = -944118751238989264L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *版本名称
     */
	private String name;
	
    /**
     *版本号
     */
	private String version;
	
    /**
     *支持的最低版本
     */
	private String minVersion;
	
    /**
     *平台
     */
	private String platform;
	
    /**
     *下载地址
     */
	private String downloadUrl;
	
    /**
     *注册提示
     */
	private String regHints;
	
    /**
     *升级提示
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
     *发布时间
     */
	private Date issueTime;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
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

