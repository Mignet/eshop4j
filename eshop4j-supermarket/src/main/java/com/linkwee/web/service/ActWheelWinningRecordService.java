package com.linkwee.web.service;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.service.ActWheelWinningRecordService;
 /**
 * 
 * @描述： ActWheelWinningRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 10:55:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActWheelWinningRecordService extends GenericService<ActWheelWinningRecord,Long>{

	/**
	 * 查询ActWheelWinningRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 投资小牛在线在活动期限内投资金额
	 * @param userId
	 * @return
	 */
	Double queryInvestTotalMoney(String userId,String startDate,String endDate);

	/**
	 * 插入抽奖记录
	 * @param baseLottery
	 * @param i
	 * @param userId
	 * @param mobile
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType) throws Exception;
	
	/**
	 * 插入抽奖记录--春节活动
	 * @param baseLottery
	 * @param i
	 * @return
	 */
	Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType, String activityId) throws Exception;

	/**
	 * 用户中奖记录分页
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	PaginatorResponse<ActWheelWinningRecord> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<ActWheelWinningRecord> page);

	/**
	 * 查询活动已经抽奖的次数
	 * @param userId
	 * @param activityId
	 * @return
	 */
	Integer queryHasDrawTimes(String userId, String activityId);

	/**
	 * 理财师名下投资者活动期间投资的总金额
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Double queryInvestorHasInvestedTotalMoney(String userId, String startDate,String endDate);
}
