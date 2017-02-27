package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.web.response.tc.FeeTopDetailResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月22日 15:50:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeedetailMapper extends GenericDao<TCFeedetail,Long>{
	
	
	/**
	 * 批量插入
	 * @param feedetails
	 * @return
	 */
	int inserts(@Param("feedetails") List<TCFeedetail> feedetails);
	
	/**
	 * 批量更新佣金结算状态
	 * @param cfplannerIds 已经汇总的理财师编号
	 * @param balanceMaps 理财师编号与汇总编号映射map
	 * @param balanceStatus	结算状态
	 * @param begintime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	int batchUpdateBalanceStatus(@Param("cfplannerIds")List<String> cfplannerIds, @Param("balanceMaps")List<Map<String, String>> balanceMaps,@Param("balanceStatus")int balanceStatus,@Param("beginTime")String begintime,@Param("endTime")String endTime);
	
	
	Map<String, Long> getFeeTotalData();
	
	List<FeeTopDetailResponse> getTop(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	Long getFeeDistributionRatioCount(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	List<Map<String, Object>> getFeeDistributionRatio(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	
	List<Map<String, Long>> getFeeDataDetail(@Param("type") int type ,@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	
	
}
