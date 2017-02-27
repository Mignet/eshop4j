package com.linkwee.api.controller.crm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.crm.IconRequest;
import com.linkwee.api.response.crm.InvestorPersonCenterResponse;
import com.linkwee.api.response.crm.PersonCenterResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.InvestorPersonCenterResp;
import com.linkwee.web.model.crm.PersonCenterResp;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmCfpNewcomerTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.WebUtil;

/**
 * 个人中心
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/personcenter")
public class PersonCenterController extends BaseController{
	
    @Resource
    private CrmCfplannerService crmCfplannerService;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
    private CimProductInvestRecordService investRecordService;
    
    @Resource
    private PartnerService partnerService;
    
    @Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
    
    @Resource
	private SysMsgService msgService;
    
    @Resource 
    private TCFeeDetailService feeDetailService ;

    @Resource
	private CimOrginfoService cimOrginfoService;
    
    @Resource
    private RedPacketService redPacketService;
    
    @Resource
	private AcAccountBindService accountbindService;
    
    @Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
    
    @Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
    
    @Resource
	private ActRedpacketService actRedpacketService;
    
    /**
	 * 个人中心-首页
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("homepage")
	@ResponseBody
	public BaseResponse homepage(AppRequestHead head) throws Exception {
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		logger.debug("个人中心首页信息: userId = " + userId + "; appType = " + appType);
		Map<String,Object> conditions = Maps.newHashMap();//未读消息查询条件
		conditions.put("userId", userId);
        conditions.put("appType",appType);
        //限制读取注册后发布的公告信息
        //regTime 注册的投资人和理财师只能看到自己注册之后的系统公告
        if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
        	CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
        	if(crmInvestor!= null && crmInvestor.getCreateTime() != null ){
        		conditions.put("regTime", DateUtils.format(crmInvestor.getCreateTime(), DateUtils.FORMAT_LONG));
        	}
        }
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
			SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_PERSONCENTER_PARTNER,userId,appType);
			Date date = null;
			if(apiInvokeLog!=null){
				date = apiInvokeLog.getChgTime();
			}
			PersonCenterResp rlt = new PersonCenterResp();
			rlt.setUserName(cfplanner.getUserName());
			rlt.setMobile(cfplanner.getMobile());
			rlt.setIsBindBankCard(accountbindService.isbindBankcard(userId));//是否已绑定银行卡
			rlt.setHeadImage(cfplanner.getHeadImage());
			rlt.setLevelExperience(cfplanner.getLevelExperience());
			
			//TODO
			rlt.setAccountBalance(accountbindService.queryAccountBalance(userId));//账户余额
			if(cfplanner.getCfpLevel() != null) {
				rlt.setCfgLevelName(CfpLevelEnum.valueOf(cfplanner.getCfpLevel()).getMsg());
			}
			PersonCenterResp p = feeDetailService.queryCfplannerMonthProfitTotal(userId);
			rlt.setFeeProfit(new BigDecimal(p.getFeeProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月佣金收益
			rlt.setRecommendProfit(new BigDecimal(p.getRecommendProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月推荐收益
			rlt.setMonthProfit(new BigDecimal( p.getMonthProfit()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());//本月总收益
			rlt.setTodayProfit(p.getTodayProfit());//今日收益
			rlt.setHistoryProfit(p.getHistoryProfit());//累计收益
			rlt.setHongbaoCount(redPacketService.queryCfplannerRedPacketCount(userId));//可用红包数量
			//未读消息数量
			int bulletinMsgCount = msgService.queryUnreadBulletinCount(conditions);
			int personMsgCount = msgService.queryUnreadLcsCount(userId, AppTypeEnum.CHANNEL.getKey());
			rlt.setMsgCount(bulletinMsgCount + personMsgCount);
			rlt.setBulletinMsgCount(bulletinMsgCount);
			rlt.setPersonMsgCount(personMsgCount);
			rlt.setNewPartnerCount(partnerService.queryNewPartnerCount(userId, date));//新团队成员数量
			rlt.setWithdrawAmount(accountbindService.queryWithdrawingAmount(userId));//提现中金额
			rlt.setUnRecordInvestCount(cimProductUnrecordInvestService.getCfplannerUnrecordInvestCount(userId));//保单记录数
			rlt.setUnFinishNewcomerTaskCount(crmCfpNewcomerTaskService.queryUnFinishNewcomerTaskCount(userId));//未完成新手任务数量
			return AppResponseUtil.getSuccessResponse(rlt,PersonCenterResponse.class);
		} else {
			CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
			InvestorPersonCenterResp rlt = new InvestorPersonCenterResp();
			rlt.setUserName(crmInvestor.getUserName());
			rlt.setMobile(crmInvestor.getMobile());
			rlt.setHeadImage(crmInvestor.getHeadImage());
			rlt.setIsBindBankCard(accountbindService.isbindBankcard(userId));//是否已绑定银行卡
			
			//TODO
			rlt.setAccountBalance(accountbindService.queryAccountBalance(userId));//账户余额
			rlt.setHongbaoCount(redPacketService.queryInvestorRedPacketCount(userId));//可用红包数量
			rlt.setInvestAmount(investRecordService.queryCustomerInvestTotalAmount(userId));//投资总额
			//未读消息数量
			if(appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
		        conditions.put("platform", AppUtils.getPlatform(head.getOrgNumber()).getKey());
		    }
			PlatformEnum platform =  WebUtil.getPlatform(head.getOrgNumber());
			if(PlatformEnum.WEB.equals(platform)){
				rlt.setMsgCount(msgService.queryUnreadLcsCount(userId, AppTypeEnum.INVESTOR.getKey()));
			} else {
				rlt.setMsgCount(msgService.queryUnreadBulletinCount(conditions) + msgService.queryUnreadLcsCount(userId, AppTypeEnum.INVESTOR.getKey()));
			}
			rlt.setTotalProfit(investRecordService.queryCustomerInvestTotalProfit(userId));//总收益
			rlt.setWithdrawAmount(accountbindService.queryWithdrawingAmount(userId));//提现中金额
			rlt.setOrgAccountCount(cimOrginfoService.queryOrgAccountCount(userId));//绑定机构帐号数
			rlt.setUnBindOrgAccountCount(cimOrginfoService.queryOrgCount() - rlt.getOrgAccountCount());//未绑定机构数
			return AppResponseUtil.getSuccessResponse(rlt,InvestorPersonCenterResponse.class);
		}
		
		
	}
	
	/**
	 * 上传头像
	 * 
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/icon")
	@ResponseBody
	public BaseResponse uploadIcon(@Valid IconRequest iconRequest,AppRequestHead appRequestHead) throws Exception {
		
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		String headImage = iconRequest.getImage();
		if(AppUtils.isChannelApp(appRequestHead.getOrgNumber())){
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(userId);
			crmCfplanner.setHeadImage(headImage);
			crmCfplannerService.updateByUserId(crmCfplanner);
		}
		else if(AppUtils.isInvestorApp(appRequestHead.getOrgNumber())){
			CrmInvestor crmInvestor = new CrmInvestor();
			crmInvestor.setUserId(userId);
			crmInvestor.setHeadImage(headImage);
			crmInvestorService.updateByUserId(crmInvestor);
		}else{
			return AppResponseUtil.getErrorBusi(new BaseResponse("uploadIconFail","上传头像更新数据库既不是理财师也不是投资者端"));
		}
		
		//理财师首次上传头像送红包
		if(cfp != null && cfp.getHeadImage() == null ) {
			//TODO
			CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(userId);
			try {
				actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.CFPLANNER_INEWCOMERWELFARE_UPLOAD_HEADIMAGE, userInfo);
			} catch (Exception e) {
				logger.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
			}
		}
		
		return AppResponseUtil.getSuccessResponse();
	}
	
	
	
}
