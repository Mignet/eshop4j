package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgMemberInfo;
 /**
 * 
 * @描述： CimOrgMemberinfoService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 18:52:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgMemberinfoService extends GenericService<CimOrgMemberInfo,Long>{

	/**
	 * 查询CimOrgMemberinfo列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 团队信息批量插入
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
