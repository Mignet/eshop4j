package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.activity.response.ActivityBillboardResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActActivityWinningRecord;
import com.linkwee.web.model.ActivityList;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityWinningRecordMapper extends GenericDao<ActActivityWinningRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActActivityWinningRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 执行查询剩余次数的SQL
	 * @param leftTimesConditionSQL
	 * @return
	 */
	Integer execLeftTimesConditionSQL(@Param("leftTimesConditionSQL")String leftTimesConditionSQL);

	/**
	 * 用户中奖记录（分页）
	 * @param actActivityWinningRecord
	 * @param page
	 * @return
	 */
	List<ActActivityWinningRecord> queryUserPrizeRecord(ActActivityWinningRecord actActivityWinningRecord,RowBounds page);

	/**
	 * 查询系统没有发放的奖励
	 * @param actActivityWinningRecord
	 * @return
	 */
	List<ActActivityWinningRecord> queryNotIssueWinningRecord(ActActivityWinningRecord actActivityWinningRecord);

	/**
	 * 按等级抽奖情况下的剩余奖品次数
	 * @param activity 
	 * @param userId 
	 * @return
	 */
	Integer queryLeftTimesInGradeCondition(@Param("userId")String userId, @Param("activity")ActivityList activity);

	/**
	 * 执行查询排行SQL
	 * @param conditionSQL
	 * @return
	 */
	List<ActivityBillboardResponse> execUnknownPrizeCaseConditionSQL(@Param("conditionSQL")String conditionSQL);
}
