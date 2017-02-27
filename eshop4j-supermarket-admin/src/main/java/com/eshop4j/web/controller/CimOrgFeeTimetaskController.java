package com.eshop4j.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.CimOrgFeeTimetask;
import com.eshop4j.web.model.User;
import com.eshop4j.web.model.cim.CimOrginfoWeb;
import com.eshop4j.web.request.CimOrgFeeTimetaskRequest;
import com.eshop4j.web.service.CimOrgFeeTimetaskService;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.RequestLogging;

/**
 * 
 * 描述：机构佣金定时设置Controller控制器
 * @author yalin
 * @date 2016年10月12日 下午3:14:45 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
@Controller
@RequestMapping(value = "cim/cimorgfeetimetask")
@RequestLogging("CimOrgFeeTimetaskController控制器")
public class CimOrgFeeTimetaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeTimetaskController.class);

	@Resource
	private CimOrgFeeTimetaskService cimOrgFeeTimetaskService;
	
	@Resource
	private CimOrginfoService cimOrginfoService; //机构服务
	
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
     * 跳到机构佣金率设置页面
     * @return
     */
    @RequestMapping(value="/toOrgFeeRatioView",method=RequestMethod.GET)
    @RequestLogging("跳到机构佣金率设置页面")
	public ModelAndView toOrgFeeRatioView(String orgNumber){
		ModelAndView modelAndView = new ModelAndView("cimorginfo/cimorgfeeratio-modal");
		if(StringUtils.isNotBlank(orgNumber)){
			List<CimOrgFeeTimetask> orgFeeTasks = cimOrgFeeTimetaskService.queryOrgFeeTimeTaskByStatus(1,orgNumber,null); //1,取出所有待执行的定时任务
			CimOrginfoWeb orgInfo = cimOrginfoService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
			modelAndView.addObject("orgFeeRatio", orgInfo.getOrgFeeRatio());
			modelAndView.addObject("orgFeeTasks", orgFeeTasks);
		}
		return modelAndView;
	}
    
    
    /**
     * 插入机构佣金设置定时任务
     */
    @RequestMapping(value = "/insertOrgFeeRatioTask", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("插入机构佣金设置定时任务")
    public Object insertOrgFeeRatioTask(CimOrgFeeTimetaskRequest request,HttpSession session) {
    		LOGGER.debug("插入机构佣金设置定时任务请求参数request = {}",JSON.toJSONString(request));
       		long start = System.currentTimeMillis();
       		StringBuilder logsb = new StringBuilder();
       		ResponseResult result = null;
       		try {
       			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
       			if(StringUtils.isNotBlank(request.getOrgNumber())){
       				CimOrginfoWeb orginfo = cimOrginfoService.findWebOrgInfo(request.getOrgNumber()); //WEB端 平台信息详情
       				if(orginfo != null){
       					orginfo.setOrgCreator(user.getUsername()); //创建人
           				orginfo.setOrgUpdater(user.getUsername()); //获取修改人用户名
           				cimOrgFeeTimetaskService.insertOrgFeeTimetask(orginfo,request); 
               			result = new ResponseResult(true, "插入机构佣金设置定时任务信息成功！");
               			logsb.append("插入机构佣金设置定时任务信息 success");
           			}
       			}
       			
       		} catch (Exception e) {
       			logsb.append("插入机构佣金设置定时任务信息 fail");
       			LOGGER.error("插入机构佣金设置定时任务失败！", e);
       			result = new ResponseResult(false, "插入机构佣金设置定时任务信息失败！");
       		}
       		long end = System.currentTimeMillis();
       		logsb.append("插入机构佣金设置定时任务信息总耗时 |totaltime=").append(end - start).append("ms");
       		LOGGER.info(logsb.toString());
       		return result;
    		
    }
    
    /**
   	 * 删除机构佣金设置定时任务
   	 * @return
   	 */
    @RequestMapping(value = "/deleteOrgFeeTask", method = RequestMethod.POST)
   	@ResponseBody
   	@RequestLogging("删除机构佣金设置定时任务") 
   	public Object deleteOrgFeeTask(Long id){
    	LOGGER.debug("删除机构佣金设置定时任务请求参数 id = {}",JSON.toJSONString(id));
   		long start = System.currentTimeMillis();
   		StringBuilder logsb = new StringBuilder();
   		ResponseResult result = null;
   		try {
   			if(null != id && id > 0){
   				int deleteRows = cimOrgFeeTimetaskService.delete(id);
   				if(deleteRows > 0){
   					result = new ResponseResult(true, "删除机构佣金设置定时任务成功！");
   					logsb.append("删除机构佣金设置定时任务成功！ success");
   				}
   			}
   		} catch (Exception e) {
   			logsb.append("删除机构佣金设置定时任务失败！ fail");
   			LOGGER.error("删除机构佣金设置定时任务失败！", e);
   			result = new ResponseResult(false, "删除机构佣金设置定时任务失败！");
   		}
   		long end = System.currentTimeMillis();
   		logsb.append("删除机构佣金设置定时任务总耗时 |totaltime=").append(end - start).append("ms");
   		LOGGER.info(logsb.toString());
   		return result;
   	}


    
	
}
