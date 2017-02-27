package com.eshop4j.web.controller.crm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.request.ListDetailRequest;
import com.eshop4j.web.service.CfplannerSaleService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月28日 16:08:06
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cfplannerSale")
@RequestLogging("投资用户投资与收益管理")
public class CfplannerSaleController extends BaseController {

	@Resource
	private SysMsgService msgService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CfplannerSaleService cfplannerSaleService;
	
	
	/**
     * 理财师销售收益列表
     */
    @RequestMapping("cfplannerSaleList")
    @RequestLogging("理财师销售收益记录列表")
    public String cfplannerSaleList(Model model) {
    	return "cfplanner/cfplannerSaleList";
    }

    /**
     * 理财师销售收益列表数据
     */
    @RequestMapping("getCfplannerSaleList")
    @ResponseBody
	public DataTableReturn getCfplannerSaleList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfplannerSaleService.selectCfplannerSaleList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 理财师佣金明细列表
     */
    @RequestMapping("feeDetailList")
    @RequestLogging("理财师佣金明细列表")
    public String feeDetailList(Model model, @RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "cfplanner/feeDetailList";
    }

    /**
     * 理财师佣金明细列表数据
     */
    @RequestMapping("getFeeDetailList")
    @ResponseBody
	public DataTableReturn getFeeDetailList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfplannerSaleService.selectFeeDetailList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 推荐受益明细列表
     */
    @RequestMapping("allowanceDetailList")
    @RequestLogging("理财师佣金明细列表")
    public String allowanceDetailList(Model model, @RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "cfplanner/allowanceDetailList";
    }

    /**
     * 推荐受益明细列表数据
     */
    @RequestMapping("getAllowanceDetailList")
    @ResponseBody
	public DataTableReturn getAllowanceDetailList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfplannerSaleService.selectAllowanceDetailList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 活动奖励明细列表
     */
    @RequestMapping("activityRewardList")
    @RequestLogging("活动奖励明细列表")
    public String activityRewardList(Model model, @RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "cfplanner/activityRewardList";
    }

    /**
     * 活动奖励明细列表数据
     */
    @RequestMapping("getActivityRewardList")
    @ResponseBody
	public DataTableReturn getActivityRewardList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfplannerSaleService.selectActivityRewardList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 客户投资列表
     */
    @RequestMapping("customerInvestList")
    @RequestLogging("客户投资列表")
    public String customerInvestList(Model model, @RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "cfplanner/customerInvestList";
    }

    /**
     * 客户投资列表数据
     */
    @RequestMapping("getCustomerInvestList")
    @ResponseBody
	public DataTableReturn getCustomerInvestList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfplannerSaleService.selectCustomerInvestList(dataTable,req);
		return tableReturn;
	}
    
	
	
}
