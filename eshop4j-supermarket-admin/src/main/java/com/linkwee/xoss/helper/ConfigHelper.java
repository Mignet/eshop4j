package com.linkwee.xoss.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.web.model.SysConfig;
import com.linkwee.web.service.SysConfigService;

@Component
public class ConfigHelper {

	
	@Resource
	private SysConfigService sysConfigService;

	/**
	 * 获取配置值
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return getValue(null,null,key);
	}
	
	/**
	 * 获取配置值
	 * @param key
	 * @return
	 */
	public String getValue(Integer appType,String key) {
		return getValue(appType,null,key);
	}
	
	
	/**
	 * 获取配置值
	 * @param appType
	 * @param type
	 * @param key
	 * @return
	 */
	public String getValue(Integer appType,String type,String key) {
		if(StringUtils.isBlank(key)){
			return null;
		}
		SysConfig t = new SysConfig();
		t.setConfigType(type);
		t.setConfigKey(key);
		t.setAppType(appType);
		SysConfig sysConfig=sysConfigService.selectOne(t);
		return sysConfig == null?"":sysConfig.getConfigValue();
	}

	/**
	 * 描述：根据类别获取配置
	 * 
	 * @param type 类别
	 * @return
	 */
	public Map<String, SysConfig> getSysConfigsByType(String type) {
		return getSysConfigsByType(null,type);
	}
	
	/**
	 * 根据类别获取配置
	 * @param appType 应用类别
	 * @param type 类别
	 * @return
	 */
	public Map<String, SysConfig> getSysConfigsByType(Integer appType,String type) {
		if(StringUtils.isBlank(type)){
			return null;
		}
		
		SysConfig t = new SysConfig();
		t.setConfigType(type);
		t.setAppType(appType);
		List<SysConfig> sysConfigList=sysConfigService.selectListByCondition(t);
		
		Map<String, SysConfig> keyConfig = new HashMap<String, SysConfig>();
		
		for(SysConfig config:sysConfigList){
			keyConfig.put(config.getConfigKey(), config);
		}
		
		return keyConfig;
	}
	
	/**
	 * 描述：根据类别获取配置值
	 * 
	 * @param type 类别
	 * @return
	 */
	public Map<String,String> getValuesByType(String type) {
		return getValuesByType(null,type);
	}
	/**
	 * 根据类别获取配置值
	 * @param appType 应用类别
	 * @param type 类别
	 * @return
	 */
	public Map<String,String> getValuesByType(Integer appType,String type) {
		Map<String, SysConfig> data = getSysConfigsByType(appType,type);
		Map<String,String> ret = new HashMap<String,String>();
		for(String key:data.keySet()){
			ret.put(key, data.get(key).getConfigValue());
		}
		return ret;
	}
}
