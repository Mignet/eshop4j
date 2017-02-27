package com.eshop4j.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.SysConfigMapper;
import com.eshop4j.web.model.SysConfig;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.constant.WebConstants;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月08日 14:46:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends GenericServiceImpl<SysConfig, Long> implements SysConfigService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysConfigServiceImpl.class);
	
	@Resource
	private SysConfigMapper sysConfigMapper;
	
	@Override
    public GenericDao<SysConfig, Long> getDao() {
        return sysConfigMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysConfig -- 排序和模糊查询 ");
		Page<SysConfig> page = new Page<SysConfig>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysConfig> list = this.sysConfigMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    
	/**
	 * 查询所有的配置
	 * @return
	 */
	public List<SysConfig> getSystemConfigs(){
		SysConfig t = new SysConfig();
		return sysConfigMapper.selectByCondition(t);
	}

	/**
	 * 根据条件查询相应配置
	 * @Auther xuzhao
	 * @Date 2016年1月19日 下午2:06:26
	 * @return
	 */
	public List<SysConfig> querySystemConfigsByConditon(String type){
		SysConfig t = new SysConfig();
		t.setConfigType(type);
		return sysConfigMapper.selectByCondition(t);
	}
	
	public String getValuesByKey(String key,Integer appType) {
		SysConfig t = new SysConfig();
		t.setConfigKey(key);
		t.setAppType(appType);
		List<SysConfig> list = sysConfigMapper.selectByCondition(t);
		if(list!=null &&list.size()>0){
			return list.get(0).getConfigValue();
		}else{
			return "";
		}
	}
	
	public String getValuesByKey(String key) {
		SysConfig t = new SysConfig();
		t.setConfigKey(key);
		List<SysConfig> list = sysConfigMapper.selectByCondition(t);
		if(list!=null &&list.size()>0){
			return list.get(0).getConfigValue();
		}else{
			return "";
		}
	}

	 /**
	  * 获得图片路径
	  * @param imgpath
	  * @return
      */
	 @Override
	 public String getImageUrl(String imgpath) {
		 if(StringUtils.isNotBlank(imgpath)){
			 if(imgpath.indexOf("http") !=-1){
				 return imgpath;
			 }

			 String imgServerUrl = getValuesByKey(WebConstants.IMAGE_SERVER_URL);
			 if(imgServerUrl.lastIndexOf("/")==-1){
				 imgServerUrl = imgServerUrl+"/";
			 }
			 return imgServerUrl+ imgpath;
		 }
		 return null;
	 }

	@Override
	public List<SysConfig> querySysConfigByName(String configName) {
		SysConfig condit = new SysConfig();
		condit.setConfigName(configName);
		return sysConfigMapper.selectByName(condit);
	}

	@Override
	public SysConfig querySysConfigByKey(String configType, String configKey, int appType) {
		SysConfig t = new SysConfig();
		t.setConfigType(configType);
		t.setConfigKey(configKey);
		t.setAppType(appType);
		return sysConfigMapper.selectOneByCondition(t);
	}

	@Override
	public void updateSysConfigByKey(String configKey, String configValue, Date createTime) {
		SysConfig sysConfig = new SysConfig();
		sysConfig.setConfigKey(configKey);
		sysConfig.setConfigValue(configValue);
		sysConfig.setCrtTime(createTime);
		sysConfigMapper.updateSysConfigByKey(sysConfig);
	}

	@Override
	public List<SysConfig> queryfuzzily(SysConfig condit) {
		return sysConfigMapper.selectByName(condit);
	}

	@Override
	public String getValuesByKey(String confType, String confKey, int appType) {
		SysConfig t = new SysConfig();
		t.setConfigType(confType);
		t.setConfigKey(confKey);
		t.setAppType(appType);
		SysConfig sysConfig=sysConfigMapper.selectOneByCondition(t);
		return sysConfig == null?"":sysConfig.getConfigValue();
	}

}
