package com.eshop4j.test.api;

import java.util.Map;

import org.junit.Test;

import com.eshop4j.api.request.DynamicNewsPageListRequest;
import com.eshop4j.api.request.NetloanNewsPageListRequest;
import com.eshop4j.api.request.mc.CustomerDeviceRequest;
import com.eshop4j.api.request.mc.MsgDelRequest;
import com.eshop4j.api.request.mc.MsgDetailRequest;
import com.eshop4j.api.response.NetloanNewsResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;
import com.eshop4j.web.model.SmDynamicNews;

public class MsgControllerTest extends BaseTest {

	/**
	 * 首页-热门产品
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHotProduct() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/bulletin/pageList", this.getToken(), Map.class, pageRequest);
		logger.debug(baseResponse.toString());
	}
		
	@Test
	public void testNoticeDtl() throws Exception {
		MsgDetailRequest msgReq = new MsgDetailRequest();
		msgReq.setMsgId("101");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/detail", this.getToken(), Map.class, msgReq);
		logger.debug(baseResponse.toString());
	}

	/// person/pageList
	@Test
	public void testPersonList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/pageList", this.getToken(), Map.class, pageRequest);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonDel() throws Exception {
		MsgDelRequest msgReq = new MsgDelRequest();
		msgReq.setMsgIds("27,28");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/del", this.getToken(), Map.class, msgReq);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testunreadCount() throws Exception {

		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/unreadCount", this.getToken(), Map.class);
		logger.debug(baseResponse.toString());
	}

	//
	// @Test
	public void testDetail() throws Exception {
		MsgDetailRequest dtl = new MsgDetailRequest();
		dtl.setMsgId("34");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/detail", this.getToken(), Map.class, dtl);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testSetMsgPush() throws Exception {
		CustomerDeviceRequest device = new CustomerDeviceRequest();
		device.setIssendNotice("0");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE),
				"/api/msg/setMsgPush", this.getToken(), Map.class, device);
		logger.debug(baseResponse.toString());
	}

	// queryMsgPushSet
	// @Test
	public void testQueryMsgPushSet() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE),
				"/api/msg/queryMsgPushSet", this.getToken(), Map.class);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonReaded() throws Exception {
		MsgDelRequest msgReq = new MsgDelRequest();
		msgReq.setMsgIds("27,28");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/readed", this.getToken(), Map.class, msgReq);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonAllReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/allReaded", this.getToken(), Map.class);
		logger.debug(baseResponse.toString());
	}

	// @Test
	public void testNoticeReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/allReaded", this.getToken(), Map.class);
		logger.debug(baseResponse.toString());
	}

	@Test
	public void testDynamicNewsPageList() throws Exception {
		DynamicNewsPageListRequest req = new DynamicNewsPageListRequest();
		req.setTypeCode("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/pageList", this.getToken(), SmDynamicNews.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testDynamicNewsDetail() throws Exception {
		MsgDetailRequest req = new MsgDetailRequest();
		req.setMsgId("8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/detail", this.getToken(), SmDynamicNews.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testDynamicNewsNearNews() throws Exception {
		MsgDetailRequest req = new MsgDetailRequest();
		req.setMsgId("8");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/nearNews", this.getToken(), Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testnearNotice() throws Exception {
		MsgDetailRequest dtl = new MsgDetailRequest();
		dtl.setMsgId("101");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/nearNotices", this.getToken(), Map.class, dtl);
		logger.info(baseResponse.toString());
	}
	
	@Test
	public void testpersonalUnreadCount() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/msg/person/personalUnreadCount",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testnetloanNews() throws Exception {
		NetloanNewsPageListRequest dtl = new NetloanNewsPageListRequest();
		dtl.setTypeCode(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/dynamicnews/netloanNews/pageList", this.getToken(), NetloanNewsResponse.class, dtl);
		logger.info(baseResponse.toString());
	}
	
	@Test
	public void testnetloanNewsDetail() throws Exception {
		NetloanNewsPageListRequest dtl = new NetloanNewsPageListRequest();
		dtl.setId(4);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/dynamicnews/netloanNews/detail", this.getToken(), NetloanNewsResponse.class, dtl);
		logger.info(baseResponse.toString());
	}
	
}
