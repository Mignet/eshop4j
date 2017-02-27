package com.eshop4j.api.request.mc;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：删除消息
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class MsgDelRequest {

	/**
	 * 反馈内容
	 */
	@NotNull(message = "消息不能为空")
	private String msgIds;

	public String getMsgIds() {
		return msgIds;
	}

	public void setMsgIds(String msgIds) {
		this.msgIds = msgIds;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
