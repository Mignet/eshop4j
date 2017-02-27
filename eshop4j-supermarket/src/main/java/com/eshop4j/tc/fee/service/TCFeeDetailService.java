package com.eshop4j.tc.fee.service;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.api.request.tc.CfplannerProfitRequest;
import com.eshop4j.api.response.tc.CfplannerProfitTotalResponse;
import com.eshop4j.api.response.tc.ProfitItemsResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.tc.fee.model.TCFeedetail;
import com.eshop4j.tc.fee.model.vo.FeedetailWrapper;
import com.eshop4j.web.model.crm.PersonCenterResp;

/**
 * 佣金明细服务
 * @author ch
 * @serial 2016-07-22 15:09:52
 *
 */
public interface TCFeeDetailService extends GenericService<TCFeedetail,Long>{
	
	/**
	 * 插入佣金明细
	 * @param feedetailWrapper
	 */
	void insertFeedetail(FeedetailWrapper... feedetailWrapper);
	
	
	/**
	 * 获取理财师本月总佣金收益
	 * @param userId 理财师编号
	 * @return
	 */
	PersonCenterResp queryCfplannerMonthProfitTotal(String userId);
	
	/**
	 * 全部佣金收益
	 * @param userId 理财师编号
	 * @param dateType 时间类别: 1:年；2:季度；3:月；4:日
	 * @param date 时间格式:2016-07-24
	 * @param profitType 收益类别
	 * @return
	 */
	CfplannerProfitTotalResponse queryCfplannerProfitTotal(String userId, Integer dateType,Date date);
	
	/**
	 * 查询理财师单个佣金收益总额
	 * @param userId
	 * @param dateType
	 * @param date
	 * @param profitType
	 * @return
	 */
	Double queryCfplannerProfitItemTotal(@Param("userId")String userId,@Param("dateType") Integer dateType,@Param("date")Date date,@Param("profitType")Integer profitType);
	
	/**
	 * 查询理财师单个类型佣金收益明细
	 * @param profitRequest
	 * @return
	 */
	PaginatorResponse<ProfitItemsResponse> queryCfplannerProfitItem(String userId,CfplannerProfitRequest profitRequest);

	/**
	 * 查一条佣金明细
	 * @param userId
	 * @param month
	 * @return
	 */
	TCFeedetail queryFeedetailByUserIdAndMonthLimitOne(String userId, String month);


	/**
	 * 佣金是否发放
	 * @param month
	 * @return
	 */
	boolean isGrantFeeByMonth(String month);

	
}
