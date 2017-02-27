package com.linkwee.api.controller.news;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 
 * @描述：平台档案
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:06:20
 *
 */
@Controller
@RequestMapping(value = "/api/platformDoc")
@RequestLogging("平台档案")
public class PlatformDocController  {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
    private JsonWebTokenHepler jsonWebTokenHepler;
	
	
}
