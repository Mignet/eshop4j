package com.linkwee.api.response.crm;

import java.io.Serializable;

import com.linkwee.web.model.crm.InvestorNewcomerWelfare;
import com.linkwee.xoss.util.WebUtil;
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
public class InvestorCfpNewcomerTaskResponse implements Serializable {
	
	private static final long serialVersionUID = -2763062497438008553L;
	public InvestorCfpNewcomerTaskResponse() {

	}

	public InvestorCfpNewcomerTaskResponse(InvestorNewcomerWelfare obj) {
		WebUtil.initObj(this, obj);
		if(obj.getBindCardStatus() == 1 && obj.getFirstInvestStatus() == 1 
				&& obj.getInviteCustomerStatus() == 1 && obj.getRegisterStatus() == 1) {
			this.setFinishAll("1");
		} else {
			this.setFinishAll("0");
		}
		this.setBindCardAmount("5");
		this.setFirstInvestAmount("12");
		this.setInviteCustomerAmount("90");
		this.setRegisterAmount("528");
	}
    /**
     *注册状态： 0未完成，1已完成
     */
	private String registerStatus;
	
	 /**
     *首次投资状态： 0未完成，1已完成
     */
	private String firstInvestStatus;
	
	 /**
     *绑卡认证状态： 0未完成，1已完成
     */
	private String bindCardStatus;
	
	 /**
     *邀请客户完成状态： 0未完成，1已完成
     */
	private String inviteCustomerStatus;
	
	/**
	 * 是否全部完成
	 */
	private String finishAll;
	
	/**
	 * 注册红包金额
	 */
	private String registerAmount;
	
	 /**
    *首次投资红包金额
    */
	private String firstInvestAmount;
	
	 /**
    *绑卡认证红包金额
    */
	private String bindCardAmount;
	
	 /**
    *邀请客户红包金额
    */
	private String inviteCustomerAmount;

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getFirstInvestStatus() {
		return firstInvestStatus;
	}

	public void setFirstInvestStatus(String firstInvestStatus) {
		this.firstInvestStatus = firstInvestStatus;
	}

	public String getBindCardStatus() {
		return bindCardStatus;
	}

	public void setBindCardStatus(String bindCardStatus) {
		this.bindCardStatus = bindCardStatus;
	}

	public String getInviteCustomerStatus() {
		return inviteCustomerStatus;
	}

	public void setInviteCustomerStatus(String inviteCustomerStatus) {
		this.inviteCustomerStatus = inviteCustomerStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFinishAll() {
		return finishAll;
	}

	public void setFinishAll(String finishAll) {
		this.finishAll = finishAll;
	}

	public String getRegisterAmount() {
		return registerAmount;
	}

	public void setRegisterAmount(String registerAmount) {
		this.registerAmount = registerAmount;
	}

	public String getFirstInvestAmount() {
		return firstInvestAmount;
	}

	public void setFirstInvestAmount(String firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}

	public String getBindCardAmount() {
		return bindCardAmount;
	}

	public void setBindCardAmount(String bindCardAmount) {
		this.bindCardAmount = bindCardAmount;
	}

	public String getInviteCustomerAmount() {
		return inviteCustomerAmount;
	}

	public void setInviteCustomerAmount(String inviteCustomerAmount) {
		this.inviteCustomerAmount = inviteCustomerAmount;
	}
	
}

