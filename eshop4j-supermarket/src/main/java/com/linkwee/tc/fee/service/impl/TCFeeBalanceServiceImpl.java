package com.linkwee.tc.fee.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.tc.fee.common.config.FeeConfig;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.model.TCFeebalance;
import com.linkwee.tc.fee.service.TCFeeBalanceService;
import com.linkwee.tc.fee.service.TCFeePayService;
import com.linkwee.web.dao.TCFeePayMapper;
import com.linkwee.web.dao.TCFeebalanceMapper;
import com.linkwee.web.dao.TCFeedetailMapper;
import com.linkwee.web.request.tc.FeeDetailRequest;
import com.linkwee.web.request.tc.FeeRequest;
import com.linkwee.web.response.tc.FeeDetailRecordResponse;
import com.linkwee.web.response.tc.FeebalanceListResponse;
import com.linkwee.xoss.helper.ThreadpoolService;

@Service("tcFeeBalanceService")
public class TCFeeBalanceServiceImpl implements TCFeeBalanceService{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeeBalanceServiceImpl.class);
	
	
	@Autowired
	private TCFeedetailMapper feedetailMapper;
	
	@Autowired
	private TCFeebalanceMapper feebalanceMapper;
	
	@Autowired
	private TCFeePayMapper feePayMapper;
	
	
	@Autowired
	private TCFeePayService feePayService;
	
	@Autowired
	private FeeConfig feeConfig;
	
	
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
	
	
	public void getFeeBalances(){
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void feeBalanceProcess() {
		
		String month = getMonth();
		String date[] =StringUtils.split(month, "-");
		String monthTrim = StringUtils.join(new Object[]{date[0],date[1]});
		if(feebalanceMapper.isFeeSummary(monthTrim))return;
		String begintime = StringUtils.join(new Object[]{month,"-01 00:00:00".intern()});
		String endtime = StringUtils.join(new Object[]{month,"-31 23:59:59".intern()});
	
		
		int feeSummaryTotalCount = feebalanceMapper.getFeebalanceByMonthCount(begintime, endtime);
		
		if(feeSummaryTotalCount==0)return;
		
		int scanCount = feeConfig.getFee_scan_count();
		
		int totalPage = feeSummaryTotalCount/scanCount;
		totalPage = feeSummaryTotalCount % scanCount >0 ? totalPage+1: totalPage;
		
		Date time = new Date();
		CountDownLatch downLatch = new CountDownLatch(totalPage);
	
		if(totalPage==1){
			Page<TCFeebalance> page= new Page<TCFeebalance>(1,scanCount);
			ThreadpoolService.execute(new FeebalanceWorker(feebalanceMapper.getFeebalanceByMonth(begintime, endtime, page), monthTrim, time, begintime, endtime,downLatch));
		}else{
			for (int i = 1; i <= totalPage; i++) {
				Page<TCFeebalance> page= new Page<TCFeebalance>(i,scanCount);
				List<TCFeebalance> feebalances = feebalanceMapper.getFeebalanceByMonth(begintime, endtime, page);
				ThreadpoolService.execute(new FeebalanceWorker(feebalances, monthTrim, time, begintime, endtime,downLatch));
			}
		}
		try {
			downLatch.await();
		} catch (InterruptedException e) {
		}
	}
	
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertFeebalances(List<TCFeebalance> feebalances,String month,Date time,String beginTime ,String endTime) throws Exception{
		List<Map<String, String>> balanceMaps= Lists.newArrayList();
		List<String> cfplannerIds= Lists.newArrayList();
		try{
			if(feebalances==null || feebalances.isEmpty())return;
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
			feedetailMapper.batchUpdateBalanceStatus(cfplannerIds,Lists.<Map<String, String>>newArrayListWithCapacity(1),1, beginTime, endTime);
			feebalanceMapper.insertFeebalances(feebalances);
			feePayMapper.inserts(feebalances);
			feedetailMapper.batchUpdateBalanceStatus(cfplannerIds,balanceMaps,2, beginTime, endTime);
		}catch(Exception e){
			feedetailMapper.batchUpdateBalanceStatus(cfplannerIds,Lists.<Map<String, String>>newArrayListWithCapacity(1),3, beginTime, endTime);
			LOGGER.error("insertFeeSummarys Exception feebalances={},month={},time={},exception={}", new Object[]{feebalances,month,time,e});
			throw new RuntimeException(e);
		}
		
	}
	
	class FeebalanceWorker implements Runnable{
		private CountDownLatch downLatch;
		private List<TCFeebalance> feebalances;
		private String month;
		private Date time;
		private String beginTime  ;
		private String endTime ;
		
		public FeebalanceWorker(List<TCFeebalance> feebalances,String month,Date time,String beginTime ,String endTime,CountDownLatch downLatch) {
			this.feebalances = feebalances;
			this.month=month;
			this.time=time;
			this.beginTime = beginTime;
			this.endTime = endTime;
			this.downLatch = downLatch;
		}

		@Override
		public void run() {
			try {
				insertFeebalances(feebalances, month, time,beginTime,endTime);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				downLatch.countDown();
			}
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
		
		final Date time = new Date();
		long s = System.currentTimeMillis();
		if(totalPage==1){
			final Page<TCFeebalance> page= new Page<TCFeebalance>(1,scanCount);
			feePayService.payFee(feePayMapper.getNoPayFeeList(monthTrim, page), monthTrim, time, operator);
		}else{
			final CountDownLatch downLatch = new CountDownLatch(totalPage);
			for (int i = 1; i <= totalPage; i++) {
					final Page<TCFeebalance> page= new Page<TCFeebalance>(i,scanCount);
					ThreadpoolService.execute(new Runnable() {
						@Override
						public void run() {
							List<TCFeePay> noPayFeeList = feePayMapper.getNoPayFeeList(monthTrim, page);
							try {
								feePayService.payFee(noPayFeeList, monthTrim, time, operator);
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
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private void feeSummary(String month, String[] date, long s) {
		feebalanceMapper.insertFeeSummary(month, 0, date[0], date[1], 1);
		feebalanceMapper.insertFeeSummarylog(month, "成功", "",System.currentTimeMillis()-s);
	}
	
	
}
