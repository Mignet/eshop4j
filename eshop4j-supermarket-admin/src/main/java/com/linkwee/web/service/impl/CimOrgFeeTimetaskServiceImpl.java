package com.linkwee.web.service.impl;

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
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.request.CimOrgFeeTimetaskRequest;
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
	public void insertOrgFeeTimetask(CimOrginfoWeb orginfo,CimOrgFeeTimetaskRequest request){
				//活动开始设置机构佣金率的定时任务
				if(request.getActivityStartTaskType() == 1){
					CimOrgFeeTimetask orgFeeTask = new CimOrgFeeTimetask();
					orginfo.setOrgFeeRatio(request.getActivityStartOrgFeeRatio());
					orgFeeTask.setOrgNumber(orginfo.getOrgNumber());
					orgFeeTask.setOrgName(orginfo.getName());
					orgFeeTask.setOrgOldFeeRatio(request.getOrgOldFeeRatio()); //机构原来的佣金率
					orgFeeTask.setOrgFeeRatio(request.getActivityStartOrgFeeRatio()); //活动开始机构新佣金率
					orgFeeTask.setTaskStartTime(request.getActivityStartTaskTime()); //定时任务开始时间
					orgFeeTask.setTaskType(1); //'定时任务类型(1:活动开始任务,2:活动结束任务)'
					orgFeeTask.setCreater(orginfo.getOrgCreator());
					orgFeeTask.setCreateTime(new Date());
					orgFeeTask.setTaskCreateReason(request.getTaskCreateReason()); //定时任务创建原因
					orgFeeTask.setTaskStatus(1); //已触发待执行
					orgFeeTask.setRemark("准备执行活动开始定时任务");
					this.insert(orgFeeTask);
				}
				
				//活动结束设置机构佣金率的定时任务
				if(request.getActivityEndTaskType() == 2){
					CimOrgFeeTimetask orgFeeTask = new CimOrgFeeTimetask();
					orginfo.setOrgFeeRatio(request.getActivityEndOrgFeeRatio());
					orgFeeTask.setOrgNumber(orginfo.getOrgNumber());
					orgFeeTask.setOrgName(orginfo.getName());
					orgFeeTask.setOrgOldFeeRatio(request.getOrgOldFeeRatio()); //机构原来的佣金率
					orgFeeTask.setOrgFeeRatio(request.getActivityEndOrgFeeRatio()); //活动结束机构新佣金率
					orgFeeTask.setTaskStartTime(request.getActivityEndTaskTime()); //定时任务开始时间
					orgFeeTask.setTaskType(2); //'定时任务类型(1:活动开始任务,2:活动结束任务)'
					orgFeeTask.setCreater(orginfo.getOrgCreator());
					orgFeeTask.setCreateTime(new Date());
					orgFeeTask.setTaskCreateReason(request.getTaskCreateReason()); //定时任务创建原因
					orgFeeTask.setTaskStatus(1); //已触发待执行
					orgFeeTask.setRemark("准备执行活动结束定时任务");
					this.insert(orgFeeTask);
					
				}
	}

	
}
