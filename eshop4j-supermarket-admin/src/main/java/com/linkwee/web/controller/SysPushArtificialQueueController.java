package com.linkwee.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.model.RedpacketImportModel;
import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.User;
import com.linkwee.web.model.mc.SysPushArtificialQueue;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysPushArtificialQueueService;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.StringUtils;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： SysPushArtificialQueueController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月10日 15:50:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/cim/syspushartificialqueue")
@RequestLogging("系统推送管理")
public class SysPushArtificialQueueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysPushArtificialQueueController.class);

	@Resource
	private SysPushArtificialQueueService sysPushArtificialQueueService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private CrmUserInfoService userInfoService;
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
    public String sysPushArtificialQueue(Model model) {
    	return "syspushartificialqueue/syspushartificialqueue-list";
    }
    /**
     * 新增推送
     * @param model
     * @return
     */
    @RequestMapping(value="/addPage", method=RequestMethod.GET)
    public String sysPushAddPage(Model model) {
    	return "syspushartificialqueue/sysPushAddPage";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getSysPushArtificialQueues(@RequestParam String  _dt_json) {
		LOGGER.debug("SysPushArtificialQueue list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = sysPushArtificialQueueService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows,HttpSession session) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,SysPushArtificialQueue> map =  (Map<String, SysPushArtificialQueue>) df.getData();
    	DataResult dr = new DataResult();
    	List<SysPushArtificialQueue> datas = new ArrayList<SysPushArtificialQueue>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();   
        //下面用到bean属性copy，需要对日期进行转换
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, java.util.Date.class); 
        User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					SysPushArtificialQueue r = new SysPushArtificialQueue();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysPushArtificialQueue>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysPushArtificialQueue> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }
			        r.setStatus(0);//待发送
			        r.setStartType(1);//定时
			        r.setSendObjectType(0);//全部
			        r.setSendType(1);//系统推送
			        r.setRemark(user.getUsername());//操作人
					this.sysPushArtificialQueueService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					SysPushArtificialQueue r = new SysPushArtificialQueue();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<SysPushArtificialQueue>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<SysPushArtificialQueue> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.sysPushArtificialQueueService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.sysPushArtificialQueueService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
   
    @RequestMapping(value = "/abandoned")
   	@ResponseBody
   	@RequestLogging("撤销推送") 
   	public Object deleteOrgFee(Integer id,HttpSession session){
    	LOGGER.debug("撤销推送记录 id = {}",id);
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//撤销推送记录
   			if(null != id && id > 0){
   				List<SysPushArtificialQueue> list = Lists.newArrayList();
   				SysPushArtificialQueue item = new SysPushArtificialQueue();
   				item.setId(id);
   				item.setStatus(2);//已撤销
   				list.add(item);
   				int rows = sysPushArtificialQueueService.renewBatch(list);
   				if(rows > 0){
   					result = new ResponseResult(true, "撤销推送记录成功！");
   					logsb.append("撤销推送记录成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("撤销推送记录失败！ fail");
   			LOGGER.error("撤销推送记录失败！", e);
   			result = new ResponseResult(false, "撤销推送记录失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("撤销推送记录总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    /**
     * 
     */
    @RequestMapping(value = "/delpush")
   	@ResponseBody
   	@RequestLogging("删除推送") 
   	public Object delpush(String id,HttpSession session){
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			//撤销推送记录
   			if(StringUtils.isNotBlank(id)){
   				int rows = sysPushArtificialQueueService.delete(Long.parseLong(id));
   				if(rows > 0){
   					result = new ResponseResult(true, "删除推送记录成功！");
   					logsb.append("删除推送记录成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除推送记录失败！ fail");
   			LOGGER.error("删除推送记录失败！", e);
   			result = new ResponseResult(false, "删除推送记录失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除推送记录总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}
    /**
     * 详情
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/pushDetail")
   	@RequestLogging("推送详情") 
   	public String pushDetail(String id,Model model){
   		try {
   			//撤销推送记录
   			if(StringUtils.isNotBlank(id)){
   				SysPushArtificialQueue item = sysPushArtificialQueueService.selectById(Long.parseLong(id));
   				if(item != null){
   					if(item.getMobiles() != null){
   						item.setMobileList(JsonUtils.fromJsonToObject(item.getMobiles(), List.class));
   					}
   					model.addAttribute("pushItem", item);
   				}
   			}
   		} catch (Exception e) {
   			LOGGER.error("推送查询失败！", e);   			
   		}
   		return "syspushartificialqueue/sysPushAddPage";
   	}
    /**
	 * 下载导入模板
	 * @param response
	 * @param request
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "downloadImportTemplate")
	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
		LOGGER.info("下载系统推送模板");
		// 下载本地文件
		String fileName = "sysPushList.xls"; // 文件的默认保存名letter
		// 读到流中
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xls/template/sysPushList.xls");
		InputStream inStream=null;
		OutputStream outStream=null;
		try {
			inStream = new FileInputStream(path);// 文件的存放路径
			response.reset();
			/*设置头信息以及编码*/
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			/*设置下载时的文件名*/
			response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			outStream=response.getOutputStream();
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				outStream.write(b, 0, len);
		} catch (IOException e) {
			LOGGER.error("下载导入模板出现异常",e);
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}
				if(outStream!=null){
					outStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("下载导入模板关闭输入流时出现异常",e);
			}
		}
	}
	
	/**
	 * 新增推送
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping(value="addSysPush")
    @ResponseBody
    @RequestLogging("新增系统推送")
	public Object sysPushAdd(HttpServletRequest request,HttpSession session) {   
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            Integer appType = Integer.valueOf(multipartRequest.getParameter("appType"));
     		String content = multipartRequest.getParameter("content");
     		String link = multipartRequest.getParameter("link");
     		Integer startType = Integer.valueOf(multipartRequest.getParameter("startType"));
     		String startTime = multipartRequest.getParameter("startTime");
     		Integer sendObjectType = Integer.valueOf(multipartRequest.getParameter("sendObjectType"));
     		Integer id = null;
     		if(StringUtils.isNotBlank(multipartRequest.getParameter("id"))){
     			id = Integer.valueOf(multipartRequest.getParameter("id"));
     		}
            
     		String msg = "设置成功";
            Map<String,Object> urlParam = Maps.newHashMap();
            urlParam.put("activityUrl", link);
            SysPushArtificialQueue r  = new SysPushArtificialQueue(); 
            if(id != null){
            	r.setId(id);
            }
            r.setAppType(appType);
    		r.setContent(content);
    		r.setLink(link);
    		r.setStartType(startType);//定时or即时
    		r.setSendObjectType(sendObjectType);//全部
    		r.setSendType(1);//系统推送
    		 r.setRemark(user.getUsername());//操作人
    		if(StringUtils.isNotBlank(startTime)){
    			r.setStartTime(DateUtils.parse(startTime, DateUtils.FORMAT_MM));
    		}
            if(ObjectUtils.equals(startType, 1)){//定时
		        r.setStatus(0);//待发送
            }else{
            	 r.setStatus(1);//已发送
            	 msg = "发送成功";
            }
            
            if(ObjectUtils.equals(sendObjectType, 0)){//全部推送
            	if(ObjectUtils.equals(startType, 0)){//即时
            		pushMessageHelper.pushByAppId((AppTypeEnum)EnumUtils.getEnumByKey(appType, AppTypeEnum.values()),SmsTypeEnum.LCSSYSACTIVITYRELEASE,"站内活动",content,urlParam);
            	}
            	 
            }else  if(ObjectUtils.equals(sendObjectType, 1)){ //导入对象推送
           		 InputStream inputStream = file.getInputStream();
           		 List<RedpacketImportModel> importModels = PoiImport.dataImport(inputStream,RedpacketImportModel.class);
           		 if(importModels.size()<1)return new ResponseResult(false,"发送失败:推送对象为空");
           		 List<String> mobiles = Lists.newArrayListWithExpectedSize(importModels.size());
           			 
           		 for(RedpacketImportModel item :importModels){
           			mobiles.add(item.getMobile());
           		 }
           		r.setMobiles(JsonUtils.fromObjectToJson(mobiles));
           		List<CrmUserInfo> userInfos =  userInfoService.queryUserListByMobileList(mobiles);
       			List<String> userIds = Lists.newArrayList();
       			for(CrmUserInfo userInfo :userInfos){
       				userIds.add(userInfo.getUserId());
       			}
       			r.setUserIds(JsonUtils.fromObjectToJson(userIds));
           		if(ObjectUtils.equals(startType, 0)){//即时           			
           			pushMessageHelper.BatchSinglePush((AppTypeEnum)EnumUtils.getEnumByKey(appType, AppTypeEnum.values()), SmsTypeEnum.LCSSYSACTIVITYRELEASE, userIds, "站内活动", content, urlParam, false, null);
           		}
            	
            }	
          //保存推送记录
            if(id ==null){
				sysPushArtificialQueueService.insert(r);
        	}else{
        		sysPushArtificialQueueService.update(r);
        	}
    		return new ResponseResult(true,msg,null);
		}catch (ServiceException e) {
			LOGGER.error("sysPushAdd exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("sysPushAdd exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"发送失败");
	}
	
	
}
