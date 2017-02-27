package com.linkwee.web.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.tc.fee.model.TCFee;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月11日 15:59:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeeMapper extends GenericDao<TCFee,Long>{
	
	/**
	 * 是否存在佣金记录
	 * @param billId
	 * @param CfplannerId
	 * @param feeType
	 * @return
	 */														
	boolean isExitFee(@Param("billId")String billId,@Param("cfplannerId")String cfplannerId,@Param("feeType")String feeType);
	
	/**
	 * 更新佣金
	 * @param fee
	 * @return
	 */
	int updateFee(TCFee fee);

	/**
	 * 查佣金收益
	 * @param userId
	 * @return
	 */
	Double queryFeeByUserId(@Param("userId")String userId, @Param("month")String month);

	/**
	 * 查推荐津贴收益
	 * @param userId
	 * @return
	 */
	Double queryAllowanceByUserId(@Param("userId")String userId, @Param("month")String month);

	/**
	 * 查团队leader奖励收益
	 * @param userId
	 * @return
	 */
	Double queryLeaderRewardByUserId(@Param("userId")String userId, @Param("month")String month);
}
