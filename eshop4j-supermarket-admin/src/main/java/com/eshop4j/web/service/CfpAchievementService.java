package com.eshop4j.web.service;

import java.util.List;
import java.util.Map;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.model.crm.CfpAchievementResp;
import com.eshop4j.web.model.crm.investmentRateResp;
import com.eshop4j.web.request.ListDetailRequest;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface CfpAchievementService {


	/**
	 * 理财师业绩
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public DataTableReturn selectCfpAchievement(DataTable dataTable, ListDetailRequest req);

	/**
	 * 理财师业绩
	 * @param req
	 * @return
	 */
	public List<CfpAchievementResp> selectCfpAchievement(ListDetailRequest req);

	/**
	 * 理财师分布城市数据
	 * @return
	 */
	public List<Map<String, Object>> queryCfpAreaData();

	/**
     * 理财师客户数量数据
     */
	public List<Map<String, Object>> queryCfpCustomerCountData();

	/**
	 * 理财师年化业绩数据
	 * @return
	 */
	public List<Map<String, Object>> queryCfpYearAchiData(Map<String, Object> paramsMap);
	
	/**
	 * 理财师拥有客户数量范围列表
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public DataTableReturn queryCfpOfCustomerCount(DataTable dataTable, ListDetailRequest req);

	/**
	 * 根据范围分类的理财师业绩列表
	 * @param dataTable
	 * @param req
	 * @return
	 */
	public DataTableReturn queryCfpAchiValueList(DataTable dataTable, ListDetailRequest req);

	public investmentRateResp queryInvestmentRate(Map<String, String> query);
}
