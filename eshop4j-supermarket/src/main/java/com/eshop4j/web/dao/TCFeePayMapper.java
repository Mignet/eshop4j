package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.tc.fee.model.TCFeePay;
import com.eshop4j.tc.fee.model.TCFeebalance;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeePayMapper extends GenericDao<TCFeePay,Long>{
	
	int inserts(@Param("feebalances")List<TCFeebalance> feebalances);
	
	int getNoPayFeeCount(String month);
	
	List<TCFeePay> getNoPayFeeList(String month,RowBounds page);
	
	int updateStatus(@Param("ids")List<String> ids ,@Param("status")int status,@Param("msgCode")String msgCode,@Param("msg")String msg);
	
}
