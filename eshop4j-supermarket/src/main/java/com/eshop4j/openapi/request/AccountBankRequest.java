package com.eshop4j.openapi.request;

import javax.validation.constraints.NotNull;

import com.eshop4j.core.base.BaseEntity;

public class AccountBankRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *用户Id
     */
	@NotNull(message="用户Id不能为空")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
