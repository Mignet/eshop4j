package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimProductCateMapper;
import com.linkwee.web.model.CimProductCate;
import com.linkwee.web.model.CimProductCateManager;
import com.linkwee.web.request.ProductCateDataRequest;
import com.linkwee.web.service.CimProductCateService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductCateService")
public class CimProductCateServiceImpl extends GenericServiceImpl<CimProductCate, Long> implements CimProductCateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductCateServiceImpl.class);
	
	@Resource
	private CimProductCateMapper cimProductCateMapper;
	
	@Override
    public GenericDao<CimProductCate, Long> getDao() {
        return cimProductCateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductCate -- 排序和模糊查询 ");
		Page<CimProductCate> page = new Page<CimProductCate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductCate> list = this.cimProductCateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getCimProductCates(ProductCateDataRequest productCateDataRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(productCateDataRequest.getDraw()+1);
		LOGGER.debug(" -- 后台查询产品分类列表 -- 排序和模糊查询 ");
		Page<CimProductCateManager> page = new Page<CimProductCateManager>(productCateDataRequest.getStart()/productCateDataRequest.getLength()+1,productCateDataRequest.getLength());
		List<CimProductCateManager> list = cimProductCateMapper.getCimProductCates(productCateDataRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
