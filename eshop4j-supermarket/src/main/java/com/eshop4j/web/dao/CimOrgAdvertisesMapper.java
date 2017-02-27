package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.CimOrgAdvertises;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 18:23:35
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgAdvertisesMapper extends GenericDao<CimOrgAdvertises,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	public List<CimOrgAdvertises> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 查询机构所有活动图片
	 * @author yalin 
	 * @date 2016年10月26日 下午6:36:38  
	 * @param orgNumber 机构编码
	 * @return
	 */
	public List<CimOrgAdvertises> queryOrgAdvertisesList(String orgNumber);
}
