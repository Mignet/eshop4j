package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.cim.CimProductUnrecordInvest;
import com.eshop4j.web.response.tc.UnrecordInvestListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestMapper extends GenericDao<CimProductUnrecordInvest,Long>{
	
	List<UnrecordInvestListResponse> getUnrecordInvestList(@Param("mobile")String mobile,@Param("investorsMobiel")String investorsMobiel,@Param("status")Integer status,RowBounds page);
	
}
