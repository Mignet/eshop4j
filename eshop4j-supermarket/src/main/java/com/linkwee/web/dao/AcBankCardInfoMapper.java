package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.acc.AcBankCardInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcBankCardInfoMapper extends GenericDao<AcBankCardInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcBankCardInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	 /**
     * 根据用户ID查询银行卡记录
     */
	AcBankCardInfo selectBankCardInfoByUserId(@Param("userId")String userId,@Param("bankCard")String bankCard);

	/**
     * 银行卡ID查询银行卡信息
     */
	AcBankCardInfo selectByBankCardId(@Param("bankCardId")String bankCardId);
	
	/**
     * 银行卡号查询银行卡信息
     */
	AcBankCardInfo selectByBankCard(@Param("bankCard")String bankCard);

	/**
     * 测试绑卡用
     */
	List<AcBankCardInfo> selectBankByBankName();
	
}
