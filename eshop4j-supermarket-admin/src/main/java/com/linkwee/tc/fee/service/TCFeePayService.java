package com.linkwee.tc.fee.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.mc.SysPushMessage;
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

	void payFee(List<TCFeePay> noPayFeeList,String month,Date time,String operator,Collection<SysMsg> sysMsgs,Collection<SysPushMessage> pushMsgs,Collection<SmMessageQueue> messages) throws Exception; 
}
