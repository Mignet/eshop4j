package com.linkwee.tc.fee.service;

import com.linkwee.core.base.ResponseResult;
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
	/**
	 * 佣金结算
	 * @return
	 * @throws Exception
	 */
	ResponseResult feeBalanceProcess()throws Exception;

	void saveFees(FeedetailWrapper... feedetailWrappers);
}
