package com.eshop4j.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.SmAppVersion;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月26日 20:22:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmAppVersionMapper extends GenericDao<SmAppVersion,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmAppVersion> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据平台类型与应用类别查询版本信息
	 * @param platform
	 * @param appType
	 * @return
	 */
	public List<SmAppVersion> queryNewAppVersion(@Param("platform")String platform,@Param("appType")Integer appType);

	/**
	 * app日志
	 * @param smAppVersion
	 * @return
	 */
	List<SmAppVersion> queryAppLogList(SmAppVersion smAppVersion);
}
