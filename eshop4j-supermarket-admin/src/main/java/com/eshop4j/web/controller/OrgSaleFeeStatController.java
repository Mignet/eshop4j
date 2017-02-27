package com.eshop4j.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.export.ExportSupport;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.vo.OrgSaleFeeData;
import com.eshop4j.web.request.OrgSaleFeeListRequest;
import com.eshop4j.web.service.CimOrgFeeGatherService;
import com.eshop4j.web.service.CimOrginfoService;


@Controller
@RequestMapping("orgsalefee")
public class OrgSaleFeeStatController {
	@Resource
	private CimOrgFeeGatherService cimOrgFeedetailService; 
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
	private ExportSupport exportSupport;

	@RequestMapping("init")
	public String advListPage(Model model){
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
		return "orgSaleFee/cimorgfeedetail-list";
	}

	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn newsListAjax(OrgSaleFeeListRequest req, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		Map<String, Object> params = getQueryParams(req);
		dataTableReturn = cimOrgFeedetailService.queryOrgSaleFee(params, dataTable);
		return dataTableReturn;
	}

	public Map<String, Object> getQueryParams(OrgSaleFeeListRequest req) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgNumber", req.getOrgNumber());
		if(StringUtils.isNotBlank(req.getStartDate())){
			params.put("startDate", req.getStartDate());
		}
		if(StringUtils.isNotBlank(req.getEndDate())){
			params.put("endDate", req.getEndDate());
		}
		return params;
	}
	
	@RequestMapping("/invest_amount")
	@ResponseBody
	public Object queryInvestAmount(OrgSaleFeeListRequest req) throws Exception{
		Map<String, Object> params = getQueryParams(req);
		Double investAmout = cimOrgFeedetailService.queryInvestAmount(params);
		return investAmout == null ? 0 : investAmout;
	}
	
	@RequestMapping(value = "export")
	public void export(HttpServletRequest request,HttpServletResponse response,OrgSaleFeeListRequest req) throws Exception {
		//导出默认时间段数据
		Map<String, Object> queryParams = getQueryParams(req);
		String tempFileName = "orgSaleStat/orgSaleStat.xls";
		List<OrgSaleFeeData> feeList = cimOrgFeedetailService.queryOrgSaleFee(queryParams);
		
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(feeList.size()));
		 params.put("list", feeList);
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
	
	
}
