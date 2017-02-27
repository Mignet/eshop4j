package com.eshop4j.job;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.eshop4j.act.redpacket.model.ActRepaymentRedpacketPool;
import com.eshop4j.act.redpacket.service.ActRedpacketService;
import com.eshop4j.act.redpacket.service.ActRepaymentRedpacketPoolService;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.vo.InvestInfo;
import com.eshop4j.web.service.CimProductInvestRecordService;

@Component
public class AdvanceSendRedPacketByNow extends AbstractSimpleElasticJob{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvanceSendRedPacketByNow.class);
	
	@Autowired
	private CimProductInvestRecordService investRecordService;
	
	@Autowired
	private ActRepaymentRedpacketPoolService repaymentRedpacketPoolService;
	
	
	@Autowired
	private ActRedpacketService redpacketService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		
		LOGGER.info("AdvanceSendRedPacketByNow start");
		
		List<InvestInfo> infos = investRecordService.getInvestInfoByNow(DateTime.now().toString("yyyy-MM-dd HH:mm"));
		if(CollectionUtils.isEmpty(infos)){
			LOGGER.info("not investInfos end");
			return;
		}
		String cpsMainPlatForm = repaymentRedpacketPoolService.getMainPlatFormByModel(2);
		Random r = new Random();
		for (InvestInfo investInfo : infos) {
			//有主推平台发主推平台红包，没有则发当前平台红包，如果当前平台没用配置红包则随机发cps红包
			if(StringUtils.isNotBlank(cpsMainPlatForm)){
				if(sendRedPacekt(investInfo, getRepaymentRedpacketByPool(cpsMainPlatForm, 2,1, 1,investInfo.getInvestAmt()), null))continue;
			}
			if(sendRedPacekt(investInfo,  getRepaymentRedpacketByPool(investInfo.getPlatfrom(), 2,0,1, investInfo.getInvestAmt()), null))continue;
			if(sendRedPacekt(investInfo,getRepaymentRedpacketByPool(null, 2,0, 1,investInfo.getInvestAmt()), r))continue;
			
		}
		LOGGER.info("AdvanceSendRedPacketByNow end");
		
	}
	
	/**
	 * 获取回款红包
	 * @param platform 平台
	 * @param model 收费模式 1=cpa|2=cps
	 * @param mainPlatform 是否主推 0=不是主推平台|1=主推平台
	 * @param productType 产品类型 0=固定|1=浮动
	 * @param investAmt 回款金额
	 * @return
	 */
	private  List<ActRepaymentRedpacketPool> getRepaymentRedpacketByPool(String platform,Integer model,Integer mainPlatform,Integer productType,BigDecimal investAmt){
		ActRepaymentRedpacketPool repaymentRedpacket = new ActRepaymentRedpacketPool();
		repaymentRedpacket.setPlatformId(platform);
		repaymentRedpacket.setModel(model);
		repaymentRedpacket.setStatus(0);
		repaymentRedpacket.setProductType(productType);
		repaymentRedpacket.setMainPlatform(mainPlatform);
		repaymentRedpacket.setRepaymentAmt(investAmt.longValue());
		return repaymentRedpacketPoolService.getRepaymentRedpackets(repaymentRedpacket);
	}
	
	private boolean sendRedPacekt(InvestInfo investInfo,List<ActRepaymentRedpacketPool> repaymentRedpackets,Random r ){
		if(CollectionUtils.isEmpty(repaymentRedpackets))return false;
		ActRepaymentRedpacketPool repaymentRedpacket= repaymentRedpackets.get(r==null?0:r.nextInt(repaymentRedpackets.size()));
		CrmUserInfo userInfo = new CrmUserInfo();
		userInfo.setUserId(investInfo.getUid());
		userInfo.setMobile(investInfo.getUmobile());
		userInfo.setUserName(investInfo.getUname());
		try {
			redpacketService.customerRedPacekt(userInfo, repaymentRedpacket.getRedpacketId(), repaymentRedpacket.getRedpacketSendId(),investInfo,2);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
}
