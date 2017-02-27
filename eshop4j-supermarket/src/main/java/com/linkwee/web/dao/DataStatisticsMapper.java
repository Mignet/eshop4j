package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import com.linkwee.web.request.DataStatisticsRequest;

/**
 * 
 * @描述： 数据统计
 * 
 * @创建人：hxb
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface DataStatisticsMapper  {

	Map<String, Object> queryInvestorLcsAndInvestment(DataStatisticsRequest dataStatisticsRequest);

	List<Map<String,Object>> queryNewInvestorTable(DataStatisticsRequest dataStatisticsRequest);

	List<Map<String,Object>> queryNewCfpTable(DataStatisticsRequest dataStatisticsRequest);

	List<Map<String,Object>> queryCurrentInvestment(DataStatisticsRequest dataStatisticsRequest);
	 
}
