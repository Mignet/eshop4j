package com.eshop4j.xoss.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.NetUtils;
import com.eshop4j.core.util.SignUtils;
import com.eshop4j.xoss.util.AppResponseUtil;
import com.eshop4j.xoss.util.RequestHeadUtil;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.constant.InnerResponseConstant;

public class MobileAuthenticationFilter extends AccessControlFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileAuthenticationFilter.class);
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (checkAccess(req,res)) {
			return onAccessSuccess(req,res);
		} else {
			return onAccessFail(req,res);
		}	
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}
	
	/**
	 * 
	 * 全部参数校验,判断参数的合法性
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public boolean checkAccess(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		HashMap<String, String> params = RequestHeadUtil.getRequestParams(request);
		LOGGER.info("[{}]APP请求URL:{}",NetUtils.getIpAddress(request),NetUtils.getRequestUrl(request,false));
		LOGGER.info("APP请求传入参数信息:{}",JSONObject.toJSONString(params));
		AppRequestHead req = RequestHeadUtil.convertToAppRequestHead(request);
		
		//参数校验 && token校验
		return checkAppSysParams(req,params,response) && checkAppToken(request,response,req);
	}
	
	/**
	 * APP系统参数校验
	 * @param req
	 * @param params
	 * @return
	 */
	private boolean checkAppSysParams(AppRequestHead req,HashMap<String, String> params,HttpServletResponse response){
		
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		String secret = "";
		/*
		 * APP标示校验
		 */
//		if(StringUtils.isBlank(req.getOrgNumber())){
//			errors.add(InnerResponseConstant.ORGNUMBER_NOTNULL);
//		} else {			
//			PlatformEnum platform =  AppUtils.getPlatform(req.getOrgNumber());
//			AppTypeEnum appType =AppUtils.getAppType(req.getOrgNumber());
//			if(platform==null||appType==null){
//				errors.add(InnerResponseConstant.ORGNUMBER_INVALID);
//			}
//			
//			// 根据app标示 查询该app对应的签名密钥
//			try {
//				secret = queryOrgSecret(req.getOrgNumber());
//				if(StringUtils.isBlank(secret)){
//					LOGGER.error("密钥查询为空,orgNumber={}",req.getOrgNumber());
//					errors.add(InnerResponseConstant.ORG_SECRET_INVALID);
//				}
//			} catch (Exception e) {
//				LOGGER.error("密钥查询异常,orgNumber={}",req.getOrgNumber(),e);
//				errors.add(InnerResponseConstant.ORG_SECRET_INVALID);
//			}
//		}
//		
//		/*
//		 * APP类型校验
//		 */
//		if(StringUtils.isBlank(req.getAppKind())){
//			errors.add(InnerResponseConstant.APPKIND_NOTNULL);
//		}
//		
//		/*
//		 * APP客户端 校验
//		 */
//		if(StringUtils.isBlank(req.getAppClient())){
//			errors.add(InnerResponseConstant.APPCLIENT_NOTNULL);
//		}
//		
//		
//		/*
//		 * app版本号校验
//		 */
//		if(StringUtils.isBlank(req.getAppVersion())){
//			errors.add(InnerResponseConstant.APPVERSION_NOTNULL);
//		}
//		
//		/*
//		 * timestamp校验
//		 */
//		if(StringUtils.isBlank(req.getTimestamp())){
//			errors.add(InnerResponseConstant.TIMESTAMP_NOTNULL);
//		}
//		if(DateUtils.parse(req.getTimestamp(), DateUtils.FORMAT_LONG)==null){
//			errors.add(InnerResponseConstant.TIMESTAMP_FORMATEERROR);
//		}
//		
//		/*
//		 * API版本号 校验
//		 */
//		if(StringUtils.isBlank(req.getV())){
//			errors.add(InnerResponseConstant.V_NOTNULL);
//		}
//		
//		if(!errors.isEmpty()){
//			RequestHeadUtil.responseOutWithJson(response,AppResponseUtil.getErrorParams(errors));
//			return false;
//		}
//		
//		/*
//		 * 签名校验
//		 */
//		if(PlatformEnum.ANDROID.equals(AppUtils.getPlatform(req.getOrgNumber())) || PlatformEnum.IOS.equals(AppUtils.getPlatform(req.getOrgNumber()))){
//			if(StringUtils.isBlank(req.getSign())){
//				RequestHeadUtil.responseOutWithJson(response,AppResponseUtil.getErrorSign(InnerResponseConstant.SIGN_NOTNULL));
//				return false;
//			} else {
//				params.remove("sign");
//				String sign = SignUtils.sign(params, secret);
//				if(!sign.equals(req.getSign())){
//					LOGGER.info("参数校验(签名校验),对去掉签名信息的参数进行重新签名,系统配置的签名密钥secret={}",secret);
//					LOGGER.info("重新签名得到的签名sign={},请求头中获取的签名sign={}",sign,req.getSign());
//					LOGGER.info("请求去签名后参数为：{}",JSONObject.toJSONString(params));
//					LOGGER.info("签名校验失败,请仔细检查所传参数是否有问题");
//					RequestHeadUtil.responseOutWithJson(response,AppResponseUtil.getErrorSign(InnerResponseConstant.SIGN_INVALID));
//					return false;
//				}
//			}
//		}
		return true;
	}
	
	/**
	 * APP token校验
	 * @param req
	 * @param response
	 * @return
	 */
	private boolean checkAppToken(HttpServletRequest request,HttpServletResponse response,AppRequestHead req){
		//获取请求的方法
		String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:  
		String targetURL = currentURL.substring(currentURL.indexOf('/', 1),currentURL.length()); // 截取到当前路径名用于比较  
		return true;
	}
	
	/**
	 * 认证成功进行的操作处理
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 * @throws Exception 
	 */
	public boolean onAccessSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException{
		return true;
	}

	/**
	 * 认证失败时处理结果
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 * @throws IOException 
	 */
	public boolean onAccessFail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		return false;
	}
}