package com.linkwee.web.controller.acc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.SmMessageTemplate;
import com.linkwee.web.model.User;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcOfflineRewardDraft;
import com.linkwee.web.model.crm.RewardDataImport;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.request.AcOfflineRewardDraftRequest;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcOfflineRewardDraftService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： AcOfflineRewardDraftController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年01月03日 16:16:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/acofflinerewarddraft")
@RequestLogging("AcOfflineRewardDraftController控制器")
public class AcOfflineRewardDraftController  extends BaseController {


	@Resource
	private AcOfflineRewardDraftService acOfflineRewardDraftService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	@Resource
	private AcAccountBindService accountbindService;
	@Resource
	private SmMessageQueueService smMessageQueueService;
	@Resource
	private SysMsgService sysMsgService;
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
	private WeiXinMsgService weiXinMsgService;

    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String acOfflineRewardDraft(Model model) {
    	List<String> list = acOfflineRewardDraftService.queryNotGrantBatchList();
    	model.addAttribute("batchList", list);
    	return "acofflinerewarddraft/acofflinerewarddraft-list";
    }

    /**
     * 线下奖励明细
     */
    @RequestMapping("getAcOfflineRewardDraft")
    @ResponseBody
	public DataTableReturn getAcOfflineRewardDraft(AcOfflineRewardDraftRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = acOfflineRewardDraftService.queryAcOfflineRewardDraft(dataTable,req);
		return tableReturn;
	}
    
    
    /**
     * 录入数据页面
     */
    @RequestMapping(value="importPage")
    public String importPage(Model model,String salesOrgId) {
    	return "acofflinerewarddraft/importPage";
    }
    
    
    @RequestMapping(value="importRewardData")
    @ResponseBody
    @RequestLogging("录入奖励数据")
	public Object importRewardData(HttpServletRequest request,HttpSession session) {   
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
    		String rewardName = multipartRequest.getParameter("rewardName");
    		String profitType = multipartRequest.getParameter("profitType");
    		String rewardTime = multipartRequest.getParameter("rewardTime");
    		Date rewardTimeDate = DateUtils.parse(rewardTime,DateUtils.FORMAT_SHORT);
            MultipartFile file  =  multipartRequest.getFile("file");
            Set<String> msg = null;
            InputStream inputStream = file.getInputStream();
            List<RewardDataImport>  cfpList = PoiImport.dataImport(inputStream, RewardDataImport.class);
            if(cfpList == null || cfpList.size() == 0) {
            	return new ResponseResult(false,"导入失败，数据为空");
            }
			String date = DateUtils.format(new Date(), "yyyyMMdd");
			String batch = rewardName + date;
			List<AcOfflineRewardDraft> list = new ArrayList<AcOfflineRewardDraft>();
			CrmUserInfo userInfo = null;
            for(RewardDataImport rewardDataImport : cfpList) {
            	
            	String mobile = rewardDataImport.getMobile();
				if(mobile == null || mobile.length() != 11){
					return new ResponseResult(false,"【 " + mobile + " 】手机号码长度不对");
				}
				userInfo = crmUserInfoService.selectCrmUserInfoByMobile(rewardDataImport.getMobile());
				if(userInfo == null){
					return new ResponseResult(false,"【 " + mobile + " 】用户不存在");
				}
				AcAccountBind ac = accountbindService.selectAccountByUserId(userInfo.getUserId());
				if(ac==null){
					return new ResponseResult(false,"【 " + mobile + " 】用户没有账号记录");
				} 
				String profit = rewardDataImport.getAmount();
				Double sussessSum = 0.0;
				try {
					sussessSum = sussessSum + Double.parseDouble(profit);
				} catch (Exception e) {
					return new ResponseResult(false,"Excel表格【收益】有非法字符,请输入数字");
				}
				
				AcOfflineRewardDraft draftCheckRepeat = new AcOfflineRewardDraft();
				draftCheckRepeat.setBatch(batch);
				draftCheckRepeat.setUserId(userInfo.getUserId());
				draftCheckRepeat.setTransAmount(BigDecimal.valueOf(Double.valueOf(rewardDataImport.getAmount())));
				List<AcOfflineRewardDraft> listCheckRepeat = acOfflineRewardDraftService.selectListByCondition(draftCheckRepeat);
				if(listCheckRepeat != null && listCheckRepeat.size() > 0){
					return new ResponseResult(false,"【 " + mobile + " 】同批次有重复数据,请确认是否重复发放");
				} 
				
            	AcOfflineRewardDraft draft = new AcOfflineRewardDraft();
            	draft.setCreatePerson(user.getUsername());
            	draft.setLastUpdatePerson(user.getUsername());
            	draft.setBatch(batch);
            	draft.setCreateTime(new Date());
            	draft.setLastUpdateTime(new Date());
            	draft.setMobile(userInfo.getMobile());
            	draft.setRemark(rewardName);
            	draft.setTransAmount(BigDecimal.valueOf(Double.valueOf(rewardDataImport.getAmount())));
            	draft.setTransType(Integer.parseInt(profitType));
            	draft.setUserId(userInfo.getUserId());
            	draft.setUserType(Integer.parseInt(profitType) == 12 || Integer.parseInt(profitType) == 14 
            			|| Integer.parseInt(profitType) == 15 ? 1 : 2);
            	draft.setRewardTime(rewardTimeDate);
            	list.add(draft);
            }
            acOfflineRewardDraftService.inputRewardData(list);
    		return new ResponseResult(true, "导入成功", msg);
		}catch (ServiceException e) {
			logger.error("import raward data exception : {}", e.getMessage());
			return new ResponseResult(false, e.getMessage());
		} catch (Exception e) {
			logger.error("import raward data exception : {}", e.getMessage());
		}
    	return new ResponseResult(false, "导入失败");
	}
    
    
    /**
   	 * 下载导入模板
   	 * @param response
   	 * @param request
   	 * @throws FileNotFoundException
   	 */
   	@RequestMapping(value = "/downloadExcelTemplate")
   	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
   		logger.info("下载销售机构导入理财师Excel模板");
   		// 下载本地文件
   		String fileName = "reward_data.xls";
   		// 读到流中
   		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
   		InputStream inStream=null;
   		OutputStream outStream=null;
   		try {
   			inStream = new FileInputStream(path+ "/xls/acc/reward_data.xls");// 文件的存放路径
   			response.reset();
   			response.setContentType("multipart/form-data");
   			response.setCharacterEncoding("UTF-8");
   			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(), "ISO8859-1") + "\"");
   			outStream=response.getOutputStream();
   			byte[] b = new byte[100];
   			int len;
   			while ((len = inStream.read(b)) > 0)
   				outStream.write(b, 0, len);
   		} catch (IOException e) {
   			logger.error("下载奖励发放Excel模板异常",e);
   		}finally{
   			try {
   				if(inStream!=null){
   					inStream.close();
   				}
   				if(outStream!=null){
   					outStream.close();
   				}
   			} catch (IOException e) {
   				logger.error("下载奖励发放Excel模板关闭输入流时出现异常",e);
   			}
   		}
   	}
   	
   	/**
     * 奖励发放页面
     */
    @RequestMapping(value="grantRewardPage")
    public String grantRewardPage(Model model,String salesOrgId) {
    	List<String> list = acOfflineRewardDraftService.queryNotGrantBatchList();
    	model.addAttribute("batchList", list);
    	return "acofflinerewarddraft/grantRewardPage";
    }
    
    @RequestMapping(value="grantReward")
    @ResponseBody
    @RequestLogging("发放奖励")
	public Object grantReward(HttpServletRequest request,HttpSession session) {   
    	try {
    		User adminuser = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
    		String batch = multipartRequest.getParameter("batch");
    		
    		AcOfflineRewardDraft draftForSearch = new AcOfflineRewardDraft();
    		draftForSearch.setBatch(batch);
    		draftForSearch.setStatus(0);
    		
    		List<AcOfflineRewardDraft> list = acOfflineRewardDraftService.selectListByCondition(draftForSearch);
    		
    		if(list == null || list.size() == 0) {
    			return new ResponseResult(false, "【 " + batch + " 】 已发放，请勿重复操作");
    		}
    		
    		acOfflineRewardDraftService.grantReward(list, adminuser);
    		
    		//发消息
    		//消息推送
    		final Set<String> userIds = Sets.newConcurrentHashSet();
    		final List<SysMsg> sysMsgs = Lists.newArrayList();
    		final List<SysPushMessage> pushMsgs = Lists.newArrayList();
    		final List<SmMessageQueue> messages = Lists.newArrayList();
    		//消息模板
    		SmMessageTemplate condit = new SmMessageTemplate();
    		condit.setModuleId( MsgModuleEnum.ACTIVITYREWARDRELEASE.getValue());
    		condit.setAppType(AppTypeEnum.INVESTOR.getKey());//提现模板 理财师和投资的一致
    		SmMessageTemplate messageTmp = smMessageQueueService.queryMessageTemplete(condit);
    		
    		//封装推送消息体
    		for(AcOfflineRewardDraft bo : list) {
    			userIds.add(bo.getUserId());
    			if(messageTmp == null) continue;
    			String content = String.format(messageTmp.getContent(),bo.getUserType().intValue() == 1? "猎财" : "T呗",bo.getRemark(),bo.getTransAmount().setScale(2, BigDecimal.ROUND_DOWN));
    			sysMsgs.add(sysMsgService.fillSysMsg(bo.getUserType(),PersonalMsgTypeEnum.PAYMENTRETURN,bo.getUserId(),content));
    			messages.add(smMessageQueueService.fillSmMessageQueue(bo.getUserType(),bo.getMobile(),content,MsgModuleEnum.ACTIVITYREWARDRELEASE));
    			pushMsgs.add(new SysPushMessage(bo.getUserId(),"活动奖励发放",content));
    		}
			
			//消息中心代码
    		final Integer transType = list.size()>0 ? list.get(0).getTransType() : null;	
			if(list!= null && list.size()>0){
				ThreadpoolService.execute(new Runnable() {			
					@Override
					public void run() {
						boolean needPushMsg = false;//关联理财师账户则推送通知栏
						if(transType.intValue() == 14 || transType.intValue() == 12 || transType.intValue() == 15){
							needPushMsg = true;
						}
						//短信通知
						smMessageQueueService.batchSendDiffContentMessageAndPmsg(messages,true,sysMsgs);
						//通知栏推送
						if(needPushMsg){ 	
							Map<String,Object> urlparam = Maps.newHashMap();
							urlparam.put("profitType", "3");
							urlparam.put("month", DateUtils.getMonth());
							pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LFEERECEIVED,urlparam,pushMsgs, null);
						}
					}
				});
			}
			
    		weiXinMsgService.sendGrantRewardList(list);
    		
    		return new ResponseResult(true,"发放成功");
		}catch (ServiceException e) {
			logger.error("grantReward exception : {}", e.getMessage());
			return new ResponseResult(false, e.getMessage());
		} catch (Exception e) {
			logger.error("grantReward exception : {}", e.getMessage());
		}
    	return new ResponseResult(false, "发放失败");
	}


	
}
