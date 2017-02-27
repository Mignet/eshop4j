package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.acc.AcOfflineRewardDraft;
import com.linkwee.web.request.AcOfflineRewardDraftRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年01月03日 16:16:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcOfflineRewardDraftMapper extends GenericDao<AcOfflineRewardDraft,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcOfflineRewardDraft> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	List<AcOfflineRewardDraft> queryAcOfflineRewardDraft(@Param("query")AcOfflineRewardDraftRequest req, RowBounds page);

	/**
	 * 未发放批次
	 * @return
	 */
	List<String> queryNotGrantBatchList();

	/**
	 * 查询对象
	 * @param id
	 * @return
	 */
	AcOfflineRewardDraft queryAcOfflineRewardDraftForUpdate(Integer id);
	
	
}
