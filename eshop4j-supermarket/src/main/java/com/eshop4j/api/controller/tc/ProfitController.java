package com.eshop4j.api.controller.tc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.tc.CfplannerProfitRequest;
import com.eshop4j.api.request.tc.ProfitCalculateRequest;
import com.eshop4j.api.response.cim.ProductDetailResponse;
import com.eshop4j.api.response.tc.CfplannerProfitTotalResponse;
import com.eshop4j.api.response.tc.ProfitCalculateResponse;
import com.eshop4j.api.response.tc.ProfitItemsResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.tc.fee.common.CalculateTools;
import com.eshop4j.tc.fee.model.TCFeeTypeConfig;
import com.eshop4j.tc.fee.service.TCFeeDetailService;
import com.eshop4j.tc.fee.service.TCFeeTypeConfigService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;

@Controller
public class ProfitController {
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private TCFeeDetailService feeDetailService;
	
	@Autowired
	private TCFeeTypeConfigService feeTypeConfigService;
	
	
	@RequestMapping("api/product/profitCalculate")
	@ResponseBody
	public Object profitCalculate(@Valid ProfitCalculateRequest req,BindingResult result, AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的投资类型类型");
		List<ProfitCalculateResponse> response=new ArrayList<ProfitCalculateResponse>();
		String feeAmount="0";
		Integer day = 0;
		boolean isEdit=false;
		if(req.getAmount()!=null){
			ProductDetailResponse productDetail =  productService.queryProductDetail(req.getProductId());
			if(productDetail==null)return AppResponseUtil.getErrorBusi("error", "产品不存在");
			isEdit = !ObjectUtils.equals(productDetail.getIsRedemption(),0);
			day = isEdit ? req.getDay()==null?productDetail.getDeadLineMinValue():req.getDay() : productDetail.getDeadLineMinValue();
			feeAmount =String.valueOf(CalculateTools.feeAmountCompute(CalculateTools.yearpurAmountCompute(new BigDecimal(req.getAmount()), day),productDetail.getFeeRatio().doubleValue()).setScale(2,   BigDecimal.ROUND_DOWN).doubleValue());
		}
		ProfitCalculateResponse productDay = new ProfitCalculateResponse("day", "产品期限",String.valueOf(day),isEdit);
		ProfitCalculateResponse feeProfit = new ProfitCalculateResponse("fee", "预期佣金", feeAmount);
		response.add(productDay);
		response.add(feeProfit);
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	@RequestMapping("api/profit/cfplannerProfitTotal")
	@ResponseBody
	public Object cfplannerProfitTotal(CfplannerProfitRequest cfplannerProfitRequest, AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的投资类型类型");
		CfplannerProfitTotalResponse response = feeDetailService.queryCfplannerProfitTotal(JsonWebTokenHepler.getUserIdByToken(head.getToken()), cfplannerProfitRequest.getDateType(), DateUtils.parse(cfplannerProfitRequest.getDate(),DateUtils.FORMAT_SHORT));
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	
	@RequestMapping("api/profit/cfplannerProfitItemTotal")
	@ResponseBody
	public Object cfplannerProfitItemTotal(CfplannerProfitRequest profitRequest,AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的投资类型类型");
		final Double total=feeDetailService.queryCfplannerProfitItemTotal(JsonWebTokenHepler.getUserIdByToken(head.getToken()), profitRequest.getDateType(),DateUtils.parse(profitRequest.getDate(),DateUtils.FORMAT_SHORT), profitRequest.getProfitType());
		@SuppressWarnings("serial")
		Map<String, Object> map = new HashMap<String, Object>(){
			{
				put("total", total);
			}
		};
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	@RequestMapping("api/profit/cfplannerProfitItem")
	@ResponseBody
	public Object cfplannerProfitItem(CfplannerProfitRequest profitRequest,AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的投资类型类型");
		PaginatorResponse<ProfitItemsResponse> response = feeDetailService.queryCfplannerProfitItem(JsonWebTokenHepler.getUserIdByToken(head.getToken()), profitRequest);
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	
	@RequestMapping("api/profit/cfplannerProfitTypes")
	@ResponseBody
	public Object cfplannerProfitTypes(AppRequestHead head){
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的投资类型类型");
		return AppResponseUtil.getSuccessResponse(feeTypeConfigService.selectListByCondition(new TCFeeTypeConfig()));
	}


}
