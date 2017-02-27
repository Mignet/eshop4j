package com.eshop4j.api.response.crm;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.web.model.crm.InvotateUserListResp;
import com.eshop4j.xoss.util.WebUtil;

public class InvotateUserListResponse extends BaseEntity{


	private static final long serialVersionUID = 1L;
	
	public InvotateUserListResponse() {

	}

	public InvotateUserListResponse(InvotateUserListResp obj) {
		WebUtil.initObj(this, obj);
		this.setRegisterDate(DateUtils.format(obj.getRegisterDate(), DateUtils.FORMAT_SHORT));
		if(obj.getInvestFlag() == 0) {
			this.setInvestFlag("已投资");
		} else {
			this.setInvestFlag("未投资");
		}
	}
	
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
	 * 是否投资
	 */
	private String investFlag;
	
	/**
	 * 注册时间
	 */
	private String registerDate;

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

	public String getInvestFlag() {
		return investFlag;
	}

	public void setInvestFlag(String investFlag) {
		this.investFlag = investFlag;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
}
