package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.linkwee.web.request.tc.TcFeeDistributionRequest;
import com.linkwee.web.response.tc.FeeTopDetailResponse;

public interface TcFeeDistributionService {
	
	
	Map<String, Long> getFeeTotalData();
	
	List<FeeTopDetailResponse> getTop(TcFeeDistributionRequest req);
	
	List<Map<String, Long>> getFeeDataDetail(TcFeeDistributionRequest req);
	
	Map<String, Float> getFeeDistributionRatio(TcFeeDistributionRequest req);

}
