package com.linkwee.test.openApi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openapi.request.InvestRecordReq;
import com.linkwee.openapi.request.RepaymentRecordReq;
import com.linkwee.test.TestSupport;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductRepaymentRecordService;

public class OpenInvestRecordControllerTest extends TestSupport{
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource
	private CimProductRepaymentRecordService repaymentRecordService;
	
	class Worker implements Runnable{
		CountDownLatch countDownLatch;
		CimProductInvestRecordService service;
		List<InvestRecordReq> investRecordReqs;
		
		
		public Worker(CountDownLatch countDownLatch,CimProductInvestRecordService investRecordService,List<InvestRecordReq> investRecordReqs) {
			this.countDownLatch = countDownLatch;
			this.service =investRecordService;
			this.investRecordReqs = investRecordReqs;
		}

		@Override
		public void run() {
			
			for (InvestRecordReq investRecordReq : investRecordReqs) {
				try {
					service.insertInvestRecordProcess(investRecordReq);
				} catch (Exception e) {
				}
			}
			countDownLatch.countDown();
			
		}
		
	} 
	
	/**
	 * 回款记录推送接口
	 * @throws Exception
	 */
	@Test
	public void sendrepaymentRecordTest() throws Exception{
		
		RepaymentRecordReq repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("11db98256efd4b8f88fa1016e432bd80");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("19a779351e1d4f04ad813940c36b795c");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(50000d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(org.apache.commons.lang.time.DateUtils.addDays(new Date(), 180));
		repaymentRecordReq.setStatus(2);
		System.out.println(JSON.toJSONString(repaymentRecordReq));
		
		repaymentRecordService.insertRepaymentRecord(repaymentRecordReq);
		
		
		repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("0891f28a9886436d9313ea0af073c7b8");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("940d9f0ba66243aca9c00c27ceadfde9");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(50000d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(org.apache.commons.lang.time.DateUtils.addDays(new Date(), 180));
		repaymentRecordReq.setStatus(3);
		System.out.println(JSON.toJSONString(repaymentRecordReq));
		
		repaymentRecordService.insertRepaymentRecord(repaymentRecordReq);
		
		
		repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("11db98256efd4b8f88fa1016e432bd80");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("e62221ccc12d42cb9566eedede690c69");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(50000d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(org.apache.commons.lang.time.DateUtils.addDays(new Date(), 180));
		repaymentRecordReq.setStatus(2);
		System.out.println(JSON.toJSONString(repaymentRecordReq));
		
		repaymentRecordService.insertRepaymentRecord(repaymentRecordReq);
		
		
		repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("11db98256efd4b8f88fa1016e432bd80");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("7e7f5b970bb54a1090f810f55cab2890");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(50000d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(org.apache.commons.lang.time.DateUtils.addDays(new Date(), 180));
		repaymentRecordReq.setStatus(3);
		System.out.println(JSON.toJSONString(repaymentRecordReq));
		
		repaymentRecordService.insertRepaymentRecord(repaymentRecordReq);
		
		
		repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("11db98256efd4b8f88fa1016e432bd80");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("61c655650917414eaaf0b92141913e85");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(50000d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(org.apache.commons.lang.time.DateUtils.addDays(new Date(), 180));
		repaymentRecordReq.setStatus(3);
		System.out.println(JSON.toJSONString(repaymentRecordReq));
		
		repaymentRecordService.insertRepaymentRecord(repaymentRecordReq);
		
		
	}
	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(random.nextInt(100000));
		}
	}
	
	/**
	 * 投资记录推送接口
	 * @throws Exception
	 */
	@Test
	public void sendInvestRecordTest() throws Exception{
		start();
		ExecutorService executorService=	Executors.newFixedThreadPool(10);
		List<InvestRecordReq> investRecordReqs1 = new ArrayList<InvestRecordReq>(2500);
		Random random = new Random();
		InvestRecordReq investRecordReq =null;
		investRecordReq = new InvestRecordReq();
		investRecordReq.setInvestId(StringUtils.getUUID());
		investRecordReq.setUserId("0891f28a9886436d9313ea0af073c7b8");
		investRecordReq.setInvestStartTime(new Date());
		investRecordReq.setInvestEndTime(DateUtils.addDay(new Date(), 180));
		investRecordReq.setProductId("123456789");
		investRecordReq.setInvestAmount(new BigDecimal(random.nextInt(100000)));
		investRecordReq.setProfit(new BigDecimal(2400d));
		investRecordReq.setPlatfromId("7777777");
		investRecordService.insertInvestRecordProcess(investRecordReq);
		Thread.sleep(10000);
		for (int i = 0; i < 50; i++) {
			investRecordReq = new InvestRecordReq();
			investRecordReq.setInvestId(StringUtils.getUUID());
			investRecordReq.setUserId("c467410eb66e45efbd1dcc547135462a");
			investRecordReq.setInvestStartTime(DateUtils.parse("2016-08-10 23:22:12"));
			investRecordReq.setInvestEndTime(DateUtils.addDay(new Date(), 180));
			investRecordReq.setProductId("123459");
			investRecordReq.setInvestAmount(new BigDecimal(random.nextInt(100000)));
			investRecordReq.setProfit(new BigDecimal(2400d));
			investRecordReq.setPlatfromId("7777777");
			System.out.println(JSON.toJSONString(investRecordReq));
			investRecordReqs1.add(investRecordReq);
			
		}
		CountDownLatch countDownLatch= new CountDownLatch(1);
		long s = System.currentTimeMillis();
		executorService.execute(new Worker(countDownLatch,investRecordService, investRecordReqs1));
		countDownLatch.await();
		System.out.println(System.currentTimeMillis()-s);
		
		end();
		Thread.sleep(200000);
	}
	
	

}
