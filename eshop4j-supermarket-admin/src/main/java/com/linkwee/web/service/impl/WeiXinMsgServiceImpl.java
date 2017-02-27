package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.acc.AcOfflineRewardDraft;
import com.linkwee.web.model.mc.SmWeixinmsgTemplate;
import com.linkwee.web.model.weixin.First;
import com.linkwee.web.model.weixin.Keyword1;
import com.linkwee.web.model.weixin.Keyword2;
import com.linkwee.web.model.weixin.Keyword3;
import com.linkwee.web.model.weixin.Keyword4;
import com.linkwee.web.model.weixin.Remark;
import com.linkwee.web.model.weixin.TemplateEntity;
import com.linkwee.web.model.weixin.WeiXinEntity;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SmWeixinmsgTemplateService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.HttpClientUtil;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("weiXinMsgService")
public class WeiXinMsgServiceImpl implements WeiXinMsgService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMsgServiceImpl.class);
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private SmWeixinmsgTemplateService smWeixinmsgTemplateService;
	
	@Override
	public void sendWeiXinMsgCommon(WeiXinMsgRequest req) {
		String swicth = sysConfigService.getValuesByKey("2".equals(req.getUseType())?SysConfigConstant.TOOBEI_WEIXIN_SWICTH:SysConfigConstant.LIECAI_WEIXIN_SWICTH);
		if(swicth!=null&&"ON".equals(swicth)){
			SmWeixinmsgTemplate t = new SmWeixinmsgTemplate();
			t.setTemkey(req.getTemkey());
			t.setAppType(Integer.parseInt(req.getUseType()));
			SmWeixinmsgTemplate rt = smWeixinmsgTemplateService.selectOne(t);
			//先判断模板是否存在
			if(req!=null&&req.getUseId()!=null&&req.getTemkey()!=null&&rt!=null){
				String openId = null;
				String mobile = null;
				String ret = null;
				if("1".equals(req.getUseType())){
					CrmCfplanner lcs = crmCfplannerService.queryCfplannerByUserId(req.getUseId());
					openId = lcs!=null?lcs.getWeiXinOpenId():null;
					mobile = lcs!=null?lcs.getMobile():null;
				}else{
					CrmInvestor inv = crmInvestorService.queryInvestorByUserId(req.getUseId());
					openId = inv!=null?inv.getWeiXinOpenId():null;
					mobile = inv!=null?inv.getMobile():null;
				}
				String accToken = sysConfigService.getValuesByKey(("2".equals(req.getUseType())?SysConfigConstant.TOOBEI_ACCTOKEN:SysConfigConstant.LIECAI_ACCTOKEN).trim());
				String url = sysConfigService.getValuesByKey(("2".equals(req.getUseType())?SysConfigConstant.TOOBEI_SEND_MSG_URL:SysConfigConstant.LIECAI_SEND_MSG_URL).trim());
				
				//判断用户的微信openId是否存在
				if(openId!=null&&!"".equals(openId)&&accToken!=null&&url!=null){
					String color = "#173177";
					
					JSONObject jsonParam = new JSONObject();
					jsonParam.put("touser", openId);
			        jsonParam.put("template_id", rt.getTemplateId());
			        jsonParam.put("url", rt.getLinkUrl());
			        jsonParam.put("topcolor", color);
			        TemplateEntity te = new TemplateEntity();
			        First first = new First();
			        first.setValue(rt.getFirst());
			        first.setColor(color);
			        te.setFirst(first);
			        Keyword1 keyword1 =new Keyword1();
			        keyword1.setColor(color);
			        Keyword2 keyword2 =new Keyword2();
			        keyword2.setColor(color);
			        Keyword3 keyword3 =new Keyword3();
			        keyword3.setColor(color);
			        Keyword4 keyword4 =new Keyword4();
			        keyword4.setColor(color);
			        Remark remark = new Remark();
			        if(SysConfigConstant.INVITATION_REGISTER_SUCCESS.equals(req.getTemkey())){//邀请注册成功通知
			        	keyword2.setValue(mobile);//账户
			        	keyword3.setValue(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//注册时间
			        	te.setKeyword3(keyword3);
			        	String crmMobile = req.getRecommendMobile();//推荐人手机号码
			        	remark.setValue(String.format(rt.getRemark(),crmMobile.substring(crmMobile.length()-4,crmMobile.length())));
			        }else if(SysConfigConstant.INVESTMENT_SUCCESS.equals(req.getTemkey())){//投资成功通知
			        	keyword1.setValue(req.getProductName());//项目名称
			        	keyword2.setValue(req.getReturnRate());//预期年化收益率
			        	keyword3.setValue(req.getTerm());//项目期限
			        	keyword4.setValue(req.getAmount());//投资金额
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	//少了平台名称
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.RECOMMEND_SUCCESS.equals(req.getTemkey())){//推荐成功通知  理财师推荐产品/平台
			        	keyword1.setValue(req.getRecommendPerson());//推荐人
			        	keyword2.setValue(req.getRecommendedPerson());//被推荐人
			        	remark.setValue(String.format(rt.getRemark(), req.getProductName()));
			        }else if(SysConfigConstant.PAYMENT_REMINDER.equals(req.getTemkey())
			        		||SysConfigConstant.SOON_PAYMENT_REMINDER.equals(req.getTemkey())
			        		||SysConfigConstant.PAYMENT_REMINDER_ACTIVE.equals(req.getTemkey())){//回款提醒(产品剩余3天到期回款)
//			        	keyword1.setValue(req.getPaymentNumber());//回款编号
//			        	keyword2.setValue(req.getPaymentDate());//回款日期
//			        	keyword3.setValue(req.getCustomer());//客户
//			        	keyword4.setValue(req.getAmount());//回款额
			        	keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getPaymentDate());//回款时间
			        	keyword4.setValue(req.getAmount());//回款金额
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(String.format(rt.getRemark(), req.getRedPacketAmt()));
			        	first.setValue(String.format(rt.getFirst(),req.getUserName()));
				        te.setFirst(first);
			        }else if(SysConfigConstant.WITHDRAWALS_ACCOUNT.equals(req.getTemkey())){//提现到账通知
			        	keyword1.setValue(req.getWithdrawAmount());//提现金额
			        	keyword2.setValue(req.getAccount());//提现账户
			        	keyword3.setValue(req.getWithdrawTime());//提现时间
			        	keyword4.setValue(req.getNoticeTime());//到账时间
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.APPLY_WITHDRAWALS_ACCOUNT.equals(req.getTemkey())){//提现申请通知
			        	keyword1.setValue(req.getNickName());//昵称
			        	keyword2.setValue(req.getWithdrawTime());//提现时间
			        	keyword3.setValue(req.getWithdrawAmount());//金额
			        	keyword4.setValue(req.getWithdrawType());//提现方式
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.BIND_SUCCESS.equals(req.getTemkey())){//绑定成功通知 重新绑定理财师
			        	keyword1.setValue(req.getBindName());//绑定姓名
			        	keyword2.setValue(req.getBindAccount());//绑定账户
			        	keyword3.setValue(req.getBindTime());//绑定时间
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.RELEASE_RELATIONSHIP.equals(req.getTemkey())){//绑定成功通知 重新绑定理财师
			        	keyword1.setValue(req.getUserName());//用户名
			        	keyword2.setValue(req.getUserType());//用户类型
			        	keyword3.setValue(req.getReleaseTime());//解绑时间
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.OPEN_THIRD_ACCOUNT_SUCCESS.equals(req.getTemkey())){//第三方账户开设成功通知
			        	keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getOpenTime());//开通时间
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION.equals(req.getTemkey())
			        		||SysConfigConstant.ARRIVAL_REMINDER_LEADER.equals(req.getTemkey())
			        		||SysConfigConstant.ARRIVAL_REMINDER_RECOMMEND.equals(req.getTemkey())){//到账提醒-佣金
			        	keyword1.setValue(req.getArrivalAmount());//到账金额
			        	keyword2.setValue(req.getArrivalTime());//到账时间
			        	keyword3.setValue(req.getArrivalDetail());//到账详情
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.ACTIVITY_COMPLETE.equals(req.getTemkey())){//活动完成通知:用于活动奖励
			        	keyword1.setValue(req.getActivityName());//活动名称
			        	keyword2.setValue(req.getActivityTime());//奖励金额
			        	remark.setValue(String.format(rt.getRemark(), req.getActivityAmount()));
			        }else if(SysConfigConstant.GRADE_CHANGE.equals(req.getTemkey())){//等级变更通知
			        	keyword1.setValue(req.getUserName());//用户姓名
			        	keyword2.setValue(req.getChangeType());//变更类型
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.AUDIT_NOT_PASSED.equals(req.getTemkey())||
			        		SysConfigConstant.AUDIT_PASSED.equals(req.getTemkey())){//报单审核未通过
			        	keyword1.setValue(req.getReason());//原因or结果
			        	keyword2.setValue(req.getAuditTime());//审核时间
			        	remark.setValue(String.format(rt.getRemark(), req.getRemark()));
			        }else{//注册成功通知register_success
			        	keyword1.setValue(mobile);//用户名
			        	keyword2.setValue(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//注册时间
			        	remark.setValue(rt.getRemark());
			        }
			        te.setKeyword1(keyword1);
			        te.setKeyword2(keyword2);
			        remark.setColor(color);
			        te.setRemark(remark);
			        jsonParam.put("data", te);
			        try {
						ret = HttpClientUtil.httpPost(String.format(url, accToken) , jsonParam);
					} catch (Exception e) {
						LOGGER.info("微信推送【{}】失败ret【{}】",rt.getTitle(),ret);
					}
			        WeiXinEntity wx = (WeiXinEntity) JSON.parseObject(ret, WeiXinEntity.class); 
			        if(wx!=null&&!"0".equals(wx.getErrcode())){
			        	String retoken = null;
			        	if("1".equals(req.getUseType())){
			        		retoken = updateWeiXinAccTokenLieCai();
			        	}else{
			        		retoken = updateWeiXinAccToken();
			        	}
			        	try {
							ret = HttpClientUtil.httpPost(String.format(url, retoken) , jsonParam);
						} catch (Exception e) {
							LOGGER.info("微信二次推送【{}】失败ret【{}】",rt.getTitle(),ret);
						}
			        }
			        LOGGER.info("微信推送【{}】ret【{}】",req.getTemkey(),ret);
				}
				
			}
		}
	}

	@Override
	public String updateWeiXinAccToken() {
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_WEIXIN_SWICTH);
		if(swicth!=null&&"ON".equals(swicth)){
			
			String appID = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_APPID);
			String appSecret = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_APPSECRET);
			String url = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_QUERY_ACCTOKEN_URL);
			
			String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret));
			LOGGER.info("微信返回数据{}",ret);
			WeiXinEntity wx = (WeiXinEntity) JSON.parseObject(ret, WeiXinEntity.class); 
			if(wx!=null&&wx.getAccess_token()!=null){
				sysConfigService.updateSysConfigByKey(SysConfigConstant.TOOBEI_ACCTOKEN, wx.getAccess_token(), new Date());
				LOGGER.info("更新T呗微信ACCTOKEN配置成功!");
				return wx.getAccess_token();
			}
		}
		return null;
	}
	
	@Override
	public String updateWeiXinAccTokenLieCai() {
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_WEIXIN_SWICTH);
		if(swicth!=null&&"ON".equals(swicth)){
			
			String appID = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_APPID);
			String appSecret = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_APPSECRET);
			String url = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_QUERY_ACCTOKEN_URL);
			
			String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret));
			LOGGER.info("微信返回数据{}",ret);
			WeiXinEntity wx = (WeiXinEntity) JSON.parseObject(ret, WeiXinEntity.class); 
			if(wx!=null&&wx.getAccess_token()!=null){
				sysConfigService.updateSysConfigByKey(SysConfigConstant.LIECAI_ACCTOKEN, wx.getAccess_token(), new Date());
				LOGGER.info("更新猎财大师微信ACCTOKEN配置成功!");
				return wx.getAccess_token();
			}
		}
		return null;
	}

	@Override
	public void sendWeiXinMsg(WeiXinMsgRequest req) {
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_WEIXIN_SWICTH);
		
		String accToken = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_ACCTOKEN);
		String ret = null; 
		//微信推送消息开关
		if(swicth!=null&&"ON".equals(swicth)){
			//先查出用户的OPENID  "openid":"oKuobw3A4MRt-awOQNR-5uxhp-hc","nickname":"容天"
			String openId = null;
			String mobile = null;
			if("2".equals(req.getUseType())){
				CrmInvestor inv = crmInvestorService.queryInvestorByUserId(req.getUseId());
				openId = inv.getWeiXinOpenId();
				mobile = inv.getMobile();
			}else if("1".equals(req.getUseType())){
				CrmCfplanner lcs = crmCfplannerService.queryCfplannerByUserId(req.getUseId());
				openId = lcs.getWeiXinOpenId();
				mobile = lcs.getMobile();
			}
			//注册成功通知
			JSONObject jsonParam = new JSONObject();
			String templateId = "tV97Gv3Kl_76UK6svvrVFvftCTBw9yaW47PQdAnaNlg";//"f5TObpaUc8E_3ybT5CBQhW34ZtQ-TjdPCazwcfqt6Yk";
			jsonParam.put("touser", openId);//"oKuobw0MXgjUYumbyAjmFcjYYHNQ");
	        jsonParam.put("template_id", templateId);
	        jsonParam.put("url", "https://www.toobei.com/app/pages/user/login.html");
	        jsonParam.put("topcolor", "#173177");
	        TemplateEntity t = new TemplateEntity();
	        First first = new First();
	        first.setValue("您已成功注册投呗平台！");
	        first.setColor("#173177");
	        t.setFirst(first);
	        Keyword1 keyword1 =new Keyword1();
	        keyword1.setValue(mobile);
	        keyword1.setColor("#173177");
	        Keyword2 keyword2 =new Keyword2();
	        keyword2.setValue(new Date().toString());
	        keyword2.setColor("#173177");
	        t.setKeyword1(keyword1);
	        t.setKeyword2(keyword2);
	        Remark remark = new Remark();
	        remark.setValue("欢迎加入T呗！为给您提供最优质的服务，我们已为您匹配专属理财师，投资问题随时互动，轻松开启专业理财！   ");
	        remark.setColor("#173177");
//	        t.setName(name);
	        t.setRemark(remark);
	        jsonParam.put("data", t);
	        try {
				ret = HttpClientUtil.httpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ accToken, jsonParam);
			} catch (Exception e) {
				LOGGER.info("微信推送【注册成功通知】失败");
				e.printStackTrace();
			}
		}else{
			SmWeixinmsgTemplate t = new SmWeixinmsgTemplate();
			t.setTemkey("register_success");
			SmWeixinmsgTemplate rt = smWeixinmsgTemplateService.selectOne(t);
			System.out.println(rt);
			
		}
		System.out.println(ret);
	}

	@Override
	public void sendGrantRewardList(final List<AcOfflineRewardDraft> list) {
		if(list.size()<=0) return;
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				for(AcOfflineRewardDraft acoff:list){
					WeiXinMsgRequest wx = new WeiXinMsgRequest();
					wx.setUseId(acoff.getUserId());
					if(acoff.getUserType()==1&&acoff.getTransType()==14
							||acoff.getUserType()==2&&acoff.getTransType()==14){//活动奖励
						wx.setTemkey(SysConfigConstant.ACTIVITY_COMPLETE);//活动完成通知 猎财
						wx.setUseType(acoff.getUserType().toString());
						wx.setActivityName(acoff.getRemark());//活动名称
//						wx.setActivityTime(DateUtils.format(acoff.getCreateTime(),DateUtils.FORMAT_LONG));//活动时间
						wx.setActivityAmount(NumberUtils.getFormat(acoff.getTransAmount(), "0.00")+"元");//奖励金额 
					}else if(acoff.getUserType()==1&&acoff.getTransType()==12){//到账提醒(佣金)
						wx.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION);
						wx.setUseType("1");
						wx.setArrivalAmount(NumberUtils.getFormat(acoff.getTransAmount(), "0.00")+"元");//到账金额
						wx.setArrivalTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//到账时间
						wx.setArrivalDetail(acoff.getRemark());//到账详情 xxx年xx月佣金
					}else if(acoff.getUserType()==1&&acoff.getTransType()==15){//到账提醒(leader奖励)
						wx.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_LEADER);
						wx.setUseType("1");
						wx.setArrivalAmount(NumberUtils.getFormat(acoff.getTransAmount(), "0.00")+"元");//到账金额
						wx.setArrivalTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//到账时间
						wx.setArrivalDetail(acoff.getRemark());//到账详情 xxx年xx月leader奖励
					}else{
						continue;
					}
					sendWeiXinMsgCommon(wx);
				}
			}
		});
	}
	
	@Override
	public void sendWeiXinMsgListCommon(List<WeiXinMsgRequest> reqList) {
		for(WeiXinMsgRequest req: reqList){
			sendWeiXinMsgCommon(req);
		}
		
	}

}
