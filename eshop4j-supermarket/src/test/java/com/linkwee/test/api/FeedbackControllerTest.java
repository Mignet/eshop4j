package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.FeedbackRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;

public class FeedbackControllerTest extends BaseTest{
	
	/**
	 * 添加反馈信息测试
	 * @throws Exception
	 */
	@Test
	public void testFeedbackSuggestion() throws Exception {
		FeedbackRequest feedbackRequest = new FeedbackRequest();
		feedbackRequest.setContent("改变apptype,测试一下，是否成功！！！！！！！！！！！！！！！");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/feedback/suggestion",this.getToken(),Object.class,feedbackRequest);		
		logger.debug(baseResponse.toString());
	}
		
}
