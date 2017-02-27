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

import com.eshop4j.web.model.CrmInvestorLoginLog;
import com.eshop4j.web.dao.CrmInvestorLoginLogMapper;
import com.eshop4j.web.service.CrmInvestorLoginLogService;
import com.eshop4j.web.service.impl.CrmInvestorLoginLogServiceImpl;


 /**
 * 
 * @描述：CrmInvestorLoginLogService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 19:11:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmInvestorLoginLogService")
public class CrmInvestorLoginLogServiceImpl extends GenericServiceImpl<CrmInvestorLoginLog, Long> implements CrmInvestorLoginLogService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmInvestorLoginLogServiceImpl.class);
	
	@Resource
	private CrmInvestorLoginLogMapper crmInvestorLoginLogMapper;
	
	@Override
    public GenericDao<CrmInvestorLoginLog, Long> getDao() {
        return crmInvestorLoginLogMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmInvestorLoginLog -- 排序和模糊查询 ");
		Page<CrmInvestorLoginLog> page = new Page<CrmInvestorLoginLog>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmInvestorLoginLog> list = this.crmInvestorLoginLogMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
