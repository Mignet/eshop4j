package com.eshop4j.tc.fee.service;

import java.util.List;
import java.util.Map;

import com.eshop4j.core.generic.GenericService;
import com.eshop4j.tc.fee.model.TCFeedetail;
import com.eshop4j.tc.fee.model.vo.FeedetailWrapper;

/**
 * 佣金明细服务
 * @author ch
 * @serial 2016-07-22 15:09:52
 *
 */
public interface TCFeeDetailService extends GenericService<TCFeedetail,Long>{
	
	/**
	 * 插入佣金明细
	 * @param feedetailWrapper
	 */
	void insertFeedetail(FeedetailWrapper... feedetailWrapper);
	
	int batchUpdateBalanceStatus(List<String> cfplannerIds,List<Map<String, String>> balanceMaps,int balanceStatus,String begintime,String endTime);
}
