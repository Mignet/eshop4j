package com.linkwee.web.model.crm;

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
public class CfpNewcomerWelfare implements Serializable {
	
	private static final long serialVersionUID = -2763062497438008553L;
	
    /**
     *上传头像状态： 0未完成，1已完成
     */
	private Integer uploadHeadImageStatus;
	
	 /**
     *邀请理财师状态： 0未完成，1已完成
     */
	private Integer inviteCfplannerStatus;
	
	 /**
     *绑卡认证状态： 0未完成，1已完成
     */
	private Integer bindCardStatus;
	
	 /**
     *邀请客户完成状态： 0未完成，1已完成
     */
	private Integer inviteCustomerStatus;


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

	public Integer getUploadHeadImageStatus() {
		return uploadHeadImageStatus;
	}

	public void setUploadHeadImageStatus(Integer uploadHeadImageStatus) {
		this.uploadHeadImageStatus = uploadHeadImageStatus;
	}

	public Integer getInviteCfplannerStatus() {
		return inviteCfplannerStatus;
	}

	public void setInviteCfplannerStatus(Integer inviteCfplannerStatus) {
		this.inviteCfplannerStatus = inviteCfplannerStatus;
	}
	
}

