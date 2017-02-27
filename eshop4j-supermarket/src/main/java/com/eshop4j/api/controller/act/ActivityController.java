package com.eshop4j.api.controller.act;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.ErrorResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.constant.ApiInvokeLogConstant;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.enums.PlatformEnum;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.SysApiInvokeLog;
import com.eshop4j.web.request.ActivityPageListRequest;
import com.eshop4j.web.response.ActivityPageListResponse;
import com.eshop4j.web.service.ActivityListService;
import com.eshop4j.web.service.SysApiInvokeLogService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.RequestLogging;

/**
 * 
 * 描述：精彩活动
 * @author yalin
 * @date 2016年7月26日 下午6:45:15 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
@Controller
@RequestMapping(value = "/api/activity")
@RequestLogging("活动")
public class ActivityController extends BaseController {


	@Resource
	private ActivityListService activityListService;
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	/**
	 * 精彩活动
	 * @author yalin 
	 * @date 2016年7月26日 下午6:32:36  
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/pageList")
	@ResponseBody
	@RequestLogging("精彩活动列表")
	public BaseResponse activityPageList(ActivityPageListRequest req,BindingResult result,AppRequestHead head) {
		logger.info("精彩活动列表请求参数 OrgInfoRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		
		if(StringUtils.isNotBlank(req.getAppType())){
			conditions.put("appType",req.getAppType());
		}
		
		Page<ActivityList> page  = new Page<ActivityList>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		PaginatorResponse<ActivityList> datas = activityListService.queryActivities(page,conditions);
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(!datas.getDatas().isEmpty()&&datas.getDatas().size()>0){
			for(ActivityList activity:datas.getDatas()){
				activity.setLinkUrl(WebUtil.creatUrl(activity.getLinkUrl(), params));
			}
		}
		
		return AppResponseUtil.getSuccessResponse(datas,ActivityPageListResponse.class);
	}
	
	@RequestMapping(value="/platform")
	@ResponseBody
	@RequestLogging("平台活动")
	public BaseResponse activityPlatform(ActivityPageListRequest req,BindingResult result,AppRequestHead head) {
		logger.info("平台活动请求参数ActivityPageListRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		
		conditions.put("appType",appType);
		
		Page<ActivityList> page  = new Page<ActivityList>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		PaginatorResponse<ActivityList> datas = activityListService.queryActivitiesByPlatform(page,conditions);
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(!datas.getDatas().isEmpty()&&datas.getDatas().size()>0){
			for(ActivityList activity:datas.getDatas()){
				activity.setLinkUrl(WebUtil.creatUrl(activity.getLinkUrl(), params));
			}
		}
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_ACTIVITY_READED, userId,appType);
		}
		
		return AppResponseUtil.getSuccessResponse(datas);
	}
	
	@RequestMapping(value="/platform/pageList")
	@ResponseBody
	@RequestLogging("平台活动列表(分页)")
	public BaseResponse activityPlatformPageList(@Valid ActivityPageListRequest req,BindingResult result,AppRequestHead head) {
		logger.info("平台活动列表请求参数ActivityPageListRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		
		conditions.put("appType",appType);
	
		conditions.put("activityPlatform", req.getActivityPlatform());
		Page<ActivityList> page  = new Page<ActivityList>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		PaginatorResponse<ActivityList> datas = activityListService.queryActivitiesListByPlatform(page,conditions);
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(!datas.getDatas().isEmpty()&&datas.getDatas().size()>0){
			for(ActivityList activity:datas.getDatas()){
				activity.setLinkUrl(WebUtil.creatUrl(activity.getLinkUrl(), params));
			}
		}
		
		return AppResponseUtil.getSuccessResponse(datas);
	}
	
	@RequestMapping(value="/platform/list")
	@ResponseBody
	@RequestLogging("平台活动列表（不分页）")
	public BaseResponse activityPlatformList(@Valid ActivityPageListRequest req,BindingResult result,AppRequestHead head) {
		logger.info("平台活动列表请求参数ActivityListRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		
		conditions.put("appType",appType);
	
		conditions.put("activityPlatform", req.getActivityPlatform());
		List<ActivityList> datas = activityListService.queryPlatformActivities(conditions);
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtils.isNotBlank(head.getToken())){
			PlatformEnum platform =  AppUtils.getPlatform(head.getOrgNumber());
			if(PlatformEnum.IOS.equals(platform)||PlatformEnum.ANDROID.equals(platform)){
				params.put("token",head.getToken());
			}
		}
		if(!datas.isEmpty()&&datas.size()>0){
			for(ActivityList activity:datas){
				activity.setLinkUrl(WebUtil.creatUrl(activity.getLinkUrl(), params));
			}
		}
		
		return AppResponseUtil.getSuccessResponse(datas);
	}
	
	@RequestMapping(value="/readed")
	@ResponseBody
	@RequestLogging("是否有未读的活动")
	public BaseResponse hasReaded(AppRequestHead head) {
		logger.info("是否有未读的活动请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_ACTIVITY_READED, userId, appType);
		ActivityList newestActivity = activityListService.queryNewest(appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(newestActivity == null && apiInvokeLog == null){
			compareResult = 0;
			resp.put("readed", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else if(apiInvokeLog == null){
			compareResult = 1;
			resp.put("readed", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else if(newestActivity == null){
			compareResult = 0;
			resp.put("readed", compareResult);		
			return AppResponseUtil.getSuccessResponse(resp);
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), newestActivity.getStartDate());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
			resp.put("readed", compareResult);	
			return AppResponseUtil.getSuccessResponse(resp);
		}
		
	}
	
}
