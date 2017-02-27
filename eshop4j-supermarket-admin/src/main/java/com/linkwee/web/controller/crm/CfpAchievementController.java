package com.linkwee.web.controller.crm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.crm.CfpAchievementResp;
import com.linkwee.web.model.crm.investmentRateResp;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.response.crm.investmentRateResponse;
import com.linkwee.web.service.CfpAchievementService;
import com.linkwee.web.service.CfplannerManagerService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestorManagerService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("cfpAchievement")
@RequestLogging("理财师业绩")
public class CfpAchievementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CfpAchievementController.class);

	@Resource
	private ExportSupport exportSupport;

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
	private CfplannerManagerService cfplannerManagerService;
	
	@Resource
	private CfpAchievementService cfpAchievementService;
	@Resource
	private InvestorManagerService investorManagerService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	
	/**
     * 理财师业绩
     */
    @RequestMapping("cfpAchievementPage")
    @RequestLogging("理财师业绩")
    public String cfpAchievementPage(Model model) {
    	return "cfplanner/cfpAchievement-list";
    }

    /**
     * 理财师业绩数据
     */
    @RequestMapping("getCfpAchievement")
    @ResponseBody
	public DataTableReturn getCfpAchievement(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfpAchievementService.selectCfpAchievement(dataTable,req);
		return tableReturn;
	}
    
    
    @RequestMapping("exportCfpAchievement")
	@RequestLogging("导出理财师业绩")
	public void vistedExport(HttpServletRequest request, HttpServletResponse response,ListDetailRequest req) {
		String tempFileName = "lcs/cfpAchievement.xls";
		List<CfpAchievementResp> list = cfpAchievementService.selectCfpAchievement(req);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("size", Long.valueOf(list.size()));
		params.put("list", list);
		exportSupport.export( request,  response,  tempFileName,  params);
	}
    
    
	
	/**
     * 理财师分布城市数据
     */
    @RequestMapping("getCfpAchiDataView")
	@ResponseBody
	@RequestLogging("理财师分布城市数据")
	public Object getCfpAchiDataView(String startDate,String endDate){
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data= new HashMap<String, Object>();
		List<Map<String, Object>> cfpAreaData = cfpAchievementService.queryCfpAreaData();
		data.put("cfpAreaData", cfpAreaData);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 理财师业绩数据分析页面
	 * @param model
	 * @return
	 */
	@RequestMapping("cfpAchiDataViewPage")
	@RequestLogging("理财师业绩数据分析页面")
	public String cfpAchiDataViewPage(Model model){
		/*String startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);*/
		return "cfplanner/cfpAchiDataView";
	}
	
	/**
     * 理财师客户数量数据
     */
    @RequestMapping("getCfpCustomerCountData")
	@ResponseBody
	@RequestLogging("理财师客户数量数据")
	public Object getCfpCustomerCountData(String startDate,String endDate){
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data= new HashMap<String, Object>();
		List<Map<String, Object>> cfpCustomerCountData = cfpAchievementService.queryCfpCustomerCountData();
		data.put("cfpCustomerCountData", cfpCustomerCountData);
		map.put("data", data);
		return map;
	}
    
    /**
     * 理财师年化业绩数据
     */
    @RequestMapping("cfpYearAchiData")
	@ResponseBody
	@RequestLogging("理财师年化业绩数据")
	public Object cfpYearAchiData(String startDate,String endDate){
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("startDate", startDate);
		paramsMap.put("endDate", endDate);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data= new HashMap<String, Object>();
		List<Map<String, Object>> cfpYearAchiData = cfpAchievementService.queryCfpYearAchiData(paramsMap);
		data.put("cfpYearAchiData", cfpYearAchiData);
		map.put("data", data);
		return map;
	}
    

    /**
     * 理财师客户数量列表
     */
    @RequestMapping("getCfpOfCustomerCount")
    @ResponseBody
	public DataTableReturn getCfpOfCustomerCount(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfpAchievementService.queryCfpOfCustomerCount(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 根据范围分类的理财师业绩列表
     */
    @RequestMapping("getCfpAchiValueList")
    @ResponseBody
	public DataTableReturn getCfpAchiValueList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = cfpAchievementService.queryCfpAchiValueList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 用户投资率
     */
    @RequestMapping("investmentRatePage")
	@RequestLogging("用户投资率")
	public String investmentRatePage(Model model){
		/*String startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);*/
    	List<CimOrginfoBindSelect> orgList = cimOrginfoService.queryAllOrgByStatus(1);
    	model.addAttribute("orgList", orgList);
		return "investor/investmentRate";
	}
    
    
    @RequestMapping("getInvestmentRate")
   	@ResponseBody
   	@RequestLogging("用户投资率数据")
   	public Object getInvestmentRate(String platfrom,String startTimeForSearch,String endTimeForSearch){
    	Map<String, String> query = new HashMap<String, String>();
    	query.put("platfrom", platfrom);
    	query.put("startTimeForSearch", startTimeForSearch);
    	query.put("endTimeForSearch", endTimeForSearch);
    	investmentRateResp re = cfpAchievementService.queryInvestmentRate(query);
    	if(re.getInvestingCount() == 0) {
    		re.setInvestingRate(0.0);
    	} else {
    		re.setInvestingRate((double)re.getInvestingCount()/re.getInvestmentCount() * 100);
    	}
    	if(re.getInvestmentCount() == 0) {
    		re.setInvestmentRate(0.0);
    	} else {
    		if(platfrom != null && !"".equals(platfrom)) {
    			re.setInvestmentRate((double)re.getInvestmentCount()/re.getRegisterCount() * 100);
    		} else {
    			re.setInvestmentRate((double)re.getInvestmentCount()/re.getLoginCount() * 100);
    		}
    	}
    	if(re.getReInvestmentCount() == 0) {
    		re.setReInvestmentRate(0.0);
    	} else {
    		re.setReInvestmentRate((double)re.getReInvestmentCount()/re.getInvestmentCount() * 100);
    	}
    	investmentRateResponse rlt = new investmentRateResponse(re);
   		return rlt;
   	}
	
	
}
