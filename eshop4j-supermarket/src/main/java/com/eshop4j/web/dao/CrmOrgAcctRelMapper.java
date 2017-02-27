package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.CrmOrgAcctRel;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年10月12日 14:52:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmOrgAcctRelMapper extends GenericDao<CrmOrgAcctRel,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmOrgAcctRel> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
