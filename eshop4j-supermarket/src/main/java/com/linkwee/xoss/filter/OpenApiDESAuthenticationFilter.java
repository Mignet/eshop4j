package com.linkwee.xoss.filter;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.crypto.Cipher3DES;
import com.linkwee.xoss.crypto.RsaSignCoder;

public class OpenApiDESAuthenticationFilter extends AccessControlFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiDESAuthenticationFilter.class);
	
	@Resource
	private SysThirdkeyConfigService sysThirdkeyConfigService;

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LOGGER.info("开放平台进入校验！" + request.getParameterMap());
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
	 * 全部参数校验,判断参数的合法性
	 * @param token
	 * @return
	 */
	public boolean checkAccess(HttpServletRequest request,HttpServletResponse response){
		LOGGER.debug("开放平台进入校验！" + request.getParameterMap());
		String datas = request.getParameter("condition");
		String orgNumber = request.getParameter("orgNumber");
		String signature = request.getParameter("signature");
		String vector = request.getParameter("vector");
		//1.根据平台编号，查询公钥
		String signPublicKey =  "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANZqzyRfXZW1Zq5qIyHHMEUeHUMQx5c3JjevUcbsLmfEGnuewsgvYOJU0mJ5GrCUoN01WWlE7+qKCB5uQ/kjG2kCAwEAAQ==";
		//平台提供的加密私钥privateKey
		String privateKey =  "l4mdofLTvHkyONpdlyXBiaTv";
		//6、验证加密内容报文的签名
		boolean isTrue = false;
		try {
			isTrue = RsaSignCoder.verify(datas, signPublicKey, signature);
			if(isTrue){//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
				//7、验签通过则进行解密返回的加密报文
				String str = Cipher3DES.decrypt(datas, privateKey, vector);
				LOGGER.info("解密结果："+str);
				JSONObject o = JSONObject.parseObject(str);
				JSONObject condition = JSONObject.parseObject(o.get("condition").toString());
				JSONObject header = JSONObject.parseObject(o.get("header").toString());
				request.setAttribute("header",header);
				request.setAttribute("privateKey",privateKey);
				Set<String> keys = condition.keySet();
				for(String key:keys){
					request.setAttribute(key,condition.get(key));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTrue;
		
	};

	/**
	 * 认证成功进行的操作处理
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 * @throws Exception 
	 */
	public boolean onAccessSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return true;
	};

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
	};

}
