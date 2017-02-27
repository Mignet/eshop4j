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

import com.eshop4j.web.model.acc.AcAccountErrorLog;
import com.eshop4j.web.dao.AcAccountErrorLogMapper;
import com.eshop4j.web.service.AcAccountErrorLogService;
import com.eshop4j.web.service.impl.AcAccountErrorLogServiceImpl;


 /**
 * 
 * @描述：AcAccountErrorLogService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月30日 18:03:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acAccountErrorLogService")
public class AcAccountErrorLogServiceImpl extends GenericServiceImpl<AcAccountErrorLog, Long> implements AcAccountErrorLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountErrorLogServiceImpl.class);
	
	@Resource
	private AcAccountErrorLogMapper acAccountErrorLogMapper;
	
	@Override
    public GenericDao<AcAccountErrorLog, Long> getDao() {
        return acAccountErrorLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcAccountErrorLog -- 排序和模糊查询 ");
		Page<AcAccountErrorLog> page = new Page<AcAccountErrorLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcAccountErrorLog> list = this.acAccountErrorLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
