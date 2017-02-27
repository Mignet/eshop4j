package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

public class InvotateUserListResp extends BaseEntity{


	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 电话
	 */
	private String mobile;
	
	/**
	 * 姓名
	 */
	private String userName;
	
	/**
	 * 是否投资 0已投资 1未投资
	 */
	private Integer investFlag;
	
	/**
	 * 注册时间
	 */
	private Date registerDate;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getInvestFlag() {
		return investFlag;
	}
	public void setInvestFlag(Integer investFlag) {
		this.investFlag = investFlag;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
}
