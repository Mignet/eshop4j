package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CustomerInvestRequest;

public interface TcInvestService {

	
	/**
	 * 统计用户在机构的投资信息
	 * @param req
	 * @return
	 */
	public DataTableReturn queryCustomerInvestStatistics(CustomerInvestRequest req);
	
	/**
	 * 查询用户在机构投资详情
	 * @param req
	 * @return
	 */
	public DataTableReturn queryCustomerInvestDetail(CustomerInvestRequest req);
	
	
	BigDecimal getInvestmentStatisticsTotal(String platfrom,String startTime,String endTime);
	
	
	List<Map<String, BigDecimal>> getInvestmentStatisticsList(String platfrom,String startTime,String endTime);
	
	BigDecimal getInvestStatisticsByPlatfromTotal(String platfrom,String startTime,String endTime);
	
	List<Map<String, BigDecimal>> getInvestStatisticsByPlatfrom(String platfrom,String startTime,String endTime);


}
