package com.eshop4j.web.service;


import java.util.List;
import java.util.Map;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.news.Activity;
/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午6:15:07 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public interface ActivityListService extends GenericService<ActivityList,Long>{

	
	
	/**
	 * 查询所有精彩活动
	 * @param req
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions);
	
	/**
	 * 查询ActivityList列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable, String actitityName);

	/**
	 * 活动平台（有封面的平台）
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivitiesByPlatform(Page<ActivityList> page, Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(分页)
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<ActivityList> queryActivitiesListByPlatform(Page<ActivityList> page, Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(不分页)
	 * @param conditions
	 * @return
	 */
	public List<ActivityList> queryPlatformActivities(Map<String, Object> conditions);

	/**
	 * 最新开始的活动
	 * @return
	 */
	public ActivityList queryNewest(Integer appType);

	/**
	 * 根据活动名称（模糊检索）和活动平台查活动
	 * @param activityName
	 * @param activityPlatform
	 * @return
	 */
	public List<ActivityList> queryActivity(String activityName,String activityPlatform);

}
