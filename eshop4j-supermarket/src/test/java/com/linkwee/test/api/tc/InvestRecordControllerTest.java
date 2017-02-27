package com.linkwee.test.api.tc;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.linkwee.api.request.tc.CustomerInvestRecordRequest;
import com.linkwee.api.request.tc.CustomerTradeMsgRequest;
import com.linkwee.api.request.tc.ImpendRepaymentRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class InvestRecordControllerTest extends BaseTest{
	
	//@Test
	public void queryCustomerInvestRecordTest() throws Exception{
		CustomerInvestRecordRequest recordRequest = new CustomerInvestRecordRequest();
		recordRequest.setStatus(1);
		recordRequest.setPageIndex(1);
		System.out.println(JSON.toJSONString(recordRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/investRecord/customerInvestRecord",this.getToken(),BaseResponse.class,recordRequest);
		logger.debug(baseResponse.toString());
	}
	
	
	//@Test
	public void customerStatisticTest() throws Exception{
		CfplannerCustomerInvestRecordRequest req = new  CfplannerCustomerInvestRecordRequest();
		req.setDate("2016-07-02");
		System.out.println(JSON.toJSONString(req));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investRecord/cfplanner/customerStatistic",this.getToken(),BaseResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
	//@Test
	public void customerInvestRecordsTest() throws Exception{
		CfplannerCustomerInvestRecordRequest req = new  CfplannerCustomerInvestRecordRequest();
		req.setDate("2016-07-02");
		req.setType(2);
		System.out.println(JSON.toJSONString(req));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investRecord/cfplanner/customerInvestRecords",this.getToken(),BaseResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void customerImpendRepaymentTest() throws Exception{
		ImpendRepaymentRequest req = new ImpendRepaymentRequest();
		//req.setCustomerId("11db98256efd4b8f88fa1016e432bd80");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investRecord/cfplanner/customerImpendRepayment",this.getToken(),BaseResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	@Test
	public void customerTradeMsgTest() throws Exception{
		CustomerTradeMsgRequest req = new CustomerTradeMsgRequest();
		req.setCustomerId("11db98256efd4b8f88fa1016e432bd80");
		req.setType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investRecord/cfplanner/customerAllTradeMsg",this.getToken(),BaseResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	@Test
	public void customerTradeMsgTestCount() throws Exception{
	
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investRecord/cfplanner/customerTradeMsgCount",this.getToken(),BaseResponse.class);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void cfpNewlyDynamic() throws Exception{
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/investRecord/cfplanner/dynamic",this.getToken(),BaseResponse.class,pageRequest);
		logger.debug(baseResponse.toString());
	}
	

}
