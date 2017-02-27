package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.acc.AcAccountErrorLog;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月30日 18:03:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcAccountErrorLogMapper extends GenericDao<AcAccountErrorLog,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcAccountErrorLog> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
