package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.GrayRelease;
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
     * 根据手机号码查询灰度用户
     */
	SysGrayRelease selectByMobile(@Param("mobile")String mobile);
}
