package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.acc.AcBankCardInfo;
import com.linkwee.web.request.acc.AddBankCardRequest;
 /**
 * 
 * @描述： AcBankCardInfoService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcBankCardInfoService extends GenericService<AcBankCardInfo,Long>{

	/**
	 * 查询AcBankCardInfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 添加银行卡信息
	 * @param req
	 * @return
	 */
	void insertBankCard(AddBankCardRequest req);

    
	/**
	 * 实名验证(调用华付接口)
	 * @param req
	 * @return String
	 */
	Map<String, String> verifyRealName(AddBankCardRequest req) throws Exception ;

	/**
	 * 获取银行卡信息
	 * @param bankCardId
	 * @return AcBankCardInfo
	 */
	AcBankCardInfo selectByBankCardId(String bankCardId);
	
	/**
	 * 获取银行卡信息
	 * @param bankCardId
	 * @return AcBankCardInfo
	 */
	AcBankCardInfo selectByBankCard(String bankCard);

	/**
	 * 先检查银行卡(排除信用卡)
	 * @param bankCardId
	 * @return AcBankCardInfo
	 */
	Map<String,String> checkBankCardAndBankName(AddBankCardRequest req) throws Exception;

	/**
	 * 查询银行
	 * @param bankCard
	 * @return String
	 */
	Map<String, String> queryBankName(String bankCard) throws Exception;
}
