package com.eshop4j.api.controller.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.activity.request.BaseActivityRequest;
import com.eshop4j.api.activity.response.ActivityDrawResponse;
import com.eshop4j.api.activity.utils.BaseActivityHelper;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.ActActivityCondition;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.service.ActActivityConditionService;
import com.eshop4j.web.service.ActActivityWinningRecordService;
import com.eshop4j.web.service.ActivityListService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("/api/activity/base")
public class BaseActivityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseActivityController.class);
			
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private ActActivityWinningRecordService actActivityWinningRecordService;
	
	@Resource
	private BaseActivityHelper baseActivityHelper;
			
	@Resource
	private ActActivityConditionService actActivityConditionService;
	
	
	/**
	 * 抽奖次数
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/times")
    @ResponseBody
	@RequestLogging("活动抽奖--抽奖次数")
    public BaseResponse prizeTimes(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}

		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			//按次数抽奖（可多次抽奖）   true:按次数 ;false:按等级
			boolean multiTimes = false;
			ActActivityCondition actActivityCondition = new ActActivityCondition();
			actActivityCondition.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityCondition> actActivityConditionList = actActivityConditionService.selectListByCondition(actActivityCondition);			
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 1){
				multiTimes = true;
			}
			if(multiTimes){
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimes(userId,activity);
				if(leftTimes < 0){
					LOGGER.info("已抽取次数大于能抽取的总次数");
					leftTimes = 0;
				}
				Map<String,Object> rlt = new HashMap<String,Object>();
				rlt.put("leftTimes", leftTimes);
				return AppResponseUtil.getSuccessResponse(rlt);
			}else {
				List<Integer> gradeList = actActivityConditionService.queryConditionCase(userId,activity);
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimesInGradeCondition(userId,activity);
				Map<String,Object> rlt = new HashMap<String,Object>();
				rlt.put("gradeList", gradeList);
				rlt.put("leftTimes", leftTimes);
				//剩余次数要考虑概率策略一个prizeCase只能算一次
				return AppResponseUtil.getSuccessResponse(rlt);
			}
					
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽一次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/one")
    @ResponseBody
	@RequestLogging("活动抽奖--抽一次")
    public BaseResponse prizeOne(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			
			//按次数抽奖（可多次抽奖）true:按次数 false:按等级
			boolean multiTimes = false;
			ActActivityCondition actActivityCondition = new ActActivityCondition();
			actActivityCondition.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityCondition> actActivityConditionList = actActivityConditionService.selectListByCondition(actActivityCondition);
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 1){
				multiTimes = true;
				actActivityCondition = actActivityConditionList.get(0);
			}
			
			//排行榜
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 3){
				List<ActActivityWinningRecord> actActivityWinningRecordList = actActivityWinningRecordService.createWinningRecords(activity);
				List<ActivityDrawResponse> activityDrawResponseList = new ArrayList<ActivityDrawResponse>();					
				for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
					ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse();
					BeanUtils.copyProperties(actActivityWinningRecordTemp, activityDrawResponse);
	    			activityDrawResponseList.add(activityDrawResponse);
				}			
				return AppResponseUtil.getSuccessResponse(activityDrawResponseList);
			}			
						
			if(multiTimes){
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimes(userId,activity);
				if(leftTimes <= 0){
					LOGGER.info("剩余次数为0,不能继续抽奖");
					return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
				}else{
					List<ActActivityWinningRecord> actActivityWinningRecordList = baseActivityHelper.generateWinningRecord(activity, userId, actActivityCondition);						
					List<ActivityDrawResponse> activityDrawResponseList = new ArrayList<ActivityDrawResponse>();					
					for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
						ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse();
						//剩余次数减一，体验而已，实际并没与减少
						activityDrawResponse.setLeftTimes(leftTimes-1);			
						BeanUtils.copyProperties(actActivityWinningRecordTemp, activityDrawResponse);
		    			activityDrawResponseList.add(activityDrawResponse);
					}			
					return AppResponseUtil.getSuccessResponse(activityDrawResponseList);
				}	
			}else {
				//按等级
				
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimesInGradeCondition(userId,activity);
				
				List<ActActivityCondition> actActivityConditionGradeList = actActivityConditionService.queryConditionTypes(userId,activity);
				List<ActivityDrawResponse> activityDrawResponseList = new ArrayList<ActivityDrawResponse>();
				
				if((activity.getPrizeIssueStyle() == 2) && (activity.getPrizeBalanceTime().compareTo(new Date()) > 0)){
					return AppResponseUtil.getErrorBusi("10092", "活动未到发放奖励时间");
				}else if((activity.getPrizeIssueStyle() == 2) && (activity.getPrizeBalanceTime().compareTo(new Date()) < 0)){
					//生成最高等级的奖励
					ActActivityCondition actActivityConditionBest = new ActActivityCondition();
					actActivityConditionBest.setConditionCase(0);
					for(ActActivityCondition actActivityConditionTemp : actActivityConditionGradeList){
						if(actActivityConditionTemp.getConditionCase() > actActivityConditionBest.getConditionCase()){
							actActivityConditionBest = actActivityConditionTemp;
						}
					}
					
					List<ActActivityWinningRecord> actActivityWinningRecordList = baseActivityHelper.generateWinningRecordInGradeCase(activity, userId, actActivityConditionBest);											
					for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
						ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse();
						//剩余次数减一，体验而已，实际并没与减少
						activityDrawResponse.setLeftTimes(leftTimes-1);			
						BeanUtils.copyProperties(actActivityWinningRecordTemp, activityDrawResponse);
		    			activityDrawResponseList.add(activityDrawResponse);
					}			
					return AppResponseUtil.getSuccessResponse(activityDrawResponseList);
					
				}else {
					for(ActActivityCondition actActivityConditionTemp : actActivityConditionGradeList){		
						List<ActActivityWinningRecord> actActivityWinningRecordList = baseActivityHelper.generateWinningRecordInGradeCase(activity, userId, actActivityConditionTemp);											
						for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
							ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse();
							//剩余次数减一，体验而已，实际并没与减少
							activityDrawResponse.setLeftTimes(leftTimes-1);			
							BeanUtils.copyProperties(actActivityWinningRecordTemp, activityDrawResponse);
			    			activityDrawResponseList.add(activityDrawResponse);
						}						
					}
					return AppResponseUtil.getSuccessResponse(activityDrawResponseList);
				}			
			}					
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽一次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/one/record")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽一次记录")
    public BaseResponse prizeOneRecord(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){
		
		//发奖励时必须满足获得奖励的条件
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			
			//按次数抽奖（可多次抽奖）true:按次数 false:按等级
			boolean multiTimes = false;
			ActActivityCondition actActivityCondition = new ActActivityCondition();
			actActivityCondition.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityCondition> actActivityConditionList = actActivityConditionService.selectListByCondition(actActivityCondition);
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 1){
				multiTimes = true;
				actActivityCondition = actActivityConditionList.get(0);
			}
			
			//排行榜
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 3 && activity.getPrizeBalanceTime().compareTo(new Date()) < 0){
				ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse(); 
				try {
					baseActivityHelper.issueWinningRecords(activity, userId, actActivityConditionList.get(0), userType, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.warn("发放奖励错误");						
					return AppResponseUtil.getErrorBusi("10089", "未生成中奖记录前不可发放奖励");						
				}						
				return AppResponseUtil.getSuccessResponse(activityDrawResponse);
			}
			
			if(multiTimes){			
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimes(userId,activity);
				if(leftTimes <= 0){
					LOGGER.info("剩余次数为0,不能继续抽奖");
					return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
				}else{
					ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse(); 
					activityDrawResponse.setLeftTimes(leftTimes-1);
					try {
						baseActivityHelper.issueWinningRecords(activity, userId, actActivityCondition, userType, 1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LOGGER.warn("发放奖励错误");						
						return AppResponseUtil.getErrorBusi("10089", "未生成中奖记录前不可发放奖励");						
					}
					
					return AppResponseUtil.getSuccessResponse(activityDrawResponse);
				}
			}else {
				//按等级
				
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimesInGradeCondition(userId,activity);
				
				List<ActActivityCondition> actActivityConditionGradeList = actActivityConditionService.queryConditionTypes(userId,activity);
				for(ActActivityCondition actActivityConditionTemp : actActivityConditionGradeList){
					//一个等级发放一次................所有等级可以考虑支持一次性发放
					if(actActivityConditionTemp.getConditionCase() == baseActivityRequest.getConditionCase()){						
						ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse(); 
						activityDrawResponse.setLeftTimes(leftTimes-1);
						try {
							baseActivityHelper.issueWinningRecords(activity, userId, actActivityConditionTemp, userType, 1);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							LOGGER.warn("发放奖励错误");						
							return AppResponseUtil.getErrorBusi("10089", "未生成中奖记录前不可发放奖励");						
						}						
						return AppResponseUtil.getSuccessResponse(activityDrawResponse);								
					}			
				}
				return AppResponseUtil.getSuccessResponse();
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽多次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/multi")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽多次")
    public BaseResponse prizeTen(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			
			//按次数抽奖（可多次抽奖）true:按次数 false:按等级
			boolean multiTimes = false;
			ActActivityCondition actActivityCondition = new ActActivityCondition();
			actActivityCondition.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityCondition> actActivityConditionList = actActivityConditionService.selectListByCondition(actActivityCondition);
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 1){
				multiTimes = true;
				actActivityCondition = actActivityConditionList.get(0);
			}
			if(multiTimes){
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimes(userId,activity);
				if(leftTimes < 10){
					LOGGER.info("剩余次数小于10,不能采用该方式抽奖");
					return AppResponseUtil.getErrorBusi("10088", "剩余次数小于10,不能采用该方式抽奖");
				}else{
					List<ActActivityWinningRecord> actActivityWinningRecordList = baseActivityHelper.generateWinningRecordMultiTimes(activity, userId, actActivityCondition, baseActivityRequest.getPrizeStyle());	
					List<ActivityDrawResponse> activityDrawResponseList = new ArrayList<ActivityDrawResponse>();					
					for(ActActivityWinningRecord actActivityWinningRecordTemp : actActivityWinningRecordList){
						ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse();
						//剩余次数减十，体验而已，实际并没与减少
						activityDrawResponse.setLeftTimes(leftTimes-10);			
						BeanUtils.copyProperties(actActivityWinningRecordTemp, activityDrawResponse);
		    			activityDrawResponseList.add(activityDrawResponse);
					}			
					return AppResponseUtil.getSuccessResponse(activityDrawResponseList);
				}	
			}else{
				return AppResponseUtil.getErrorBusi("10099", "本活动奖励不能抽取多次");
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
								
    }
	
	/**
	 * 抽多次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/multi/record")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽多次记录")
    public BaseResponse prizeTenRecord(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){
		
		//发奖励时必须满足获得奖励的条件
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			
			//按次数抽奖（可多次抽奖）true:按次数 false:按等级
			boolean multiTimes = false;
			ActActivityCondition actActivityCondition = new ActActivityCondition();
			actActivityCondition.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityCondition> actActivityConditionList = actActivityConditionService.selectListByCondition(actActivityCondition);
			if(actActivityConditionList != null && actActivityConditionList.size() == 1 && actActivityConditionList.get(0).getConditionType() == 1){
				multiTimes = true;
				actActivityCondition = actActivityConditionList.get(0);
			}
			if(multiTimes){		
				Integer leftTimes = actActivityWinningRecordService.queryLeftTimes(userId,activity);
				if(leftTimes < 10){
					LOGGER.info("剩余次数小于10,不能采用该方式抽奖");
					return AppResponseUtil.getErrorBusi("10088", "剩余次数小于10,不能采用该方式抽奖");
				}else{
					ActivityDrawResponse activityDrawResponse = new ActivityDrawResponse(); 
					activityDrawResponse.setLeftTimes(leftTimes-10);
					try {
						baseActivityHelper.issueWinningRecords(activity, userId, actActivityCondition, userType, 10);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LOGGER.warn("发放奖励错误");						
						return AppResponseUtil.getErrorBusi("10089", "未生成中奖记录前不可发放奖励");						
					}
					
					return AppResponseUtil.getSuccessResponse(activityDrawResponse);
					
				}
			}else{
				return AppResponseUtil.getErrorBusi("10099", "本活动奖励不能抽取多次");
			}		
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 所有抽奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/all")
    @ResponseBody
	@RequestLogging("转盘抽奖--所有抽奖记录")
    public BaseResponse prizeRecordAll(BaseActivityRequest baseActivityRequest,AppRequestHead head,BindingResult result){	
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
			actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
			List<ActActivityWinningRecord> activityWinningRecordList = actActivityWinningRecordService.selectListByCondition(actActivityWinningRecord);		
			return AppResponseUtil.getSuccessResponse(activityWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}				
    }
	
	/**
	 * 用户抽奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录")
    public BaseResponse prizeRecordUser(BaseActivityRequest baseActivityRequest,AppRequestHead head,PaginatorRequest paginatorRequest){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		List<ActivityList> activityLists = activityListService.queryActivity(baseActivityRequest.getActivityName(),baseActivityRequest.getActivityPlatform());
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
			actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
			actActivityWinningRecord.setUserId(userId);
			Page<ActActivityWinningRecord> page = new Page<ActActivityWinningRecord>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());
			PaginatorResponse<ActActivityWinningRecord> activityWinningRecordList = actActivityWinningRecordService.queryUserPrizeRecord(actActivityWinningRecord,page);		
			return AppResponseUtil.getSuccessResponse(activityWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}			
    }
}
