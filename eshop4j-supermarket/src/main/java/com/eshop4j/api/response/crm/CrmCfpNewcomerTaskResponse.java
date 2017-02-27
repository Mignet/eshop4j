package com.eshop4j.api.response.crm;

import java.io.Serializable;

import com.eshop4j.web.model.crm.CrmCfpNewcomerTask;
import com.eshop4j.xoss.util.WebUtil;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpNewcomerTaskResponse implements Serializable {
	
	private static final long serialVersionUID = 3734284429588325954L;
	
	public CrmCfpNewcomerTaskResponse() {

	}

	public CrmCfpNewcomerTaskResponse(CrmCfpNewcomerTask obj) {
		WebUtil.initObj(this, obj);
		this.setBuyProductStatus(obj.getRecommendPlatformStatus() + "");
		if(obj.getInviteCustomerStatus() == 0) {
			this.setGrantHongbaoStatus("3");
			this.setRecommendProductStatus("3");
		}
		if(obj.getGrantHongbaoStatus() != 0
				&& obj.getInviteCfplannerStatus() != 0
				&& obj.getInviteCustomerStatus() != 0
				&& obj.getRecommendPlatformStatus() != 0
				&& obj.getRecommendProductStatus() != 0
				&& obj.getSeeProfitStatus() != 0) {
			this.setFinishAll("1");
		} else {
			this.setFinishAll("0");
		}
	}
	
	
	 /**
     *邀请客户完成状态： 0未完成，1已完成 , 2已领取
     */
	private String inviteCustomerStatus;
	
    /**
     *邀请理财师完成状态： 0未完成，1已完成, 2已领取
     */
	private String inviteCfplannerStatus;
	
    /**
     *推荐产品完成状态： 0未完成，1已完成 , 2已领取
     */
	private String recommendProductStatus;
	
    /**
     *推荐平台完成状态： 0未完成，1已完成 , 2已领取
     */
	private String buyProductStatus;
	
    /**
     *发红包完成状态： 0未完成，1已完成, 2已领取
     */
	private String grantHongbaoStatus;
	
    /**
     *查看收益完成状态： 0未完成，1已完成, 2已领取
     */
	private String seeProfitStatus;
	
	/**
	 * 是否完成所有:0未完成， 1已完成
	 */
	private String finishAll;

	public String getInviteCustomerStatus() {
		return inviteCustomerStatus;
	}

	public void setInviteCustomerStatus(String inviteCustomerStatus) {
		this.inviteCustomerStatus = inviteCustomerStatus;
	}

	public String getInviteCfplannerStatus() {
		return inviteCfplannerStatus;
	}

	public void setInviteCfplannerStatus(String inviteCfplannerStatus) {
		this.inviteCfplannerStatus = inviteCfplannerStatus;
	}

	public String getRecommendProductStatus() {
		return recommendProductStatus;
	}

	public void setRecommendProductStatus(String recommendProductStatus) {
		this.recommendProductStatus = recommendProductStatus;
	}


	public String getGrantHongbaoStatus() {
		return grantHongbaoStatus;
	}

	public void setGrantHongbaoStatus(String grantHongbaoStatus) {
		this.grantHongbaoStatus = grantHongbaoStatus;
	}

	public String getSeeProfitStatus() {
		return seeProfitStatus;
	}

	public void setSeeProfitStatus(String seeProfitStatus) {
		this.seeProfitStatus = seeProfitStatus;
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

	public String getBuyProductStatus() {
		return buyProductStatus;
	}

	public void setBuyProductStatus(String buyProductStatus) {
		this.buyProductStatus = buyProductStatus;
	}

	

	
}

