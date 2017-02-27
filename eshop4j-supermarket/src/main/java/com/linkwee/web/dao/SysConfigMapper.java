package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.SysConfigRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SysConfig;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月08日 16:50:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysConfigMapper extends GenericDao<SysConfig,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysConfig> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	List<SysConfig> selectByName(SysConfig sysConfig);
	
	/**
	 * 根据应用类别查询系统配置（应用类别对应查询结果关系1-0,1;2-0,2）
	 * @param sysConfigRequest
	 * @param page
	 * @return
	 */
	List<SysConfig> selectByAppType(SysConfigRequest sysConfigRequest,RowBounds page);
	
	/**
	 * 根据key更新
	 * @param sysConfig
	 */
	void updateSysConfigByKey(SysConfig sysConfig);

}
