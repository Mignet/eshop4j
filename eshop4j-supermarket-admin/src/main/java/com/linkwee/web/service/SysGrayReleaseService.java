package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysGrayRelease;
 /**
 * 
 * @描述： SysGrayReleaseService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysGrayReleaseService extends GenericService<SysGrayRelease,Long>{

	/**
	 * 查询SysGrayRelease列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
     * 根据手机号码查询灰度用户
     * @param mobile
     * @return
     */
	SysGrayRelease selectByMobile(String mobile);
}
