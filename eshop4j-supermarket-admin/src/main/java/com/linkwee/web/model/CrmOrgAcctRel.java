package com.linkwee.web.model;

import java.io.Serializable;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmOrgAcctRel implements Serializable {
	
	private static final long serialVersionUID = -4696492987392159556L;
	
    /**
     *主键，自增长
     */
	private String id;
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgNumber;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     * 机构后台账户
     */
	private String orgAccount;
	
	/**
	 * 机构账户类型
	 */
	private int orgAccountType;
	
    /**
     *是否投资
     */
	private int isInvested;
	
    /**
     *是否新账户
     */
	private int isNewUser;
	
    /**
     *创建时间
     */
	private Date creatTime;
	 /**
     *修改时间
     */
	private Date updateTime;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgAccount() {
		return orgAccount;
	}
	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}
	public int getOrgAccountType() {
		return orgAccountType;
	}
	public void setOrgAccountType(int orgAccountType) {
		this.orgAccountType = orgAccountType;
	}
	public int getIsInvested() {
		return isInvested;
	}
	public void setIsInvested(int isInvested) {
		this.isInvested = isInvested;
	}
	public int getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(int isNewUser) {
		this.isNewUser = isNewUser;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}

