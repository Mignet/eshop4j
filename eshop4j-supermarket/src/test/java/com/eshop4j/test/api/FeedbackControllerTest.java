package com.eshop4j.test.api;

import org.junit.Test;

import com.eshop4j.api.request.FeedbackRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.test.BaseTest;
import com.eshop4j.test.TestHelper;
import com.eshop4j.test.enums.AppEnum;

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
