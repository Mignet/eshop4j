package com.linkwee.web.service;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.cim.CimProductUnrecordInvest;
import com.linkwee.web.request.tc.AuditUnrecordInvestRequest;
import com.linkwee.web.request.tc.UnrecordInvestRequest;
 /**
 * 
 * @描述： CimProductUnrecordInvestService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestService extends GenericService<CimProductUnrecordInvest,Long>{


	DataTableReturn getUnrecordInvestList(UnrecordInvestRequest req); 
	
	/**
	 * 审核
	 * @param req 审核数据
	 * @param userName 审核人
	 */
	ResponseResult audit(AuditUnrecordInvestRequest req,String userName)throws Exception;
}
