package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcHolidayData;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月08日 19:56:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcHolidayDataMapper extends GenericDao<AcHolidayData,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcHolidayData> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
