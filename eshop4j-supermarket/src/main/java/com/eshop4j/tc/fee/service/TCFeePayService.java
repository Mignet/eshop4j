package com.eshop4j.tc.fee.service;

import java.util.Date;
import java.util.List;

import com.eshop4j.core.generic.GenericService;
import com.eshop4j.tc.fee.model.TCFeePay;
 /**
 * 
 * @描述： CimFeePayService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeePayService extends GenericService<TCFeePay,Long>{

	void payFee(List<TCFeePay> noPayFeeList,String month,Date time,String operator) throws Exception; 
}
