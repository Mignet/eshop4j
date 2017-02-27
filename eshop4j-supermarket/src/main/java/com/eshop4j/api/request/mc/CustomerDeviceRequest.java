package com.eshop4j.api.request.mc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class CustomerDeviceRequest {
	/**
	 * 是否推送通知栏消息：0-不开启 1-开启免打扰
	 */
	@NotNull(message = "issendNotice不能为空")
	private String issendNotice;
	/**
	 * 互动消息免打扰是否开启：0-不开启 1-开启免打扰
	 */
	/*private int interactflag;

	public int getPlatformflag() {
		return platformflag;
	}

	public void setPlatformflag(int platformflag) {
		this.platformflag = platformflag;
	}*/


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	public String getIssendNotice() {
		return issendNotice;
	}
	public void setIssendNotice(String issendNotice) {
		this.issendNotice = issendNotice;
	}
}
