package com.linkwee.api.response.mc;

import java.util.Date;

/**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2015年10月27日 09:41:37
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class MsgResp  {
	/**
     *流水号
     */
	private String id;
	
    /**
     *
     */
	private String content;
	
	
    /**
     *状态(0发布,1删除)
     */
	private String status;
	
	
    /**
     *用户ID
     */
	private String userNumber;
	
    /**
     *生效时间
     */
	private Date startTime;
	
	
	/**
	 * 读取状态(1已读,0未读)
	 */
	private String readStatus;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
    /**
     *应用类别0公共，1理财师，2投资者
     */
	private String appType;
	
    /**
     *类型名称
     */
	private String typeName;
	
	 /**
     *公告标题
     */
	private String message;
	/**
	 * 跳转按钮显示文案
	 */
	private String linkBtnTxt;
	/**
	 * 跳转地址key
	 */
	private String linkUrlKey;
	
	
	
	/**
	 * 0 系统消息；1个人消息
	 */
	private int type;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
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

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 公告链接
	 */
	private String link;
	/**
	 * 是否已读 1已读,0未读
	 */
	private Integer read;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLinkBtnTxt() {
		return linkBtnTxt;
	}

	public void setLinkBtnTxt(String linkBtnTxt) {
		this.linkBtnTxt = linkBtnTxt;
	}

	public String getLinkUrlKey() {
		return linkUrlKey;
	}

	public void setLinkUrlKey(String linkUrlKey) {
		this.linkUrlKey = linkUrlKey;
	}
	


}
