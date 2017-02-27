package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimProductRef;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 18:21:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRefMapper extends GenericDao<CimProductRef,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimProductRef> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 批量删除对象
	 * @param cimProductRef
	 * @return
	 */
	int deleteByCondition(CimProductRef cimProductRef);
}
