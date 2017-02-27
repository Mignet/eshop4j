package com.eshop4j.api.controller.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.news.NetNewsTypeRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.web.model.news.HomepageNetNewsListResp;
import com.eshop4j.web.service.NetLoanNewsService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

/**
 * 
 * @描述：网贷资讯
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:06:20
 *
 */
@Controller
@RequestMapping(value = "/api/netLoanNews")
@RequestLogging("网贷资讯")
public class NetLoanNewsController {
	
	@Resource
	private NetLoanNewsService netLoanNewsService;

	/**
	 * 网贷资讯首页列表
	 * 
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("首页列表")
	@RequestMapping("/homeList")
	@ResponseBody
	public BaseResponse homeList(NetNewsTypeRequest request, AppRequestHead head) {
		String type = null;
		String url = null;
		if(request != null && request.getType() != null) {
			if(request.getType().equals("1")) {
				type = "tzgl";
				url = "article-tzgl-1.html";
			} else if (request.getType().equals("2")) {
				type = "zcfg";
				url = "article-zcfg-1.html";
			} else if (request.getType().equals("3")) {
				type = "qwgd";
				url = "article-qwgd-1.html";
			} else if (request.getType().equals("4")) {
				type = "hydt";
				url = "article-hydt-1.html";
			}
		}
		List<HomepageNetNewsListResp> list = netLoanNewsService.queryHomepageNetNewsList(type);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("moreUrl", url);
		map.put("list", list);
		return AppResponseUtil.getSuccessResponse(map);

	}

}
