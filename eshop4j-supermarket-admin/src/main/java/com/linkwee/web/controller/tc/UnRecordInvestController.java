package com.linkwee.web.controller.tc;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.User;
import com.linkwee.web.request.tc.AuditUnrecordInvestRequest;
import com.linkwee.web.request.tc.UnrecordInvestRequest;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

@Controller
@RequestMapping("unRecordInvest")
@RequestLogging("理财师报单")
public class UnRecordInvestController {
	
	
	
	@Autowired
	private CimProductUnrecordInvestService unrecordInvestService;
	
	
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	/**
    * 查看列表
    */
   @RequestMapping("initPage")
   public String initPage(Model model) {
   		return "cfplanner/unRecordInvest-page";
   }
   
   @RequestMapping("updateStatusPage")
   public String updateStatusPage(Model model) {
   		return "cfplanner/unRecordInvest-update-page";
   }
   
   @RequestMapping("list")
   @ResponseBody
   @RequestLogging("查看列表")
   public Object getList(@RequestParam String  _dt_json ){
	   	UnrecordInvestRequest req = JsonUtils.fromJsonToObject(_dt_json, UnrecordInvestRequest.class);
   		return unrecordInvestService.getUnrecordInvestList(req);
   }
   
   @RequestMapping("updateStatus")
   @ResponseBody
   @RequestLogging("审核")
   public Object updateStatus(@Valid AuditUnrecordInvestRequest req,BindingResult validResult,HttpSession session) throws Exception{
	    if(ResponseUtil.existsParamsError(validResult)) {
  	    	return ResponseUtil.getErrorParams(validResult);
        }
		try {

			User user = (User) session.getAttribute("userInfo");
			return unrecordInvestService.audit(req, user.getUsername());
		} catch (Exception e) {
		}
		return new ResponseResult(false, "更新审核状态失败,请重试");
   }
   
}
