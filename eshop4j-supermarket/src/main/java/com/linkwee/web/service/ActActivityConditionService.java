package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActActivityCondition;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActActivityConditionService;
 /**
 * 
 * @描述： ActActivityConditionService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityConditionService extends GenericService<ActActivityCondition,Long>{

	/**
	 * 查询ActActivityCondition列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询活动剩余次数的条件sql
	 * @param activity
	 * @return
	 */
	String queryLeftTimeConditionSQL(ActivityList activity);

	/**
	 * 用户满足本次活动的等级列表(等级)
	 * @param userId
	 * @param activity
	 * @return
	 */
	List<Integer> queryConditionCase(String userId, ActivityList activity);

	/**
	 * 用户满足本次活动的等级列表(等级对象)
	 * @param userId
	 * @param activity
	 * @return
	 */
	List<ActActivityCondition> queryConditionTypes(String userId,ActivityList activity);

	/**
	 * 未知中奖奖项的sql
	 * @param activity
	 * @return
	 */
	String queryUnknownPrizeCaseConditionSQL(ActivityList activity);
}
