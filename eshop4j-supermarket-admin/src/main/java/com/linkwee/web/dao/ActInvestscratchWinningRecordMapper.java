package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActInvestscratchWinningRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月25日 17:04:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActInvestscratchWinningRecordMapper extends GenericDao<ActInvestscratchWinningRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActInvestscratchWinningRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 查询最近中奖的50条记录
	 * @return
	 */
	List<ActInvestscratchWinningRecord> queryWinningRecordOfLastFifty();

	/**
	 * 查询已经使用的刮奖次数
	 * @param userId
	 * @return
	 */
	Integer queryScratchedTime(String userId);

	/**
	 * 查询总的的刮奖次数
	 * @param userId
	 * @return
	 */
	Integer queryTotalScratchTime(String userId);

	/**
	 * 查询第多少个中奖用户
	 * @param userId
	 * @return
	 */
	Integer queryWinningUserNumber();
}
