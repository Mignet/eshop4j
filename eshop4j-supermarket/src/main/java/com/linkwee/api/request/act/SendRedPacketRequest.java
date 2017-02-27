package com.linkwee.api.request.act;

import org.hibernate.validator.constraints.NotBlank;

import com.linkwee.core.base.BaseEntity;

public class SendRedPacketRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7393082417259196339L;
	
	@NotBlank(message="红包编号不能为空")
	private String rid;
	@NotBlank(message="用户手机号码不能为空")
	private String userMobiles;
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getUserMobiles() {
		return userMobiles;
	}
	public void setUserMobiles(String userMobiles) {
		this.userMobiles = userMobiles;
	}
	
	

}
