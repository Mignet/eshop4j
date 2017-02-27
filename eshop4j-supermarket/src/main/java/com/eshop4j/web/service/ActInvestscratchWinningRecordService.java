package com.eshop4j.web.service;

import java.util.List;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.ActInvestscratchWinningRecord;
import com.eshop4j.web.service.ActInvestscratchWinningRecordService;
 /**
 * 
 * @描述： ActInvestscratchWinningRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月25日 17:04:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActInvestscratchWinningRecordService extends GenericService<ActInvestscratchWinningRecord,Long>{

	/**
	 * 查询ActInvestscratchWinningRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询最近中奖的50条记录
	 * @return
	 */
	List<ActInvestscratchWinningRecord> queryWinningRecord();

	/**
	 * 查询已使用的刮奖次数
	 * @param userId
	 * @return
	 */
	Integer queryScratchedTime(String userId);

	/**
	 * 查询总的刮奖次数
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
