package com.eshop4j.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.xoss.util.RequestLogging;


@Controller
@RequestMapping("api/order")
@RequestLogging("订单记录")
public class OrderController {

	@RequestMapping("")
	@ResponseBody
	@RequestLogging("订单记录测试")
	public String test(){
		return "{\"test\":\"success\"}";
	}
	
}
