package com.eshop4j.api.controller.acc;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.crm.WeiXinMsgRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.web.service.WeiXinMsgService;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述：账户相关接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/weixin")
@RequestLogging("账户相关接口")
public class WeiXinMsgController {


	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	/**
	 * 微信消息推送
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendWeiXinMsgCommon")
	@ResponseBody
	public BaseResponse getWithdrawSummary(WeiXinMsgRequest req) {
		weiXinMsgService.sendWeiXinMsgCommon(req);
		return AppResponseUtil.getSuccessResponse();
	}
	
	
	
	
	
}
