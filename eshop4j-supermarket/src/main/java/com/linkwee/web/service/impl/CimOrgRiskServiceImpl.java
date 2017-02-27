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
import com.linkwee.web.model.CimOrgRisk;
import com.linkwee.web.dao.CimOrgRiskMapper;
import com.linkwee.web.service.CimOrgRiskService;
import com.linkwee.web.service.impl.CimOrgRiskServiceImpl;


 /**
 * 
 * @描述：CimOrgRiskService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月22日 11:03:54
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRiskService")
public class CimOrgRiskServiceImpl extends GenericServiceImpl<CimOrgRisk, Long> implements CimOrgRiskService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskServiceImpl.class);
	
	@Resource
	private CimOrgRiskMapper cimOrgRiskMapper;
	
	@Override
    public GenericDao<CimOrgRisk, Long> getDao() {
        return cimOrgRiskMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRisk -- 排序和模糊查询 ");
		Page<CimOrgRisk> page = new Page<CimOrgRisk>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRisk> list = this.cimOrgRiskMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgRisk> queryOrgRiskInfoByOrgNumber(String orgNumber) {
		return cimOrgRiskMapper.queryOrgRiskInfoByOrgNumber(orgNumber);
	}

}
