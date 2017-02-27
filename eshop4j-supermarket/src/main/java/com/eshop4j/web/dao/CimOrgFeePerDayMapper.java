package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.cim.CimOrgFeePerDay;
import com.eshop4j.web.model.cim.CimOrgFeeGather;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 18:26:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeePerDayMapper extends GenericDao<CimOrgFeePerDay,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgFeePerDay> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	Integer addBatch(List<CimOrgFeePerDay> list);
	Integer updateBatch(List<CimOrgFeePerDay> list);
	List<CimOrgFeePerDay> queryByFeeDetialIdAndDate(List<CimOrgFeeGather> feeDetail,String feeGernerateDate);
	CimOrgFeePerDay queryByInvestIdAndFeeDate(@Param("investId")String investId,@Param("feeDate")String feeDate);
}
