package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.linkwee.web.dao.InvestmentDistributionMapper;
import com.linkwee.web.request.tc.TcFeeDistributionRequest;
import com.linkwee.web.response.tc.InvestmentTopDetailResponse;
import com.linkwee.web.service.InvestmentDistributionService;


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
@Service("investmentDistributionService")
public class InvestmentDistributionServiceImpl implements InvestmentDistributionService{
	
	
	@Resource
	private InvestmentDistributionMapper investmentDistributionMapper;
	
	@Override
	public Map<String, Long> getFeeTotalData() {
		return investmentDistributionMapper.getFeeTotalData();
	}

	@Override
	public List<InvestmentTopDetailResponse> getTop(TcFeeDistributionRequest req) {
	
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		return investmentDistributionMapper.getTop(req.getOrgInfo(),start, end);
	}

	@Override
	public List<Map<String, Long>> getFeeDataDetail(TcFeeDistributionRequest req) {
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		return investmentDistributionMapper.getFeeDataDetail(req.getType(), req.getOrgInfo(), start, end);
	}

	@Override
	public Map<String, Float> getFeeDistributionRatio(TcFeeDistributionRequest req) {
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		Long count = investmentDistributionMapper.getFeeDistributionRatioCount(req.getOrgInfo(), start, end);
		Float total = count.floatValue();
		Map<String, Float> result = Maps.newTreeMap();
		List<Map<String, Object>> datas = investmentDistributionMapper.getFeeDistributionRatio(req.getOrgInfo(), start, end);
		
		for (Map<String, Object> map : datas) {
    		result.put(((BigDecimal)map.get("amt")).setScale(2,BigDecimal.ROUND_DOWN).toString(),new BigDecimal(((Long)map.get("count") )/total * 100f).setScale(2,BigDecimal.ROUND_DOWN).floatValue());
		}
		return result;
	}



	
	
}
