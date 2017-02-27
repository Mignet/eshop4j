package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.CrmCfpLoginLog;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 18:57:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLoginLogMapper extends GenericDao<CrmCfpLoginLog,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpLoginLog> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
