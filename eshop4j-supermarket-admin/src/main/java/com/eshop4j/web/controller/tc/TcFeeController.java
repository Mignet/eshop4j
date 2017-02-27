package com.eshop4j.web.controller.tc;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.export.ExportSupport;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.tc.fee.service.TCFeeBalanceService;
import com.eshop4j.tc.fee.service.TCFeeService;
import com.eshop4j.web.model.User;
import com.eshop4j.web.request.tc.FeeDetailRequest;
import com.eshop4j.web.request.tc.FeeRequest;
import com.eshop4j.web.response.tc.FeebalanceListResponse;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("fee")
@RequestLogging("佣金")
public class TcFeeController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(TcFeeController.class);
	
	@Autowired
	private ExportSupport exportSupport;
	
	@Autowired
	private TCFeeBalanceService feeBalanceService;
	
	@Autowired
	private TCFeeService feeService;
	
	 /**
     * 查看列表
     */
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	return "fee/fee-page";
    }
    
    @RequestMapping(value="{mobile}/recordPage")
    @RequestLogging("获取理财师佣金记录页面")
    public Object recordPage(@PathVariable("mobile")String mobile,Model model){
    	model.addAttribute("mobile", mobile);
    	return "fee/fee-record-page";
    }
    
    @RequestMapping(value="{month}/{mobile}/recordDetailPage")
    @RequestLogging("获取理财师佣金记录页面")
    public Object recordDetailPage(@PathVariable("month")String month,@PathVariable("mobile")String mobile,Model model){
    	model.addAttribute("month", month);
    	model.addAttribute("mobile", mobile);
    	return "fee/fee-record-detail-page";
    }
    
    
    
    @RequestMapping("list")
    @ResponseBody
    @RequestLogging("查看列表")
    public Object getList(@RequestParam String  _dt_json ){
    	FeeRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()))return new ResponseResult(false,"手机号码不能为空");
    	return feeBalanceService.getFeebalanceList(feeRequest);
    }
    
   
    @RequestMapping("record")
    @ResponseBody
    @RequestLogging("查看理财师佣金记录")
    public Object getRecord(@RequestParam String  _dt_json){
    	FeeDetailRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeDetailRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()))return new ResponseResult(false,"手机号码不能为空");
    	return feeBalanceService.getFeebalanceRecordByMobile(feeRequest);
    }
    
    @RequestMapping("recordDetail")
    @ResponseBody
    @RequestLogging("查看理财师佣金记录明细")
    public Object getRecordDetail(@RequestParam String  _dt_json){
    	FeeDetailRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeDetailRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()) || StringUtils.isBlank(feeRequest.getMonth()))return new ResponseResult(false,"手机号码或者月份不能为空");
    	return feeBalanceService.getFeeDetailRecord(feeRequest);
    }
    
    @RequestMapping("feeCalcu")
    @ResponseBody
    @RequestLogging("佣金计算")
    public Object feeCalcu(){
    	try {
    		return feeService.feeBalanceProcess();
		} catch (Exception e) {
			LOGGER.error("feeBalanceProcess Exception",e);
		}
    	return new ResponseResult(true,"计算完成");
    }
    
    @RequestMapping("feeDownload")
    @RequestLogging("佣金导出")
    public void feeDownload(HttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<FeebalanceListResponse> list = feeBalanceService.getFeebalanceListByMonth();
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
    	exportSupport.export(request, response, "lcs/fee/feeList.xls", datas);
    }
    
    @RequestMapping("feePay")
    @ResponseBody
    @RequestLogging("佣金发放")
    public Object feePay(HttpSession session){
    	User user = (User) session.getAttribute("userInfo"); 
    	try {
			feeBalanceService.feePay(user.getUsername());
			return new ResponseResult(true,"发放完成");
		} catch (Exception e) {
			LOGGER.error("feePay Exception",e);
		}
    	return new ResponseResult(false,"发放失败");
    }
    
}
