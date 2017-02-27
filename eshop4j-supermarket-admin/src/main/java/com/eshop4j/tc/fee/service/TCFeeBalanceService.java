package com.eshop4j.tc.fee.service;

import java.util.Date;
import java.util.List;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.tc.fee.model.TCFeebalance;
import com.eshop4j.web.request.tc.FeeDetailRequest;
import com.eshop4j.web.request.tc.FeeRequest;
import com.eshop4j.web.response.tc.FeebalanceListResponse;

public interface TCFeeBalanceService {
	
	
	void insertFeebalances(String  month, Date time,String begintime,String endtime,Page<TCFeebalance> page)throws Exception;
	
	DataTableReturn getFeebalanceList(FeeRequest feeRequest);
	
	DataTableReturn getFeebalanceRecordByMobile(FeeDetailRequest feeRequest);
	
	DataTableReturn getFeeDetailRecord(FeeDetailRequest feeRequest);
	
	List<FeebalanceListResponse> getFeebalanceListByMonth();
	
	void feePay(String operator) throws Exception;
	
	
	
	int updateStatusAndGetCalculateCount(String begintime,String endTime,List<Integer> beforeStatus,Integer afterStatus);
}
