package com.linkwee.tc.fee.service;

import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.TCFee;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
 /**
 * 
 * @描述： CimFeeService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月11日 15:59:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeeService extends GenericService<TCFee,Long>{

	void saveFees(FeedetailWrapper... feedetailWrappers);

	/**
	 * 查用户佣金收益
	 * @param userId
	 * @return
	 */
	Double queryFeeByUserId(String userId, String month);

	/**
	 * 查用户推荐津贴收益
	 * @param userId
	 * @return
	 */
	Double queryAllowanceByUserId(String userId, String month);

	/**
	 * 查用户团队leader收益
	 * @param userId
	 * @return
	 */
	Double queryLeaderRewardByUserId(String userId, String month);
	
	
	String productProfitCalculate(ProductDetailResponse productDetail);
	
}
