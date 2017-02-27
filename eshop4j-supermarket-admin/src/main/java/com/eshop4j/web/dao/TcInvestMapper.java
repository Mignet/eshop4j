package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.web.model.CustomerInvestDetail;
import com.eshop4j.web.model.CustomerInvestStatistics;
import com.eshop4j.web.response.tc.InvestorRepaymentResponse;

public interface TcInvestMapper {
	
	/**
	 * 统计用户在机构的投资信息
	 * @param platfrom 机构编码
	 * @param page
	 * @return
	 */
	public List<CustomerInvestStatistics> queryCustomerInvestStatistics(@Param("platfrom")String platfrom,@Param("nameOrMobile")String nameOrMobile,RowBounds page);
	
	/**
	 * 查询用户在机构投资详情
	 * @param platfrom 机构编码
	 * @param userId 用户编号
	 * @param page
	 * @return
	 */
	public List<CustomerInvestDetail> queryCustomerInvestDetail(@Param("platfrom")String platfrom,@Param("userId")String userId,RowBounds page);
	
	
	BigDecimal getInvestmentStatisticsTotal(@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	
	List<Map<String, BigDecimal>> getInvestmentStatisticsList(@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	
	BigDecimal getInvestStatisticsByPlatfromTotal(@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	
	List<Map<String, BigDecimal>> getInvestStatisticsByPlatfrom(@Param("platfrom")String platfrom,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	List<InvestorRepaymentResponse> customerImpendRepayment(@Param("type")int type,@Param("mobileOrName")String mobileOrName,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("isExport")int isExport,RowBounds page);
	
	List<InvestorRepaymentResponse> getCustomerImpendRepaymentList(@Param("type")int type,@Param("mobileOrName")String mobileOrName,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("isExport")int isExport);

}
