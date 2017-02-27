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
 * @创建时间：2016年07月25日 11:21:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysPushParameter implements Serializable {
	
	private static final long serialVersionUID = 242179971696999037L;
	
    /**
     *主键id,自增
     */
	private Long id;
	
    /**
     *猎财大师1,投呗2
     */
	private Integer appType;
	
    /**
     *操作系统类型
     */
	private String osType;
	
    /**
     *应用id
     */
	private String appId;
	
    /**
     *应用key
     */
	private String appKey;
	
    /**
     *地址
     */
	private String host;
	
    /**
     *密钥
     */
	private String appSecret;
	
    /**
     *密钥
     */
	private String masterSecret;
	
    /**
     *状态（1为可用，2为不可用）
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *创建人员
     */
	private String createPerson;
	
    /**
     *最后更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
    /**
     *最后更新人员
     */
	private String lastUpdatePerson;
	
    /**
     *备注信息
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
	
	public void setAppType(Integer appType){
		this.appType = appType;
	}
	
	public Integer getAppType(){
		return appType;
	}
	
	public void setOsType(String osType){
		this.osType = osType;
	}
	
	public String getOsType(){
		return osType;
	}
	
	public void setAppId(String appId){
		this.appId = appId;
	}
	
	public String getAppId(){
		return appId;
	}
	
	public void setAppKey(String appKey){
		this.appKey = appKey;
	}
	
	public String getAppKey(){
		return appKey;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return host;
	}
	
	public void setAppSecret(String appSecret){
		this.appSecret = appSecret;
	}
	
	public String getAppSecret(){
		return appSecret;
	}
	
	public void setMasterSecret(String masterSecret){
		this.masterSecret = masterSecret;
	}
	
	public String getMasterSecret(){
		return masterSecret;
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
	
}

