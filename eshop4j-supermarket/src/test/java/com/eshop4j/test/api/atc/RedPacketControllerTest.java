package com.eshop4j.test.api.atc;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.eshop4j.api.request.act.RedpacketRequest;
import com.eshop4j.api.request.act.SendRedPacketRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;

public class RedPacketControllerTest extends BaseTest{

	
	@Test
	public void queryRedPacketTest() throws Exception{
		RedpacketRequest request = new RedpacketRequest();
		request.setType(3);;
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/redPacket/queryRedPacket",this.getToken(),BaseResponse.class,request);
		logger.debug(baseResponse.toString());
	} 
	
	@Test
	public void queryAvailableRedPacket() throws Exception{
		RedpacketRequest request = new RedpacketRequest();
		request.setType(1);
		request.setPatform("OPEN_YUECAIHUI_WEB");
		request.setModel(1);
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/redPacket/queryAvailableRedPacket",this.getToken(),BaseResponse.class,request);
		logger.debug(baseResponse.toString());
	} 
	
	@Test
	public void sendRedPacketTest() throws Exception{
		SendRedPacketRequest request = new SendRedPacketRequest();
		request.setRid("123");
		request.setUserMobiles("18111111124");
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/redPacket/sendRedPacket",this.getToken(),BaseResponse.class,request);
		logger.debug(baseResponse.toString());
	} 
}
