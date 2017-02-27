package com.eshop4j.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.AcAccountBindMapper;
import com.eshop4j.web.dao.AcBalanceRecordMapper;
import com.eshop4j.web.dao.AcOfflineRewardDraftMapper;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.User;
import com.eshop4j.web.model.acc.AcAccountBind;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.model.acc.AcOfflineRewardDraft;
import com.eshop4j.web.request.AcOfflineRewardDraftRequest;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.AcBalanceRecordService;
import com.eshop4j.web.service.AcOfflineRewardDraftService;
import com.eshop4j.web.service.CrmUserInfoService;


 /**
 * 
 * @描述：AcOfflineRewardDraftService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年01月03日 16:16:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acOfflineRewardDraftService")
public class AcOfflineRewardDraftServiceImpl extends GenericServiceImpl<AcOfflineRewardDraft, Long> implements AcOfflineRewardDraftService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcOfflineRewardDraftServiceImpl.class);
	
	@Resource
	private AcOfflineRewardDraftMapper acOfflineRewardDraftMapper;
	@Resource
	private AcBalanceRecordService acBalanceRecordService;
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	@Resource
	private AcAccountBindMapper accountbindMapper;
	@Resource
	private AcAccountBindService accountbindService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Override
    public GenericDao<AcOfflineRewardDraft, Long> getDao() {
        return acOfflineRewardDraftMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcOfflineRewardDraft -- 排序和模糊查询 ");
		Page<AcOfflineRewardDraft> page = new Page<AcOfflineRewardDraft>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcOfflineRewardDraft> list = this.acOfflineRewardDraftMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn queryAcOfflineRewardDraft(DataTable dataTable, AcOfflineRewardDraftRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<AcOfflineRewardDraft> page = new Page<AcOfflineRewardDraft>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<AcOfflineRewardDraft> list = this.acOfflineRewardDraftMapper.queryAcOfflineRewardDraft(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}


	@Transactional
	@Override
	public void inputRewardData(List<AcOfflineRewardDraft> list) {
		for(AcOfflineRewardDraft draft : list) {
			this.insert(draft);
		}
	}

	@Override
	public List<String> queryNotGrantBatchList() {
		List<String> list = acOfflineRewardDraftMapper.queryNotGrantBatchList();
		return list;
	}

	@Transactional
	@Override
	public void grantReward(List<AcOfflineRewardDraft> list, User adminUser) {
		for(AcOfflineRewardDraft bo : list) {
			AcOfflineRewardDraft dr = acOfflineRewardDraftMapper.queryAcOfflineRewardDraftForUpdate(bo.getId());
			if(dr.getStatus() == 0) {
				AcAccountBind ac = accountbindService.selectAccountByUserId(dr.getUserId());
				CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(dr.getUserId());
				AcBalanceRecord re = new AcBalanceRecord();
				re.setBankCardId(ac.getBankCardId());
				re.setTransAmount(dr.getTransAmount());
				re.setMobile(userInfo.getMobile());
				re.setCreateTime(new Date());
				re.setCreatePerson(adminUser.getUsername());
				re.setOrderId(StringUtils.getUUID());
				re.setTransDate(new Date());
				re.setTransType(dr.getTransType());
				re.setUserId(dr.getUserId());
				re.setUserType(dr.getUserType());//投资者
				re.setRemark(dr.getRemark());
				re.setCreateType(1);
				
				//插入交易明细
				acBalanceRecordMapper.insertSelective(re);
			    //添加发放金额
				accountbindMapper.acGrantProfit(re.getTransAmount().toString(),re.getUserId());
				
				//修改草稿表状态为已发放
				AcOfflineRewardDraft  update = new AcOfflineRewardDraft();
				update.setId(dr.getId());
				update.setStatus(1);
				update.setGrantTime(new Date());
				update.setLastUpdateTime(new Date());
				update.setLastUpdatePerson(adminUser.getUsername());
				this.update(update);
				
			}
		}
	}


}
