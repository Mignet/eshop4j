package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Long;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.request.cim.ProductCateForShowRequest;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.core.base.BaseEnum;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.dao.ActWheelWinningRecordMapper;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.impl.ActWheelWinningRecordServiceImpl;


 /**
 * 
 * @描述：ActWheelWinningRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 10:55:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actWheelWinningRecordService")
public class ActWheelWinningRecordServiceImpl extends GenericServiceImpl<ActWheelWinningRecord, Long> implements ActWheelWinningRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActWheelWinningRecordServiceImpl.class);
	
	@Resource
	private ActWheelWinningRecordMapper actWheelWinningRecordMapper;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Override
    public GenericDao<ActWheelWinningRecord, Long> getDao() {
        return actWheelWinningRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActWheelWinningRecord -- 排序和模糊查询 ");
		Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActWheelWinningRecord> list = this.actWheelWinningRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public Double queryInvestTotalMoney(String userId,String startDate,String endDate) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryInvestTotalMoney(userId,startDate,endDate);
	}

	@Override
	public Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType) throws Exception {
		// TODO Auto-generated method stub
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		actWheelWinningRecord.setDrawTimes(i);
		actWheelWinningRecord.setOrderDesc(baseLottery.getPrize());
		actWheelWinningRecord.setMobile(mobile);
		String wheelId = StringUtils.getUUID();
		actWheelWinningRecord.setWheelId(wheelId);
		actWheelWinningRecord.setWinningOrder(baseLottery.getId());
		actWheelWinningRecord.setCrtTime(new Date());
		if(baseLottery.getId() == 1 || baseLottery.getId() == 4){
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(actWheelWinningRecord.getWheelId());
			if(baseLottery.getId() == 1){
				recharge.setTransAmount(new BigDecimal(8));
			}else if(baseLottery.getId() == 4){
				recharge.setTransAmount(new BigDecimal(20));
			}	
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(3);
			recharge.setRemark("幸运大转盘抽取万元大奖活动奖励");
			accountbindService.accountRecharge(recharge);
			actWheelWinningRecord.setIssued(1);
		}else{
			actWheelWinningRecord.setIssued(0);
		}
		Integer resultInteger = actWheelWinningRecordMapper.insertSelective(actWheelWinningRecord);
		
		return resultInteger;
	}
	
	@Override
	public Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType, String activityId) throws Exception {
		// TODO Auto-generated method stub
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		actWheelWinningRecord.setDrawTimes(i);
		actWheelWinningRecord.setOrderDesc(baseLottery.getPrize());
		actWheelWinningRecord.setMobile(mobile);
		actWheelWinningRecord.setExtends1(activityId);
		String wheelId = StringUtils.getUUID();
		actWheelWinningRecord.setWheelId(wheelId);
		actWheelWinningRecord.setWinningOrder(baseLottery.getId());
		actWheelWinningRecord.setCrtTime(new Date());
		if(baseLottery.getId() == 1 || baseLottery.getId() == 2){
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(actWheelWinningRecord.getWheelId());
			if(baseLottery.getId() == 1){
				recharge.setTransAmount(new BigDecimal(8));
			}else if(baseLottery.getId() == 2){
				recharge.setTransAmount(new BigDecimal(18));
			}	
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(14);
			recharge.setRemark("春节大转盘玩赚乐视超级电视活动奖励");
			accountbindService.accountRecharge(recharge);
			actWheelWinningRecord.setIssued(1);
		}else if(baseLottery.getId() == 3 || baseLottery.getId() == 4){
			ActicityRedPacketEnum acticityRedPacketEnum = (ActicityRedPacketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityRedPacketEnum.values());
			redPacketService.lcsActicityRedPacket(userId, acticityRedPacketEnum);
			actWheelWinningRecord.setIssued(1);
		}else{
			actWheelWinningRecord.setIssued(0);
		}
		Integer resultInteger = actWheelWinningRecordMapper.insertSelective(actWheelWinningRecord);
		
		return resultInteger;
	}

	@Override
	public PaginatorResponse<ActWheelWinningRecord> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<ActWheelWinningRecord> page) {
		PaginatorResponse<ActWheelWinningRecord> paginatorResponse = new PaginatorResponse<ActWheelWinningRecord>();
		List<ActWheelWinningRecord> winningRecordPageListResponses = actWheelWinningRecordMapper.queryUserPrizeRecord(actWheelWinningRecord,page);	
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer queryHasDrawTimes(String userId, String activityId) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryHasDrawTimes(userId,activityId);
	}

	@Override
	public Double queryInvestorHasInvestedTotalMoney(String userId,String startDate, String endDate) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryInvestorHasInvestedTotalMoney(userId,startDate,endDate);
	}

}
