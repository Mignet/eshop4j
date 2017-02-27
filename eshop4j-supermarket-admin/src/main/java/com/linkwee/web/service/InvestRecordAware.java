package com.linkwee.web.service;

import com.linkwee.web.model.vo.InvestRecordWrapper;


public interface InvestRecordAware{
	
	/**
	 * 投资记录处理
	 * @param investRecord
	 * @throws Exception
	 */
	void investRecordProcess(InvestRecordWrapper investRecord)throws Exception;
}
