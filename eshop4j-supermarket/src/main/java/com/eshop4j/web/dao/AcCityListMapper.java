package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.response.acc.CityInfoResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcCityList;

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
public interface AcCityListMapper extends GenericDao<AcCityList,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcCityList> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
     * 查询城市
     */
	List<CityInfoResponse> selectByProvinceCode(@Param("provinceId")String provinceId);
}
