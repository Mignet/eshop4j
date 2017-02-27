package com.eshop4j.web.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProductCate;
import com.eshop4j.web.request.ProductCateDataRequest;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductCateService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimproductcate")
@RequestLogging("实体控制器")
public class CimProductCateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductCateController.class);

	@Resource
	private CimProductCateService cimProductCateService;
	@Resource
	private SysConfigService systemConfigService; 
	@Resource
	private CimOrginfoService cimOrginfoService;
	
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
    public String cimProductCate(Model model) {	
    	/**
    	 * 查询所有的合作中机构
    	 */
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
    	model.addAttribute("img_server",getImgServerUrl());
    	
    	return "cimproductcate/cimproductcate-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimProductCates(@RequestBody ProductCateDataRequest productCateDataRequest) {
    	LOGGER.info("管理后台-查看产品分类列表数据,productCateDataRequest={}",JSONObject.toJSONString(productCateDataRequest));
		DataTableReturn tableReturn = cimProductCateService.getCimProductCates(productCateDataRequest);		
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,CimProductCate> map =  (Map<String, CimProductCate>) df.getData();
    	DataResult dr = new DataResult();
    	List<CimProductCate> datas = new ArrayList<CimProductCate>();
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
					CimProductCate r = new CimProductCate();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimProductCate>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimProductCate> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.cimProductCateService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimProductCate r = new CimProductCate();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimProductCate>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimProductCate> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.cimProductCateService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.cimProductCateService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    /**
     * 添加产品分类
     * @param cimProductCate
     * @return
     */
    @RequestMapping(value="/addProductcate", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("添加产品分类")
	public String addProductcate(CimProductCate cimProductCate) {
    	LOGGER.info("管理后台-添加产品分类,cimProductCate={}",JSONObject.toJSONString(cimProductCate));
    	if(cimProductCate.getCateId() != null){
    		cimProductCate.setCateId(null);
    	}
    	cimProductCate.setModifyTime(new Date());
    	try {
    		cimProductCateService.insert(cimProductCate);
    		return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
    
	/**
	 * 编辑产品分类
	 * @param cimProductCate
	 * @return
	 */
    @RequestMapping(value="/updateProductcate", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("编辑产品分类")
	public String updateProductcate(CimProductCate cimProductCate) {
    	LOGGER.info("管理后台-编辑产品分类,cimProductCate={}",JSONObject.toJSONString(cimProductCate));
    	if(cimProductCate.getCateId() != null){		
    		cimProductCate.setModifyTime(new Date());
    		try {
    			cimProductCateService.update(cimProductCate);
    			return "success";
    		} catch (Exception e) {
    			return "fail";
    		}
    	} else {
    		return "fail";
    	}
	}
    
	/**
	 * 查询产品分类
	 * @param cimProductCate
	 * @return
	 */
    @RequestMapping(value="/queryProductCate", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查询产品分类")
	public CimProductCate queryProductCate(Integer cateId) {
    	LOGGER.info("管理后台-查询产品分类,cateId={}",cateId);
    	CimProductCate cimProductCate = new CimProductCate();
    	if(cateId != null){
    		cimProductCate.setCateId(cateId);
    	    cimProductCate = cimProductCateService.selectOne(cimProductCate);
    	}
    	return cimProductCate;
	}
    
	private  String getImgServerUrl(){
		return systemConfigService.getValuesByKey("img_server_url");
	}
}
