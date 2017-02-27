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

import com.linkwee.web.model.acc.AcWithdrawRecord;
import com.linkwee.web.dao.AcWithdrawRecordMapper;
import com.linkwee.web.service.AcWithdrawRecordService;
import com.linkwee.web.service.impl.AcWithdrawRecordServiceImpl;


 /**
 * 
 * @描述：AcWithdrawRecordService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acWithdrawRecordService")
public class AcWithdrawRecordServiceImpl extends GenericServiceImpl<AcWithdrawRecord, Long> implements AcWithdrawRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcWithdrawRecordServiceImpl.class);
	
	@Resource
	private AcWithdrawRecordMapper acWithdrawRecordMapper;
	
	@Override
    public GenericDao<AcWithdrawRecord, Long> getDao() {
        return acWithdrawRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcWithdrawRecord -- 排序和模糊查询 ");
		Page<AcWithdrawRecord> page = new Page<AcWithdrawRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcWithdrawRecord> list = this.acWithdrawRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
