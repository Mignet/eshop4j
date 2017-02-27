package com.linkwee.web.service;


import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityListRequest;
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

	public DataTableReturn findActivityList(ActivityListRequest request,DataTable dataTable);

	public void updateWithoutPrimaryKey(ActivityList bo);
	
}
