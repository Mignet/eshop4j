package com.eshop4j.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.web.model.mc.SysNotice;
import com.eshop4j.web.model.mc.SysPushArtificialQueue;
import com.eshop4j.web.request.MsgRequest;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.web.service.SysNoticeService;
import com.eshop4j.web.service.SysPushArtificialQueueService;
import com.eshop4j.xoss.constant.WebConstants;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.rbac.PermissionSign;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016年06月03日 17:34:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cms/msg")
@RequestLogging("公告")
public class NoticeManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeManageController.class);

	@Resource
	private SysNoticeService sysNoticeService;
	@Resource
	private SysConfigService systemConfigService; 
	@Resource 
	private PushMessageHelper pushMessageHelper;
	@Resource
	private SysPushArtificialQueueService sysPushArtificialQueueService;
	
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
    @RequiresPermissions(value = PermissionSign.CMS_MSG_ALL)
    public ModelAndView msg(Model model) {
    	ModelAndView modelAndView = new ModelAndView("msg/msg-list");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestLogging("公告列表查询")
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getMsgs(@RequestParam String  _dt_json,MsgRequest msg) {
		LOGGER.debug("Msg list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initFOrdersNoPrefix();
		Map<String,Object> condit = Maps.newHashMap();
		if(msg.getAppType() != null){
			condit.put("appType", msg.getAppType());
		}
		DataTableReturn tableReturn = sysNoticeService.selectByDatatables(dataTable,condit);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("公告编辑")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,SysNotice> map =  (Map<String, SysNotice>) df.getData();
    	DataResult dr = new DataResult();
    	List<SysNotice> datas = new ArrayList<SysNotice>();
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
					SysNotice r = new SysNotice();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysNotice>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysNotice> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }   
			        
			        /**
			         * 推行公告发布通知
			         */
			        //if( r.getAppType().intValue() == 1){
			        	SysPushArtificialQueue model = new SysPushArtificialQueue();
			        	model.setAppType(r.getAppType());
			        	model.setContent(r.getMessage());
			        	//model.setLink(getPushLinkUrl(r)); insert sysnotice 后再 设置链接的msgID
			        	model.setStatus(0);//待发送
			        	model.setStartType(1);//定时
			        	model.setStartTime(r.getStartTime());
			        	model.setSendObjectType(0);//全部
			        	model.setSendType(0);//公告区分系统推送
			        	model.setCrtTime(new Date());
			        	sysPushArtificialQueueService.releaseNotice(r,model) ;	   
			        /*}else{
			        	sysNoticeService.insert(r);
			        }*/
			        
			        
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					SysNotice r = new SysNotice();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysNotice>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysNotice> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
			        sysNoticeService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					sysNoticeService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
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
