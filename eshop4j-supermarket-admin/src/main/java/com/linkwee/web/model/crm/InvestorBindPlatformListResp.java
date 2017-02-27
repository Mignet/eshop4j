package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * 描述：投资者所绑定的机构信息
 * @author yalin
 * @date 2016年9月29日 下午2:07:09 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class InvestorBindPlatformListResp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3380246188914091161L;
	
	/**
	 * 机构编码
	 */
	private String orgNumber;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机构帐号
	 */
	private String orgAccount;
	
	/**
	 * 第三方账号类别: 1、微信账号 2、第三方web账户
	 */
	private Integer orgAccountType;
	
	/**
	 * 是否机构新用户 0否，1是
	 */
	private Integer isNewUser;
	/**
	 * 是否投资 0否，1是
	 */
	private Integer isInvested;
	/**
	 * 绑定时间
	 */
	private Date bindDate;
	
	
	

	

	public Integer getOrgAccountType() {
		return orgAccountType;
	}

	public void setOrgAccountType(Integer orgAccountType) {
		this.orgAccountType = orgAccountType;
	}

	public Integer getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Integer isNewUser) {
		this.isNewUser = isNewUser;
	}

	public Integer getIsInvested() {
		return isInvested;
	}

	public void setIsInvested(Integer isInvested) {
		this.isInvested = isInvested;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	
}
