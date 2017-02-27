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

import com.eshop4j.web.model.CrmCfpLoginLog;
import com.eshop4j.web.dao.CrmCfpLoginLogMapper;
import com.eshop4j.web.service.CrmCfpLoginLogService;
import com.eshop4j.web.service.impl.CrmCfpLoginLogServiceImpl;


 /**
 * 
 * @描述：CrmCfpLoginLogService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 18:57:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLoginLogService")
public class CrmCfpLoginLogServiceImpl extends GenericServiceImpl<CrmCfpLoginLog, Long> implements CrmCfpLoginLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLoginLogServiceImpl.class);
	
	@Resource
	private CrmCfpLoginLogMapper crmCfpLoginLogMapper;
	
	@Override
    public GenericDao<CrmCfpLoginLog, Long> getDao() {
        return crmCfpLoginLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLoginLog -- 排序和模糊查询 ");
		Page<CrmCfpLoginLog> page = new Page<CrmCfpLoginLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLoginLog> list = this.crmCfpLoginLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
