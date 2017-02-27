package com.linkwee.tc.fee.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.service.TCFeePayService;
import com.linkwee.web.dao.TCFeePayMapper;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.service.AcBalanceRecordService;


 /**
 * 
 * @描述：CimFeePayService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeePayService")
public class TCFeePayServiceImpl extends GenericServiceImpl<TCFeePay, Long> implements TCFeePayService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeePayServiceImpl.class);
	
	@Autowired
	private TCFeePayMapper feePayMapper;
	
	@Autowired
	private AcBalanceRecordService  balanceRecordService;
	
	@Override
    public GenericDao<TCFeePay, Long> getDao() {
        return feePayMapper;
    }
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void payFee(List<TCFeePay> noPayFeeList, String month, Date time,String operator) throws Exception {
		List<String> noPayFeeIds = Lists.newArrayListWithCapacity(noPayFeeList.size());
		try{
			List<AcBalanceRecord> balanceRecords = Lists.newArrayListWithCapacity(noPayFeeList.size());
			AcBalanceRecord balanceRecord;
			for (TCFeePay noPayFee : noPayFeeList) {
				noPayFeeIds.add(noPayFee.getBillId());
				balanceRecord = new AcBalanceRecord();
				balanceRecord.setOrderId(com.linkwee.core.util.StringUtils.getUUID());
				balanceRecord.setUserType(1);
				balanceRecord.setUserId(noPayFee.getUserId());
				balanceRecord.setUserName(noPayFee.getUserName());
				balanceRecord.setMobile(noPayFee.getUserMobile());
				balanceRecord.setTransType(12);
				balanceRecord.setTypeName("佣金发放");
				balanceRecord.setRemark(month +" 佣金发放");
				balanceRecord.setTransAmount(noPayFee.getFeeAmount());
				balanceRecord.setTransDate(time);
				balanceRecord.setSerialNumber(noPayFee.getBillId());
				balanceRecord.setCreatePerson(operator);
				balanceRecords.add(balanceRecord);
			}
			feePayMapper.updateStatus(noPayFeeIds, 1, "processing", "处理中");
			balanceRecordService.grantProfit(balanceRecords);
			feePayMapper.updateStatus(noPayFeeIds, 2,"success","成功");
		}catch(Exception e){
			feePayMapper.updateStatus(noPayFeeIds, 3,"failure","失败");
			throw e;
		}
	}
    


}
