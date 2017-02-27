package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.CrmCfpNewcomerTask;
 /**
 * 
 * @描述： CrmCfpNewcomerTaskService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpNewcomerTaskService extends GenericService<CrmCfpNewcomerTask,Long>{

	/**
	 * 查询CrmCfpNewcomerTask列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 领取奖励
	 * @param userId
	 * @param taskType
	 */
	void receiveTaskReward(String userId, String taskType) throws Exception;

	/**
	 * 未完成新手任务数量
	 * @param userId
	 * @return
	 */
	int queryUnFinishNewcomerTaskCount(String userId);
	
	
	
}
