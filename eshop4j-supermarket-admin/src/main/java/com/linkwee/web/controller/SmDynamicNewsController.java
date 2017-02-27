package com.linkwee.web.controller;

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
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.web.enums.DynamicNewsTypeEnum;
import com.linkwee.web.enums.NewsTypeEnum;
import com.linkwee.web.model.SmDynamicNews;
import com.linkwee.web.model.news.News;
import com.linkwee.web.request.DynamicNewsRequest;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.SmDynamicNewsService;
import com.linkwee.web.service.SysConfigService;
import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.HtmlFilterUtil;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： SmDynamicNewsController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月18日 19:01:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "sm/smdynamicnews")
@RequestLogging("SmDynamicNewsController控制器")
public class SmDynamicNewsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmDynamicNewsController.class);

	@Resource
	private SmDynamicNewsService smDynamicNewsService;
	
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
     * 查看列表
     */
    @RequestMapping("/list")
    @RequestLogging("查看列表页面")
    public ModelAndView smDynamicNews(Model model) {
    	ModelAndView modelAndView = new ModelAndView("smdynamicnews/smdynamicnews-list");
		modelAndView.addObject("dynamicNewsTypeList",DynamicNewsTypeEnum.values());
		return modelAndView;
    }

    @RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn newsListAjax(DynamicNewsRequest dynamicNewsRequest, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(dynamicNewsRequest==null){
			dynamicNewsRequest = new DynamicNewsRequest();
		}
		if(dynamicNewsRequest.getAppType() == null || dynamicNewsRequest.getAppType()<=0){
			dynamicNewsRequest.setAppType(2);
		}
		dataTableReturn = smDynamicNewsService.findNewsList(dynamicNewsRequest,dataTable);
		return dataTableReturn;
	}
  
    @RequestMapping("tosave")
	public ModelAndView toSave(Integer id){
		ModelAndView modelAndView = new ModelAndView("smdynamicnews/smdynamicnewsDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		if(null !=id && id>0){
			SmDynamicNews smDynamicNews = smDynamicNewsService.selectById(Long.parseLong(String.valueOf(id)));
			smDynamicNews.setTitle(HtmlFilterUtil.filterHtml(smDynamicNews.getTitle()));
			smDynamicNews.setImg(systemConfigService.getImageUrl(smDynamicNews.getImg()));
			modelAndView.addObject("smDynamicNews",smDynamicNews);
		}
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
    
    @RequestMapping("save")
	@ResponseBody
	public Object save(@Valid DynamicNewsRequest dynamicNewsRequest,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		if(dynamicNewsRequest.getAppType() == null || dynamicNewsRequest.getAppType()<=0){
			dynamicNewsRequest.setAppType(2);
		}
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		ReturnCode returnCode =null;
		String message = "添加成功";
		dynamicNewsRequest.setTitle(HtmlFilterUtil.filterHtml(dynamicNewsRequest.getTitle()));
		if(dynamicNewsRequest.getId()!=null && dynamicNewsRequest.getId()>0){
			message = "更新成功";
			returnCode = smDynamicNewsService.updateDynamicNews(convertToDynamicNews(dynamicNewsRequest));
		}
		else{
			returnCode = smDynamicNewsService.SaveDynamicNews(convertToDynamicNews(dynamicNewsRequest));
		}
			

		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, message);
			logsb.append("newsService SaveNews success");
			LOGGER.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "操作失败");
		}
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
    
    private SmDynamicNews convertToDynamicNews(DynamicNewsRequest dynamicNewsRequest){
    	SmDynamicNews ret = new SmDynamicNews();
		if(dynamicNewsRequest!=null){
			ret.setAppType(dynamicNewsRequest.getAppType());		
			String prefix = "<section style=\"padding:0 20px;\">";
			String endfix = "</section>";
			String currentUser = JSON.toJSONString(SecurityUtils.getSubject().getPrincipal());
			ret.setCreator(currentUser);
			ret.setTitle(dynamicNewsRequest.getTitle());
			ret.setValidBegin(dynamicNewsRequest.getValidBegin());
			ret.setValidEnd(dynamicNewsRequest.getValidEnd());
			ret.setShowIndex(dynamicNewsRequest.getShowIndex());
			ret.setStatus(dynamicNewsRequest.getStatus());
			ret.setTypeCode(dynamicNewsRequest.getTypeCode());
			for(DynamicNewsTypeEnum item :DynamicNewsTypeEnum.values()){
				if(String.valueOf(item.getKey()).equals(dynamicNewsRequest.getTypeCode())){
					ret.setTypeName(item.getValue());
					break;
				}
			}
	
			if(dynamicNewsRequest.getContent()!=null && !dynamicNewsRequest.getContent().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(dynamicNewsRequest.getContent());
				stringBuilder.append(endfix);
				dynamicNewsRequest.setContent(stringBuilder.toString());
			}
			
			ret.setContent(dynamicNewsRequest.getContent());
			if(dynamicNewsRequest.getImg() != null && dynamicNewsRequest.getImg().indexOf("http") != -1){
	        	ret.setImg(dynamicNewsRequest.getImg().substring(dynamicNewsRequest.getImg().lastIndexOf("/")+1));
	        }else {
	        	ret.setImg(dynamicNewsRequest.getImg());
			}
			ret.setCrtTime(new Date());
			if(dynamicNewsRequest.getId()!=null && dynamicNewsRequest.getId()>0){
				ret.setModifiyTime(new Date());
			}
			ret.setId(dynamicNewsRequest.getId());
			ret.setSummary(dynamicNewsRequest.getSummary());			
			ret.setLinkUrl(dynamicNewsRequest.getLinkUrl());

		}
		return ret;
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
			int returnCode = smDynamicNewsService.delete(Long.parseLong(id));
			if(returnCode > 0){
				result = new ResponseResult(true, "删除成功");
				logsb.append("smDynamicNewsService DeleteDynamicNews success");
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
	
}
