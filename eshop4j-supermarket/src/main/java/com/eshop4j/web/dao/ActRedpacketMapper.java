package com.eshop4j.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.act.redpacket.model.ActRedpacket;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.response.act.RedpacketListResponse;
import com.eshop4j.web.response.act.RedpacketStatisticsResponse;

 /**
 * 
 * @描述： 红包接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 13:13:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketMapper extends GenericDao<ActRedpacket,Long>{
	
	/**
	 * 红包是否存在
	 * @param redpacketId
	 * @return
	 */
	boolean isExistRedpacket(@Param("redpacketId")String redpacketId);
	
	/**
	 * 获取红包列表
	 * @return
	 */
	List<RedpacketListResponse> getRedpacketList(RowBounds page);
	
	/**
	 * 更新红包
	 * @param redpacket
	 * @return
	 */
	int updateRedpacket(ActRedpacket redpacket);
	
	/**
	 * 更新发放红包
	 * @param redpacket
	 * @return
	 */
	int updateSendRedpacket(ActRedpacket redpacket);
	
	
	int updateSendRedpackets(@Param("redpacketIds")Set<String> redpacketIds);
	
	
	/**
	 * 红包每日统计
	 * @param start
	 * @param end
	 * @return
	 */
	RedpacketStatisticsResponse getRedpacketStatistics(@Param("start")Date start,@Param("end")Date end);
	
	
	/**
	 * 获取一组红包
	 * @param redpacketIds
	 * @return
	 */
	List<ActRedpacket> getRedpackets(@Param("redpacketIds")List<String> redpacketIds);
}
