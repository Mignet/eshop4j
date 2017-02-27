package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimProductExtends;
import com.eshop4j.web.model.share.ShareContent;
 /**
 * 
 * @描述： CimProductExtendsService服务接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 17:02:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductExtendsService extends GenericService<CimProductExtends,Long>{

	/**
	 * 查询CimProductExtends列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据产品id查询产品扩展信息
	 * @param productId
	 * @return
	 */
	CimProductExtends selectByProductId(String productId);
	/**
	 * 根据产品id查询产品分享信息
	 * @param productId
	 * @return
	 */
	ShareContent selectShareContentByProductId(String productId);
}
