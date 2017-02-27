package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.tc.fee.model.TCFeebalance;
import com.eshop4j.web.response.tc.FeeDetailRecordResponse;
import com.eshop4j.web.response.tc.FeebalanceListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月05日 18:31:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeebalanceMapper extends GenericDao<TCFeebalance,Long>{
	
	/**
	 * 月度佣金结算数量 -
	 * @param begintime 月开始时间
	 * @param endTime 月结束时间
	 * @return
	 */
	int getFeebalanceByMonthCount(@Param("beginTime")String begintime,@Param("endTime")String endTime);

	/**
	 *  保存月度佣金结算明细 
	 * @param feebalances
	 * @return
	 */
	int insertFeebalances(@Param("feebalances")List<TCFeebalance> feebalances);
	
	/**
	 * 获取月度佣金结算明细 
	 * @param begintime 月开始时间
	 * @param endTime 月结束时间
	 * @param page 分页参数
	 * @return
	 */
	List<TCFeebalance> getFeebalanceByMonth(@Param("beginTime")String begintime,@Param("endTime")String endTime,RowBounds page);
	
	/**
	 * 保存月度或季度佣金汇总明细 
	 * @param bizId
	 * @param type 0 =  月度 | 1 = 季度 
	 * @param year
	 * @param moth
	 * @param count
	 * @param begintime
	 * @param endTime
	 * @return
	 */
	int insertFeeSummary(@Param("bizId")String bizId,@Param("type")int type,@Param("year")String year,@Param("moth")String moth,@Param("count")int count);
	
	/**
	 * 保存佣金汇总日志
	 * @param bizId
	 * @param code
	 * @param msg
	 * @param time
	 * @return
	 */
	int insertFeeSummarylog(@Param("bizId")String bizId,@Param("code")String code,@Param("msg")String msg,@Param("time")Long time);
	
	/**
	 * 查询月度是否汇总
	 * @param bizId
	 * @return
	 */
	boolean isFeeSummary(@Param("bizId")String bizId);
	
	/**
	 * 获取月度佣金计算结果
	 * @param month
	 * @return
	 */
	List<FeebalanceListResponse> getFeebalanceListByMonth(@Param("month")String month);
	
	/**
	 * 查询佣金计算列表
	 * @param mobile
	 * @param page
	 * @return
	 */
	List<FeebalanceListResponse> getFeebalanceList(@Param("mobile")String mobile,RowBounds page);
	
	/**
	 * 查询理财师佣金结算记录
	 * @param mobile
	 * @param page
	 * @return
	 */
	List<FeebalanceListResponse> getFeebalanceRecordByMobile(@Param("mobile")String mobile,@Param("month")String month,RowBounds page);
	
	/**
	 * 查询佣金明细记录
	 * @param mobile
	 * @param month
	 * @param customerMobile
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	List<FeeDetailRecordResponse>  getFeeDetailRecord(@Param("mobile")String mobile,@Param("month")String month,@Param("customerMobile")String customerMobile,@Param("startTime")String startTime,@Param("endTime")String endTime,RowBounds page);
}
