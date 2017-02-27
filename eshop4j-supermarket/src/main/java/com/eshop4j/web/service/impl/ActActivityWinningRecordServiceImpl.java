package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.api.activity.response.ActivityBillboardResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActActivityWinningRecordMapper;
import com.eshop4j.web.model.ActActivityCondition;
import com.eshop4j.web.model.ActActivityPrizeCase;
import com.eshop4j.web.model.ActActivityWinningRecord;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.service.ActActivityConditionService;
import com.eshop4j.web.service.ActActivityWinningRecordService;
import com.eshop4j.web.service.CrmUserInfoService;


 /**
 * 
 * @描述：ActActivityWinningRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:49:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actActivityWinningRecordService")
public class ActActivityWinningRecordServiceImpl extends GenericServiceImpl<ActActivityWinningRecord, Long> implements ActActivityWinningRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityWinningRecordServiceImpl.class);
	
	@Resource
	private ActActivityWinningRecordMapper actActivityWinningRecordMapper;
	
	@Resource
	private ActActivityConditionService actActivityConditionService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Override
    public GenericDao<ActActivityWinningRecord, Long> getDao() {
        return actActivityWinningRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActActivityWinningRecord -- 排序和模糊查询 ");
		Page<ActActivityWinningRecord> page = new Page<ActActivityWinningRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActActivityWinningRecord> list = this.actActivityWinningRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public Integer queryLeftTimes(String userId, ActivityList activity) {
		String leftTimesConditionSQL = actActivityConditionService.queryLeftTimeConditionSQL(activity);
		Integer leftTimes = 0;
		if(StringUtils.isNotBlank(leftTimesConditionSQL)){
			leftTimesConditionSQL = leftTimesConditionSQL.replaceAll("#\\{userId\\}", "'"+userId+"'").replaceAll("#\\{startDate\\}", "'"+DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG)+"'").replaceAll("#\\{endDate\\}","'"+DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG)+"'");
			leftTimes = actActivityWinningRecordMapper.execLeftTimesConditionSQL(leftTimesConditionSQL);
		}		
		return leftTimes;
	}
	
	@Override
	public Integer queryLeftTimesInGradeCondition(String userId, ActivityList activity) {
		Integer leftTimes = actActivityWinningRecordMapper.queryLeftTimesInGradeCondition(userId,activity);		
		return leftTimes;
	}

	@Override
	public ActActivityWinningRecord queryNotIssuedPrize(ActivityList activity, String userId, int i) {
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		actActivityWinningRecord.setIssued(0);
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setWinningType(1);
		return this.selectOne(actActivityWinningRecord);
	}

	@Override
	public ActActivityWinningRecord queryNotIssuedPrize(ActActivityPrizeCase actActivityPrizeCase, String userId) {
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(actActivityPrizeCase.getActivityId());
		actActivityWinningRecord.setIssued(0);
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setWinningType(actActivityPrizeCase.getPrizeStyle());
		actActivityWinningRecord.setPrizeCase(actActivityPrizeCase.getPrizeCase());
		actActivityWinningRecord.setConditionCase(actActivityPrizeCase.getConditionCase());
		return this.selectOne(actActivityWinningRecord);
	}

	@Override
	public List<ActActivityWinningRecord> queryIssuedPrize(ActActivityPrizeCase actActivityPrizeCase, String userId) {
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(actActivityPrizeCase.getActivityId());
		actActivityWinningRecord.setIssued(1);
		actActivityWinningRecord.setUserId(userId);
		actActivityWinningRecord.setWinningType(actActivityPrizeCase.getPrizeStyle());
		actActivityWinningRecord.setConditionCase(actActivityPrizeCase.getConditionCase());
		actActivityWinningRecord.setPrizeCase(actActivityPrizeCase.getPrizeCase());
		return this.selectListByCondition(actActivityWinningRecord);
	}

	@Override
	public PaginatorResponse<ActActivityWinningRecord> queryUserPrizeRecord(ActActivityWinningRecord actActivityWinningRecord, Page<ActActivityWinningRecord> page) {
		PaginatorResponse<ActActivityWinningRecord> paginatorResponse = new PaginatorResponse<ActActivityWinningRecord>();
		List<ActActivityWinningRecord> winningRecordPageListResponses = actActivityWinningRecordMapper.queryUserPrizeRecord(actActivityWinningRecord,page);	
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<ActActivityWinningRecord> queryNotIssueWinningRecord(ActActivityWinningRecord actActivityWinningRecord) {
		return actActivityWinningRecordMapper.queryNotIssueWinningRecord(actActivityWinningRecord);
	}

	@Override
	public List<ActActivityWinningRecord> createWinningRecords(ActivityList activity) {
		String conditionSQL = actActivityConditionService.queryUnknownPrizeCaseConditionSQL(activity);
		List<ActivityBillboardResponse> activityBillboardList = actActivityWinningRecordMapper.execUnknownPrizeCaseConditionSQL(conditionSQL);
		ActActivityWinningRecord actActivityWinningRecord = new ActActivityWinningRecord();
		actActivityWinningRecord.setActivityId(String.valueOf(activity.getId()));
		List<ActActivityWinningRecord> tempList = selectListByCondition(actActivityWinningRecord);
		
		if(tempList != null && tempList.size() > 0){
			for(int i = 0; i < tempList.size(); i++){
				ActActivityWinningRecord actActivityWinningRecordResult = new ActActivityWinningRecord();
				actActivityWinningRecordResult.setId(tempList.get(i).getId());
				actActivityWinningRecordResult.setUserId(activityBillboardList.get(i).getUserId());
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(activityBillboardList.get(i).getUserId());
				actActivityWinningRecordResult.setMobile(crmUserInfo.getMobile());
				actActivityWinningRecordResult.setOrderData(String.valueOf(activityBillboardList.get(i).getOrderData()));
				update(actActivityWinningRecordResult);
			}
		}else{
			int i = 1;
			for(ActivityBillboardResponse activityBillboardResponse : activityBillboardList){
				ActActivityWinningRecord actActivityWinningRecordResult = new ActActivityWinningRecord();
				actActivityWinningRecordResult.setActivityId(String.valueOf(activity.getId()));
				actActivityWinningRecordResult.setCreator("sys");
				actActivityWinningRecordResult.setCrtTime(new Date());
				actActivityWinningRecordResult.setPrizeStyle(0);
				actActivityWinningRecordResult.setIssued(0);
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(activityBillboardResponse.getUserId());
				actActivityWinningRecordResult.setMobile(crmUserInfo.getMobile());
				actActivityWinningRecordResult.setOrderDesc("");
				actActivityWinningRecordResult.setRecordId(StringUtils.getUUID());
				actActivityWinningRecordResult.setUserId(activityBillboardResponse.getUserId());
				actActivityWinningRecordResult.setWinningAmt(new BigDecimal(0));
				actActivityWinningRecordResult.setConditionCase(1);
				actActivityWinningRecordResult.setWinningType(0);
				actActivityWinningRecordResult.setPrizeCase(i++);
				actActivityWinningRecordResult.setWasteTimes(0);
				actActivityWinningRecordResult.setOrderData(String.valueOf(activityBillboardResponse.getOrderData()));
				insert(actActivityWinningRecordResult);
			}
		}
		
		List<ActActivityWinningRecord> resultList = selectListByCondition(actActivityWinningRecord);
		
		return resultList;
	}

}
