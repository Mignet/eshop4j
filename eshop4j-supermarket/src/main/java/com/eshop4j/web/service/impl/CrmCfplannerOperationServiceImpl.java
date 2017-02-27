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
import com.eshop4j.web.dao.CrmCfplannerOperationMapper;
import com.eshop4j.web.model.crm.CrmCfplannerOperation;
import com.eshop4j.web.service.CrmCfplannerOperationService;


 /**
 * 
 * @描述：CrmCfplannerOperationService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 10:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfplannerOperationService")
public class CrmCfplannerOperationServiceImpl extends GenericServiceImpl<CrmCfplannerOperation, Long> implements CrmCfplannerOperationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfplannerOperationServiceImpl.class);
	
	@Resource
	private CrmCfplannerOperationMapper crmCfplannerOperationMapper;
	
	@Override
    public GenericDao<CrmCfplannerOperation, Long> getDao() {
        return crmCfplannerOperationMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfplannerOperation -- 排序和模糊查询 ");
		Page<CrmCfplannerOperation> page = new Page<CrmCfplannerOperation>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfplannerOperation> list = this.crmCfplannerOperationMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CrmCfplannerOperation> queryChangeParentRecordList(String userId) {
		return crmCfplannerOperationMapper.queryChangeParentRecordList(userId);
	}

}
