package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.NewsDetailRequest;
import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.api.response.NewsDtlResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.SmNews;

public class NewsControllerTest extends BaseTest{
		
	/**
	 * 资讯分页
	 * @throws Exception
	 */
	@Test
	public void testNewsPageList() throws Exception {
		NewsPageListRequest newsPageListRequest = new NewsPageListRequest();
		newsPageListRequest.setTypeCode("3");
		newsPageListRequest.setPageIndex(1);
		newsPageListRequest.setPageSize(5);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, this.getUrl(PathEnum.LOCALHOST), "/api/news/newsPageList", this.getToken(), SmNews.class, newsPageListRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 资讯详情
	 * @throws Exception
	 */
	@Test
	public void testNewsDetail() throws Exception {
		NewsDetailRequest newsDetailRequest = new NewsDetailRequest();
		newsDetailRequest.setNewsId("27");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(), "/api/news/pageList/detail", this.getToken(), NewsDtlResponse.class, newsDetailRequest);
		logger.debug(baseResponse.toString());
	}
}
