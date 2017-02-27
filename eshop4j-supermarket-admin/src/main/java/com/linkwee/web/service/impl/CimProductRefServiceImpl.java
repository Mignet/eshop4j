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

import com.linkwee.web.model.CimProductRef;
import com.linkwee.web.dao.CimProductRefMapper;
import com.linkwee.web.service.CimProductRefService;
import com.linkwee.web.service.impl.CimProductRefServiceImpl;


 /**
 * 
 * @描述：CimProductRefService 服务实现类
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 18:21:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductRefService")
public class CimProductRefServiceImpl extends GenericServiceImpl<CimProductRef, Long> implements CimProductRefService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductRefServiceImpl.class);
	
	@Resource
	private CimProductRefMapper cimProductRefMapper;
	
	@Override
    public GenericDao<CimProductRef, Long> getDao() {
        return cimProductRefMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductRef -- 排序和模糊查询 ");
		Page<CimProductRef> page = new Page<CimProductRef>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductRef> list = this.cimProductRefMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
