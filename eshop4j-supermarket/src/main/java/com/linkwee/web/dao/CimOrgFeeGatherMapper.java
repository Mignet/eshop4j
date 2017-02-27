package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.cim.CimOrgFeeGather;
import com.linkwee.web.model.vo.OrgSaleFeeData;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 18:27:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeGatherMapper extends GenericDao<CimOrgFeeGather,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgFeeGather> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	List<CimOrgFeeGather> queryRedemptionDetialByPate(Page<CimOrgFeeGather> page,Map<String,Object> map);
	
	/**
	 * 查询后台管理 -产品投资统计-总体数据
	 * @param page
	 * @param params
	 * @return
	 */
	List<OrgSaleFeeData> queryOrgSaleFee(Page<OrgSaleFeeData> page,Map<String,Object> params);
	/**
	 * 条件查询不分页
	 * @param params
	 * @return
	 */
	List<OrgSaleFeeData> queryOrgSaleFee(Map<String,Object> params);
	
	Double queryInvestAmount(Map<String,Object> params);
}
