package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.response.acc.ProvinceInfoResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcProvinceList;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月26日 17:59:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcProvinceListMapper extends GenericDao<AcProvinceList,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcProvinceList> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
     * 查询所有的省
     * @return
     */
	List<ProvinceInfoResponse> queryAllProvince();
}
