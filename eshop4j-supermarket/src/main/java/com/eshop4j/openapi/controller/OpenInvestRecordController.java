package com.eshop4j.openapi.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.exception.ServiceException;
import com.eshop4j.openapi.request.InvestRecordReq;
import com.eshop4j.openapi.request.RepaymentRecordReq;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CimProductRepaymentRecordService;
import com.eshop4j.xoss.api.OpenRequestHead;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.OpenResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 开放投资记录接口
 * 
 * @创建人： chenheng
 * 
 * @创建时间：2016-07-20 15:56:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "openapi/invest")
@RequestLogging("投资记录")
public class OpenInvestRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenInvestRecordController.class);

	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource
	private CimProductRepaymentRecordService productRepaymentRecordService;
	
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	/**
	 * 推送投资记录
	 * @throws Exception 
	 */
	@RequestMapping("pushInvestRecord")
	@ResponseBody
	@RequestLogging("推送投资记录")
	public Object investRecor(@Valid InvestRecordReq investRecordReq,BindingResult validResult,OpenRequestHead requestHead) {
		if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
		
		//参数无错误,无论无何返回成功
		try{
			investRecordReq.setPlatfromId(requestHead.getOrgNumber());
			investRecordService.insertInvestRecordProcess(investRecordReq);
		}catch(ServiceException e){
			LOGGER.warn("investRecor serviceException investRecordReq={},exception={}", investRecordReq,e);
			return OpenResponseUtil.getErrorBusi(new BaseResponse("error", e.getMessage()));
		}catch(Exception e){
			LOGGER.error("investRecor exception investRecordReq={},exception={}", investRecordReq,e);
		}
		return OpenResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 回款记录
	 * @param investRecordReq
	 * @param validResult
	 * @param requestHead
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pushRepaymentRecord")
	@ResponseBody
	@RequestLogging("推送回款记录")
	public Object repaymentRecor(@Valid RepaymentRecordReq req,BindingResult validResult,OpenRequestHead requestHead) {
		if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
		try{
			productRepaymentRecordService.insertRepaymentRecord(req);
		}catch(ServiceException e){
			LOGGER.warn("repaymentRecor serviceException requestHead={},repaymentRecordReq={},exception={}",new Object[]{requestHead,req,e.getMessage()} );
			return OpenResponseUtil.getErrorBusi(new BaseResponse("error", e.getMessage()));
		}catch(Exception e){
			LOGGER.error("repaymentRecor exception equestHead={},repaymentRecordReq={},exception={}", new Object[]{requestHead,req,e.getMessage()});
		}
		return OpenResponseUtil.getSuccessResponse();
	}
	
	
	
}
