package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述：红包明细接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 10:52:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketDetailMapper extends GenericDao<ActRedpacketDetail,Long>{
	
	/**
	 * 批量插入红包
	 * @param redpackets
	 * @return
	 */
	int inserts(@Param("redpackets")List<ActRedpacketDetail> redpackets);

	/**
	 * 获取发放红包编号 
	 * @param userId
	 * @param redpacketId
	 * @param count
	 * @return
	 */
	List<String> getSendRedpacketIds(@Param("userId")String userId,@Param("redpacketId")String redpacketId,@Param("count")Integer count);
	
	/**
	 * 发放红包
	 * @param redpacketIds
	 * @return
	 */
	int sendRedpacket(ActRedpacketDetail sendRedpacketDetail);
	
	/**
	 * 更新红包过期状态
	 * @param sendRedpacketIds
	 * @return
	 */
	int updateExpirationStatus(@Param("sendRedpacketIds")List<String> redpacketSendIds);
	
	/**
	 * 更新红包过期状态
	 * @param sendRedpacketIds
	 * @return
	 */
	int updateExpirationStatusByDay(@Param("sendRedpacketIds")List<String> redpacketSendIds);
	
	/**
	 * 使用红包
	 * @param redpacketDetailId
	 * @return
	 */
	int useRedpacket(@Param("redpacketId")String redpacketId,@Param("updateTime")Date updateTime);
	
	/**
	 * 获取用户可使用红包
	 * @param userId
	 * @return
	 */
	List<ActRedpacketDetail> getUserUsableRedpackets(@Param("userId")String userId);
	
}
