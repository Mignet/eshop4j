package com.eshop4j.web.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.core.export.ExportSupport;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.cim.CimOrginfoBindSelect;
import com.eshop4j.web.request.CustomerInvestRequest;
import com.eshop4j.web.request.InvestmentDistributionStatisticsRequest;
import com.eshop4j.web.request.tc.InvestorRepaymentRequest;
import com.eshop4j.web.response.tc.FeebalanceListResponse;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.TcInvestService;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("invest")
public class InvestController {

	@Autowired
	private TcInvestService investService;
	@Autowired
	private CimOrginfoService cimOrginfoService;
	
	@Autowired
	private ExportSupport exportSupport;
	
	@RequestMapping("investList")
	public String init(Model model){
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
		return "investor/investList";
	}
	
	@ResponseBody
	@RequestLogging("管理后台-投资统计-加载投资用户列表数据")
	@RequestMapping("queryCustomerInvestStatistics")
	public Object queryCustomerInvestStatistics(CustomerInvestRequest req){
		return investService.queryCustomerInvestStatistics(req);
	}
	
	
	@RequestMapping("getCustomerInvestDetailPage")
	public String getCustomerInvestDetailPage(){
		return "investor/investDetail";
	}
	
	@RequestMapping("customerImpendRepaymentPage")
	public String customerImpendRepaymentPage(){
		return "investor/investorRepayment";
	}
	
	@ResponseBody
	@RequestLogging("管理后台-投资统计-加载投资用户详情数据")
	@RequestMapping("queryCustomerInvestDetail")
	public Object queryCustomerInvestDetail(CustomerInvestRequest req){
		return investService.queryCustomerInvestDetail(req);
	}
	
	
	@RequestMapping("investmentDistributionStatisticsPage")
	public String investmentDistributionStatisticsPage(Model model){
		List<CimOrginfoBindSelect> selects = cimOrginfoService.queryOrgOfCooperation();
		model.addAttribute("selects", selects);
		return "investor/investmentDistributionStatisticsPage";
	}
	
	@ResponseBody
	@RequestLogging("管理后台-投资统计-投资分布统计")
	@RequestMapping("investmentDistributionStatistics")
	public Object investmentDistributionStatistics(InvestmentDistributionStatisticsRequest req){
		Map<String, Object> map = Maps.newHashMap();
		map.put("total", investService.getInvestmentStatisticsTotal(req.getPlatfrom(), req.getStartTime(), req.getEndTime()));
		map.put("datas", investService.getInvestmentStatisticsList(req.getPlatfrom(), req.getStartTime(), req.getEndTime()));
		return map;
	}
	
	@ResponseBody
	@RequestLogging("管理后台-投资统计-平台产品投资分布统计")
	@RequestMapping("platfromInvestStatistics")
	public Object  platfromInvestStatistics(InvestmentDistributionStatisticsRequest req){
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isBlank(req.getPlatfrom()))return map;
		map.put("total", investService.getInvestStatisticsByPlatfromTotal(req.getPlatfrom(), req.getStartTime(), req.getEndTime()));
		map.put("datas", investService.getInvestStatisticsByPlatfrom(req.getPlatfrom(), req.getStartTime(), req.getEndTime()));
		return map;
	}
	
	@ResponseBody
	@RequestLogging("管理后台-投资统计-客户即将回款")
	@RequestMapping("investorRepayment")
	public Object customerImpendRepayment(InvestorRepaymentRequest repaymentRequest){
		return investService.customerImpendRepayment(repaymentRequest);
	}
	
	 @RequestMapping("investorRepaymentDownload")
	 @RequestLogging("投资统计导出")
	 public void investorRepaymentDownload(HttpServletRequest request, HttpServletResponse response,InvestorRepaymentRequest repaymentRequest){
		 exportSupport.export(request, response, "investor/investorRepaymentList.xls", investService.getCustomerImpendRepaymentList(repaymentRequest));
	 }
	
}
