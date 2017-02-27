package com.eshop4j.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.eshop4j.web.request.tc.TcFeeDistributionRequest;
import com.eshop4j.web.response.tc.FeeTopDetailResponse;

public interface TcFeeDistributionService {
	
	
	Map<String, Long> getFeeTotalData();
	
	List<FeeTopDetailResponse> getTop(TcFeeDistributionRequest req);
	
	List<Map<String, Long>> getFeeDataDetail(TcFeeDistributionRequest req);
	
	Map<String, Float> getFeeDistributionRatio(TcFeeDistributionRequest req);

}
