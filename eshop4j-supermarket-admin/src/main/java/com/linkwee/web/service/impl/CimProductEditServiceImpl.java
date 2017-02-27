package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProductEdit;
import com.linkwee.web.dao.CimProductEditMapper;
import com.linkwee.web.request.CimProductEditDataTableRequest;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.response.CimProductEditResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductEditService;
import com.linkwee.web.service.impl.CimProductEditServiceImpl;


 /**
 * 
 * @描述：CimProductEditService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月28日 13:47:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductEditService")
public class CimProductEditServiceImpl extends GenericServiceImpl<CimProductEdit, Long> implements CimProductEditService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductEditServiceImpl.class);
	
	@Resource
	private CimProductEditMapper cimProductEditMapper;
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Override
    public GenericDao<CimProductEdit, Long> getDao() {
        return cimProductEditMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductEdit -- 排序和模糊查询 ");
		Page<CimProductEdit> page = new Page<CimProductEdit>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductEdit> list = this.cimProductEditMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findProductEditList(CimProductEditDataTableRequest dt) {
		Page<CimProductEdit> page = new Page<CimProductEdit>(dt.getStart() / dt.getLength() + 1,dt.getLength());
		List<CimProductEditResponse> cimProductEditResponses = cimProductEditMapper.findProductEditList(dt,page);
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setDraw(dt.getDraw()+1);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(cimProductEditResponses);
		return dataTableReturn;
	}

}
