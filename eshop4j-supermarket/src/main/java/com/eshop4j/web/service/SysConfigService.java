package com.eshop4j.web.service;

import java.util.Date;
import java.util.List;

import com.eshop4j.api.request.SysConfigRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.SysConfig;
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
public interface SysConfigService extends GenericService<SysConfig,Long>{

	/**
	 * 查询SysConfig列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据应用类别查询系统配置（应用类别对应查询结果关系1-0,1;2-0,2）
	 * @author hxb
	 * @param dt
	 * @return
	 */
	PaginatorResponse<SysConfig> selectByAppType(SysConfigRequest sysConfigRequest,Page<SysConfig> page);
	
	/**
	 * 查询所有的配置
	 * 
	 * @return
	 */
	public List<SysConfig> getSystemConfigs();

	public List<SysConfig> querySystemConfigsByConditon(String string);

	public String getValuesByKey(String key);

	public String getImageUrl(String imgpath);

	public String getValuesByKey(String key, Integer appType);
	
	public List<SysConfig> querySysConfigByName(String configName);
	public List<SysConfig> queryfuzzily(SysConfig condit);

	/**
	 * 根据key查配置信息
	 * @param confType
	 * @param confKey
	 * @param appType
	 * @return
	 */
	SysConfig querySysConfigByKey(String confType, String confKey, int appType);
	
	/**
	 * 查询value配置信息
	 * @param key
	 * @param appType
	 * @return
	 */
	public String getValuesByKey(String confType, String confKey, int appType);

	/**
	 * 更新configValue
	 * @param configKey
	 * @param configValue
	 * @param createTime
	 */
	void updateSysConfigByKey(String configKey, String configValue, Date createTime);
	
}
