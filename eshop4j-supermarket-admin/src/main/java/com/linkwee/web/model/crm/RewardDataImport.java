package com.linkwee.web.model.crm;

import java.io.Serializable;

public class RewardDataImport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5204475246348147454L;
	
	private String mobile;
	
	private String amount;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
