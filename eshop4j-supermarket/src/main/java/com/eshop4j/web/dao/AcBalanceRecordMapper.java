package com.eshop4j.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.request.acc.MonthProfixDetailRequest;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.model.acc.MonthProfixDetailListResp;
import com.eshop4j.web.model.acc.MonthProfixTotalListResp;

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
public interface AcBalanceRecordMapper extends GenericDao<AcBalanceRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcBalanceRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	

	/**
	 * 我的账户明细
	 * @param page
	 * @return
	 */
	List<AcBalanceRecord> queryMyAccountDetails(Page<AcBalanceRecord> page, Map<String, Object> conditions);

	/**
	 * 我的账户明细
	 * @param page
	 * @return
	 */
	List<AcBalanceRecord> queryMyAccountDetails2(Page<AcBalanceRecord> page, Map<String, Object> conditions);

	 /**
     * 发放奖励
     * @param dt
     * @param page
     * @return
     */
	List<AcBalanceRecord> selectGrantBySearchInfo(@Param("dt")DataTable dt, Page<AcBalanceRecord> page);

	/**
	 * 检查当天是否有重复发放的数据
	 * @param serialNumber
	 * @return
	 */
	List<AcBalanceRecord> checkSameSerialNumber(@Param("serialNumber")String serialNumber);

	/**
	 * 月度收益明细列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<MonthProfixDetailListResp> queryMonthProfixDetailList(MonthProfixDetailRequest req,
			Page<MonthProfixDetailListResp> page);


	/**
	 * 月度收益总计列表
	 * @param req
	 * @param page
	 * @return
	 */
	List<MonthProfixTotalListResp> queryProfixTotalList(MonthProfixDetailRequest req,
			Page<MonthProfixTotalListResp> page);

	/**
     * 累计收益
     */
	Double queryTotalIncome(@Param("userId")String userId);
	
	
	
}
