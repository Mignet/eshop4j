package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.acc.AcWithdrawApply;
import com.linkwee.web.model.acc.AcWithdrawTimes;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWithdrawApplyMapper extends GenericDao<AcWithdrawApply,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcWithdrawApply> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcWithdrawApply> selectDetailBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 提现记录明细
	 * @param page
	 * @return
	 */
	List<AcWithdrawApply> queryWithdrawLog(Page<AcWithdrawApply> page, Map<String, Object> conditions);

	/**
	 * 累计提现金额
	 * @return
	 */
	Double queryWithdrawSummary(@Param("userId")String userId);

	/**
	 * 查询本月提现次数
	 * @return
	 */
	AcWithdrawTimes queryWithdrawTimes(@Param("userId")String userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 需要更新的批次号
	 * @return
	 */
	List<AcWithdrawApply> queryWithdrawforJob();

	/**
	 * 根据ID查询提现记录
	 * @return
	 */
	AcWithdrawApply selectWithdrawById(@Param("id")String id);
}
