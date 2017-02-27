package com.eshop4j.api.controller.crm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.crm.NewcomerTaskRequest;
import com.eshop4j.api.response.crm.CfpNewcomerWelfareResponse;
import com.eshop4j.api.response.crm.CrmCfpNewcomerTaskResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.CfpNewcomerWelfare;
import com.eshop4j.web.model.crm.CrmCfpNewcomerTask;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.CrmCfpNewcomerTaskService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;
import com.eshop4j.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： 理财师新手任务
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/cfpnewcomertask")
@RequestLogging("CrmCfpNewcomerTaskController控制器")
public class CrmCfpNewcomerTaskController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpNewcomerTaskController.class);
	
	@Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	@Resource
    private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	/**
	 * 新手任务首页
	 * @param head
	 * @return
	 */
	@RequestMapping("homepage")
	@ResponseBody
	public BaseResponse homePage(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmCfpNewcomerTask req = new CrmCfpNewcomerTask();
		req.setUserId(userId);
		CrmCfpNewcomerTask rlt = crmCfpNewcomerTaskService.selectOne(req);
		if(rlt == null) {
			CrmCfpNewcomerTask crmCfpNewcomerTask = new CrmCfpNewcomerTask();
			crmCfpNewcomerTask.setUserId(userId);
			crmCfpNewcomerTask.setCreateTime(new Date());
			crmCfpNewcomerTask.setLastUpdateTime(new Date());
			crmCfpNewcomerTaskService.insert(crmCfpNewcomerTask);
			rlt = crmCfpNewcomerTaskService.selectOne(req);
		}
		return AppResponseUtil.getSuccessResponse(rlt, CrmCfpNewcomerTaskResponse.class);
	}
	
	/**
	 * 完成任务
	 * @param head
	 * @return
	 */
	@RequestMapping("finishTask")
	@ResponseBody
	public BaseResponse finishTask(@Valid NewcomerTaskRequest req, BindingResult result, AppRequestHead head) {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		try {
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			CrmCfpNewcomerTask task = new CrmCfpNewcomerTask();
			task.setUserId(userId);
			task = crmCfpNewcomerTaskService.selectOne(task);
			CrmCfpNewcomerTask bo = new CrmCfpNewcomerTask();
			bo.setId(task.getId());
			String exc = "This task was Received Reward";
			if("1".equals(req.getTaskType())) {
				if(task.getInviteCustomerStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setInviteCustomerStatus(1);
				}
				
			} else if("4".equals(req.getTaskType())) {
				if(task.getInviteCfplannerStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setInviteCfplannerStatus(1);
				}
			} else if("2".equals(req.getTaskType())) {
				if(task.getRecommendProductStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setRecommendProductStatus(1);
				}
			} else if("5".equals(req.getTaskType())) {
				if(task.getRecommendPlatformStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setRecommendPlatformStatus(1);
				}
			} else if("3".equals(req.getTaskType())) {
				if(task.getGrantHongbaoStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setGrantHongbaoStatus(1);
				}
			} else if("6".equals(req.getTaskType())) {
				if(task.getSeeProfitStatus() == 2) {
					return AppResponseUtil.getErrorBusi(exc, "网络繁忙，请联系客服");
				} else {
					bo.setSeeProfitStatus(1);
				}
			}
			crmCfpNewcomerTaskService.update(bo);
			return AppResponseUtil.getSuccessResponse();
		} catch (Exception e) {
			LOGGER.info("完成新手任务异常 ： " + e);
			return AppResponseUtil.getErrorBusi(e.getMessage(), "网络繁忙，请联系客服");
		}
	}
	
	/**
	 * 领取奖励
	 * @param head
	 * @return
	 */
	@RequestMapping("receiveTaskReward")
	@ResponseBody
	public BaseResponse receiveTaskReward(@Valid NewcomerTaskRequest req, BindingResult result, AppRequestHead head) {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		try {
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			crmCfpNewcomerTaskService.receiveTaskReward(userId, req.getTaskType());
			return AppResponseUtil.getSuccessResponse();
		} catch (Exception e) {
			LOGGER.info("领取新手任务奖励异常 ： " + e);
			return AppResponseUtil.getErrorBusi(e.getMessage(), "网络繁忙，请联系客服");
		}
	}
	
	
	/**
	 * 新手福利
	 * @param head
	 * @return
	 */
	@RequestMapping("newcomerWelfare")
	@ResponseBody
	public BaseResponse newcomerWelfare(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CfpNewcomerWelfare rlt = new CfpNewcomerWelfare();
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfp != null && cfp.getHeadImage() != null) {
			rlt.setUploadHeadImageStatus(1);//是否上传头像
		} else {
			rlt.setUploadHeadImageStatus(0);
		}
		rlt.setBindCardStatus(accountbindService.isbindBankcard(userId) ? 1 : 0 );
		rlt.setInviteCfplannerStatus(crmCfplannerService.queryTeamMemberCount(userId) > 0 ? 1 : 0);//是邀请理财师
		List<CrmInvestor> invList = new ArrayList<CrmInvestor>();
		CrmInvestor inv = new CrmInvestor();
		inv.setRefUser(userId);
		invList = crmInvestorService.selectListByCondition(inv);
		if(invList != null && invList.size() > 0) {
			rlt.setInviteCustomerStatus(1);
		} else {
			rlt.setInviteCustomerStatus(0);
		}
		return AppResponseUtil.getSuccessResponse(rlt, CfpNewcomerWelfareResponse.class);
	}
	
	
}
