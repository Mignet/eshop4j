package com.eshop4j.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.api.PaginatorRequest;

public class MsgRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614746364L;
	
    /**
     *流水号
     */
	private Integer msgId;
	
    /**
     *消息内容
     */
	private String content;
	
    /**
     *链接
     */
	private String link;
	
    /**
     *状态(0发布,1删除)
     */
	private Integer status;
	
    /**
     *消息类别(0系统消息;1理财师消息)
     */
	private Integer type;
	
    /**
     *用户编码
     */
	private String userNumber;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date startTime;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  
	private Date crtTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	private Date modifyTime;


	private String message;
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private Integer appType;

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
