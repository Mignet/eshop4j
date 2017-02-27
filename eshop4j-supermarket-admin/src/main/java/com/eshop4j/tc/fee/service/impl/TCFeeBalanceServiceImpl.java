package com.eshop4j.tc.fee.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.core.constant.SysConfigConstant;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.exception.ServiceException;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.tc.fee.common.config.FeeConfig;
import com.eshop4j.tc.fee.model.TCFeePay;
import com.eshop4j.tc.fee.model.TCFeebalance;
import com.eshop4j.tc.fee.service.TCFeeBalanceService;
import com.eshop4j.tc.fee.service.TCFeeDetailService;
import com.eshop4j.tc.fee.service.TCFeePayService;
import com.eshop4j.web.dao.TCFeePayMapper;
import com.eshop4j.web.dao.TCFeebalanceMapper;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.SmMessageQueue;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.request.tc.FeeDetailRequest;
import com.eshop4j.web.request.tc.FeeRequest;
import com.eshop4j.web.response.tc.FeeDetailRecordResponse;
import com.eshop4j.web.response.tc.FeebalanceListResponse;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;

@Service("tcFeeBalanceService")
public class TCFeeBalanceServiceImpl implements TCFeeBalanceService{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeeBalanceServiceImpl.class);
	
	
	@Autowired
	private TCFeeDetailService feeDetailService;
	
	@Autowired
	private TCFeebalanceMapper feebalanceMapper;
	
	@Autowired
	private TCFeePayMapper feePayMapper;
	
	
	
	@Autowired
	private TCFeePayService feePayService;
	
	@Autowired
	private FeeConfig feeConfig;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private PushMessageHelper pushMessageHelper;
	

	@Autowired
	private SysConfigService sysConfigService;
	
	
	
	
	protected  String getMonth() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0) {
			//1月份处理去年12月份
			year = year - 1;
			month = 12;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("-");
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void insertFeebalances(String month, Date time, String beginTime,String endTime, Page<TCFeebalance> page) throws Exception{
		List<TCFeebalance> feebalances = feebalanceMapper.getFeebalanceByMonth(beginTime, endTime, page);
		List<Map<String, String>> balanceMaps= Lists.newArrayListWithCapacity(feebalances.size());
		List<String> cfplannerIds= Lists.newArrayList();
		try{
			if(feebalances==null || feebalances.isEmpty())return ;
			for (TCFeebalance feebalance : feebalances) {
				feebalance.setBizId(StringUtils.join(new Object[]{feebalance.getUserId(),month},"_"));
				Map<String, String> balanceMap = Maps.newHashMap();
				cfplannerIds.add(feebalance.getUserId());
				balanceMap.put("userId", feebalance.getUserId());
				balanceMap.put("balanceNumber", feebalance.getBizId());
				balanceMaps.add(balanceMap);
				feebalance.setMonth(month);
				feebalance.setCreateTime(time);
				feebalance.setUpdateTime(time);
			}
			feebalanceMapper.insertFeebalances(feebalances);
			feePayMapper.inserts(feebalances);
			
			feeDetailService.batchUpdateBalanceStatus(cfplannerIds, balanceMaps, 2, beginTime, endTime);
			
		}catch(Exception e){
			feeDetailService.batchUpdateBalanceStatus(cfplannerIds,Lists.<Map<String, String>>newArrayListWithCapacity(1),3, beginTime, endTime);
			LOGGER.error("insertFeeSummarys Exception feebalances={},month={},time={},exception={}", new Object[]{feebalances,month,time,e});
			throw new ServiceException("佣金计算异常");
		}
	}
	
	
	
	

	@Override
	public DataTableReturn getFeebalanceList(FeeRequest feeRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(feeRequest.getDraw()+1);
		Page<FeebalanceListResponse> page = new Page<FeebalanceListResponse>(feeRequest.getStart()/feeRequest.getLength()+1,feeRequest.getLength());
		List<FeebalanceListResponse> list = feebalanceMapper.getFeebalanceList(feeRequest.getMobile(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getFeebalanceRecordByMobile(FeeDetailRequest feeRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(feeRequest.getDraw()+1);
		Page<FeebalanceListResponse> page = new Page<FeebalanceListResponse>(feeRequest.getStart()/feeRequest.getLength()+1,feeRequest.getLength());
		List<FeebalanceListResponse> list = feebalanceMapper.getFeebalanceRecordByMobile(feeRequest.getMobile(),feeRequest.getMonth(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getFeeDetailRecord(FeeDetailRequest feeRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(feeRequest.getDraw()+1);
		Page<FeeDetailRecordResponse> page = new Page<FeeDetailRecordResponse>(feeRequest.getStart()/feeRequest.getLength()+1,feeRequest.getLength());
		List<FeeDetailRecordResponse> list;
		if(StringUtils.isBlank(feeRequest.getTime())){
			list = feebalanceMapper.getFeeDetailRecord(feeRequest.getMobile(), feeRequest.getMonth(), feeRequest.getCustomerMobile(), null, null, page);
		}else{
			String begintime = StringUtils.join(new Object[]{feeRequest.getTime()," 00:00:00"});
			String endtime = StringUtils.join(new Object[]{feeRequest.getTime()," 23:59:59"});
			list = feebalanceMapper.getFeeDetailRecord(feeRequest.getMobile(), feeRequest.getMonth(), feeRequest.getCustomerMobile(), begintime, endtime, page);
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}


	@Override
	public List<FeebalanceListResponse> getFeebalanceListByMonth() {
		String month = getMonth();
		String date[] =StringUtils.split(month, "-");
		String monthTrim = StringUtils.join(new Object[]{date[0],date[1]});
		return feebalanceMapper.getFeebalanceListByMonth(monthTrim);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void feePay(final String operator) throws Exception {
		final String month = getMonth();
		String date[] =StringUtils.split(month, "-");
		final String monthTrim = StringUtils.join(new Object[]{date[0],date[1]});
		int noPayFeeCount = feePayMapper.getNoPayFeeCount(monthTrim);
		
		if(noPayFeeCount==0)return;
		
		int scanCount = feeConfig.getFee_scan_count();
		
		int totalPage = noPayFeeCount/scanCount;
		
		totalPage = noPayFeeCount % scanCount >0 ? totalPage+1: totalPage;
		
		final List<SysMsg> sysMsgs = Lists.newArrayList();
		final List<SysPushMessage> pushMsgs = Lists.newArrayList();
		final List<SmMessageQueue> messages = Lists.newArrayList();
		
		final Date time = new Date();
		long s = System.currentTimeMillis();
		if(totalPage==1){
			final Page<TCFeebalance> page= new Page<TCFeebalance>(1,scanCount);
			feePayService.payFee(feePayMapper.getNoPayFeeList(monthTrim, page), monthTrim, time, operator,sysMsgs,pushMsgs,messages);
		}else{
			final CountDownLatch downLatch = new CountDownLatch(totalPage);
			for (int i = 1; i <= totalPage; i++) {
					final Page<TCFeebalance> page= new Page<TCFeebalance>(i,scanCount);
					ThreadpoolService.execute(new Runnable() {
						@Override
						public void run() {
							List<TCFeePay> noPayFeeList = feePayMapper.getNoPayFeeList(monthTrim, page);
							try {
								feePayService.payFee(noPayFeeList, monthTrim, time, operator,sysMsgs,pushMsgs,messages);
								
							} catch (Exception e) {
								LOGGER.warn("payFee Exception ", noPayFeeList);
							}finally{
								downLatch.countDown();
							}
						}
					});	
			}
			downLatch.await();
		}
		feeSummary(monthTrim, date,  s);
		ThreadpoolService.execute(new Runnable() {			
			@Override
			public void run() {
				//短信通知
				messageQueueService.batchSendDiffContentMessageAndPmsg(messages,true,sysMsgs);
				//通知栏推送
				Map<String,Object> urlparam = Maps.newHashMap();
				urlparam.put("profitType", "1");
				urlparam.put("month", month);
				pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LFEERECEIVED,urlparam,pushMsgs, null);
			}
		});
		
			
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private void feeSummary(String month, String[] date, long s) {
		feebalanceMapper.insertFeeSummary(month, 0, date[0], date[1], 1);
		feebalanceMapper.insertFeeSummarylog(month, "成功", "",System.currentTimeMillis()-s);
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateStatusAndGetCalculateCount(String begintime,String endTime, List<Integer> beforeStatus, Integer afterStatus) {
		int updateCount = feebalanceMapper.updateFeebalanceStatus(begintime, endTime, beforeStatus, afterStatus);
		if(updateCount==0)return 0;
		return feebalanceMapper.getFeebalanceByMonthCount(begintime, endTime);
	}	
	
}
