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
import com.linkwee.web.model.SmCustomerDevice;
import com.linkwee.web.service.SmCustomerDeviceService;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月11日 18:50:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "sys/smcustomerdevice")
@RequestLogging("实体控制器")
public class SmCustomerDeviceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmCustomerDeviceController.class);

	@Resource
	private SmCustomerDeviceService smCustomerDeviceService;
	
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
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String smCustomerDevice(Model model) {
    	return "smcustomerdevice/smcustomerdevice-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getSmCustomerDevices(@RequestParam String  _dt_json) {
		LOGGER.debug("SmCustomerDevice list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = smCustomerDeviceService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,SmCustomerDevice> map =  (Map<String, SmCustomerDevice>) df.getData();
    	DataResult dr = new DataResult();
    	List<SmCustomerDevice> datas = new ArrayList<SmCustomerDevice>();
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
					SmCustomerDevice r = new SmCustomerDevice();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SmCustomerDevice>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SmCustomerDevice> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.smCustomerDeviceService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					SmCustomerDevice r = new SmCustomerDevice();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SmCustomerDevice>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SmCustomerDevice> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.smCustomerDeviceService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.smCustomerDeviceService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
	
}
