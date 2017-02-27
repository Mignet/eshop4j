package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.EasemobService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.EasemobileConstants;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.HttpClientUtil;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("easemobService")
public class EasemobServiceImpl implements EasemobService{
	private static final Logger logger = LoggerFactory.getLogger(EasemobServiceImpl.class);
	@Resource
	private CrmUserInfoMapper crmUserInfoMapper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
    private CrmCfplannerService crmCfplannerService;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private EasemobService easemobService;
	
    @Override
	public void generateEasemobThread(final String userId) {
    	ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				generateEasemob(userId);
			}
		});
		//MainExecutor.addEasemobRegTask(userId, easemobService);
	}
    
	@Override
	public void generateEasemob(String userId) {
        String tokenString = queryToken();
        String registerUrl = sysConfigService.getValuesByKey(EasemobileConstants.EASEMOB_REGISTER_URL);
        
        //投资者环信帐号生成
        CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
        if(crmInvestor != null) {
            //发送环信注册HTTP请求
          	JSONObject jsonParamInvestor = new JSONObject();
            jsonParamInvestor.put("username", crmInvestor.getEasemobAcct());
            jsonParamInvestor.put("password", crmInvestor.getEasemobPassword());
            if(StringUtils.isNotBlank(crmInvestor.getUserName())){
            	jsonParamInvestor.put("nickname", crmInvestor.getUserName());
            }
            String retInvestor = null;
            logger.debug("url: {}; jsonParam: {}  ", registerUrl , jsonParamInvestor);
    		try {
    			retInvestor = HttpClientUtil.httpPost(registerUrl , jsonParamInvestor, "Bearer " + tokenString);
    			logger.debug("环信注册investor：customerid: {},注册返回数据：{}", userId, retInvestor);
    		} catch (Exception e) {
    			logger.warn("环信注册investor：customerid: {},HTTP请求错误： {}", userId, e);
    		}
    		
    		//如果注册成功将数据库字段改为成功字段
            JSONObject jsonInvestor = JSONObject.parseObject(retInvestor);
            if(null != jsonInvestor.get("action")) {
            	//注册成功
            	CrmInvestor crmInvestorForUpdate = new CrmInvestor();
            	crmInvestorForUpdate.setEasemobRegStatus(1);
            	crmInvestorForUpdate.setId(crmInvestor.getId());
            	crmInvestorService.update(crmInvestorForUpdate);
            } else {
            	if(null != jsonInvestor.get("error")) {
            		String error = jsonInvestor.get("error").toString();
            		logger.debug("环信注册investor：customerid: {},注册失败，error msg: {}", userId, error);
            		//如果注册失败信息为 用户已存在，将环信状态改为成功
                	if("duplicate_unique_property_exists".equals(error)) {
                		CrmInvestor crmInvestorForUpdate = new CrmInvestor();
                    	crmInvestorForUpdate.setEasemobRegStatus(1);
                    	crmInvestorForUpdate.setId(crmInvestor.getId());
                    	crmInvestorService.update(crmInvestorForUpdate);
                	}
            	}
            }
        }
        
        //理财师环信帐号生成
        CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
        if(crmCfplanner != null) {
        	//发送环信注册HTTP请求
          	JSONObject jsonParamLcs = new JSONObject();
            jsonParamLcs.put("username", crmCfplanner.getEasemobAcct());
            jsonParamLcs.put("password", crmCfplanner.getEasemobPassword());
            if(StringUtils.isNotBlank(crmCfplanner.getUserName())){
            	jsonParamLcs.put("nickname", crmCfplanner.getUserName());
            }
            String retLcs = null;
            logger.debug("url: {}; jsonParam: {};  ", registerUrl , jsonParamLcs);
    		try {
    			retLcs = HttpClientUtil.httpPost(registerUrl , jsonParamLcs, "Bearer " + tokenString);
    			logger.debug("环信注册lcs：customerid: {},注册返回数据：{}", userId, retLcs);
    		} catch (Exception e) {
    			logger.warn("环信注册lcs：customerid: {},HTTP请求错误： {}", userId, e);
    		}
    		
    		//如果注册成功将数据库字段改为成功字段
            JSONObject json = JSONObject.parseObject(retLcs);
            if(null != json.get("action")) {
            	//注册成功
            	CrmCfplanner crmCfplannerForUpdate = new CrmCfplanner();
            	crmCfplannerForUpdate.setEasemobRegStatus(1);
            	crmCfplannerForUpdate.setId(crmCfplanner.getId());
            	crmCfplannerService.update(crmCfplannerForUpdate);
            } else {
            	if(null != json.get("error")) {
            		String error = json.get("error").toString();
            		logger.debug("环信注册lcs：customerid: {},注册失败，error msg: {}", userId, error);
            		//如果注册失败信息为 用户已存在，将环信状态改为成功
                	if("duplicate_unique_property_exists".equals(error)) {
                		CrmCfplanner crmCfplannerForUpdate = new CrmCfplanner();
                    	crmCfplannerForUpdate.setEasemobRegStatus(1);
                    	crmCfplannerForUpdate.setId(crmCfplanner.getId());
                    	crmCfplannerService.update(crmCfplannerForUpdate);
                	}
            	}
            }
        }
	}
	
	/**
	 * 从数据库查token
	 * @Auther ZhongLing
	 * @Date 2015年12月24日 下午5:58:43
	 * @return
	 */
	private String queryToken() {
		SysConfig sysConfig = sysConfigService.querySysConfigByKey(null,EasemobileConstants.EASEMOB_TOKEN,AppTypeEnum.FORALL.getKey());
        String tokenString = null;
        if(sysConfig != null && sysConfig.getConfigValue() != null ) {
        	long hour = 0;
	        tokenString = sysConfig.getConfigValue();
	        Date lastUpdateTime = sysConfig.getCrtTime();
            try  
            {   
                Date d1 = lastUpdateTime;   
                Date d2 = new Date();
                long diff = d2.getTime() - d1.getTime();   
                hour = diff / (1000 * 60 * 60 );
            }   
            catch (Exception e)   
            {
            	logger.debug("时间处理错误： " ,e);
            }
            if(hour > 24) {
            	//如果更新时间超过24小时，更新token
            	String str =  initEaseToken();
            	if(str != null &&  !"".equals(str)){
            		tokenString = initEaseToken();
            	}
            }
        } else {
        	String str =  initEaseToken();
        	if(str != null &&  !"".equals(str)){
        		tokenString = initEaseToken();
        	}
        }
		return tokenString;
	}
	
	/**
	 * 获取环信token写入数据库
	 * 环信管理员token有效时间,默认是七天。
	 * @Auther ZhongLing
	 * @date 2015年12月18日 下午3:57:45
	 * @return
	 */
	private String initEaseToken() {
		String getTokenUrl = sysConfigService.getValuesByKey(EasemobileConstants.EASEMOB_TOKEN_URL);
		String clientId = sysConfigService.getValuesByKey(EasemobileConstants.EASEMOB_CLIENT_ID);
		String clientSecret =  sysConfigService.getValuesByKey(EasemobileConstants.EASEMOB_CLIENT_SECRET);
		logger.debug("获取环信管理员 url = "+ getTokenUrl + " ;clientId = " + clientId + " ;clientSecret = " + clientSecret);
		JSONObject jsonParam = new JSONObject();
        jsonParam.put("client_id", clientId);
        jsonParam.put("client_secret", clientSecret);
        jsonParam.put("grant_type", "client_credentials");
        String ret = null;
		try {
			ret = HttpClientUtil.httpPost(getTokenUrl , jsonParam);
			logger.debug("获取环信管理员token,返回数据：{}", ret);
		} catch (Exception e) {
			logger.error("获取环信管理员token,HTTP请求错误： {}", e);
		}
		JSONObject json = JSONObject.parseObject(ret);
        
        if(null != json.get("access_token")) {
        	//更新数据库token
        	String token = json.get("access_token").toString();
        	sysConfigService.updateSysConfigByKey(EasemobileConstants.EASEMOB_TOKEN, token, new Date());
        	return token;
        }else {
        	return null;
        }
	}

	@Override
	public void generateCityInfo(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateCityInfoThread(final String userId) {
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				generateCityInfo(userId);
			}
		});
	}
	
	public String addAreaOfSearchJuhe( String mobile) {
		// 查号码归属地
		String url = "http://apis.juhe.cn/mobile/get";
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("phone", mobile);
		paramsMap.put("key", "8feee473809a1b30d365b7f476d61809");
		String ret = null;
		logger.debug("url: {}; params: {}; ", url, paramsMap);
		try {
			ret = HttpClientUtil.httpPost(url, paramsMap);
			logger.debug("用户地区查询：customerid: {},返回数据：{}", mobile, ret);
		} catch (Exception e) {
			logger.warn("用户地区查询：customerid: {},HTTP请求错误： {}", mobile, e);
		}
		String province = null;
		String city = null;
		JSONObject json = JSONObject.parseObject(ret);
		JSONObject json2  = (JSONObject) json.get("result");
		if (null != json2.get("province")) {
			// 获取数据成功
			province = json2.get("province").toString();
			city = json2.get("city").toString();
		}
		return city;
	}
	
}
