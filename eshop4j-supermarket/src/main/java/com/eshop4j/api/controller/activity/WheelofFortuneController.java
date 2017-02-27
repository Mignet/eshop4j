package com.eshop4j.api.controller.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.activity.BaseLottery;
import com.eshop4j.api.activity.response.BigWheelDrawResponse;
import com.eshop4j.api.activity.utils.NewYearBigWheelDrawUtil;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.PaginatorRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.ActWheelWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.service.ActWheelWinningRecordService;
import com.eshop4j.web.service.ActivityListService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("/api/activity/wheel")
public class WheelofFortuneController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WheelofFortuneController.class);
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private ActWheelWinningRecordService actWheelWinningRecordService;
	
	@Resource
	private ActivityListService activityListService;
	
	/**
	 * 抽奖次数
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/times")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽奖次数")
    public BaseResponse prizeTimes(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}

		String activityName = "大转盘";
		String activityPlatform = "T呗";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			Double totalMoney = actWheelWinningRecordService.queryInvestTotalMoney(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG));
			Integer totalTimes = (int) Math.floor(totalMoney/10000);
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes < 0){
				LOGGER.info("已抽取次数大于能抽取的总次数");
				leftTimes = 0;
			}
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("leftTimes", leftTimes);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }*/
	
	/**
	 * 抽一次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/one")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽一次")
    public BaseResponse prizeOne(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		String activityName = "大转盘";
		String activityPlatform = "T呗";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			Double totalMoney = actWheelWinningRecordService.queryInvestTotalMoney(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG));
			Integer totalTimes = (int) Math.floor(totalMoney/10000);
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes <= 0){
				LOGGER.info("剩余次数为0,不能继续抽奖");
				return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
			}else{
				BaseLottery baseLottery = BigWheelDrawUtil.generateAward();
				BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
				bigWheelDrawResponse.setPrizeId(baseLottery.getId());
				bigWheelDrawResponse.setLeftTimes(leftTimes-1);
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				try {
					actWheelWinningRecordService.insertDrawRecord(baseLottery,1,userId,crmUserInfo.getMobile(),userType);
				} catch (Exception e) {
					LOGGER.warn("记录大转盘抽奖记录失败");
					return AppResponseUtil.getErrorBusi("10099", "记录大转盘抽奖记录失败");
				}
				return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }*/
	
	/**
	 * 抽十次
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/ten")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽十次")
    public BaseResponse prizeTen(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		String activityName = "大转盘";
		String activityPlatform = "T呗";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			Double totalMoney = actWheelWinningRecordService.queryInvestTotalMoney(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG));
			Integer totalTimes = (int) Math.floor(totalMoney/10000);
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes < 10){
				LOGGER.info("剩余次数小于10,不能采用该方式抽奖");
				return AppResponseUtil.getErrorBusi("10089", "剩余次数小于10,不能采用该方式抽奖");
			}else {
				BaseLottery baseLottery = new BaseLottery(8888,"小米移动电源",0);
				BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
				bigWheelDrawResponse.setPrizeId(baseLottery.getId());
				bigWheelDrawResponse.setLeftTimes(leftTimes-10);
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				try {
					actWheelWinningRecordService.insertDrawRecord(baseLottery,10,userId,crmUserInfo.getMobile(),userType);
				} catch (Exception e) {
					LOGGER.warn("记录大转盘抽奖记录失败");
					return AppResponseUtil.getErrorBusi("10099", "记录大转盘抽奖记录失败");
				}
				return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }*/
	
	/**
	 * 所有抽奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/record/all")
    @ResponseBody
	@RequestLogging("转盘抽奖--所有抽奖记录")
    public BaseResponse prizeRecordAll(AppRequestHead head,BindingResult result){					
		List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(null);
		for(ActWheelWinningRecord actWheelWinningRecord : actWheelWinningRecordList){
			String mobile = actWheelWinningRecord.getMobile().substring(0, 3)+"****"+actWheelWinningRecord.getMobile().substring(7);
			actWheelWinningRecord.setMobile(mobile);
		}
		return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);				
    }*/
	
	/**
	 * 用户抽奖记录(分页)
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/record/user/pageList")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录")
    public BaseResponse prizeRecordUserPageList(AppRequestHead head,PaginatorRequest paginatorRequest){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());		
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		PaginatorResponse<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.queryUserPrizeRecord(actWheelWinningRecord,page);
		
		return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);				
    }*/
	
	/**
	 * 用户抽奖记录(不分页)
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/prize/record/user")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录")
    public BaseResponse prizeRecordUser(AppRequestHead head,BindingResult result){	
			
		ActWheelWinningRecord actWheelWinningRecordTemp = new ActWheelWinningRecord();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}else {
			actWheelWinningRecordTemp.setUserId(userId);
		}	
		List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(actWheelWinningRecordTemp);	
		return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);				
    }*/
	
	/**
	 * 抽奖次数 -- 春节大转盘
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/times")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽奖次数--春节大转盘")
    public BaseResponse newYearPrizeTimes(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}

		String activityName = "吃福饺 行大运";
		String activityPlatform = "猎财大师";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			Double totalMoney = actWheelWinningRecordService.queryInvestorHasInvestedTotalMoney(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG));
			Integer totalTimes = (int) Math.floor(totalMoney/10000);
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes < 0){
				LOGGER.info("已抽取次数大于能抽取的总次数");
				leftTimes = 0;
			}
			Map<String,Object> rlt = new HashMap<String,Object>();
			rlt.put("leftTimes", leftTimes);
			rlt.put("totalTimes", totalTimes);
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 抽一次 -- 春节大转盘
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/one")
    @ResponseBody
	@RequestLogging("转盘抽奖--抽一次--春节大转盘")
    public BaseResponse newYearPrizeOne(AppRequestHead head,BindingResult result){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;	
		
		String activityName = "吃福饺 行大运";
		String activityPlatform = "猎财大师";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			Integer hasDrawedTimes = actWheelWinningRecordService.queryHasDrawTimes(userId,activity.getId().toString());
			Double totalMoney = actWheelWinningRecordService.queryInvestorHasInvestedTotalMoney(userId,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG));
			Integer totalTimes = (int) Math.floor(totalMoney/10000);
			Integer leftTimes = totalTimes - hasDrawedTimes;
			if(leftTimes <= 0){
				LOGGER.info("剩余次数为0,不能继续抽奖");
				return AppResponseUtil.getErrorBusi("10088", "剩余次数为0,不能继续抽奖");
			}else{
				BaseLottery baseLottery = NewYearBigWheelDrawUtil.generateAward();
				BigWheelDrawResponse bigWheelDrawResponse = new BigWheelDrawResponse(); 
				bigWheelDrawResponse.setPrizeId(baseLottery.getId());
				bigWheelDrawResponse.setLeftTimes(leftTimes-1);
				if(baseLottery.getId() == 6666){
					ActWheelWinningRecord temp = new ActWheelWinningRecord();
					temp.setWinningOrder(6666);
					ActWheelWinningRecord rtl = actWheelWinningRecordService.selectOne(temp);
					if(rtl != null){
						while (baseLottery.getId() == 6666) {
							baseLottery = NewYearBigWheelDrawUtil.generateAward();
						}
						bigWheelDrawResponse.setPrizeId(baseLottery.getId());
					}
				}
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				try {
					actWheelWinningRecordService.insertDrawRecord(baseLottery,1,userId,crmUserInfo.getMobile(),userType,activity.getId().toString());
				} catch (Exception e) {
					LOGGER.warn("记录大转盘抽奖记录失败");
					return AppResponseUtil.getErrorBusi("10099", "记录大转盘抽奖记录失败");
				}
				return AppResponseUtil.getSuccessResponse(bigWheelDrawResponse);
			}	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
						
    }
	
	/**
	 * 所有抽奖记录 -- 春节大转盘
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/all")
    @ResponseBody
	@RequestLogging("转盘抽奖--所有抽奖记录--春节大转盘")
    public BaseResponse newYearPrizeRecordAll(AppRequestHead head,BindingResult result){
		String activityName = "吃福饺 行大运";
		String activityPlatform = "猎财大师";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
		
			actWheelWinningRecord.setExtends1(activity.getId().toString());
			List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(actWheelWinningRecord);
			for(ActWheelWinningRecord actWheelWinningRecordTemp : actWheelWinningRecordList){
				String mobile = actWheelWinningRecordTemp.getMobile().substring(0, 3)+"****"+actWheelWinningRecordTemp.getMobile().substring(7);
				actWheelWinningRecordTemp.setMobile(mobile);
			}
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
					
    }
	
	/**
	 * 用户抽奖记录(分页) -- 春节大转盘
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user/pageList")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录--春节大转盘")
    public BaseResponse newYearPrizeRecordUserPageList(AppRequestHead head,PaginatorRequest paginatorRequest){
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		String activityName = "吃福饺 行大运";
		String activityPlatform = "猎财大师";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
		
			Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(paginatorRequest.getPageIndex(), paginatorRequest.getPageSize());		
			ActWheelWinningRecord actWheelWinningRecordTemp = new ActWheelWinningRecord();
			actWheelWinningRecordTemp.setUserId(userId);
			actWheelWinningRecordTemp.setExtends1(activity.getId().toString());
			PaginatorResponse<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.queryUserPrizeRecord(actWheelWinningRecordTemp,page);
			
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
	/**
	 * 用户抽奖记录(不分页) -- 春节大转盘
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/prize/record/user")
    @ResponseBody
	@RequestLogging("转盘抽奖--用户抽奖记录--春节大转盘")
    public BaseResponse newYearPrizeRecordUser(AppRequestHead head,BindingResult result){	
			
		ActWheelWinningRecord actWheelWinningRecordTemp = new ActWheelWinningRecord();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}else {
			actWheelWinningRecordTemp.setUserId(userId);
		}	
		String activityName = "吃福饺 行大运";
		String activityPlatform = "猎财大师";
		List<ActivityList> activityLists = activityListService.queryActivity(activityName,activityPlatform);
		ActivityList activity = null;
		if(activityLists != null && activityLists.size() > 0){
			activity = activityLists.get(0);
			actWheelWinningRecordTemp.setExtends1(activity.getId().toString());
			List<ActWheelWinningRecord> actWheelWinningRecordList = actWheelWinningRecordService.selectListByCondition(actWheelWinningRecordTemp);	
			return AppResponseUtil.getSuccessResponse(actWheelWinningRecordList);	
		}else{
			return AppResponseUtil.getErrorBusi("10086", "活动不存在或已过期");
		}
    }
	
}
