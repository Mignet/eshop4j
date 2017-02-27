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

import com.linkwee.web.model.mc.SysPushParameter;
import com.linkwee.web.dao.SysPushParameterMapper;
import com.linkwee.web.service.SysPushParameterService;
import com.linkwee.web.service.impl.SysPushParameterServiceImpl;


 /**
 * 
 * @描述：SysPushParameterService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 10:43:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysPushParameterService")
public class SysPushParameterServiceImpl extends GenericServiceImpl<SysPushParameter, Long> implements SysPushParameterService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPushParameterServiceImpl.class);
	
	@Resource
	private SysPushParameterMapper sysPushParameterMapper;
	
	@Override
    public GenericDao<SysPushParameter, Long> getDao() {
        return sysPushParameterMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysPushParameter -- 排序和模糊查询 ");
		Page<SysPushParameter> page = new Page<SysPushParameter>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysPushParameter> list = this.sysPushParameterMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
