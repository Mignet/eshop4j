package com.eshop4j.api.response.mc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 消息详情
 * 
 * @Author ZhongLing
 * @Date 2016年1月25日 下午2:44:45
 */
public class MsgDetailResponse extends BaseEntity {
	private static final long serialVersionUID = 3700974022985203466L;

	public MsgDetailResponse() {

	}

	public MsgDetailResponse(SysMsg obj) {
		WebUtil.initObj(this, obj);
		this.setTime(DateUtils.format(obj.getStartTime(), DateUtils.FORMAT_MM));
	}

	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 创建时间
	 */
	private String time;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
