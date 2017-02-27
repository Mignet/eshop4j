package com.eshop4j.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.CimOrgAdvertises;
import com.eshop4j.web.dao.CimOrgAdvertisesMapper;
import com.eshop4j.web.service.CimOrgAdvertisesService;
import com.eshop4j.web.service.impl.CimOrgAdvertisesServiceImpl;


 /**
 * 
 * @描述：CimOrgAdvertisesService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 18:23:35
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgAdvertisesService")
public class CimOrgAdvertisesServiceImpl extends GenericServiceImpl<CimOrgAdvertises, Long> implements CimOrgAdvertisesService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgAdvertisesServiceImpl.class);
	
	@Resource
	private CimOrgAdvertisesMapper cimOrgAdvertisesMapper;
	
	@Override
    public GenericDao<CimOrgAdvertises, Long> getDao() {
        return cimOrgAdvertisesMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgAdvertises -- 排序和模糊查询 ");
		Page<CimOrgAdvertises> page = new Page<CimOrgAdvertises>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgAdvertises> list = this.cimOrgAdvertisesMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgAdvertises> queryOrgAdvertisesList(String orgNumber) {
		return cimOrgAdvertisesMapper.queryOrgAdvertisesList(orgNumber);
	}

}
