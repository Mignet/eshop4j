package com.eshop4j.web.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.eshop4j.api.response.mc.MsgResp;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysNotice;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2015年10月26日 20:05:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface SysMsgService{

	
	public Integer deletePersonMsgs(String[] ids,String userNumber);
	
	/**
	 * 
	 * @Date 2016年2月23日 上午11:38:17
	 * @param request
	 * @param msgType  消息类别，"sys"-系统消息  ，"person"-个人消息
	 * @return
	 */
	public PaginatorResponse<MsgResp> queryMsgResp(Page<MsgResp> page,Map<String,Object> conditions);
	
	
	/**
	 * 查询未读消息数
	 * @param userId 用户id
	 * @param userNumber 理财师编码
	 * @return
	 */
	public int queryLcsMsgCount(String userId,Integer appType);
	
	/**
	 * 批量添加个人信息
	 * @param personMsgs
	 * @return
	 */
	public int addMsgs(List<SysMsg> personMsgs);
	public int batchAddMsgs(AppTypeEnum appType,String content,Collection<String> userIds,PersonalMsgTypeEnum msgType);
	
	/**
	 * 添加信息个人
	 * @param msg
	 * @return
	 */
	public void addMsg(SysMsg msg);
	/**
	 * 添加公告
	 * @param msg
	 * @return
	 */
	public void addNotice(SysNotice notice);


	/**
	 * 未读公告和个人消息
	 * @param userId 
	 * @Date 2016年1月20日 下午7:30:37
	 * @param appType
	 * @return
	 */
	public int queryUnReadMsgCount( String userId, Integer appType);


	/**
	 * 未读公告消息统计
	 * @Date 2016年1月25日
	 * @param userId
	 * @return
	 */
	public Integer queryUnreadBulletinCount(Map<String,Object> conditions);
	
	/**
	 * 未读个人消息统计
	 * @Date 2016年1月25日
	 * @param userId
	 * @return
	 */
	public Integer queryUnreadLcsCount(String userId,Integer appType);


	/**
	 * 消息详情（个人）
	 * @Date 2016年1月25日
	 * @param msgId
	 * @return
	 */
	public SysMsg queryMsgDetail(String msgId);
	/**
	 * 消息详情公告
	 * @Date 2016年1月25日
	 * @param msgId
	 * @return
	 */
	public SysNotice queryNoticeDetail(String msgId);
	/**
	 * 设置个人消息已读
	 * @param ids 消息ID
	 * @param userNumber
	 * @return
	 */
	public Integer markPersonMsgReaded(String[] ids,String userNumber);
	
	/**
	 * 设置个人消息全部已读
	 * @param userNumber
	 * @return
	 */
	public Integer markPersonMsgAllReaded(String userNumber);
	
	/**
	 * 公告上下条记录
	 * @param rlt
	 * @return
	 */
	public Map<String, Object> findNearNotices(SysNotice rlt);

	
}
