package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 消息推送实体bean
 * 
 * @author xuzhao
 * @Date 2016年3月8日 上午11:00:21
 */
public class PushMessageInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String msgType;
	private String msgParam;
	private Integer appType;
	private Integer isTimingTask;
	private Date sendTime;
	private Integer delstatus;
	private String errorInfo;
	private Integer status;
	private Date chgTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgParam() {
		return msgParam;
	}

	public void setMsgParam(String msgParam) {
		this.msgParam = msgParam;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getIsTimingTask() {
		return isTimingTask;
	}

	public void setIsTimingTask(Integer isTimingTask) {
		this.isTimingTask = isTimingTask;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(Integer delstatus) {
		this.delstatus = delstatus;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getChgTime() {
		return chgTime;
	}

	public void setChgTime(Date chgTime) {
		this.chgTime = chgTime;
	}

}
