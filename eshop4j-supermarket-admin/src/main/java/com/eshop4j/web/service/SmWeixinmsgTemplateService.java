package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.mc.SmWeixinmsgTemplate;
import com.eshop4j.web.service.SmWeixinmsgTemplateService;
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
