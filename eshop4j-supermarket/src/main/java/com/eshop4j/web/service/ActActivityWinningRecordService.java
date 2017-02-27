package com.eshop4j.web.service;

import java.util.List;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.service.ActActivityWinningRecordService;
 /**
 * 
 * @描述： ActActivityWinningRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityWinningRecordService extends GenericService<ActActivityWinningRecord,Long>{

	/**
	 * 查询ActActivityWinningRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 剩余参与活动次数
	 * @param userId
	 * @param format
	 * @param format2
	 * @return
	 */
	Integer queryLeftTimes(String userId, ActivityList activity);

	/**
	 * 查询活动按某种抽奖方式未发放的奖励
	 * @param activity
	 * @param userId
	 * @param i
	 * @return
	 */
	ActActivityWinningRecord queryNotIssuedPrize(ActivityList activity, String userId, int i);

	/**
	 * 按奖励等级查未发放的奖励记录
	 * @param actActivityPrizeCase
	 * @param userId
	 * @return
	 */
	ActActivityWinningRecord queryNotIssuedPrize(ActActivityPrizeCase actActivityPrizeCase, String userId);

	/**
	 * 按奖励等级查已经发放的奖励记录
	 * @param actActivityPrizeCase
	 * @param userId
	 * @return
	 */
	List<ActActivityWinningRecord> queryIssuedPrize(ActActivityPrizeCase actActivityPrizeCase, String userId);

	/**
	 * 用户中奖记录分页
	 * @param actActivityWinningRecord
	 * @param page
	 * @return
	 */
	PaginatorResponse<ActActivityWinningRecord> queryUserPrizeRecord(ActActivityWinningRecord actActivityWinningRecord,Page<ActActivityWinningRecord> page);

	/**
	 * 查询系统没有发放的奖励
	 * @param actActivityWinningRecord
	 * @return
	 */
	List<ActActivityWinningRecord> queryNotIssueWinningRecord(ActActivityWinningRecord actActivityWinningRecord);

	/**
	 * 按等级抽奖情况下的剩余奖品次数
	 * @param userId
	 * @param activity
	 * @return
	 */
	Integer queryLeftTimesInGradeCondition(String userId, ActivityList activity);

	/**
	 * 未知中奖奖项的情况下创建中奖记录
	 * @param activity
	 * @return
	 */
	List<ActActivityWinningRecord> createWinningRecords(ActivityList activity);
}
