package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.mc.SmWeixinmsgTemplate;
import com.linkwee.web.service.SmWeixinmsgTemplateService;
 /**
 * 
 * @描述： SmWeixinmsgTemplateService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月22日 19:11:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmWeixinmsgTemplateService extends GenericService<SmWeixinmsgTemplate,Long>{

	/**
	 * 查询SmWeixinmsgTemplate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
