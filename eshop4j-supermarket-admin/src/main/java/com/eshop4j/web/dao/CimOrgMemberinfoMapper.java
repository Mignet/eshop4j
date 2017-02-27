package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.CimOrgMemberInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 18:52:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgMemberinfoMapper extends GenericDao<CimOrgMemberInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgMemberInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 团队成员信息批量插入
	 * @author yalin 
	 * @date 2016年8月18日 下午3:20:55  
	 * @param teams
	 */
	public void insertBatch(List<CimOrgMemberInfo> teams);
	
	/**
	 * 团队成员信息批量更新
	 * @author yalin 
	 * @date 2016年9月7日 下午3:42:43  
	 * @param teams
	 */
	public void updateBatchTeam(List<CimOrgMemberInfo> teams);
}
