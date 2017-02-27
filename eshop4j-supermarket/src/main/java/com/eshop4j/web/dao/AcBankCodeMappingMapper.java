package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.response.acc.AcBankCodeResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.acc.AcBankCode;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月27日 10:35:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcBankCodeMappingMapper extends GenericDao<AcBankCode,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcBankCode> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询银行
	 * */
	List<AcBankCodeResponse> queryAllBank();
	
	/**
	 * 根据ID查询银行
	 * */
	AcBankCodeResponse queryBankById(@Param("bankId") int bankId);
	
	/**
	 * 根据银行名称查询银行
	 * */
	AcBankCodeResponse queryBankByName(@Param("bankName") String bankName);
}
