package com.eshop4j.web.service;

import java.util.List;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimOrgPicture;
import com.eshop4j.web.service.CimOrgPictureService;
 /**
 * 
 * @描述： CimOrgPictureService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月27日 14:51:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgPictureService extends GenericService<CimOrgPicture,Long>{

	/**
	 * 查询CimOrgPicture列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据图片类型和端口类型查询该机构所有图片
	 * @author yalin 
	 * @date 2016年10月27日 下午3:18:53  
	 * @param orgNumber
	 * @return
	 */
	public List<CimOrgPicture> queryOrgPictureList(String orgNumber,int pictureType,int source);
	
	/**
	 * 图片批量插入
	 * @author yalin 
	 * @date 2016年8月18日 下午3:20:55  
	 * @param teams
	 */
	public void insertBatchPicture(List<CimOrgPicture> pictures);
	
	/**
	 * 图片批量更新
	 * @author yalin 
	 * @date 2016年8月18日 下午3:20:55  
	 * @param teams
	 */
	public void updateBatchPicture(List<CimOrgPicture> pictures);
}
