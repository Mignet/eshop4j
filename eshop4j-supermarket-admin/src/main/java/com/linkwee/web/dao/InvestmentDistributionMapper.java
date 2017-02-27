package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.web.response.tc.InvestmentTopDetailResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface InvestmentDistributionMapper  extends BaseDao {

	Map<String, Long> getFeeTotalData();
	
	List<InvestmentTopDetailResponse> getTop(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	Long getFeeDistributionRatioCount(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	List<Map<String, Object>> getFeeDistributionRatio(@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
	
	List<Map<String, Long>> getFeeDataDetail(@Param("type") int type ,@Param("org") String org,@Param("start")String start,@Param("end")String end);
	
}
