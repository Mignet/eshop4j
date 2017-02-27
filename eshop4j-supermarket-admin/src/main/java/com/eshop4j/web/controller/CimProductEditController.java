package com.eshop4j.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProductEdit;
import com.eshop4j.web.model.SmNewsClassify;
import com.eshop4j.web.model.news.News;
import com.eshop4j.web.request.CimProductEditDataTableRequest;
import com.eshop4j.web.request.NewsRequest;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductEditService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.base.ReturnCode;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.xoss.constant.WebConstants;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.HtmlFilterUtil;
import com.eshop4j.xoss.util.RequestLogging;
import com.eshop4j.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： CimProductEditController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月28日 13:47:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimproductedit")
@RequestLogging("CimProductEditController控制器")
public class CimProductEditController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductEditController.class);

	@Resource
	private CimProductEditService cimProductEditService;
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
	private SysConfigService systemConfigService;
	
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
	 * 产品详情编辑列表页
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/list")
	public ModelAndView cimProductEditList() throws Exception{
		ModelAndView modelAndView = new ModelAndView("cimproductedit/cimproductedit-list");
		List<CimOrginfo> orgList = cimOrginfoService.selectListByCondition(null);
		modelAndView.addObject("orgList",orgList);
		return modelAndView;
	}

	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn cimProductEditListAjax(@RequestBody CimProductEditDataTableRequest cimProductEditDataTableRequest) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		cimProductEditDataTableRequest.initOrdersOriginal();
		dataTableReturn = cimProductEditService.findProductEditList(cimProductEditDataTableRequest);
		return dataTableReturn;
	}

	/**
	 * 转新增页
	 * @return
	 */
	@RequestMapping("tosave")
	public ModelAndView toSave(Long id){
		ModelAndView modelAndView = new ModelAndView("cimproductedit/cimproducteditDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		if(null !=id && id>0){		
			CimProductEdit cimProductEdit = cimProductEditService.selectById(id);
			modelAndView.addObject("cimProductEdit",cimProductEdit);
		}
		List<CimOrginfo> orgList = cimOrginfoService.selectListByCondition(null);
		modelAndView.addObject("orgList",orgList);
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(CimProductEdit cimProductEdit,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		Integer returnCode =null;
		String message = "添加成功";
		String prefix = "<section style=\"padding:0 20px;\">";
		String endfix = "</section>";
		if(!cimProductEdit.getProductDesc().startsWith(prefix)){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(prefix);
			stringBuilder.append(cimProductEdit.getProductDesc());
			stringBuilder.append(endfix);
			cimProductEdit.setProductDesc(stringBuilder.toString());
		}
		if(cimProductEdit.getId()!=null && cimProductEdit.getId()>0){
			message = "更新成功";
			returnCode = cimProductEditService.update(cimProductEdit);
		}
		else{
			returnCode = cimProductEditService.insert(cimProductEdit);
		}
		
		if(returnCode > 0){
			result = new ResponseResult(true, message);
			logsb.append("cimProductEditService SaveCimProductEdit success");
			LOGGER.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "操作失败");
		}
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object del(@RequestParam String id ){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id)){			
			Integer returnCode = cimProductEditService.delete(Long.parseLong(id));
			if(returnCode > 0){
				result = new ResponseResult(true, "删除成功");
				logsb.append("cimProductEditService DeleteCimProductEdit success");
				LOGGER.info(logsb.toString());
			}else{
				result = new ResponseResult(false, "删除失败");			
			}
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
		
    /**
	 *
	 * @return
    */
	@RequestMapping("/ueditor_config")
	@ResponseBody
	public Object ueditorConfig(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("imageActionName","zimg");
		result.put("imageFieldName","userfile");
		result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		return result;
	}
}
