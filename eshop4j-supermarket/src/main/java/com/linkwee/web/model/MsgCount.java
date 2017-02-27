package com.linkwee.web.model;

/**
 * 未读消息统计
 * 
 * @Author ZhongLing
 * @Date 2016年1月25日 上午11:10:21
 */
public class MsgCount  {

	/**
	 * 未读公告消息数量
	 */
	private Integer bulletinMsgCount;
	/**
	 * 未读个人消息数量
	 */
	private Integer personMsgCount;
	public Integer getBulletinMsgCount() {
		return bulletinMsgCount;
	}
	public void setBulletinMsgCount(Integer bulletinMsgCount) {
		this.bulletinMsgCount = bulletinMsgCount;
	}
	public Integer getPersonMsgCount() {
		return personMsgCount;
	}
	public void setPersonMsgCount(Integer personMsgCount) {
		this.personMsgCount = personMsgCount;
	}

}
