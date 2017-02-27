package com.eshop4j.web.service;

import java.util.Map;

import com.eshop4j.core.base.ErrorCode;
import com.eshop4j.core.base.PaginatorSevReq;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.InvestedUserDataStatisticResp;
import com.eshop4j.web.model.crm.InvestorManagerDetailResp;
import com.eshop4j.web.model.crm.UserDataStatisticResp;
import com.eshop4j.web.request.DataStatisticsRequest;
import com.eshop4j.web.request.ListDetailRequest;

/**
 * 
 * @描述：数据统计
 *
 * @author hxb
 * @时间 2016年4月8日下午5:43:30
 *
 */
public interface DataStatisticsService {

	Map<String, Object> queryInvestorLcsAndInvestment(DataStatisticsRequest dataStatisticsRequest);
	
}
