package com.linkwee.api.request.mc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：公告消息详情
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class MsgDetailRequest {

	/**
	 * 消息id
	 */
	@NotNull(message = "ID不能为空")
	private String msgId;


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
