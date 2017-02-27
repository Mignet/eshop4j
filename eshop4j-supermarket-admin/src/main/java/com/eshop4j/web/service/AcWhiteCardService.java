package com.eshop4j.web.service;

import java.util.List;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.acc.AcWhiteCard;
import com.eshop4j.web.service.AcWhiteCardService;
 /**
 * 
 * @描述： AcWhiteCardService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月26日 17:45:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWhiteCardService extends GenericService<AcWhiteCard,Long>{

	/**
	 * 查询AcWhiteCard列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询白名单
	 * */
	boolean queryAcWhiteCardByBankCard(String bankCard);
}
