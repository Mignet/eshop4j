package com.eshop4j.web.service;

import java.util.List;
import java.util.Map;

import com.eshop4j.web.request.tc.TcFeeDistributionRequest;
import com.eshop4j.web.response.tc.InvestmentTopDetailResponse;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface InvestmentDistributionService {

	
	Map<String, Long> getFeeTotalData();
	
	List<InvestmentTopDetailResponse> getTop(TcFeeDistributionRequest req);
	
	List<Map<String, Long>> getFeeDataDetail(TcFeeDistributionRequest req);
	
	Map<String, Float> getFeeDistributionRatio(TcFeeDistributionRequest req);
	
	
}
