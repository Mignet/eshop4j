package com.linkwee.web.controller.acc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.result.Result;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.service.AcWithdrawApplyService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： AcWithdrawApplyController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/acwithdrawapply")
@RequestLogging("提现记录")
public class AcWithdrawApplyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcWithdrawApplyController.class);

	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	/**
	 * 提现单个审批(acwithdrawapply.approveOnePay)
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/approveOnePay")
	@ResponseBody
	public Result approveOnePay(HttpServletRequest reqeust,@RequestParam String ids) {
		//前端传回审批记录id
		String[] idslit = ids.split(",");
		List<String> listStr = new ArrayList<String>();
		for(int i=0;i<idslit.length;i++){
			listStr.add(idslit[i]);
		}
		
		StringBuilder logsb = new StringBuilder();
		logsb.append("approveOnePay|head=");
		Map<String, String> resultStr = null;
		try {
			resultStr = acWithdrawApplyService.approveOnePay(listStr);
		} catch(Exception e){
			return new Result(false,500,"提现审批异常");
		} 
		if(resultStr!=null&&resultStr.get("code")!=null&&!"00".equals(resultStr.get("code"))){
			return new Result(false,500,resultStr.get("code"));
			
		}else if(resultStr!=null&&resultStr.get("code")==null){
			return new Result(false,500,"提现审批异常(code值为空)");
		}
		return new Result(true,200,"审核成功");
	}
	
	/**
	 * 提现批量审批(acwithdrawapply.approveWithdraw)
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/approveWithdraw")
	@ResponseBody
	public Result approveWithdraw(HttpServletRequest reqeust,@RequestParam String ids) {
		//前端传回审批记录id
		String[] idslit = ids.split(",");
		List<String> listStr = new ArrayList<String>();
		for(int i=0;i<idslit.length;i++){
			listStr.add(idslit[i]);
		}
		
		StringBuilder logsb = new StringBuilder();
		logsb.append("approveWithdraw|head=");
		Map<String, String> resultStr = null;
		try {
			resultStr = acWithdrawApplyService.approveWithdraw(listStr);
		} catch(Exception e){
			return new Result(false,500,"提现审批异常");
		} 
		if(resultStr!=null&&resultStr.get("code")!=null&&!"00".equals(resultStr.get("code"))){
			return new Result(false,500,resultStr.get("code"));
			
		}else if(resultStr!=null&&resultStr.get("code")==null){
			return new Result(false,500,"提现审批异常(code值为空)");
		}
		return new Result(true,200,"审核成功");
	}
	
	
	
	/**
     * 查看列表
     */
    @RequestMapping(value="/detaillist",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String queryAcWithdrawApply(Model model) {
    	return "acwithdrawapply/acwithdrawapplydetail-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/detaillist", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn queryAcWithdrawApplys(@RequestParam String  _dt_json) {
		LOGGER.debug("AcWithdrawApply list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acWithdrawApplyService.selectDetailByDatatables(dataTable);
		return tableReturn;
	}

	/**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String acWithdrawApply(Model model) {
    	return "acwithdrawapply/acwithdrawapply-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getAcWithdrawApplys(@RequestParam String  _dt_json) {
		LOGGER.debug("AcWithdrawApply list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acWithdrawApplyService.selectByDatatables(dataTable);
		return tableReturn;
	}
    /*********************************单个审核接口********************************************/
    /**
     * 查看列表
     */
    @RequestMapping(value="/oneDetaillist",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String queryOneAcWithdrawApply(Model model) {
    	return "acwithdrawapply/oneAcwithdrawapplydetail-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/oneDetaillist", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn queryOneAcWithdrawApplys(@RequestParam String  _dt_json) {
		LOGGER.debug("AcWithdrawApply list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acWithdrawApplyService.selectDetailByDatatables(dataTable);
		return tableReturn;
	}

	/**
     * 查看列表
     */
    @RequestMapping(value="/onelist",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String oneAcWithdrawApply(Model model) {
    	return "acwithdrawapply/oneAcwithdrawapply-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/onelist", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getOneAcWithdrawApplys(@RequestParam String  _dt_json) {
		LOGGER.debug("AcWithdrawApply list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acWithdrawApplyService.selectByDatatables(dataTable);
		return tableReturn;
	}

	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}
