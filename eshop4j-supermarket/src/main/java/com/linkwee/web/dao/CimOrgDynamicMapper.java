package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgDynamic;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月02日 14:59:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgDynamicMapper extends GenericDao<CimOrgDynamic,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgDynamic> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 平台动态分页查询 显示最近10条动态
	 * @author yalin 
	 * @date 2016年11月2日 下午4:01:07  
	 * @return
	 */
	List<CimOrgDynamic> queryCimOrgDynamicList(String orgNumber);
	
	/**
	 * 平台动态详情
	 * @author yalin 
	 * @date 2016年11月2日 下午4:06:13  
	 * @param orgDynamicId
	 * @return
	 */
	CimOrgDynamic queryOrgDynamicInfo(int orgDynamicId);
}
