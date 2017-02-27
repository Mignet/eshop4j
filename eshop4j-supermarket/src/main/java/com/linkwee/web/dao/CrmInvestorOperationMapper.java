package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmInvestorOperation;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 18:17:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorOperationMapper extends GenericDao<CrmInvestorOperation,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmInvestorOperation> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 归属理财师变更记录
	 * @param userId
	 * @return
	 */
	List<CrmInvestorOperation> queryChangeCfpRecordList(String userId);
	
}
