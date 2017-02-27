package com.linkwee.openapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.openapi.request.ProductPushRequest;
import com.linkwee.openapi.request.ProductUpdatRequest;
import com.linkwee.web.service.CimOpenProductService;
import com.linkwee.xoss.api.OpenRequestHead;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 开放平台产品
 * @author liqimoon
 *
 */
@Controller
@RequestLogging("开放平台产品")
@RequestMapping(value = "/openapi/product")
public class OpenProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenProductController.class);

	@Resource
	private CimOpenProductService cimOpenProductService;
	
    @ResponseBody
    @RequestLogging("推送产品")
    @RequestMapping("/pushProduct")
	public BaseResponse pushProduct(@Valid ProductPushRequest productPushRequest,BindingResult validResult,OpenRequestHead openRequestHead){
    	
    	LOGGER.info("开始推送产品,OrgNumber={},ProductPushRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(productPushRequest));
    	
		/**
		 * 普通参数校验
		 */
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	/**
    	 * 特殊参数校验
    	 * 1:	需要募集开始及截止时间时,募集开始时间和募集截止时间不能为null
    	 * 2:	是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让) 时 ,对应的可赎回天数和可转让天数不能为空
    	 */
    	List<BaseResponse> errors = new ArrayList<BaseResponse>();
	    if(productPushRequest.getIsCollect() == 2 && (productPushRequest.getCollectBeginTime() == null || productPushRequest.getCollectEndTime()== null)){
	    	errors.add(new BaseResponse("PUSHPRODUCT_FAIL", "推送产品失败,需要募集开始及截止时间时,募集开始时间和募集截止时间不能为null"));
		}
	    if(productPushRequest.getIsRedemption() == 1 && (productPushRequest.getRedemptionTime() == null || productPushRequest.getRedemptionTime() <= 0)){
	    	errors.add(new BaseResponse("PUSHPRODUCT_FAIL", "推送产品失败,产品为1=可赎回时,推送可赎回天数不正确"));
		}
	    if(productPushRequest.getIsRedemption() == 2 && (productPushRequest.getAssignmentTime() == null || productPushRequest.getAssignmentTime() <= 0)){
	    	errors.add(new BaseResponse("PUSHPRODUCT_FAIL", "推送产品失败,产品为2=可转让时,推送可转让天数不正确"));
		}
	    if(productPushRequest.getIsRedemption() == 3 && (productPushRequest.getRedemptionTime() == null || productPushRequest.getRedemptionTime() <= 0 || productPushRequest.getAssignmentTime() == null || productPushRequest.getAssignmentTime() <= 0)){
	    	errors.add(new BaseResponse("PUSHPRODUCT_FAIL", "推送产品失败,3=可赎回且可转让时,推送可赎回或可转让天数不正确"));
		}
		if(!errors.isEmpty()){
			return OpenResponseUtil.getErrorParams(errors);
		}
		
		return cimOpenProductService.pushProduct(productPushRequest, openRequestHead);
	}
    
    
    @ResponseBody
    @RequestLogging("更新产品")
    @RequestMapping("/updateProduct")
    public BaseResponse updateProduct(@Valid ProductUpdatRequest productUpdatRequest,BindingResult validResult,OpenRequestHead openRequestHead){
    	
    	LOGGER.info("开始更新产品,OrgNumber={},productUpdatRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(productUpdatRequest));
    	
		/**
		 * 参数校验
		 */
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	
    	return cimOpenProductService.updateProduct(productUpdatRequest, openRequestHead);
    }
    
    
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
