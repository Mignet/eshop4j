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

import com.eshop4j.web.model.CimProductStatistics;
import com.eshop4j.web.dao.CimProductStatisticsMapper;
import com.eshop4j.web.service.CimProductStatisticsService;
import com.eshop4j.web.service.impl.CimProductStatisticsServiceImpl;


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
@Service("cimProductStatisticsService")
public class CimProductStatisticsServiceImpl extends GenericServiceImpl<CimProductStatistics, Long> implements CimProductStatisticsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductStatisticsServiceImpl.class);
	
	@Resource
	private CimProductStatisticsMapper cimProductStatisticsMapper;
	
	@Override
    public GenericDao<CimProductStatistics, Long> getDao() {
        return cimProductStatisticsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductStatistics -- 排序和模糊查询 ");
		Page<CimProductStatistics> page = new Page<CimProductStatistics>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductStatistics> list = this.cimProductStatisticsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
