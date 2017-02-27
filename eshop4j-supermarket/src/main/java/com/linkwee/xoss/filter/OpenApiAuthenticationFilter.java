package com.linkwee.xoss.filter;

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

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NetUtils;
import com.linkwee.core.util.SignUtils;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.api.OpenRequestHead;
import com.linkwee.xoss.constant.InnerResponseConstant;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestHeadUtil;

public class OpenApiAuthenticationFilter extends AccessControlFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiAuthenticationFilter.class);
	
	@Resource
	private SysThirdkeyConfigService sysThirdkeyConfigService;
	@Resource
	private JedisCluster jedisCluster;
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LOGGER.debug("开放平台进入校验！" + request.getParameterMap());
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
		
		//密钥
		String secret = "";
		
		HashMap<String, String> params = RequestHeadUtil.getRequestParams(request);		
		LOGGER.info("[{}]开放平台请求传入参数信息:{}",NetUtils.getIpAddress(request),JSONObject.toJSONString(params));
		OpenRequestHead req = RequestHeadUtil.convertToOpenRequestHead(request);
		
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		
		//机构编码校验
		if(StringUtils.isBlank(req.getOrgNumber())){
			errors.add(InnerResponseConstant.OPEN_ORGNUMBER_NOTNULL);
		}
		
		//机构公钥校验
		if(StringUtils.isBlank(req.getOrgKey())){
			errors.add(InnerResponseConstant.OPEN_ORGKEY_NOTNULL);
		}
		
		//机构公钥和机构编码  都存在的话，根据机构编码和机构公钥查询该机构对应的签名私钥
		if(StringUtils.isNotBlank(req.getOrgNumber()) && StringUtils.isNotBlank(req.getOrgKey())){
			// 根据app标示 查询该app对应的签名密钥
			try {
				secret = queryOrgSecret(req.getOrgNumber(),req.getOrgKey());
				if(StringUtils.isBlank(secret)){
					//查询当前机构是否是合作状态  如果是合作状态  则系统出现bug  抛出错误触发监控
					CimOrginfo cimOrginfo = cimOrginfoService.queryOrgInfoByOrgNumber(req.getOrgNumber());
					if(cimOrginfo != null && cimOrginfo.getStatus() == 1){	
						LOGGER.error("密钥查询为空或秘钥处于关闭状态,orgNumber={},OrgKey={}",req.getOrgNumber(),req.getOrgKey());
					} else {
						LOGGER.info("密钥查询为空或秘钥处于关闭状态,orgNumber={},OrgKey={}",req.getOrgNumber(),req.getOrgKey());
					}
					errors.add(InnerResponseConstant.ORG_SECRET_CLOSE_INVALID);
				}
			} catch (Exception e) {
				LOGGER.error("密钥查询异常,orgNumber={}",req.getOrgNumber(),e);
				errors.add(InnerResponseConstant.ORG_SECRET_INVALID);
			}
		}
		
		//timestamp校验
		if(StringUtils.isBlank(req.getTimestamp())){
			errors.add(InnerResponseConstant.TIMESTAMP_NOTNULL);
		}
		if(DateUtils.parse(req.getTimestamp(), DateUtils.FORMAT_LONG)==null){
			errors.add(InnerResponseConstant.TIMESTAMP_FORMATEERROR);
		}
		
		if(!errors.isEmpty()){
			RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorParams(errors));
			return false;
		}
		
		//签名校验
		if(StringUtils.isBlank(req.getSign())){
			RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorSign(InnerResponseConstant.SIGN_NOTNULL));
			return false;
		} else {
			params.remove("sign");
			String sign = SignUtils.sign(params, secret);
			if(!sign.equals(req.getSign())){
				LOGGER.info("参数校验(签名校验),对去掉签名信息的参数进行重新签名,系统配置的签名密钥secret:{}",secret);
				LOGGER.info("重新签名得到的签名sign={},请求头中获取的签名sign={}",sign,req.getSign());
				LOGGER.info("请求去签名后参数为：{}",JSONObject.toJSONString(params));
				LOGGER.info("签名校验失败,请仔细检查所传参数是否有问题");
				RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorSign(InnerResponseConstant.SIGN_INVALID));
				return false;
			} else {
				return true;
			}
		}
		
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

	/**
	 * 查询机构秘钥
	 * @param OrgNumber  机构编码
	 * @param OrgKey	 机构公钥
	 * @return
	 */
	private String queryOrgSecret(String orgNumber,String orgKey){
		String orgSecret = null;
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = orgNumber+orgKey;
			if(jedisCluster.exists(redisOrgKey)){
				orgSecret = jedisCluster.get(redisOrgKey);
			} else {
				orgSecret = queryOrgSecretByDB(orgNumber,orgKey);
				if(StringUtils.isNotBlank(orgSecret)){	
					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,orgSecret);
				}
			}
		} else {
			orgSecret = queryOrgSecretByDB(orgNumber,orgKey);
		}
		return orgSecret;
	}
	
	/**
	 * 查询机构秘钥DB
	 * @param orgNumber 机构编码
	 * @param orgKey 机构公钥
	 * @return
	 */
	private String queryOrgSecretByDB(String orgNumber,String orgKey){
		String secret = null;
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(orgNumber);
		sysThirdkeyConfig.setOrgKey(orgKey);
		sysThirdkeyConfig.setOrgStatus("y");
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		if(sysThirdkeyConfig != null){				
			secret = sysThirdkeyConfig.getOrgSecret();
		}
		return secret;
	}
}
