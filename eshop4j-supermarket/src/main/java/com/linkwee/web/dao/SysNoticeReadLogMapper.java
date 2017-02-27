package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.mc.SysNoticeReadLog;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月04日 17:09:02
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysNoticeReadLogMapper extends GenericDao<SysNoticeReadLog,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysNoticeReadLog> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	/**
	 * 录入用户通知读取日记
	 */
	int addNoticeReadLogByUserId(@Param("userId")String userId,@Param("appType")Integer appType);
}
