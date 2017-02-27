package com.linkwee.web.controller;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.result.Result;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysGrayRelease;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： SysGrayReleaseController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "sys/gray")
@RequestLogging("SysGrayReleaseController控制器")
public class SysGrayReleaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysGrayReleaseController.class);

	@Resource
	private SysGrayReleaseService sysGrayReleaseService;
	
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
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
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String sysGrayRelease(Model model) {
    	return "gray/gray-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getSysGrayReleases(@RequestParam String  _dt_json) {
		LOGGER.debug("SysGrayRelease list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = sysGrayReleaseService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,SysGrayRelease> map =  (Map<String, SysGrayRelease>) df.getData();
    	DataResult dr = new DataResult();
    	List<SysGrayRelease> datas = new ArrayList<SysGrayRelease>();
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
					SysGrayRelease r = new SysGrayRelease();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysGrayRelease>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysGrayRelease> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
			        //判断给手机是否存在
			    	CrmUserInfo user = crmUserInfoService.selectCrmUserInfoByMobile(r.getMobile());
					r.setUserId(user.getUserId());
					r.setUserName(user.getUserName());
					r.setStatus(0);
					this.sysGrayReleaseService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					SysGrayRelease r = new SysGrayRelease();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysGrayRelease>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysGrayRelease> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.sysGrayReleaseService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.sysGrayReleaseService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    @RequestMapping(value="/checkMobile", method = RequestMethod.POST)
    @ResponseBody
	public Result checkMobile(@RequestParam String  mobile) {
    	//判断是否已经是灰度用户
    	SysGrayRelease isGray = sysGrayReleaseService.selectByMobile(mobile);
    	if(isGray!=null){
    		LOGGER.info("【{}】灰度用户已经存在!",mobile);
			return new Result(false,500,String.format("【%s】灰度用户已经存在!",mobile));
    	}
    	//判断给手机是否存在
    	CrmUserInfo user = crmUserInfoService.selectCrmUserInfoByMobile(mobile);
		if(user == null){
			LOGGER.info("【{}】投资客户信息不存在!",mobile);
			return new Result(false,500,String.format("【%s】投资客户信息不存在!",mobile));
		}
		return new Result(true,200,"SUCCESS");
	}
	
}
