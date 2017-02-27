package com.linkwee.api.controller.activity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.response.activity.ScratchDetailResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActInvestscratchWinningRecord;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActInvestscratchWinningRecordService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("/api/activity/scratch")
public class InvestScratchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvestScratchController.class);
	
	@Resource
	private ActInvestscratchWinningRecordService winningRecordService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	/**
	 * 查询中奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/records")
    @ResponseBody
	@RequestLogging("中奖记录")
    public BaseResponse winningRecord(AppRequestHead head,BindingResult result,HttpServletRequest request) throws Exception {
		List<ActInvestscratchWinningRecord> winningRecords = winningRecordService.queryWinningRecord();
		Map<String,Object> rlt = new HashMap<String,Object>();
		if(winningRecords.size() > 0){
			rlt.put("hasRecord", new Boolean(true));
			rlt.put("winningRecords", winningRecords);
		}else{
			rlt.put("hasRecord", new Boolean(false));
			rlt.put("winningRecords", null);
		}
		return AppResponseUtil.getSuccessResponse(rlt);			
    }
	
	/**
	 * 刮奖详情
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
    @ResponseBody
	@RequestLogging("刮奖详情")
    public BaseResponse scratchDetail(AppRequestHead head,BindingResult result,HttpServletRequest request) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return AppResponseUtil.getErrorToken();
		}
				
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		if(StringUtils.isBlank(userId)){
			return AppResponseUtil.getErrorToken();
		}
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		try {
			startDate = dateFormat.parse("2016-08-31");
			endDate = dateFormat.parse("2016-09-30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.info("投资刮刮乐,活动时间错误");
			return AppResponseUtil.getErrorBusi("11001","活动时间错误");
		}
		if(date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0){
			return AppResponseUtil.getErrorBusi("11001","活动还没有开始，或者活动已经结束！");
		}
		
		Integer scratchedTime = winningRecordService.queryScratchedTime(userId);		
		Integer totalScratchTime = winningRecordService.queryTotalScratchTime(userId);
		Integer availableScratchTime = totalScratchTime - scratchedTime;
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		ActInvestscratchWinningRecord model = new ActInvestscratchWinningRecord();
		model.setUserId(userId);
		model.setMobile(crmUserInfo.getMobile());		
		BigDecimal nextScratchMoney = null;
		
		if (availableScratchTime == 0) {
			nextScratchMoney = new BigDecimal("0.0");
		} 
		else if (totalScratchTime == 2 && availableScratchTime == 2) {
			Random random = new Random();		
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * 25, "0.0"));
			if(winningRecordService.queryWinningUserNumber() != null && winningRecordService.queryWinningUserNumber() == 49){
				nextScratchMoney = new BigDecimal("588.0");
			}
		}
		else if(totalScratchTime == 2 && availableScratchTime == 1){
			model.setIsshaved(1);
			ActInvestscratchWinningRecord winningRecord = winningRecordService.selectOne(model);
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat((250 - winningRecord.getWinningAmt().doubleValue()*10)*0.1,"0.0"));	
			if(Integer.parseInt(NumberUtils.getFormat((winningRecord.getWinningAmt().doubleValue()*10),"0")) == 5880){
				Random random = new Random();
				nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * 10, "0.0"));
			}
		}
		else if (totalScratchTime == 3 && availableScratchTime == 3) {
			Random random = new Random();		
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * 38, "0.0"));		
			if(winningRecordService.queryWinningUserNumber() != null && winningRecordService.queryWinningUserNumber() == 49){
				nextScratchMoney = new BigDecimal("588.0");
			}
		}
		else if(totalScratchTime == 3 && availableScratchTime == 2){
			model.setIsshaved(1);
			ActInvestscratchWinningRecord winningRecord = winningRecordService.selectOne(model);
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat((380 - winningRecord.getWinningAmt().doubleValue()*10)*0.1,"0.0"));
			Random random = new Random();
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * nextScratchMoney.doubleValue(), "0.0"));
			if(Integer.parseInt(NumberUtils.getFormat((winningRecord.getWinningAmt().doubleValue()*10),"0")) == 5880){
				nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * 10, "0.0"));
			}
		}
		else if(totalScratchTime == 3 && availableScratchTime == 1){
			model.setIsshaved(1);
			List<ActInvestscratchWinningRecord> winningRecord = winningRecordService.selectListByCondition(model);
			nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat((380 - winningRecord.get(0).getWinningAmt().doubleValue()*10 - winningRecord.get(1).getWinningAmt().doubleValue()*10)*0.1,"0.0"));	
			if(Integer.parseInt(NumberUtils.getFormat((winningRecord.get(0).getWinningAmt().doubleValue()*10),"0")) == 5880 || Integer.parseInt(NumberUtils.getFormat((winningRecord.get(1).getWinningAmt().doubleValue()*10),"0")) == 5880){
				Random random = new Random();
				nextScratchMoney = 	new BigDecimal(NumberUtils.getFormat(random.nextDouble() * 10, "0.0"));
			}
		}else{
			nextScratchMoney = new BigDecimal("0.0");
		}
		
		if(availableScratchTime == 1 || availableScratchTime == 2 || availableScratchTime == 3){
			model.setIsshaved(0);
			try {
				ActInvestscratchWinningRecord winningRecord = winningRecordService.selectOne(model);
				if(winningRecord != null){
					nextScratchMoney = winningRecord.getWinningAmt();				
				}else{
					model.setWinningAmt(nextScratchMoney);
					model.setIsshaved(0);
					model.setWinningTime(new Date());
					String serialNumber = StringUtils.getUUID();
					model.setInvestscratchId(serialNumber);
					winningRecordService.insert(model);
					LOGGER.info("投资刮刮乐,插入未刮开的投资中奖记录：winningRecord={}", model);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("查询到两个及以上的未刮中奖记录");
				return AppResponseUtil.getErrorBusi("11000","查询到两个及以上的未刮中奖记录");	
			}			
		}
		
		ScratchDetailResponse rlt = new ScratchDetailResponse();
		rlt.setAvailableScratchTime(availableScratchTime);
		rlt.setTotalScratchTime(totalScratchTime);
		rlt.setNextScratchMoney(nextScratchMoney.doubleValue());
		return AppResponseUtil.getSuccessResponse(rlt);			
    }
	
	/**
	 * 插入中奖记录
	 * @param head
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/winning")
    @ResponseBody
	@RequestLogging("投资刮刮乐中奖")
    public BaseResponse scratchWinning(AppRequestHead head,BindingResult result,HttpServletRequest request) throws Exception {
		
		if(StringUtils.isBlank(head.getToken())){
			return AppResponseUtil.getErrorToken();
		}
				
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		if(StringUtils.isBlank(userId)){
			return AppResponseUtil.getErrorToken();
		}
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		try {
			startDate = dateFormat.parse("2016-08-31");
			endDate = dateFormat.parse("2016-09-30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.info("投资刮刮乐,活动时间错误");
			return AppResponseUtil.getErrorBusi("11001","活动时间错误");
		}
		if(date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0){
			return AppResponseUtil.getErrorBusi("11001","活动还没有开始，或者活动已经结束！");
		}
		
		Integer userType = AppUtils.isChannelApp(head.getOrgNumber()) ? 1 : 2;
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		ActInvestscratchWinningRecord model = new ActInvestscratchWinningRecord();
		model.setUserId(userId);
		model.setMobile(crmUserInfo.getMobile());
		model.setIsshaved(0);
		ActInvestscratchWinningRecord winningRecord = winningRecordService.selectOne(model);
		if(winningRecord != null){
			model.setIsshaved(1);
			model.setId(winningRecord.getId());
			winningRecordService.update(model);
			LOGGER.info("投资刮刮乐刮开，更新中奖记录表：model={}",JSONObject.toJSONString(model));
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(winningRecord.getInvestscratchId());
			recharge.setTransAmount(winningRecord.getWinningAmt());
			recharge.setUserId(winningRecord.getUserId());
			recharge.setUserType(userType);
			recharge.setTransType(3);
			recharge.setRemark("投资刮刮乐活动奖励");
			accountbindService.accountRecharge(recharge);			
		}else{
			return AppResponseUtil.getErrorBusi("11000", "不存在未刮开的中奖记录！！！");
		}	
		
		return AppResponseUtil.getSuccessResponse();			
    }
}
