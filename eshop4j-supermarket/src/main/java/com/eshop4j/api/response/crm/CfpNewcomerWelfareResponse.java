package com.eshop4j.api.response.crm;

import java.io.Serializable;

import com.eshop4j.web.model.crm.CfpNewcomerWelfare;
import com.eshop4j.xoss.util.WebUtil;
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
public class CfpNewcomerWelfareResponse implements Serializable {
	
	private static final long serialVersionUID = -2763062497438008553L;
	public CfpNewcomerWelfareResponse() {

	}

	public CfpNewcomerWelfareResponse(CfpNewcomerWelfare obj) {
		WebUtil.initObj(this, obj);
		if(obj.getBindCardStatus() == 1 && obj.getInviteCfplannerStatus() == 1 
				&& obj.getInviteCustomerStatus() == 1 && obj.getUploadHeadImageStatus() == 1) {
			this.setFinishAll("1");
		} else {
			this.setFinishAll("0");
		}
		this.setBindCardAmount("15");
		this.setInviteCfplannerAmount("110");
		this.setInviteCustomerAmount("50");
		this.setUploadHeadImageAmount("10");
	}
	
    /**
     *上传头像状态： 0未完成，1已完成
     */
	private String uploadHeadImageStatus;
	
	 /**
     *邀请理财师状态： 0未完成，1已完成
     */
	private String inviteCfplannerStatus;
	
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
	 * 上传头像红包金额
	 */
	private String uploadHeadImageAmount;
	
	/**
    *邀请理财师红包金额
    */
	private String inviteCfplannerAmount;
	
	 /**
    *绑卡认证红包金额
    */
	private String bindCardAmount;
	
	 /**
    *邀请客户红包金额
    */
	private String inviteCustomerAmount;
	
	
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

	public String getUploadHeadImageStatus() {
		return uploadHeadImageStatus;
	}

	public void setUploadHeadImageStatus(String uploadHeadImageStatus) {
		this.uploadHeadImageStatus = uploadHeadImageStatus;
	}

	public String getInviteCfplannerStatus() {
		return inviteCfplannerStatus;
	}

	public void setInviteCfplannerStatus(String inviteCfplannerStatus) {
		this.inviteCfplannerStatus = inviteCfplannerStatus;
	}

	public String getFinishAll() {
		return finishAll;
	}

	public void setFinishAll(String finishAll) {
		this.finishAll = finishAll;
	}

	public String getUploadHeadImageAmount() {
		return uploadHeadImageAmount;
	}

	public void setUploadHeadImageAmount(String uploadHeadImageAmount) {
		this.uploadHeadImageAmount = uploadHeadImageAmount;
	}

	public String getInviteCfplannerAmount() {
		return inviteCfplannerAmount;
	}

	public void setInviteCfplannerAmount(String inviteCfplannerAmount) {
		this.inviteCfplannerAmount = inviteCfplannerAmount;
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

