package com.eshop4j.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.request.DataStatisticsRequest;
import com.eshop4j.web.service.DataStatisticsService;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "/data")
@RequestLogging("DataStatisticsController控制器")
public class DataStatisticsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataStatisticsController.class);
	
	@Resource
	private DataStatisticsService dataStatisticsService;

	/**
	 * 数据统计
	 * @param model
	 * @return
	 */
	@RequestMapping("/statistics")
	@RequestLogging("数据统计-页面")
	@ResponseBody
	public BaseResponse dataStatistics(DataStatisticsRequest request){
		String dateFormat="yyyy-MM-dd HH";
		if(request.getStartTime() != null && request.getEndTime() != null){
			//开始结束时间为同一天
			if(DateUtils.countDays(request.getStartTime(), request.getEndTime()) == 0){
				dateFormat = "yyyy-MM-dd HH";
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(request.getStartTime());
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				request.setStartTime(calendar.getTime());
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				request.setEndTime(calendar.getTime());
			}else{
				dateFormat = "yyyy-MM-dd";
			}
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			request.setStartTime(calendar.getTime());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			request.setEndTime(calendar.getTime());
		}
			
		request.setDateFormat(dateFormat);
		Map<String,Object> mapRlt = dataStatisticsService.queryInvestorLcsAndInvestment(request);
		return AppResponseUtil.getSuccessResponse(mapRlt);
	}
	
	@RequestMapping("/statistics/tag")
	@RequestLogging("数据统计-页面Tag")
	@ResponseBody
	public BaseResponse dataStatisticsWithTag(DataStatisticsRequest request){
		String dateFormat="yyyy-MM-dd HH";
		if(request.getTag() != null){
			Integer tag = request.getTag();
			switch(tag){
				case 0:		
					//昨天
					dateFormat = "yyyy-MM-dd HH";
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -1); 
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					request.setStartTime(calendar.getTime());
					calendar.set(Calendar.HOUR_OF_DAY, 23);
					request.setEndTime(calendar.getTime());
					break;
				case 1:
					//今天
					dateFormat = "yyyy-MM-dd HH";
					Calendar calendar1 = Calendar.getInstance();
					calendar1.set(Calendar.HOUR_OF_DAY, 0);
					request.setStartTime(calendar1.getTime());
					calendar1.set(Calendar.HOUR_OF_DAY, 23);
					request.setEndTime(calendar1.getTime());
					break;
				case 7:
					//最近一周
					dateFormat = "yyyy-MM-dd";
					Calendar calendar7 = Calendar.getInstance();
					calendar7.add(Calendar.DATE, -7);
					request.setStartTime(calendar7.getTime());
					calendar7.add(Calendar.DATE, 6);
					request.setEndTime(calendar7.getTime());
					break;
				case 30:
					//最近一个月
					dateFormat = "yyyy-MM-dd";
					Calendar calendar30 = Calendar.getInstance();
					calendar30.add(Calendar.DATE, -30);
					request.setStartTime(calendar30.getTime());
					calendar30.add(Calendar.DATE, 29);
					request.setEndTime(calendar30.getTime());
					break;
				default:
					Calendar calendard = Calendar.getInstance();
					calendard.set(Calendar.HOUR_OF_DAY, 0);
					request.setStartTime(calendard.getTime());
					calendard.set(Calendar.HOUR_OF_DAY, 23);
					request.setEndTime(calendard.getTime());
					dateFormat = "yyyy-MM-dd HH";
			}
		}else{			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			request.setStartTime(calendar.getTime());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			request.setEndTime(calendar.getTime());
		}
			
		request.setDateFormat(dateFormat);
		Map<String,Object> mapRlt = dataStatisticsService.queryInvestorLcsAndInvestment(request);
		return AppResponseUtil.getSuccessResponse(mapRlt);
	}
	
	/**
	 * 数据统计
	 * @param model
	 * @return
	 */
	@RequestMapping("/statistics/view")
	@RequestLogging("数据统计-页面")
	public String dataStatisticsView(Model model){
		return "datastatistics/datastatisticsView";
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
}
