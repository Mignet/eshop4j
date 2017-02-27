package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgFeeTimetaskMapper;
import com.linkwee.web.model.CimOrgFeeTimetask;
import com.linkwee.web.service.CimOrgFeeTimetaskService;
import com.linkwee.web.service.CimOrginfoService;


 /**
 * 
 * @描述：CimOrgFeeTimetaskService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月11日 17:22:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFeeTimetaskService")
public class CimOrgFeeTimetaskServiceImpl extends GenericServiceImpl<CimOrgFeeTimetask, Long> implements CimOrgFeeTimetaskService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeTimetaskServiceImpl.class);
	
	@Resource
	private CimOrgFeeTimetaskMapper cimOrgFeeTimetaskMapper;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	
	@Override
    public GenericDao<CimOrgFeeTimetask, Long> getDao() {
        return cimOrgFeeTimetaskMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFeeTimetask -- 排序和模糊查询 ");
		Page<CimOrgFeeTimetask> page = new Page<CimOrgFeeTimetask>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFeeTimetask> list = this.cimOrgFeeTimetaskMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgFeeTimetask> queryOrgFeeTimeTaskByStatus(int taskStatus,String orgNumber,Date currentTime) {
		return cimOrgFeeTimetaskMapper.queryOrgFeeTimeTaskByStatus(taskStatus,orgNumber,currentTime);
	}
	

	@Override
	public BigDecimal queryOrgCurrentBuyDateFee(String orgNumber, Date buyDate) {
		CimOrgFeeTimetask orgFeeTimetask = cimOrgFeeTimetaskMapper.queryOrgCurrentBuyDateFee(orgNumber, buyDate);
		if(orgFeeTimetask == null){
			//返回当前机构的佣金率
			BigDecimal currOrgFeeRatio = cimOrginfoService.queryOrgFeeRatio(orgNumber);
			return currOrgFeeRatio;
		}else{
			return orgFeeTimetask.getOrgFeeRatio(); //新佣金率
		}
	}

}
