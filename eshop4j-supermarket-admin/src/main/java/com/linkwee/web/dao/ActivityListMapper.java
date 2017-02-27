package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityListRequest;

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
	 * 后台活动列表查询
	 * @param request
	 * @param page
	 * @return
	 */
	List<ActivityList> findActivityList(ActivityListRequest request,Page<ActivityListRequest> page);

	void updateWithoutPrimaryKey(ActivityList bo);
	
}
