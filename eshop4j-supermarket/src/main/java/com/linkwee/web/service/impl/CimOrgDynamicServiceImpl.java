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
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.dao.CimOrgDynamicMapper;
import com.linkwee.web.service.CimOrgDynamicService;
import com.linkwee.web.service.impl.CimOrgDynamicServiceImpl;


 /**
 * 
 * @描述：CimOrgDynamicService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月02日 14:59:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgDynamicService")
public class CimOrgDynamicServiceImpl extends GenericServiceImpl<CimOrgDynamic, Long> implements CimOrgDynamicService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgDynamicServiceImpl.class);
	
	@Resource
	private CimOrgDynamicMapper cimOrgDynamicMapper;
	
	@Override
    public GenericDao<CimOrgDynamic, Long> getDao() {
        return cimOrgDynamicMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgDynamic -- 排序和模糊查询 ");
		Page<CimOrgDynamic> page = new Page<CimOrgDynamic>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgDynamic> list = this.cimOrgDynamicMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgDynamic> queryCimOrgDynamicList(String orgNumber) {
		return cimOrgDynamicMapper.queryCimOrgDynamicList(orgNumber);
	}

	@Override
	public CimOrgDynamic queryOrgDynamicInfo(int orgDynamicId) {
		return cimOrgDynamicMapper.queryOrgDynamicInfo(orgDynamicId);
	}

}
