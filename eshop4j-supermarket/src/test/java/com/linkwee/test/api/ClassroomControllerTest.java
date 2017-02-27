package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import com.linkwee.api.response.mc.ClassroomPageListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

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
