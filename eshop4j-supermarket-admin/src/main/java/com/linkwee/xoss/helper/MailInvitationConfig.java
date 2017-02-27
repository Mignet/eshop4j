package com.linkwee.xoss.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MailInvitationConfig {
	String customerInvitationCustomer;
	String invitationCustomer;
	String invitationLcs;
	public String getCustomerInvitationCustomer() {
		return customerInvitationCustomer;
	}
	public void setCustomerInvitationCustomer(String customerInvitationCustomer) {
		this.customerInvitationCustomer = customerInvitationCustomer;
	}
	public String getInvitationCustomer() {
		return invitationCustomer;
	}
	public void setInvitationCustomer(String invitationCustomer) {
		this.invitationCustomer = invitationCustomer;
	}
	public String getInvitationLcs() {
		return invitationLcs;
	}
	public void setInvitationLcs(String invitationLcs) {
		this.invitationLcs = invitationLcs;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
