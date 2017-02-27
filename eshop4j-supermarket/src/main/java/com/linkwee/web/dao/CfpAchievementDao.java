package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.CfpAchiAnalysisResp;
import com.linkwee.web.model.crm.CfpAchievementResp;
import com.linkwee.web.request.ListDetailRequest;

/**
 * 
 * @描述： 理财师业绩
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface CfpAchievementDao  {

	/**
	 * 理财师业绩
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public List<CfpAchievementResp> selectCfpAchievement(@Param("query")ListDetailRequest req, RowBounds page);
	
	public List<CfpAchievementResp> selectCfpAchievement(@Param("query")ListDetailRequest req);

	/**
	 * 理财师分布城市数据
	 * @return
	 */
	public List<Map<String, Object>> queryCfpAreaData();

	/**
	 * 理财师客户数量数据
	 * @return
	 */
	public List<Map<String, Object>> queryCfpCustomerCountData();

	/**
	 * 理财师年化业绩数据
	 * @return
	 */
	public List<Map<String, Object>> queryCfpYearAchiData(Map<String, Object> paramsMap);
	
	/**
	 * 理财师业绩 
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public List<CfpAchiAnalysisResp> queryCfpOfCustomerCount(@Param("query")ListDetailRequest req, RowBounds page);

	/**
	 * 根据范围分类的理财师业绩列表
	 * @param req
	 * @param page
	 * @return
	 */
	public List<CfpAchiAnalysisResp> queryCfpAchiValueList(@Param("query")ListDetailRequest req, RowBounds page);
    
}
