package com.linkwee.act.redpacket.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketUseRecord;
import com.linkwee.act.redpacket.service.ActRedpacketUseRecordService;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActRedpacketUseRecordMapper;
import com.linkwee.web.model.vo.InvestRecordWrapper;


 /**
 * 
 * @描述：ActRedpacketUseRecordService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 19:49:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketUseRecordService")
public class ActRedpacketUseRecordServiceImpl extends GenericServiceImpl<ActRedpacketUseRecord, Long> implements ActRedpacketUseRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketUseRecordServiceImpl.class);
	
	@Resource
	private ActRedpacketUseRecordMapper actRedpacketUseRecordMapper;
	
	@Override
    public GenericDao<ActRedpacketUseRecord, Long> getDao() {
        return actRedpacketUseRecordMapper;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int insertRedpacketUseRecord(String rechargeId,InvestRecordWrapper investRecord, ActRedpacketDetail redpacketDetail) {
		ActRedpacketUseRecord useRecord = new ActRedpacketUseRecord();
		useRecord.setRedpacketId(redpacketDetail.getRedpacketId());
		useRecord.setRedpacketSendId(redpacketDetail.getSendId());
		useRecord.setRedpacketDetailId(redpacketDetail.getRedpacketDetailId());
		useRecord.setName(redpacketDetail.getName());
		useRecord.setType(redpacketDetail.getType());
		useRecord.setMoney(redpacketDetail.getMoney());
		useRecord.setUserId(redpacketDetail.getUserId());
		useRecord.setUserMobile(redpacketDetail.getUserMobile());
		useRecord.setUserName(redpacketDetail.getUserName());
		useRecord.setCfplannerId(redpacketDetail.getCfplannerId());
		useRecord.setCfplannerMobile(redpacketDetail.getCfplannerMobile());
		useRecord.setCfplannerName(redpacketDetail.getCfplannerName());
		useRecord.setRechargeId(rechargeId);
		useRecord.setInvestId(investRecord.getInvestId());
		useRecord.setInvestMoney(investRecord.getInvestAmt());
		useRecord.setProductId(investRecord.getProductId());
		useRecord.setRemark("购买"+investRecord.getProductName()+"使用红包");
		Date time = new Date();
		useRecord.setUseDate(time);
		useRecord.setCreateDate(time);
		LOGGER.debug("insert redpacket use record recharge {}",useRecord);
		return insert(useRecord);
	}


	
    


}
