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

import com.linkwee.web.model.CrmCfpUpgradeRecord;
import com.linkwee.web.dao.CrmCfpUpgradeRecordMapper;
import com.linkwee.web.service.CrmCfpUpgradeRecordService;
import com.linkwee.web.service.impl.CrmCfpUpgradeRecordServiceImpl;


 /**
 * 
 * @描述：CrmCfpUpgradeRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 16:04:56
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpUpgradeRecordService")
public class CrmCfpUpgradeRecordServiceImpl extends GenericServiceImpl<CrmCfpUpgradeRecord, Long> implements CrmCfpUpgradeRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpUpgradeRecordServiceImpl.class);
	
	@Resource
	private CrmCfpUpgradeRecordMapper crmCfpUpgradeRecordMapper;
	
	@Override
    public GenericDao<CrmCfpUpgradeRecord, Long> getDao() {
        return crmCfpUpgradeRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpUpgradeRecord -- 排序和模糊查询 ");
		Page<CrmCfpUpgradeRecord> page = new Page<CrmCfpUpgradeRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpUpgradeRecord> list = this.crmCfpUpgradeRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
