package com.linkwee.web.controller.acc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.result.Result;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.request.acc.AddBankCardRequest;
import com.linkwee.web.response.acc.AcBankCodeResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： AcAccountBindController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月12日 15:18:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/acaccountbind")
@RequestLogging("AcAccountBindCardController控制器")
public class AcAccountBindCardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountBindCardController.class);

	@Resource
	private AcAccountBindService acAccountBindService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private AcBankCardInfoService acBankCardInfoService;
	
	 /**
     * 跳到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",method=RequestMethod.GET)
    @RequestLogging("跳到绑卡编辑页面")
    public String toEditView(String mobile, ModelMap model) {
    	LOGGER.debug("绑卡编辑请求参数 mobile = {}" ,mobile);
    	CrmUserInfo crm = crmUserInfoService.selectCrmUserInfoByMobile(mobile);
    	AcAccountBind ac = acAccountBindService.selectAccountByUserId(crm.getUserId());
    	List<AcBankCodeResponse> bankList = acAccountBindService.queryAllBank();
		model.addAttribute("mobile",mobile);
		model.addAttribute("bankCard",ac.getBankCard());
		model.addAttribute("reserveMobile",ac.getReserveMobile());
		model.addAttribute("idCard",ac.getIdCard());
		model.addAttribute("userName",ac.getUserName());
		model.addAttribute("bankName",ac.getBankName());
		model.addAttribute("bankList",bankList);
    	return "acaccountbind/acaccountbind-edit";
    }
    
    /**
     * 用户绑卡
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("用户绑卡")
    public Object updateAccount(@RequestBody AddBankCardRequest req) {
    		LOGGER.debug("用户绑卡请求参数 AcAccountBindRequest = {}",JSON.toJSONString(req));
       		long start = System.currentTimeMillis();
       		StringBuilder logsb = new StringBuilder();
       		ResponseResult result = null;
       		try {
       			CrmUserInfo crm = crmUserInfoService.selectCrmUserInfoByMobile(req.getMobile());
       			AcBankCodeResponse bank = acAccountBindService.queryBankByBankId(req.getBankId());
       			req.setUserId(crm.getUserId());
       			req.setUserType(2);
       			req.setBankCode(bank.getBankCode());
       			req.setBankName(bank.getBankName());
       			req.setMobile(req.getReserveMobile());
       			acBankCardInfoService.insertBankCard(req);
       			result = new ResponseResult(true, "用户绑卡成功！");
       			logsb.append("用户绑卡 success");
       		} catch (Exception e) {
       			logsb.append("用户绑卡 fail");
       			LOGGER.error("用户绑卡失败！", e);
       			result = new ResponseResult(false, "用户绑卡失败！");
       		}
       		long end = System.currentTimeMillis();
       		logsb.append("用户绑卡总耗时 |totaltime=").append(end - start).append("ms");
       		LOGGER.info(logsb.toString());
       		return result;
    		
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
	
    /**
     * 用户解绑银行卡
     */
    @RequestMapping(value="/unbund",   method=RequestMethod.POST)
    @ResponseBody
    @RequestLogging("用户解绑银行卡")
    public Result acAccountUnbund(@RequestParam String mobile) {
    	try {
    		CrmUserInfo crm = crmUserInfoService.selectCrmUserInfoByMobile(mobile);
    		acAccountBindService.acAccountUnbund(crm.getUserId());
		} catch (Exception e) {
			LOGGER.info("【{}】解绑失败!",mobile);
			return new Result(false,500,"解绑失败");
		}
    	return new Result(true,200,"解绑成功");
    }

    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String acAccountBind(Model model) {
    	return "acaccountbind/acaccountbind-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getAcAccountBinds(@RequestParam String  _dt_json) {
		LOGGER.debug("AcAccountBind list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acAccountBindService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,AcAccountBind> map =  (Map<String, AcAccountBind>) df.getData();
    	DataResult dr = new DataResult();
    	List<AcAccountBind> datas = new ArrayList<AcAccountBind>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();   
        //下面用到bean属性copy，需要对日期进行转换
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, java.util.Date.class); 
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					AcAccountBind r = new AcAccountBind();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<AcAccountBind>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<AcAccountBind> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.acAccountBindService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					AcAccountBind r = new AcAccountBind();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<AcAccountBind>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<AcAccountBind> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.acAccountBindService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.acAccountBindService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
	
}
