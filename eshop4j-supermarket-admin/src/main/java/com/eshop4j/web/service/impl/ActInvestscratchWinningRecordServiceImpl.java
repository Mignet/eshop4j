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

import com.eshop4j.web.model.ActInvestscratchWinningRecord;
import com.eshop4j.web.dao.ActInvestscratchWinningRecordMapper;
import com.eshop4j.web.service.ActInvestscratchWinningRecordService;
import com.eshop4j.web.service.impl.ActInvestscratchWinningRecordServiceImpl;


 /**
 * 
 * @描述：ActInvestscratchWinningRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月25日 17:04:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actInvestscratchWinningRecordService")
public class ActInvestscratchWinningRecordServiceImpl extends GenericServiceImpl<ActInvestscratchWinningRecord, Long> implements ActInvestscratchWinningRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActInvestscratchWinningRecordServiceImpl.class);
	
	@Resource
	private ActInvestscratchWinningRecordMapper actInvestscratchWinningRecordMapper;
	
	@Override
    public GenericDao<ActInvestscratchWinningRecord, Long> getDao() {
        return actInvestscratchWinningRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActInvestscratchWinningRecord -- 排序和模糊查询 ");
		Page<ActInvestscratchWinningRecord> page = new Page<ActInvestscratchWinningRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActInvestscratchWinningRecord> list = this.actInvestscratchWinningRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    @Override
	public List<ActInvestscratchWinningRecord> queryWinningRecord() {
		// TODO Auto-generated method stub
		return actInvestscratchWinningRecordMapper.queryWinningRecordOfLastFifty();
	}

	@Override
	public Integer queryScratchedTime(String userId) {
		// TODO Auto-generated method stub
		return actInvestscratchWinningRecordMapper.queryScratchedTime(userId);
	}

	@Override
	public Integer queryTotalScratchTime(String userId) {
		// TODO Auto-generated method stub
		return actInvestscratchWinningRecordMapper.queryTotalScratchTime(userId);
	}

	@Override
	public Integer queryWinningUserNumber() {
		// TODO Auto-generated method stub
		return actInvestscratchWinningRecordMapper.queryWinningUserNumber();
	}

}
