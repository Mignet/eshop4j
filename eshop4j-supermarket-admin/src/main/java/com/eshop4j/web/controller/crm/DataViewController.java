package com.eshop4j.web.controller.crm;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.InvestedUserDataStatisticResp;
import com.eshop4j.web.model.crm.UserDataStatisticResp;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.InvestorManagerService;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月28日 16:08:06
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "dataview")
@RequestLogging("数据概览")
public class DataViewController extends BaseController {

	@Resource
	private SysMsgService msgService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private InvestorManagerService investorManagerService;
	
	
	/**
	 * 数据概览
	 * @param model
	 * @return
	 */
	@RequestMapping("dataview")
	@RequestLogging("投资数据概览-页面")
	public String dataView(Model model){
		String startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		long startTime = System.currentTimeMillis();
		Map<String,Object> mapRlt = investorManagerService.queryInvestorAndMoney();
		logger.debug("customerCftRelFixService.queryInvestorAndMoney():"+(System.currentTimeMillis()-startTime));
		model.addAttribute("data",mapRlt);
		UserDataStatisticResp userDataStatisticResp  = investorManagerService.queryUserRegisterTotalData();
		InvestedUserDataStatisticResp investedUserDataStatisticResp = investorManagerService.queryUserInvestTotalData();
		model.addAttribute("register",userDataStatisticResp);
		model.addAttribute("investor",investedUserDataStatisticResp);
		logger.debug("queryTotalData():"+(System.currentTimeMillis()-startTime));
		return "dataview/dataView";
	}
	
	/**
     * 投资用户图表
     */
    @RequestMapping("statdata")
	@ResponseBody
	@RequestLogging("投资客户数据概览-报表图")
	public Object dataViewInvestMoney(String startDate,String endDate){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		if(StringUtils.isBlank(startDate)  || StringUtils.isBlank(endDate)){
			startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
			endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		}
		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = investorManagerService.queryInvestorAndMoneyByDate(startDate, endDate);
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	/**
	 * 总投资人数和年化投资额 异步加载
	 */
	@RequestMapping("totaldata")
	@ResponseBody
	public Map<String, Object> queryTotalData(){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = investorManagerService.queryTotalInvestorAndMoney();
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	
}
