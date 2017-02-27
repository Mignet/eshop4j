package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SysGrayRelease;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysGrayReleaseMapper extends GenericDao<SysGrayRelease,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysGrayRelease> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询用户是否拥有灰度权限
	 * @param userId 用户id
	 * @param grayType  权限类型 0-公共 1-产品 2-机构  多个请使用","分割  如 0,1
	 * @return
	 */
	Integer ifHaveGrayPermission(@Param("userId") String userId, @Param("grayType") String grayType);
}
