package com.eshop4j.api.controller.tc;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.eshop4j.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.eshop4j.api.request.tc.CustomerInvestRecordRequest;
import com.eshop4j.api.request.tc.CustomerTradeMsgRequest;
import com.eshop4j.api.request.tc.ImpendRepaymentRequest;
import com.eshop4j.api.request.tc.UnRecordInvestRequest;
import com.eshop4j.api.response.tc.CfplannerUnrecordInvestResponse;
import com.eshop4j.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.eshop4j.api.response.tc.CustomerTradeMsgResponse;
import com.eshop4j.api.response.tc.CustomerUnrecordInvestResponse;
import com.eshop4j.api.response.tc.InvestRecordResponse;
import com.eshop4j.api.response.tc.RepaymentResponse;
import com.eshop4j.api.response.tc.TradeNewlyDynamicResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CimProductUnrecordInvestService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("api/investRecord")
@RequestLogging("投资记录")
public class InvestRecordController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvestRecordController.class);
	
	@Autowired
	private CimProductInvestRecordService productInvestRecordService;
	
	@Autowired CrmCfplannerService cfplannerService;
	
	@Autowired CrmInvestorService investorService;
	
	@Autowired
	private CimProductUnrecordInvestService unrecordInvestService;
	
	
	@RequestMapping("customer/investRecordCounts")
	@ResponseBody
	@RequestLogging("客户投资记录数量")
	public Object investRecordCounts(AppRequestHead head){
		if(!AppUtils.isInvestorApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			return AppResponseUtil.getSuccessResponse(productInvestRecordService.getInvestRecordCounts(JsonWebTokenHepler.getUserIdByToken(head.getToken())));
		}catch(Exception e){
			LOGGER.error("investRecordCounts Exception" , e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	@RequestMapping("customer/investRecord")
	@ResponseBody
	@RequestLogging("客户投资记录")
	public Object queryCustomerInvestRecord(@Valid CustomerInvestRecordRequest req,BindingResult validResult,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
		if(!AppUtils.isInvestorApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			Page<InvestRecordResponse> page  = new Page<InvestRecordResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
			PaginatorResponse<InvestRecordResponse> result = productInvestRecordService.queryCustomerInvestRecord(JsonWebTokenHepler.getUserIdByToken(head.getToken()), req.getStatus(),page);
			return AppResponseUtil.getSuccessResponse(result);
		}catch(Exception e){
			LOGGER.error("queryCustomerInvestRecord Exception" , e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	
	@RequestMapping("customer/unRecordInvestList")
	@ResponseBody
	@RequestLogging("客户报单列表")
	public Object customerUnRecordInvestList(PaginatorRequest req,AppRequestHead head){
		if(!AppUtils.isInvestorApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		Page<CustomerUnrecordInvestResponse> page  = new Page<CustomerUnrecordInvestResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		try{
			return AppResponseUtil.getSuccessResponse(unrecordInvestService.getCustomerUnrecordInvest(JsonWebTokenHepler.getUserIdByToken(head.getToken()), page));
		}catch(Exception e){
			LOGGER.error("customerUnRecordInvestList Exception" , e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	
	@RequestMapping("cfplanner/customerStatistic")
	@ResponseBody
	@RequestLogging("理财师客户投资记录总计")
	public Object cfplannerCustomerInvestRecordStatistic(@Valid CfplannerCustomerInvestRecordRequest req,BindingResult validResult,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			req.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			CustomerInvestRecordStatisticResponse customerInvestRecordStatisticResponse = productInvestRecordService.queryCfplannerCustomerInvestRecordStatistic(req);
			if(customerInvestRecordStatisticResponse==null)customerInvestRecordStatisticResponse = new CustomerInvestRecordStatisticResponse();
			return AppResponseUtil.getSuccessResponse(customerInvestRecordStatisticResponse);
		}catch(Exception e){
			LOGGER.error("cfplannerCustomerInvestRecordStatistic Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	@RequestMapping("cfplanner/customerInvestRecords")
	@ResponseBody
	@RequestLogging("理财师所有客户投资记录")
	public Object cfplannerInvestRecords(@Valid CfplannerCustomerInvestRecordRequest req,BindingResult validResult,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			req.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			if(ObjectUtils.equals(req.getType(), 1)){
				return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCfplannerCustomerInvestRecord(req));
			}else if(ObjectUtils.equals(req.getType(), 2)){
				return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCfplannerInvestCustomerDetail(req));
			}
			return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		}catch(Exception e){
			LOGGER.error("cfplannerInvestRecords Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	
	@RequestMapping("cfplanner/customerImpendRepayment")
	@ResponseBody
	@RequestLogging("理财师客户即将回款投资记录")
	public Object customerImpendRepayment(ImpendRepaymentRequest req,BindingResult validResult,AppRequestHead head){
	
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			Page<RepaymentResponse> page  = new Page<RepaymentResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
			return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCustomerRepayment(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getCustomerId(), page));
		}catch(Exception e){
			LOGGER.error("customerImpendRepayment Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	@RequestMapping("cfplanner/customerAllTradeMsg")
	@ResponseBody
	@RequestLogging("理财师客户交易")
	public Object customerTrade(CustomerTradeMsgRequest req,AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			if(StringUtils.isBlank(req.getCustomerId()))return  AppResponseUtil.getErrorBusi("100002", "客户编号不能为空");
			Page<CustomerTradeMsgResponse> page  = new Page<CustomerTradeMsgResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
			return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCustomerTradeMsg(req.getCustomerId(), JsonWebTokenHepler.getUserIdByToken(head.getToken()), page));
		}catch(Exception e){
			LOGGER.error("customerTrade Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	
	
	@RequestMapping("cfplanner/customerTradeMsgCount")
	@ResponseBody
	@RequestLogging("理财师客户交易消息数量")
	public Object customerTradeMsgCount(AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			
			Map<String, String> counts = Maps.newHashMap();
			counts.put("investCount", String.valueOf(productInvestRecordService.queryCustomerInvestTradeMsgCount(JsonWebTokenHepler.getUserIdByToken(head.getToken()))));
			counts.put("repaymentCount",String.valueOf(productInvestRecordService.queryCustomerRepaymentTradeMsgCount(JsonWebTokenHepler.getUserIdByToken(head.getToken()))));
			return AppResponseUtil.getSuccessResponse(counts);
		}catch(Exception e){
			LOGGER.error("customerTradeMsgCount Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	@RequestMapping("cfplanner/customerTradeMsg")
	@ResponseBody
	@RequestLogging("理财师客户交易消息")
	public Object customerInvestTradeMsg(@Valid CustomerTradeMsgRequest req,BindingResult validResult,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		try{
			int type = req.getType();
			Page<CustomerTradeMsgResponse> page  = new Page<CustomerTradeMsgResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
			if(ObjectUtils.equals(type, 1)){
				return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCustomerInvestTradeMsg(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getCustomerId(), page));
			}else if(ObjectUtils.equals(type, 2)){
				return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCustomerRepaymentTradeMsg(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getCustomerId(), page));
			}
			return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		}catch(Exception e){
			LOGGER.error("customerInvestTradeMsg Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	
	
	@RequestMapping("cfplanner/dynamic")
	@ResponseBody
	@RequestLogging("理财师动态汇总")
	public Object cfplannerDynamic(PaginatorRequest req,AppRequestHead head){
		Page<TradeNewlyDynamicResponse> page  = new Page<TradeNewlyDynamicResponse>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		try{
			return AppResponseUtil.getSuccessResponse(productInvestRecordService.queryCfpNewlyDynamic(page,JsonWebTokenHepler.getUserIdByToken(head.getToken())));
		}catch(Exception e){
			LOGGER.error("cfplannerDynamic Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	@RequestMapping("cfplanner/unRecordInvestList")
	@ResponseBody
	@RequestLogging("理财师报单列表")
	public Object cfplannerUnRecordInvestList(PaginatorRequest req,AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		Page<CfplannerUnrecordInvestResponse> page  = new Page<CfplannerUnrecordInvestResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		try{
			return AppResponseUtil.getSuccessResponse(unrecordInvestService.getCfplannerUnrecordInvest(JsonWebTokenHepler.getUserIdByToken(head.getToken()), page));
		}catch(Exception e){
			LOGGER.error("cfplannerUnRecordInvestList Exception" , e);
		}
		return AppResponseUtil.getErrorServ();
	}
	
	@RequestMapping("cfplanner/addUnRecordInvest")
	@ResponseBody
	@RequestLogging("保存理财师报单")
	public Object addUnRecordInvest(@Valid UnRecordInvestRequest req,BindingResult validResult,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(StringUtils.isBlank(userId))return AppResponseUtil.getErrorBusi("100002", "您未登陆,请先登录");
		return unrecordInvestService.inserUnrecordInvest(req, userId);
	}

}
