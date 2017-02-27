package com.eshop4j.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.AcAccountBindMapper;
import com.eshop4j.web.dao.AcBalanceRecordMapper;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.service.AcBalanceRecordService;


 /**
 * 
 * @描述：AcBalanceRecordService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acBalanceRecordService")
public class AcBalanceRecordServiceImpl extends GenericServiceImpl<AcBalanceRecord, Long> implements AcBalanceRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBalanceRecordServiceImpl.class);
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	

	
	@Override
    public GenericDao<AcBalanceRecord, Long> getDao() {
        return acBalanceRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}
	
	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails2(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails2(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}

	@Override
	@Transactional
	public void grantProfit(List<AcBalanceRecord> balanList) {
		
		for(AcBalanceRecord ac:balanList){
			//插入交易明细
			acBalanceRecordMapper.insertSelective(ac);
		    //添加发放金额
			accountbindMapper.acGrantProfit(ac.getTransAmount().toString(),ac.getUserId());
			
		}
		
	}

	@Override
	public DataTableReturn selectGrantByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectGrantBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<AcBalanceRecord> checkSameSerialNumber(String serialNumber) {
		return acBalanceRecordMapper.checkSameSerialNumber(serialNumber);
	}
	

}
