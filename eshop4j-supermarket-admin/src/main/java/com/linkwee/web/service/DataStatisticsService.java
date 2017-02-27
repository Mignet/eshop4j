package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.InvestedUserDataStatisticResp;
import com.linkwee.web.model.crm.InvestorManagerDetailResp;
import com.linkwee.web.model.crm.UserDataStatisticResp;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.request.ListDetailRequest;

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
