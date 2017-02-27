package com.linkwee.api.controller.mc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.linkwee.api.request.mc.CustomerDeviceRequest;
import com.linkwee.api.request.mc.MsgDelRequest;
import com.linkwee.api.request.mc.MsgDetailRequest;
import com.linkwee.api.response.mc.CustomerDeviceResponse;
import com.linkwee.api.response.mc.MsgResp;
import com.linkwee.api.response.mc.NoticeDetailResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.MsgCount;
import com.linkwee.web.model.SmCustomerDevice;
import com.linkwee.web.model.mc.SysNotice;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SmCustomerDeviceService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.SysNoticeReadLogService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：个人中心-消息中心
 *
 * @author chenchy
 * @时间 2015年10月16日上午11:06:20
 *
 */
@Controller
@RequestMapping(value = "/api/msg")
@RequestLogging("站内消息")
public class MsgController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MsgController.class);
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
	private SysMsgService msgService;
	
	@Resource
	private SysApiInvokeLogService apiInvokeLogService;
	@Resource
	private SmCustomerDeviceService customerDeviceService;
	@Resource
	private SysNoticeReadLogService sysNoticeReadLogService;
	@Resource
	private CrmInvestorService crmInvestorService;
	
	/**
	 * 公告消息-分页
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("站内公告列表")
	@RequestMapping("/bulletin/pageList")
	@ResponseBody
	public BaseResponse bulletinPageList(PaginatorRequest req,AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		LOGGER.info("bulletinPageList输入参数：userId={},appType={}",userId,appType);	
		Page<MsgResp> page  = new Page<MsgResp>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		conditions.put("msgType", "sys");//查询系统消息（公告）
        conditions.put("type", MsgTypeEnum.SYS.getKey());
        conditions.put("appType", appType);
        conditions.put("userNumber", userId);
        if(appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
        conditions.put("platform", AppUtils.getPlatform(head.getOrgNumber()).getKey());
        }
        //regTime 注册的投资人和理财师只能看到自己注册之后的系统公告
        if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
        	CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
        	if(crmInvestor!= null && crmInvestor.getCreateTime() != null ){
        		conditions.put("regTime", DateUtils.format(crmInvestor.getCreateTime(), DateUtils.FORMAT_LONG));
        	}
        }
        
        
		PaginatorResponse<MsgResp> orgdatas = msgService.queryMsgResp(page, conditions);
		
		 return AppResponseUtil.getSuccessResponse(orgdatas);
	}
	
	/**
	 * 个人消息-分页
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("站内个人消息列表")
	@RequestMapping("/person/pageList")
	@ResponseBody
	public BaseResponse personPageList(PaginatorRequest req,AppRequestHead head) throws Exception {
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		Page<MsgResp> page  = new Page<MsgResp>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
		 conditions.put("userNumber", userId);
        conditions.put("type", MsgTypeEnum.SYS.getKey());
        conditions.put("appType",appType);
        conditions.put("msgType", "person");////查询个人消息（通知）
        PlatformEnum platform =  WebUtil.getPlatform(head.getOrgNumber());
		if(PlatformEnum.WECHAT.equals(platform) && appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
			conditions.put("needUpdateReadStatus", "0");
		}else{
			conditions.put("needUpdateReadStatus", "1");
		}
		PaginatorResponse<MsgResp> msgdatas = msgService.queryMsgResp(page, conditions);
		 return AppResponseUtil.getSuccessResponse(msgdatas);
		
	}
	
	
	/**
	 * 个人消息-删除
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("删除个人消息")
	@RequestMapping("/person/del")
	@ResponseBody
	public BaseResponse personDel(@Valid MsgDelRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String[] idArr = req.getMsgIds().split(",");
		msgService.deletePersonMsgs(idArr, userId);
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 未读消息统计
	 * @Date 2016年1月25日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("站内未读消息统计")
	@RequestMapping("/person/unreadCount")
	@ResponseBody
	public BaseResponse unreadCount(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		MsgCount msgCount = new MsgCount();
		Map<String,Object> conditions = Maps.newHashMap();//查询条件
		conditions.put("userId", userId);
        conditions.put("appType",appType);		
        if(appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
        conditions.put("platform", AppUtils.getPlatform(head.getOrgNumber()).getKey());
        }
      //regTime 注册的投资人和理财师只能看到自己注册之后的系统公告
        if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
        	CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
        	if(crmInvestor!= null && crmInvestor.getCreateTime() != null ){
        		conditions.put("regTime", DateUtils.format(crmInvestor.getCreateTime(), DateUtils.FORMAT_LONG));
        	}
        }
		Integer bulletinCount = msgService.queryUnreadBulletinCount(conditions);
		Integer personCount = msgService.queryUnreadLcsCount(userId, appType);
		if(null !=bulletinCount && bulletinCount >99){
			bulletinCount = 99;
		}
		if(null != personCount && personCount >99){
			personCount = 99;
		}
		msgCount.setBulletinMsgCount(bulletinCount);
		msgCount.setPersonMsgCount(personCount);
		return AppResponseUtil.getSuccessResponse(msgCount);
	}
	
	/**
	 * 消息详情
	 * @Date 2016年1月25日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("站内公告详情")
	@RequestMapping("/notice/detail")
	@ResponseBody
	public BaseResponse detail(@Valid MsgDetailRequest req,BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
			sysNoticeReadLogService.updateNoticeReadLog(ApiInvokeLogConstant.MSG_NOTICE_DTL, userId, appType, Integer.parseInt(req.getMsgId()));
		}
		SysNotice rlt = msgService.queryNoticeDetail(req.getMsgId());
		return AppResponseUtil.getSuccessResponse(rlt,NoticeDetailResponse.class);
	}

	/**
	 * 设置消息免打扰
	 * @Date 2016年3月22日 下午1:37:08
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("设置消息免打扰")
	@RequestMapping("/setMsgPush")
	@ResponseBody
	public BaseResponse setMsgPush(@Valid CustomerDeviceRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmCustomerDevice customerDevice = customerDeviceService.queryCustomerDevice(appType, userId);
		if(null != customerDevice){
			customerDevice.setIsSendnotice(Byte.valueOf(req.getIssendNotice()));
			customerDeviceService.doDeviceInfo(customerDevice);
		}else{
			AppResponseUtil.getErrorBusi("device_not_found", "未找到该设备信息，设置失败");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 查询消息免打扰设置信息
	 * @Date 2016年3月25日 上午10:02:59
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("查询消息免打扰设置信息")
	@RequestMapping("/queryMsgPushSet")
	@ResponseBody
	public BaseResponse queryMsgPushSet(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmCustomerDevice customerDevice = customerDeviceService.queryCustomerDevice(appType, userId);
		return AppResponseUtil.getSuccessResponse(customerDevice, CustomerDeviceResponse.class);
	}
	
	/**
	 * 个人消息-设置已读
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("个人消息-设置已读")
	@RequestMapping("/person/readed")
	@ResponseBody
	public BaseResponse mardPersonMsgReaded(@Valid MsgDelRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String[] idArr = req.getMsgIds().split(",");
		msgService.markPersonMsgReaded(idArr, userId);
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 个人消息-全部设置已读
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("个人消息-全部设置已读")
	@RequestMapping("/person/allReaded")
	@ResponseBody
	public BaseResponse mardPersonMsgAllReaded(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		msgService.markPersonMsgAllReaded(userId);
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 公告-全部设置已读
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("公告-全部设置已读")
	@RequestMapping("/notice/allReaded")
	@ResponseBody
	public BaseResponse markNoticeAllReaded(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		try {
			sysNoticeReadLogService.setNoticeReaded(userId, appType);
			return AppResponseUtil.getSuccessResponse();
		} catch (Exception e) {
			return AppResponseUtil.getErrorBusi("100001", e.getMessage());
		}
		
	}	
	/**
	 * 未读消息统计(个人消息)
	 * @Date 2016年10月18日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("站内通知未读消息统计")
	@RequestMapping("/person/personalUnreadCount")
	@ResponseBody
	public BaseResponse personalUnreadCount(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		Map<String,Integer> msgCount = Maps.newHashMap();
		Map<String,Object> conditions = Maps.newHashMap();//查询条件
		conditions.put("userId", userId);
        conditions.put("appType",appType);		
        if(appType != null && appType.intValue() == AppTypeEnum.INVESTOR.getKey()){
        conditions.put("platform", AppUtils.getPlatform(head.getOrgNumber()).getKey());
        }
		Integer personCount = msgService.queryUnreadLcsCount(userId, appType);
		if(null != personCount && personCount >99){
			personCount = 99;
		}
		msgCount.put("personMsgCount", personCount);
		return AppResponseUtil.getSuccessResponse(msgCount);
	}
	
	/**
	 * 公告详情上下页
	 * @Date 2016年1月25日
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("公告详情上下页")
	@RequestMapping("/notice/nearNotices")
	@ResponseBody
	public BaseResponse nearNotices(@Valid MsgDetailRequest req,BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			sysNoticeReadLogService.updateNoticeReadLog(ApiInvokeLogConstant.MSG_NOTICE_DTL, userId, appType, Integer.parseInt(req.getMsgId()));
		}
		SysNotice rlt = msgService.queryNoticeDetail(req.getMsgId());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(rlt != null){
			resultMap = msgService.findNearNotices(rlt);
		}else{
			return new ErrorResponse("10010", "查询该条记录的公告失败");
		}
		return AppResponseUtil.getSuccessResponse(resultMap);
	}
}
