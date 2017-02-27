package com.linkwee.web.controller.crm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestorInvestService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.util.RequestLogging;

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
@RequestMapping(value = "investorInvest")
@RequestLogging("投资用户投资与收益管理")
public class InvestorInvestController extends BaseController {

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
	private InvestorInvestService investorInvestService;
	
	
	/**
     * 用户投资与收益列表
     */
    @RequestMapping("investorInvestPage")
    @RequestLogging("客户投资记录列表")
    public String investorInvestPage(Model model) {
    	return "investor/investor-invest";
    }

    /**
     * 用户投资与收益列表数据
     */
    @RequestMapping("getInvestorInvest")
    @ResponseBody
	public DataTableReturn getInvestorInvest(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = investorInvestService.selectInvestorInvest(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 用户投资记录
     */
    @RequestMapping("investRecordPage")
    @RequestLogging("客户投资记录列表")
    public String investRecordPage(Model model,@RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "investor/investRecord-list";
    }

    /**
     * 用户投资记录数据
     */
    @RequestMapping("getInvestRecord")
    @ResponseBody
	public DataTableReturn getInvestRecord(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = investorInvestService.selectInvestRecord(dataTable,req);
		return tableReturn;
	}
    
	
	
}
