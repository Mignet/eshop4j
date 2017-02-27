package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgRef;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月17日 15:26:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgRefMapper extends GenericDao<CimOrgRef,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgRef> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 批量删除对象
	 * @param cimOrgRefNew
	 * @return
	 */
	int deleteByCondition(CimOrgRef cimOrgRefNew);
}
