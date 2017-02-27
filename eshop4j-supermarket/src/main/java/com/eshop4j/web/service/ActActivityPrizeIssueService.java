package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.ActActivityPrizeIssue;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.service.ActActivityPrizeIssueService;
 /**
 * 
 * @描述： ActActivityPrizeIssueService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月07日 21:30:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityPrizeIssueService extends GenericService<ActActivityPrizeIssue,Long>{

	/**
	 * 查询ActActivityPrizeIssue列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 发放奖励
	 * @param actActivityWinningRecord
	 * @param userId
	 */
	void prizeIssue(ActActivityWinningRecord actActivityWinningRecord,String userId);
}
