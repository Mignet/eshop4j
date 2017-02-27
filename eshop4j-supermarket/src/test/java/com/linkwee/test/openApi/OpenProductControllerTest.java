package com.linkwee.test.openApi;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.test.openApi.request.ProductPushRequestNew;
import com.linkwee.test.openApi.request.ProductUpdatRequestNew;

public class OpenProductControllerTest extends BaseTest {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 推送产品
	 * @throws Exception
	 */
	@Test
	public void sendProduct() throws Exception {
		
		ProductPushRequestNew productPushRequestNew = new ProductPushRequestNew();
		productPushRequestNew.setThirdProductId("49132288");
		productPushRequestNew.setProductDesc("陆11金所2222222");
		productPushRequestNew.setProductName("陆金所2222222");
		productPushRequestNew.setProductType(999);
		productPushRequestNew.setBuyedTotalMoney(new BigDecimal(116320));
		productPushRequestNew.setBuyedTotalPeople(30);
		productPushRequestNew.setBuyIncreaseMoney(new BigDecimal(100.05));
		productPushRequestNew.setBuyMaxMoney(new BigDecimal(1000000.05));
		productPushRequestNew.setBuyMinMoney(new BigDecimal(100.05));
		productPushRequestNew.setBuyTotalMoney(new BigDecimal(10000000.05));
		productPushRequestNew.setIsCollect(1);
		productPushRequestNew.setCollectBeginTime(dateFormat.format(new Date()));
		productPushRequestNew.setCollectEndTime(dateFormat.format(new Date()));
		productPushRequestNew.setIsFlow(2);
		productPushRequestNew.setFlowMaxRate(new BigDecimal(10.88));
		productPushRequestNew.setFlowMinRate(new BigDecimal(2.88));
		productPushRequestNew.setInterestWay(2);
		productPushRequestNew.setIsQuota(2);
		productPushRequestNew.setIsRedemption(3);
		productPushRequestNew.setRedemptionTime(30);
		productPushRequestNew.setAssignmentTime(60);
		productPushRequestNew.setMoneyType(1);
		productPushRequestNew.setRepaymentWay(5);
		productPushRequestNew.setRiskControlType(1);
		productPushRequestNew.setRiskLevel(1);
		productPushRequestNew.setValidBeginDate(dateFormat.format(new Date()));
		productPushRequestNew.setValidEndDate(dateFormat.format(new Date()));
		productPushRequestNew.setIsFixedDeadline(2);
		productPushRequestNew.setDeadLineMinValue(720);
		productPushRequestNew.setDeadLineMaxValue(720);
		productPushRequestNew.setDeadLineMinSelfDefined("24个月");
		productPushRequestNew.setDeadLineMaxSelfDefined("24个月");
		productPushRequestNew.setIsHaveProgress(0);
		productPushRequestNew.setSaleStartTime(dateFormat.format(new Date()));
//		productPushRequestNew.setIfRookie(2);
		
		logger.info("推送产品：productSendRequestNew={}",JSONObject.toJSONString(productPushRequestNew));
		BaseResponse baseResponse = TestHelper.remote(this.getUrl(PathEnum.LOCALHOST),"/openapi/product/pushProduct",BaseResponse.class,productPushRequestNew);
		logger.info(baseResponse.toString());
	}
	
	/**
	 * 更新产品
	 * @throws Exception
	 */
	@Test
	public void updateProduct() throws Exception {	
		ProductUpdatRequestNew productUpdatRequest = new ProductUpdatRequestNew();
		productUpdatRequest.setBuyedTotalMoney(new BigDecimal("1888888.88"));
		productUpdatRequest.setBuyedTotalPeople(168);
		productUpdatRequest.setThirdProductId("xinshoubiao999");
		productUpdatRequest.setStatus(1);
		productUpdatRequest.setAddRate(new BigDecimal("13.5"));
		productUpdatRequest.setSaleEndTime(dateFormat.format(new Date()));
		productUpdatRequest.setBuyTotalMoney(new BigDecimal(110000000.05) );
		productUpdatRequest.setDeadLineMinValue(30);
		productUpdatRequest.setDeadLineMinSelfDefined("1个月");
		productUpdatRequest.setDeadLineMaxValue(60);
		productUpdatRequest.setDeadLineMaxSelfDefined("2个月");
		
		logger.info("更新产品：productUpdatRequest={}",JSONObject.toJSONString(productUpdatRequest));
		BaseResponse baseResponse = TestHelper.remote(this.getUrl(PathEnum.LOCALHOST),"/openapi/product/updateProduct",BaseResponse.class,productUpdatRequest);
		logger.info(baseResponse.toString());
	}
}
