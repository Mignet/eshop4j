package com.linkwee.tc.fee.service;

import java.util.List;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.tc.FeeDetailRequest;
import com.linkwee.web.request.tc.FeeRequest;
import com.linkwee.web.response.tc.FeebalanceListResponse;

public interface TCFeeBalanceService {
	
	/**
	 * 佣金汇总处理
	 */
	void feeBalanceProcess();
	
	
	DataTableReturn getFeebalanceList(FeeRequest feeRequest);
	
	DataTableReturn getFeebalanceRecordByMobile(FeeDetailRequest feeRequest);
	
	DataTableReturn getFeeDetailRecord(FeeDetailRequest feeRequest);
	
	List<FeebalanceListResponse> getFeebalanceListByMonth();
	
	void feePay(String operator) throws Exception;
}
