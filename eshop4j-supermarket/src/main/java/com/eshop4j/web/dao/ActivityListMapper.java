package com.eshop4j.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.ActivityList;

/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午5:26:32 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public interface ActivityListMapper extends GenericDao<ActivityList,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActivityList> selectBySearchInfo(@Param("actitityName")String actitityName,RowBounds page);
	
	/**
	 * 查询精彩活动
	 * @param req
	 * @return
	 */
	public List<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions);

	/**
	 * 活动平台（有封面的平台）
	 * @param page
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryActivitiesByPlatform(Page<ActivityList> page,Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(分页)
	 * @param page
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryActivitiesListByPlatform(Page<ActivityList> page,Map<String, Object> conditions);

	/**
	 * 某个平台的活动列表(不分页)
	 * @param conditions
	 * @return
	 */
	List<ActivityList> queryPlatformActivities(Map<String, Object> conditions);

	/**
	 * 最新开始的平台活动
	 * @return
	 */
	ActivityList queryNewest(Map<String, Object> map);

	/**
	 * 根据活动名称（模糊检索）和活动平台查活动
	 * @param activityName
	 * @param activityPlatform
	 * @return
	 */
	List<ActivityList> queryActivity(@Param("activityName")String activityName,@Param("activityPlatform")String activityPlatform);
	
}
