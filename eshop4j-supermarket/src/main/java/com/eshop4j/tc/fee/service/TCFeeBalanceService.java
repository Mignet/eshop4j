package com.eshop4j.tc.fee.service;

import java.util.List;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.request.tc.FeeDetailRequest;
import com.eshop4j.web.request.tc.FeeRequest;
import com.eshop4j.web.response.tc.FeebalanceListResponse;

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
