package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.tc.fee.model.TCFee;
import com.eshop4j.tc.fee.model.TeamLeaderYearpurAmt;
import com.eshop4j.web.model.crm.CrmCfgLevel;

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
	 * 获取佣金
	 * @param billId
	 * @param feeType
	 * @return
	 */
	BigDecimal getFeeAmt(@Param("billId")String billId,@Param("feeType")String feeType);
	
	
	List<TeamLeaderYearpurAmt> getCfpYearpurAmt(@Param("level") CrmCfgLevel cfgLevel,@Param("begintime")String begintime,@Param("endtime")String endtime);
	
	List<TeamLeaderYearpurAmt> getSubYearpurAmt(@Param("cfplannerIds") Set<String> cfplannerIds,@Param("begintime")String begintime,@Param("endtime")String endtime);
	
	
	int teamLeaderCount(@Param("time") String time);
}
