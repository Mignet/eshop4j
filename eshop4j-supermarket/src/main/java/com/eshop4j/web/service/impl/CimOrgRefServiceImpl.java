package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.CimOrgRefMapper;
import com.eshop4j.web.model.CimOrgRef;
import com.eshop4j.web.service.CimOrgRefService;


 /**
 * 
 * @描述：CimOrgRefService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月17日 15:26:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRefService")
public class CimOrgRefServiceImpl extends GenericServiceImpl<CimOrgRef, Long> implements CimOrgRefService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRefServiceImpl.class);
	
	@Resource
	private CimOrgRefMapper cimOrgRefMapper;
	
	@Override
    public GenericDao<CimOrgRef, Long> getDao() {
        return cimOrgRefMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRef -- 排序和模糊查询 ");
		Page<CimOrgRef> page = new Page<CimOrgRef>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRef> list = this.cimOrgRefMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public int deleteByCondition(CimOrgRef cimOrgRefNew) {
		// TODO Auto-generated method stub
		return cimOrgRefMapper.deleteByCondition(cimOrgRefNew);
	}
}
