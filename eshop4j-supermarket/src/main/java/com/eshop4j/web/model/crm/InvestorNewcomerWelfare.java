package com.eshop4j.web.model.crm;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月09日 14:24:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvestorNewcomerWelfare implements Serializable {
	
	private static final long serialVersionUID = -2763062497438008553L;
	
    /**
     *注册状态： 0未完成，1已完成
     */
	private Integer registerStatus;
	
	 /**
     *首次投资状态： 0未完成，1已完成
     */
	private Integer firstInvestStatus;
	
	 /**
     *绑卡认证状态： 0未完成，1已完成
     */
	private Integer bindCardStatus;
	
	 /**
     *邀请客户完成状态： 0未完成，1已完成
     */
	private Integer inviteCustomerStatus;

	public Integer getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(Integer registerStatus) {
		this.registerStatus = registerStatus;
	}

	public Integer getFirstInvestStatus() {
		return firstInvestStatus;
	}

	public void setFirstInvestStatus(Integer firstInvestStatus) {
		this.firstInvestStatus = firstInvestStatus;
	}

	public Integer getBindCardStatus() {
		return bindCardStatus;
	}

	public void setBindCardStatus(Integer bindCardStatus) {
		this.bindCardStatus = bindCardStatus;
	}

	public Integer getInviteCustomerStatus() {
		return inviteCustomerStatus;
	}

	public void setInviteCustomerStatus(Integer inviteCustomerStatus) {
		this.inviteCustomerStatus = inviteCustomerStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

