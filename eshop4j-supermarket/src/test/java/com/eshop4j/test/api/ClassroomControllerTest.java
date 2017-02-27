package com.eshop4j.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import com.eshop4j.api.response.mc.ClassroomPageListResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;
import com.eshop4j.test.enums.PathEnum;

public class ClassroomControllerTest extends BaseTest{
	
	@Test//课程列表
	public void queryClassroomList() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/queryClassroomList",this.getToken(),ClassroomPageListResponse.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test//课程列表
	public void queryClassRoomDetail() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/queryClassRoomDetail",this.getToken(),ClassroomPageListResponse.class,parameterMap);
		logger.debug(baseResponse.toString());
	}

}
