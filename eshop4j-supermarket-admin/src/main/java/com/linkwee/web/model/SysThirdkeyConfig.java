package com.linkwee.web.model;

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
 * @创建时间：2016年08月17日 11:16:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysThirdkeyConfig implements Serializable {
	
	private static final long serialVersionUID = -6638085575417205654L;
	
    /**
     *主键
     */
	private Long orgId;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构公钥
     */
	private String orgKey;
	
    /**
     *机构私钥
     */
	private String orgSecret;
	
    /**
     *y：开启，n：关闭
     */
	private String orgStatus;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *逻辑删除：0:可用，1：删除
     */
	private Integer archive;
	
    /**
     *创建人
     */
	private String createUser;
	


	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public Long getOrgId(){
		return orgId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgKey(String orgKey){
		this.orgKey = orgKey;
	}
	
	public String getOrgKey(){
		return orgKey;
	}
	
	public void setOrgSecret(String orgSecret){
		this.orgSecret = orgSecret;
	}
	
	public String getOrgSecret(){
		return orgSecret;
	}
	
	public void setOrgStatus(String orgStatus){
		this.orgStatus = orgStatus;
	}
	
	public String getOrgStatus(){
		return orgStatus;
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
	
	public void setArchive(Integer archive){
		this.archive = archive;
	}
	
	public Integer getArchive(){
		return archive;
	}
	
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	
	public String getCreateUser(){
		return createUser;
	}
	
}

