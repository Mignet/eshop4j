package com.linkwee.api.response.mc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.SmCustomerDevice;
import com.linkwee.xoss.util.WebUtil;

public class CustomerDeviceResponse extends BaseEntity {
	private static final long serialVersionUID = 3700974022985203466L;

	public CustomerDeviceResponse() {

	}

	public CustomerDeviceResponse(SmCustomerDevice obj) {
		WebUtil.initObj(this, obj);
		this.setPlatformflag(obj.getIsSendnotice()+"");
		//this.setInteractflag(obj.getIsSendinteractnotice()+"");
	}
	/**
	 * 平台消息免打扰是否开启：0-不开启 1-开启免打扰
	 */
	private String platformflag;
	/**
	 * 互动消息免打扰是否开启：0-不开启 1-开启免打扰
	 */
	//private String interactflag;

	

	public String getPlatformflag() {
		return platformflag;
	}



	public void setPlatformflag(String platformflag) {
		this.platformflag = platformflag;
	}



/*	public String getInteractflag() {
		return interactflag;
	}



	public void setInteractflag(String interactflag) {
		this.interactflag = interactflag;
	}*/



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
