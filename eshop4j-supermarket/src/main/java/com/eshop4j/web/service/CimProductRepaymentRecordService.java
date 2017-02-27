package com.eshop4j.web.service;

import com.eshop4j.core.generic.GenericService;
import com.eshop4j.openapi.request.RepaymentRecordReq;
import com.eshop4j.web.model.cim.CimProductRepaymentRecord;
 /**
 * 
 * @描述： CimProductRepaymentRecordService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月25日 17:15:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRepaymentRecordService extends GenericService<CimProductRepaymentRecord,Long>{

	void insertRepaymentRecord(RepaymentRecordReq recordReq) throws Exception;
}
