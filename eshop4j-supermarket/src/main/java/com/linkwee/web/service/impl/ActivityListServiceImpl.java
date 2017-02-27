package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActivityListMapper;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActivityListService;


/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午6:18:22 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
@Service("activityListService")
public class ActivityListServiceImpl extends GenericServiceImpl<ActivityList, Long> implements ActivityListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListServiceImpl.class);
	
	@Resource
	private ActivityListMapper activityListMapper;
	
	@Override
    public GenericDao<ActivityList, Long> getDao() {
        return activityListMapper;
    }
	
	 @Override
		public DataTableReturn selectByDatatables(DataTable dt, String actitityName) {
			DataTableReturn tableReturn = new DataTableReturn();
			tableReturn.setDraw(dt.getDraw()+1);
			LOGGER.debug(" -- ActivityList -- 排序和模糊查询 ");
			Page<ActivityList> page = new Page<ActivityList>(dt.getStart()/dt.getLength()+1,dt.getLength());
			List<ActivityList> list = this.activityListMapper.selectBySearchInfo(actitityName,page);
			tableReturn.setData(list);
			tableReturn.setRecordsFiltered(page.getTotalCount());
			tableReturn.setRecordsTotal(page.getTotalCount());
			return tableReturn;
		}

	
	@Override
	public PaginatorResponse<ActivityList> queryActivities(Page<ActivityList> page,Map<String,Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivities(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public PaginatorResponse<ActivityList> queryActivitiesByPlatform(
			Page<ActivityList> page, Map<String, Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivitiesByPlatform(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public PaginatorResponse<ActivityList> queryActivitiesListByPlatform(
			Page<ActivityList> page, Map<String, Object> conditions) {
		PaginatorResponse<ActivityList> activityListResponse = new PaginatorResponse<ActivityList>();
		List<ActivityList> activityList = activityListMapper.queryActivitiesListByPlatform(page,conditions);
		activityListResponse.setDatas(activityList);
		activityListResponse.setValuesByPage(page);
		return activityListResponse;
	}

	@Override
	public List<ActivityList> queryPlatformActivities(
			Map<String, Object> conditions) {
		List<ActivityList> activityList = activityListMapper.queryPlatformActivities(conditions);
		return activityList;
	}

	@Override
	public ActivityList queryNewest(Integer appType) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appType", appType);
		return activityListMapper.queryNewest(map);
	}

	@Override
	public List<ActivityList> queryActivity(String activityName,String activityPlatform) {
		// TODO Auto-generated method stub
		return activityListMapper.queryActivity(activityName,activityPlatform);
	}
}
