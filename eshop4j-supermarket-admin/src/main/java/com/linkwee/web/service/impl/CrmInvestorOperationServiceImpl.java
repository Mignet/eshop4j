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

import com.linkwee.web.model.CrmInvestorOperation;
import com.linkwee.web.dao.CrmInvestorOperationMapper;
import com.linkwee.web.service.CrmInvestorOperationService;
import com.linkwee.web.service.impl.CrmInvestorOperationServiceImpl;


 /**
 * 
 * @描述：CrmInvestorOperationService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 18:17:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmInvestorOperationService")
public class CrmInvestorOperationServiceImpl extends GenericServiceImpl<CrmInvestorOperation, Long> implements CrmInvestorOperationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmInvestorOperationServiceImpl.class);
	
	@Resource
	private CrmInvestorOperationMapper crmInvestorOperationMapper;
	
	@Override
    public GenericDao<CrmInvestorOperation, Long> getDao() {
        return crmInvestorOperationMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmInvestorOperation -- 排序和模糊查询 ");
		Page<CrmInvestorOperation> page = new Page<CrmInvestorOperation>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmInvestorOperation> list = this.crmInvestorOperationMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CrmInvestorOperation> queryChangeCfpRecordList(String userId) {
		return crmInvestorOperationMapper.queryChangeCfpRecordList(userId);
	}

}
