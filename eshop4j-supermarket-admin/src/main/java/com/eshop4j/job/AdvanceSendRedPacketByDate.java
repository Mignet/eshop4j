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
public class AdvanceSendRedPacketByDate extends AbstractSimpleElasticJob{

	private static final Logger LOGGER = LoggerFactory.getLogger(AdvanceSendRedPacketByDate.class);
	@Autowired
	private CimProductInvestRecordService investRecordService;
	
	@Autowired
	private ActRepaymentRedpacketPoolService repaymentRedpacketPoolService;
	
	
	@Autowired
	private ActRedpacketService redpacketService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info("AdvanceSendRedPacketByDate start");
		List<InvestInfo> infos = investRecordService.getInvestInfoByDate(DateTime.now().plusDays(3).toString("yyyy-MM-dd"));
		if(CollectionUtils.isEmpty(infos)){
			LOGGER.info("not investInfos end");
			return;
		}
		String cpaMainPlatForm = repaymentRedpacketPoolService.getMainPlatFormByModel(1);
		String cpsMainPlatForm = repaymentRedpacketPoolService.getMainPlatFormByModel(2);
		Random r = new Random();
		for (InvestInfo investInfo : infos) {
			
			if( ObjectUtils.equals(investInfo.getModel(), 1)){
				
				//有主推平台，判断是否在该平台投资，如果有投资投资,相当于无主推平台，进入下一步，如果没有投资，则发红包
				if(StringUtils.isNotBlank(cpaMainPlatForm)){
					//是否投资
					boolean isInvest = investRecordService.queryUserPlatfromInvestCount(investInfo.getUid(), cpaMainPlatForm)>0;
					if(!isInvest){
						//无投资发红包
						if(sendRedPacekt(investInfo,getRepaymentRedpacketByPool(cpaMainPlatForm, 1,1,null, investInfo.getAmtLimit().compareTo(investInfo.getInvestAmt())>=0?investInfo.getInvestAmt():investInfo.getAmtLimit()), null))continue;						
					}
				}
				//没有主推平台 判断是否在所有cpa平台投资 没有则随机派发cpa红包，有则随机派发cps红包
				List<String> platforms = investRecordService.queryUserInvestCountByPlatFormModel(investInfo.getUid(), 1);
				if(!CollectionUtils.isEmpty(platforms)){
					//随机选择红包
					String platform = platforms.get(r.nextInt(platforms.size()));
					
					if(sendRedPacekt(investInfo,getRepaymentRedpacketByPool(platform, 1,0,null, investInfo.getAmtLimit().compareTo(investInfo.getInvestAmt())>=0?investInfo.getInvestAmt():investInfo.getAmtLimit()), null))continue;
					
					
				}
				if(sendRedPacekt(investInfo,getRepaymentRedpacketByPool(null, 2,null, null,investInfo.getInvestAmt()), r))continue;
				
			}else if(ObjectUtils.equals(investInfo.getModel(), 2)){
				//有主推平台发主推平台红包，没有则发当前平台红包，如果当前平台没配置红包则随机发cps红包
				if(StringUtils.isNotBlank(cpsMainPlatForm)){
					if(sendRedPacekt(investInfo, getRepaymentRedpacketByPool(cpsMainPlatForm, 2,1, 0,investInfo.getInvestAmt()), null))continue;					
				}
				
				if(sendRedPacekt(investInfo, getRepaymentRedpacketByPool(investInfo.getPlatfrom(), 2,0,0, investInfo.getInvestAmt()), null))continue;
				if(sendRedPacekt(investInfo,  getRepaymentRedpacketByPool(null, 2,0, 0,investInfo.getInvestAmt()), r))continue;			
			}
		}
		LOGGER.info("AdvanceSendRedPacketByDate end");
		
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
	private List<ActRepaymentRedpacketPool> getRepaymentRedpacketByPool(String platform,Integer model,Integer mainPlatform,Integer productType,BigDecimal investAmt){
		ActRepaymentRedpacketPool repaymentRedpacket = new ActRepaymentRedpacketPool();
		repaymentRedpacket.setPlatformId(platform);
		repaymentRedpacket.setModel(model);

		repaymentRedpacket.setMainPlatform(mainPlatform);

		repaymentRedpacket.setProductType(productType);

		repaymentRedpacket.setRepaymentAmt(investAmt.longValue());
		repaymentRedpacket.setStatus(0);
		return repaymentRedpacketPoolService.getRepaymentRedpackets(repaymentRedpacket);
	}
	 
	
	private boolean sendRedPacekt(InvestInfo investInfo,List<ActRepaymentRedpacketPool> repaymentRedpackets,Random r ){
		if(CollectionUtils.isEmpty(repaymentRedpackets))return false;
		ActRepaymentRedpacketPool repaymentRedpacket= repaymentRedpackets.get(r==null?0:r.nextInt(repaymentRedpackets.size()));
		CrmUserInfo userInfo = new CrmUserInfo();
		userInfo.setUserId(investInfo.getCid());
		userInfo.setMobile(investInfo.getCmobile());
		userInfo.setUserName(investInfo.getCname());
		try {
			redpacketService.lcsRedPacekt(userInfo, repaymentRedpacket.getRedpacketId(), repaymentRedpacket.getRedpacketSendId(),investInfo,1);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
