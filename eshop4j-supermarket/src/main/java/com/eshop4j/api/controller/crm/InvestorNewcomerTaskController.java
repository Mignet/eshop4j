package com.eshop4j.api.controller.crm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.response.crm.InvestorCfpNewcomerTaskResponse;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.crm.InvestorNewcomerWelfare;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.api.BaseController;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述：投资者新手任务
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/investornewcomertask")
@RequestLogging("InvestorNewcomerTaskController控制器")
public class InvestorNewcomerTaskController extends BaseController {
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	/**
	 * 新手福利
	 * @param head
	 * @return
	 */
	@RequestMapping("newcomerWelfare")
	@ResponseBody
	public BaseResponse newcomerWelfare(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		InvestorNewcomerWelfare rlt = new InvestorNewcomerWelfare();
		rlt.setRegisterStatus(1);
		rlt.setBindCardStatus(accountbindService.isbindBankcard(userId) ? 1 : 0 );
		CrmInvestor investor = crmInvestorService.queryInvestorByUserId(userId);
		rlt.setFirstInvestStatus(investor.getRectInvestTime() != null ? 1 : 0);
		List<CrmInvestor> invList = new ArrayList<CrmInvestor>();
		CrmInvestor inv = new CrmInvestor();
		inv.setRefUser(userId);
		invList = crmInvestorService.selectListByCondition(inv);
		if(invList != null && invList.size() > 0) {
			rlt.setInviteCustomerStatus(1);
		} else {
			rlt.setInviteCustomerStatus(0);
		}
		return AppResponseUtil.getSuccessResponse(rlt, InvestorCfpNewcomerTaskResponse.class);
	}
	
	
}
