package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgRisk;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月22日 11:03:54
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRiskMapper extends GenericDao<CimOrgRisk,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgRisk> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 查询机构风控信息
	 * @author yalin 
	 * @date 2016年11月22日 上午11:12:07  
	 * @param orgNumber
	 * @return
	 */
	public List<CimOrgRisk> queryOrgRiskInfoByOrgNumber(String orgNumber);
}
