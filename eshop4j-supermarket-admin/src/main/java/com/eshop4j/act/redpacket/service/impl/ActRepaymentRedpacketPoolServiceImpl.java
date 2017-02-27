package com.eshop4j.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.act.redpacket.model.ActRedpacketSendRecord;
import com.eshop4j.act.redpacket.model.ActRepaymentRedpacketPool;
import com.eshop4j.act.redpacket.service.ActRedpacketSendRecordService;
import com.eshop4j.act.redpacket.service.ActRedpacketService;
import com.eshop4j.act.redpacket.service.ActRepaymentRedpacketPoolService;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActRepaymentRedpacketPoolMapper;
import com.eshop4j.web.request.act.RedPacketInfoRequest;
import com.eshop4j.web.request.act.RedPacketTemplateInfoRequest;


 /**
 * 
 * @描述：ActRepaymentRedpacketPoolService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 22:14:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRepaymentRedpacketPoolService")
public class ActRepaymentRedpacketPoolServiceImpl extends GenericServiceImpl<ActRepaymentRedpacketPool, Long> implements ActRepaymentRedpacketPoolService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRepaymentRedpacketPoolServiceImpl.class);
	
	@Resource
	private ActRepaymentRedpacketPoolMapper actRepaymentRedpacketPoolMapper;
	@Resource
	private ActRedpacketService redpacketService;
	@Resource
	private ActRedpacketSendRecordService sendRecordService ;
	
	@Override
    public GenericDao<ActRepaymentRedpacketPool, Long> getDao() {
        return actRepaymentRedpacketPoolMapper;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void savePlatFormRedpacket(String redPacketTemplateId,String platformId, Integer model,RedPacketTemplateInfoRequest redPacketInfo) throws Exception {
		ActRepaymentRedpacketPool queryRepaymentRedpacketPool = new ActRepaymentRedpacketPool();
		queryRepaymentRedpacketPool.setRedpacketTemplateId(redPacketTemplateId);
		queryRepaymentRedpacketPool.setModel(model);
		//queryRepaymentRedpacketPool.setStatus(1);
		queryRepaymentRedpacketPool.setPlatformId(platformId);
		queryRepaymentRedpacketPool = selectOne(queryRepaymentRedpacketPool);
		if(queryRepaymentRedpacketPool!=null){
			ActRepaymentRedpacketPool update = new ActRepaymentRedpacketPool();
			update.setId(queryRepaymentRedpacketPool.getId());
			update.setStatus(0);
			update.setUpdateTime(new Date());
			update.setOperator(redPacketInfo.getOperator());
			update(update);
			return;
			
		}
		
		Date date =new Date();
		RedPacketInfoRequest redPacketInfoRequest = new RedPacketInfoRequest();
		redPacketInfoRequest.setType(1);
		redPacketInfoRequest.setName(redPacketInfo.getName());
		redPacketInfoRequest.setMoney(redPacketInfo.getMoney());
		redPacketInfoRequest.setRemark(redPacketInfo.getRemark());
		redPacketInfoRequest.setLimitPlatform(1);
		redPacketInfoRequest.setPlatformId(platformId);
		redPacketInfoRequest.setLimitMoney(redPacketInfo.getLimitMoney());
		redPacketInfoRequest.setInvestMoney(redPacketInfo.getInvestMoney());
		redPacketInfoRequest.setInvestLlimit(redPacketInfo.getInvestLlimit());
		redPacketInfoRequest.setLimitProduct(redPacketInfo.getLimitProduct());
		redPacketInfoRequest.setRelationalOperator(redPacketInfo.getRelationalOperator());
		redPacketInfoRequest.setDeadline(redPacketInfo.getDeadline());
	
		redPacketInfoRequest.setOperator(redPacketInfo.getOperator());
		redpacketService.insertRedpacket(redPacketInfoRequest);
		String redpacketId = redPacketInfoRequest.getRedpacketId();
		String sendId = StringUtils.getUUID();
		ActRedpacketSendRecord sendRecord = new ActRedpacketSendRecord();
		sendRecord.setRedpacketId(redpacketId);
		sendRecord.setSendId(sendId);
		sendRecord.setExpiresDay(redPacketInfo.getDay());
		sendRecord.setSendCount(0);
		sendRecord.setSendMoney(BigDecimal.ZERO);
		sendRecord.setSendNumber(0);
		sendRecord.setSendTime(date);
		sendRecord.setExpiresStatus(0);
		sendRecord.setOperator(redPacketInfo.getOperator());
		sendRecordService.insert(sendRecord);
		
		ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		repaymentRedpacketPool.setBizId( StringUtils.getUUID());
		repaymentRedpacketPool.setRedpacketTemplateId(redPacketTemplateId);
		repaymentRedpacketPool.setRedpacketId(redpacketId);
		repaymentRedpacketPool.setRedpacketSendId(sendId);
		repaymentRedpacketPool.setModel(model);
		repaymentRedpacketPool.setRepaymentAmt(redPacketInfo.getRepaymentAmt().longValue());
		repaymentRedpacketPool.setMaxRepaymentAmt(redPacketInfo.getMaxRepaymentAmt().longValue());
		repaymentRedpacketPool.setProductType(redPacketInfo.getProductType());
		repaymentRedpacketPool.setPlatformId(platformId);
		repaymentRedpacketPool.setOperator(redPacketInfo.getOperator());
		insert(repaymentRedpacketPool);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updatePlatFormRedpacketRule(String redPacketTemplateId,
			String platformId, Integer model,
			RedPacketTemplateInfoRequest redPacketInfo) throws Exception {
		ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		repaymentRedpacketPool.setRedpacketTemplateId(redPacketTemplateId);
		repaymentRedpacketPool.setModel(model);
		repaymentRedpacketPool.setPlatformId(platformId);
		repaymentRedpacketPool = selectOne(repaymentRedpacketPool);
		if(repaymentRedpacketPool==null)return;
		ActRepaymentRedpacketPool update = new ActRepaymentRedpacketPool();
		update.setId(repaymentRedpacketPool.getId());
		update.setRepaymentAmt(redPacketInfo.getRepaymentAmt().longValue());
		update.setMaxRepaymentAmt(redPacketInfo.getMaxRepaymentAmt().longValue());
		update.setProductType(redPacketInfo.getProductType());
		update.setUpdateTime(new Date());
		update.setOperator(redPacketInfo.getOperator());
		update(update);
		
		
		
		RedPacketInfoRequest redPacketInfoRequest = new RedPacketInfoRequest();
		redPacketInfoRequest.setRedpacketId(redPacketInfo.getRedpacketId());
		redPacketInfoRequest.setType(1);
		redPacketInfoRequest.setName(redPacketInfo.getName());
		redPacketInfoRequest.setMoney(redPacketInfo.getMoney());
		redPacketInfoRequest.setRemark(redPacketInfo.getRemark());
		redPacketInfoRequest.setLimitPlatform(1);
		redPacketInfoRequest.setPlatformId(platformId);
		redPacketInfoRequest.setLimitMoney(redPacketInfo.getLimitMoney());
		redPacketInfoRequest.setInvestMoney(redPacketInfo.getInvestMoney());
		redPacketInfoRequest.setInvestLlimit(redPacketInfo.getInvestLlimit());
		redPacketInfoRequest.setLimitProduct(redPacketInfo.getLimitProduct());
		redPacketInfoRequest.setRelationalOperator(redPacketInfo.getRelationalOperator());
		redPacketInfoRequest.setDeadline(redPacketInfo.getDeadline());
		redPacketInfoRequest.setOperator(redPacketInfo.getOperator());
		redpacketService.updateRedpacket(redPacketInfoRequest);
	}

	@Override
	public String getMainPlatFormByModel(Integer model) {
		return actRepaymentRedpacketPoolMapper.getMainPlatFormByModel(model);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ResponseResult setMainPlatFormByModel(String platform, Integer model,String operator) {
		ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		if(!StringUtils.isBlank(platform)){
			repaymentRedpacketPool = new ActRepaymentRedpacketPool();
			repaymentRedpacketPool.setModel(model);
			repaymentRedpacketPool.setPlatformId(platform);
			if(CollectionUtils.isEmpty(selectListByCondition(repaymentRedpacketPool)))
				return new ResponseResult(false, "请先为平台添加红包后,再设置为主推平台!");
		}
		
		Date date =new Date();
		repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		repaymentRedpacketPool.setMainPlatform(0);
		repaymentRedpacketPool.setUpdateTime(date);
		repaymentRedpacketPool.setOperator(operator);
		repaymentRedpacketPool.setModel(model);
		actRepaymentRedpacketPoolMapper.setMainPlatForm(repaymentRedpacketPool);
		
		if(!StringUtils.isBlank(platform)){
			
			repaymentRedpacketPool = new ActRepaymentRedpacketPool();
			repaymentRedpacketPool.setModel(model);
			repaymentRedpacketPool.setPlatformId(platform);
			repaymentRedpacketPool.setMainPlatform(1);
			repaymentRedpacketPool.setUpdateTime(date);
			repaymentRedpacketPool.setOperator(operator);
			actRepaymentRedpacketPoolMapper.setMainPlatForm(repaymentRedpacketPool);
		}
		return new ResponseResult(true, "设置成功");
		
	}

	@Override
	public List<ActRepaymentRedpacketPool> getRepaymentRedpackets(ActRepaymentRedpacketPool repaymentRedpacketPool) {
		
		return actRepaymentRedpacketPoolMapper.getRepaymentRedpackets(repaymentRedpacketPool);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateStatus(String platform, Integer model, Integer status,Integer grayStatus,String operator) {
		ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		//repaymentRedpacketPool.setModel(model);
		repaymentRedpacketPool.setPlatformId(platform);
		//不合作状态 设置未删除状态
		if(status==null || status==0){
			repaymentRedpacketPool.setStatus(Integer.valueOf(1));
		}else if(status==1){
			//合作bing
			if(grayStatus==0)repaymentRedpacketPool.setStatus(Integer.valueOf(0));
			else if(grayStatus==1)repaymentRedpacketPool.setStatus(Integer.valueOf(1));
		}
		/*if(ObjectUtils.equals(status, 0))	repaymentRedpacketPool.setStatus(1);
		if(ObjectUtils.equals(status, 1))	repaymentRedpacketPool.setStatus(0);*/
		repaymentRedpacketPool.setOperator(operator);
		actRepaymentRedpacketPoolMapper.updateStatus(repaymentRedpacketPool);
	}

}
