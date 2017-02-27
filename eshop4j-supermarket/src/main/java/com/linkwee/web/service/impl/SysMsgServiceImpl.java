package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.linkwee.api.response.mc.MsgResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.SysMsgMapper;
import com.linkwee.web.dao.SysNoticeMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.mc.SysNotice;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.util.HtmlFilterUtil;
import com.linkwee.xoss.util.InterceptUtility;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： 何源
 * 
 * @创建时间：2015年10月26日 20:05:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("msgService")
public class SysMsgServiceImpl implements SysMsgService{
	
	@Autowired
	private SysMsgMapper sysMsgMapper;
	@Autowired
	private SysNoticeMapper sysNoticeMapper;
	
	@Resource
	private SysApiInvokeLogService apiInvokeLogService;
	
	public Integer deletePersonMsgs(String[] ids,String userNumber){
		return sysMsgMapper.deletePersonMsgs(ids,userNumber);
	}
	
	public PaginatorResponse<MsgResp> queryMsgResp(Page<MsgResp> page,Map<String,Object> conditions){
		
		String msgType= "";
		String userNumber = "";
		if(conditions!=null && conditions.containsKey("msgType")){
			msgType = (String)conditions.get("msgType");
		}
		if(conditions!=null && conditions.containsKey("userNumber")){
			userNumber = (String)conditions.get("userNumber");
		}
		List<MsgResp> msgList = null;
		if(msgType.equals("sys")){
	    	msgList = sysNoticeMapper.querySysMsgResp(page, conditions);
	    	for(MsgResp notice : msgList){
	    		if(notice != null && StringUtils.isNotBlank(notice.getContent()) ){
	    			String content = HtmlFilterUtil.filterHtml(notice.getContent()).replaceAll("&nbsp;", "").replaceAll("\\s*", "");
	    			if(StringUtils.isNotBlank(content) && content.length() > 100){
	    				notice.setContent(content.substring(0, 100));
	    			}else{
	    				notice.setContent(content);
	    			}   			
	    		}    		
	    	}
		}else {
			msgList = sysMsgMapper.queryPersonMsgResp(page, conditions);
			Object needUpdateReadStatus = conditions.get("needUpdateReadStatus");
			if(needUpdateReadStatus!=null && ObjectUtils.equals(needUpdateReadStatus, "1")){
				//修改个人消息为已读
				if(msgList!=null && msgList.size()>0){
					List<String> idList = new ArrayList<String>();
					for(MsgResp item :msgList){
						idList.add(String.valueOf(item.getId()));
					}
					
				sysMsgMapper.markMsgReaded((String[])idList.toArray(new String[idList.size()]), userNumber);
				}
				
			}
			
			
		}
		
		/*for(MsgResp item:msgList){
			if(msgType.equals("person")){//个人消息加载出来的页都为已读
				item.setRead(1);
			}
		}*/
		
		 PaginatorResponse<MsgResp> paginatorResponse = new PaginatorResponse<MsgResp>();
	        paginatorResponse.setDatas(msgList);
	        paginatorResponse.setValuesByPage(page);
		return  paginatorResponse;
	}
	
	/**
	 * 查询未读消息数 （公告和个人消息）
	 * @param userId 用户id
	 * @return
	 */
	public int queryLcsMsgCount(String userId,Integer appType){
		SysApiInvokeLog apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_MSG_BULLETIN_PAGELIST,userId,appType);
		Date sysMsgDate = null;
		if(apiInvokeLog!=null){
			sysMsgDate = apiInvokeLog.getChgTime();
		}else{
			sysMsgDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_MSG_PERSON_PAGELIST,userId,appType);
		Date personMsgDate = null;
		if(apiInvokeLog!=null){
			personMsgDate = apiInvokeLog.getChgTime();
		}else{
			personMsgDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		return sysMsgMapper.queryUnReadMsgCount(userId, personMsgDate, sysMsgDate,appType);
	}
	
	/**
	 * 添加信息
	 * @param personMsgs
	 * @return
	 */
	public int addMsgs(List<SysMsg> personMsgs){
		return sysMsgMapper.addBatch(personMsgs);
	}
	
	/**
	 * 添加信息
	 * @param msg
	 * @return
	 */
	public void addMsg(SysMsg msg){
		sysMsgMapper.insertSelective(msg);
	}


	@Override
	public int queryUnReadMsgCount(String userId, Integer appType) {
		SysApiInvokeLog apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_MSG_BULLETIN_PAGELIST,userId,appType);
		Date sysMsgDate = null;
		if(apiInvokeLog!=null){
			sysMsgDate = apiInvokeLog.getChgTime();
		}else{
			sysMsgDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		apiInvokeLog = apiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_MSG_PERSON_PAGELIST,userId,appType);
		Date personMsgDate = null;
		if(apiInvokeLog!=null){
			personMsgDate = apiInvokeLog.getChgTime();
		}else{
			personMsgDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		return sysMsgMapper.queryUnReadMsgCount(userId,personMsgDate, sysMsgDate,appType);
	}

	@Override
	public Integer queryUnreadBulletinCount(Map<String,Object> conditions) {		
		return sysNoticeMapper.queryUnReadSysMsgCount(conditions);
	}

	@Override
	public Integer queryUnreadLcsCount(String userId,Integer appType) {
	
		return sysMsgMapper.queryPersonUnreadCount(userId,appType);
	}

	@Override
	public SysMsg queryMsgDetail(String msgId) {
		return sysMsgMapper.selectByPrimaryKey(Long.valueOf(msgId));
	}

	@Override
	public void addNotice(SysNotice notice) {
		sysNoticeMapper.insertSelective(notice);
		
	}

	@Override
	public SysNotice queryNoticeDetail(String msgId) {
		return sysNoticeMapper.selectByPrimaryKey(Long.valueOf(msgId));
	}

	@Override
	public Integer markPersonMsgReaded(String[] ids, String userNumber) {
		return sysMsgMapper.markMsgReaded(ids,userNumber);
	}

	@Override
	public Integer markPersonMsgAllReaded(String userNumber) {
		return sysMsgMapper.markMsgAllReaded(userNumber);
	}
	
	@Override
	public int batchAddMsgs(AppTypeEnum appType, String content,
			Collection<String> userIds, PersonalMsgTypeEnum personalMsgType) {
		//userIds 分批处理		
		List<List<String>> userIdss =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(userIds) , userIdss,500);
		int rows = 0;
		for(final List<String> list :userIdss){
			List<SysMsg> msgs = new ArrayList<SysMsg>();
			for(String userId :list){
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
				msg.setTypeName(personalMsgType != null ? personalMsgType.getValue() : null);
				msg.setStartTime(new Date());
				msg.setCrtTime(new Date());
				msg.setModifyTime(new Date());
				msgs.add(msg);
			}
			rows += sysMsgMapper.addBatch(msgs);
		}
		
		return rows;
	}
	
	@Override
	public Map<String, Object> findNearNotices(SysNotice rlt) {
		// TODO Auto-generated method stub
		MsgResp beforeNews = sysNoticeMapper.beforeOneNotice(rlt);
		MsgResp nextNews = sysNoticeMapper.nextOneNotice(rlt);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("beforOne", beforeNews);
		resultMap.put("nextOne", nextNews);
		return resultMap;
	}
	
}
