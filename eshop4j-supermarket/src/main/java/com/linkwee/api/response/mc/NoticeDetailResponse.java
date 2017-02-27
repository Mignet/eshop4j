package com.linkwee.api.response.mc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.mc.SysNotice;
import com.linkwee.xoss.util.WebUtil;

/**
 * 消息详情
 * 
 * @Author ZhongLing
 * @Date 2016年1月25日 下午2:44:45
 */
public class NoticeDetailResponse extends BaseEntity {
	private static final long serialVersionUID = 3700974022985203466L;

	public NoticeDetailResponse() {

	}

	public NoticeDetailResponse(SysNotice obj) {
		WebUtil.initObj(this, obj);
		this.setTime(DateUtils.format(obj.getStartTime(), DateUtils.FORMAT_MM));
		this.title = obj.getMessage();
		this.link = obj.getLink();
		this.content = obj.getContent();
	}

	/**
	 * 消息id
	 */
	private String id;

	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 创建时间
	 */
	private String time;
	/**
	 * 链接
	 * @return
	 */
	private String link;
	/**
	 * 
	 * @return
	 */
	private String content;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
