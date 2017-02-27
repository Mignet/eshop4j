package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.User;
import com.linkwee.web.model.acc.AcOfflineRewardDraft;
import com.linkwee.web.request.AcOfflineRewardDraftRequest;
 /**
 * 
 * @描述： AcOfflineRewardDraftService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年01月03日 16:16:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcOfflineRewardDraftService extends GenericService<AcOfflineRewardDraft,Long>{

	/**
	 * 查询AcOfflineRewardDraft列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	DataTableReturn queryAcOfflineRewardDraft(DataTable dataTable, AcOfflineRewardDraftRequest req);

	void inputRewardData(List<AcOfflineRewardDraft> list);

	List<String> queryNotGrantBatchList();

	void grantReward(List<AcOfflineRewardDraft> list, User adminUser);
	
	
}
