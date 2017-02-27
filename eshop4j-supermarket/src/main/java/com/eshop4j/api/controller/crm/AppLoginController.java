package com.eshop4j.api.controller.crm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop4j.api.request.crm.DeviceInfoRequest;
import com.eshop4j.api.request.crm.JinfuLoginRequest;
import com.eshop4j.api.request.crm.LoginRequest;
import com.eshop4j.api.request.crm.WechatBindRequest;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.CrmCfpLoginLog;
import com.eshop4j.web.model.CrmCfplanner;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.web.model.CrmInvestorLoginLog;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.service.CrmCfpLoginLogService;
import com.eshop4j.web.service.CrmCfplannerService;
import com.eshop4j.web.service.CrmInvestorLoginLogService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.SmCustomerDeviceService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.constant.InnerResponseConstant;
import org.apache.axis.encoding.Base64;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.MD5;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestLogging("用户中心")
@RequestMapping("/api/user")
public class AppLoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppLoginController.class);

    @Resource
    private JsonWebTokenHepler jsonWebTokenHepler;
    
	@Autowired
	private SmCustomerDeviceService customerDeviceService;
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Resource
	private CrmCfpLoginLogService crmCfpLoginLogService;
	
	@Resource
	private CrmInvestorLoginLogService crmInvestorLoginLogService;

	@ResponseBody
	@RequestLogging("登录")
	@RequestMapping("/login")
	public Object login(@Valid LoginRequest loginRequest,BindingResult result,AppRequestHead requestHead,DeviceInfoRequest deviceInfoRequest,WechatBindRequest wechatBindRequest){
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String orgNumber = requestHead.getOrgNumber();
		if(StringUtils.isBlank(loginRequest.getMobile()) || StringUtils.isBlank(loginRequest.getPassword())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","用户名或者密码不能为空"));
		}
		if(AppUtils.isChannelApp(orgNumber)&&!cfplannerService.isExistsCfplanner(loginRequest.getMobile())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","帐号未注册"));
		}
		if(AppUtils.isInvestorApp(orgNumber)&&!investorService.isExistsInvestor(loginRequest.getMobile())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","帐号未注册"));
		}
		//判断帐号是否锁定
		if(AppUtils.isChannelApp(orgNumber) && cfplannerService.isLockedCfplanner(loginRequest.getMobile())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","该账号被冻结，暂不可登录"));
		}
		if(AppUtils.isInvestorApp(orgNumber) && investorService.isLockedInventor(loginRequest.getMobile())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","该账号被冻结，暂不可登录"));
		}
		//理财师是否禁止登录90天
		if(AppUtils.isChannelApp(orgNumber) && cfplannerService.isDisabledLogin90days(loginRequest.getMobile())){
			String disabledLoginTime = cfplannerService.queryDisabledLoginTime(loginRequest.getMobile());
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","账号于" + disabledLoginTime + "被禁用，期限90天"));
		}
		
		CrmUserInfo userInfo = new CrmUserInfo();
		userInfo.setMobile(loginRequest.getMobile());
		userInfo.setPassword(MD5.crypt(loginRequest.getPassword()));
		userInfo = userInfoService.selectOne(userInfo);
		if(userInfo != null){
			String userId = userInfo.getUserId();
			String token = jsonWebTokenHepler.creatToken(requestHead.getAppKind(),userInfo.getUserId());
			Map<String ,String > map = new HashMap<String ,String >();
			map.put("token", token);
			customerDeviceService.recordCustomerDeviceInfo(userId,AppUtils.getPlatform(orgNumber).getValue(),AppUtils.getAppType(orgNumber).getKey(), deviceInfoRequest);
			//登录日志
			if(AppUtils.isChannelApp(orgNumber)) {
				CrmCfpLoginLog log = new CrmCfpLoginLog();
				log.setLogId(StringUtils.getUUID());
				if(loginRequest.getAccessUrl() != null && !"".equals(loginRequest.getAccessUrl()) && loginRequest.getAccessUrl().length() > 256) {
					log.setAccessUrl(loginRequest.getAccessUrl().substring(0, 256));
				} else {
					log.setAccessUrl(loginRequest.getAccessUrl());
				}
				log.setAppVersion(requestHead.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(orgNumber).getValue());
				if(loginRequest.getFromUrl() != null && !"".equals(loginRequest.getFromUrl()) && loginRequest.getFromUrl().length() > 256) {
					log.setFromUrl(loginRequest.getFromUrl().substring(0, 256));
				} else {
					log.setFromUrl(loginRequest.getFromUrl());
				}
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(userId);
				crmCfpLoginLogService.insert(log);
				
				//更新访问时间
				CrmCfplanner crmCfplannerForUpdateVisitTime = new CrmCfplanner();
				crmCfplannerForUpdateVisitTime.setUserId(userInfo.getUserId());
				crmCfplannerForUpdateVisitTime.setRectVisitTime(new Date());
				cfplannerService.updateByUserId(crmCfplannerForUpdateVisitTime);
			} else {
				CrmInvestorLoginLog log = new CrmInvestorLoginLog();
				log.setLogId(StringUtils.getUUID());
				if(loginRequest.getAccessUrl() != null && !"".equals(loginRequest.getAccessUrl()) && loginRequest.getAccessUrl().length() > 256) {
					log.setAccessUrl(loginRequest.getAccessUrl().substring(0, 256));
				} else {
					log.setAccessUrl(loginRequest.getAccessUrl());
				}
				log.setAppVersion(requestHead.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(orgNumber).getValue());
				if(loginRequest.getFromUrl() != null && !"".equals(loginRequest.getFromUrl()) && loginRequest.getFromUrl().length() > 256) {
					log.setFromUrl(loginRequest.getFromUrl().substring(0, 256));
				} else {
					log.setFromUrl(loginRequest.getFromUrl());
				}
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(userId);
				crmInvestorLoginLogService.insert(log);
				
				CrmInvestor crmInvestor = investorService.queryInvestorByMobile(loginRequest.getMobile());
				if(crmInvestor != null && StringUtils.isBlank(crmInvestor.getCfplanner())){
					//如果客户没有理财师 则分配理财师
					if(!cfplannerService.isExistsCfplanner(loginRequest.getMobile())){
						investorService.allotCfplanner(userId, loginRequest.getMobile());
					}
				}
				//更新访问时间
				CrmInvestor investorForUpdateVistTime = new CrmInvestor();
				investorForUpdateVistTime.setUserId(userInfo.getUserId());
				investorForUpdateVistTime.setRectVisitTime(new Date());
				investorService.updateByUserId(investorForUpdateVistTime);
			}
			
			return AppResponseUtil.getSuccessResponse(map);
		}
		return AppResponseUtil.getErrorBusi(new BaseResponse("error","用户名或者密码错误"));
	}
	
	@ResponseBody
	@RequestLogging("退出")
	@RequestMapping("/logout")
	public Object logout(AppRequestHead requestHead){
		String token = requestHead.getToken();
		if(StringUtils.isBlank(token))return AppResponseUtil.getErrorBusi(new BaseResponse("error","您未登陆,请先登录"));
		return jsonWebTokenHepler.removeToken(requestHead.getAppKind(),token)?AppResponseUtil.getSuccessResponse():AppResponseUtil.getErrorBusi(new BaseResponse("error","退出失败,请重新退出"));
	}
	
	/**
	 * 手势密码登录
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestLogging("手势密码登录")
	@RequestMapping("/gesturePwdLogin")
	public BaseResponse gesturePwdLogin(AppRequestHead requestHead){
		LOGGER.info("手势密码登录,token={}", requestHead.getToken());
		if(StringUtils.isBlank(requestHead.getToken())){
			return	AppResponseUtil.getErrorBusi(InnerResponseConstant.TOKEN_NOTNULL);
		}else {
			if(jsonWebTokenHepler.resetTokenValidDate(requestHead.getAppKind(),requestHead.getToken())){
				return	AppResponseUtil.getSuccessResponse();
			} else {
				return	AppResponseUtil.getErrorBusi("token_isNotExist","token不存在");
			}
		}
	}
	
	/**
	 * 金服跳转登录
	 * @param loginRequest
	 * @param result
	 * @param requestHead
	 * @param deviceInfoRequest
	 * @param wechatBindRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("金服跳转登录")
	@RequestMapping("/jinfuLogin")
	public Object jinfuLogin(@Valid JinfuLoginRequest loginRequest,BindingResult result,AppRequestHead requestHead,DeviceInfoRequest deviceInfoRequest,WechatBindRequest wechatBindRequest){
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String orgNumber = requestHead.getOrgNumber();
		String mobile = new String(Base64.decode(loginRequest.getKey()));
		if(!MD5.crypt(MD5.crypt(mobile) + "linghuikeji@tbei2016").equals(loginRequest.getCode())){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","code校验失败"));
		}
		if(StringUtils.isBlank(mobile) ){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","参数不能为空"));
		}
		if(AppUtils.isChannelApp(orgNumber)&&!cfplannerService.isExistsCfplanner(mobile)){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","帐号未注册"));
		}
		if(AppUtils.isInvestorApp(orgNumber)&&!investorService.isExistsInvestor(mobile)){
			return AppResponseUtil.getErrorBusi(new BaseResponse("error","帐号未注册"));
		}
		//判断帐号是否锁定
		if(AppUtils.isChannelApp(orgNumber) && cfplannerService.isLockedCfplanner(mobile)){
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","该账号被冻结，暂不可登录"));
		}
		if(AppUtils.isInvestorApp(orgNumber) && investorService.isLockedInventor(mobile)){
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","该账号被冻结，暂不可登录"));
		}
		//理财师是否禁止登录90天
		if(AppUtils.isChannelApp(orgNumber) && cfplannerService.isDisabledLogin90days(mobile)){
			String disabledLoginTime = cfplannerService.queryDisabledLoginTime(mobile);
			return AppResponseUtil.getErrorBusi(new BaseResponse("userIsLocked","账号于" + disabledLoginTime + "被禁用，期限90天"));
		}
		
		CrmUserInfo userInfo = new CrmUserInfo();
		userInfo.setMobile(mobile);
		userInfo = userInfoService.selectOne(userInfo);
		if(userInfo != null){
			String userId = userInfo.getUserId();
			String token = jsonWebTokenHepler.creatToken(requestHead.getAppKind(),userInfo.getUserId());
			Map<String ,String > map = new HashMap<String ,String >();
			map.put("token", token);
			customerDeviceService.recordCustomerDeviceInfo(userId,AppUtils.getPlatform(orgNumber).getValue(),AppUtils.getAppType(orgNumber).getKey(), deviceInfoRequest);
			//登录日志
			if(AppUtils.isChannelApp(orgNumber)) {
				CrmCfpLoginLog log = new CrmCfpLoginLog();
				log.setLogId(StringUtils.getUUID());
				log.setAppVersion(requestHead.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(orgNumber).getValue());
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(userId);
				crmCfpLoginLogService.insert(log);
				
				//更新访问时间
				CrmCfplanner crmCfplannerForUpdateVisitTime = new CrmCfplanner();
				crmCfplannerForUpdateVisitTime.setUserId(userInfo.getUserId());
				crmCfplannerForUpdateVisitTime.setRectVisitTime(new Date());
				cfplannerService.updateByUserId(crmCfplannerForUpdateVisitTime);
			} else {
				CrmInvestorLoginLog log = new CrmInvestorLoginLog();
				log.setLogId(StringUtils.getUUID());
				log.setAppVersion(requestHead.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(orgNumber).getValue());
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(userId);
				crmInvestorLoginLogService.insert(log);
				
				CrmInvestor crmInvestor = investorService.queryInvestorByMobile(mobile);
				if(crmInvestor != null && StringUtils.isBlank(crmInvestor.getCfplanner())){
					//如果客户没有理财师 则分配理财师
					if(!cfplannerService.isExistsCfplanner(mobile)){
						investorService.allotCfplanner(userId, mobile);
					}
				}
				//更新访问时间
				CrmInvestor investorForUpdateVistTime = new CrmInvestor();
				investorForUpdateVistTime.setUserId(userInfo.getUserId());
				investorForUpdateVistTime.setRectVisitTime(new Date());
				investorService.updateByUserId(investorForUpdateVistTime);
			}
			
			return AppResponseUtil.getSuccessResponse(map);
		}
		return AppResponseUtil.getErrorBusi(new BaseResponse("error","用户不存在"));
	}
	
}
