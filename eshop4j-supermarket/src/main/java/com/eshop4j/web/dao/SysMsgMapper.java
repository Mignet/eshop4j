package com.eshop4j.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.response.mc.MsgResp;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.mc.SysMsg;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 15:59:52
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysMsgMapper extends GenericDao<SysMsg,Long>{
	
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param pageni
     * @return
     */
	List<SysMsg> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 删除个人消息		
	 * @param msgs 消息id
	 * @param userNumber 用户编码
	 */
	public Integer deletePersonMsgs(@Param("msgs") String[] msgs,@Param("userNumber")String userNumber);
	/**
	 * 标识为已读
	 * @param msgs
	 * @param userNumber
	 * @return
	 */
	
	public Integer markMsgReaded(@Param("msgs") String[] msgs,@Param("userNumber")String userNumber);
	/**
	 * 个人消息全部设置为已读
	 * @param userNumber
	 * @return
	 */
	public Integer markMsgAllReaded(@Param("userNumber")String userNumber);
	/**
	 * 消息分页
	 * @param request
	 * @return
	 */
	
	public List<MsgResp> queryPersonMsgResp(Page<MsgResp> page,Map<String,Object> map);
	
	
	/**
	 * 统计未读消息数(公告和个人消息和)
	 * @param userNumber 用户编号
	 * @param personMsgDate 上次查看个人消息的时间
	 * @param sysMsgDate 上次查看系统消息的时间
	 * @param app类别
	 * @return
	 */
	public Integer queryUnReadMsgCount(@Param("userNumber")String userNumber,@Param("personMsgDate")Date personMsgDate
			,@Param("sysMsgDate")Date sysMsgDate,@Param("appType")Integer appType);


	/**
	 * 统计个人未读消息
	 * @Auther ZhongLing
	 * @Date 2016年1月25日
	 * @param userId
	 * @param sysMsgDate
	 * @return
	 */
	public Integer queryPersonUnreadCount(@Param("userNumber")String userNumber,@Param("appType")Integer appType);
	

	/**
	 * 批量新增
	 * @param 
	 * @return
	 */
	public Integer addBatch(List<SysMsg> list);
}
