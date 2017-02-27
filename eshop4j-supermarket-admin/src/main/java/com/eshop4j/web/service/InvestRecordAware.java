package com.eshop4j.web.service;

import com.eshop4j.web.model.vo.InvestRecordWrapper;


public interface InvestRecordAware{
	
	/**
	 * 投资记录处理
	 * @param investRecord
	 * @throws Exception
	 */
	void investRecordProcess(InvestRecordWrapper investRecord)throws Exception;
}
