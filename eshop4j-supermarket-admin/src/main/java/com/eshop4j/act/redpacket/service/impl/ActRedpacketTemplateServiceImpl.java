package com.eshop4j.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.act.redpacket.model.ActRedpacketTemplate;
import com.eshop4j.act.redpacket.model.ActRepaymentRedpacketPool;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleDetailService.ProductLimit;
import com.eshop4j.act.redpacket.service.ActRedpacketTemplateService;
import com.eshop4j.act.redpacket.service.ActRepaymentRedpacketPoolService;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActRedpacketTemplateMapper;
import com.eshop4j.web.request.act.RedPacketTemplateInfoRequest;
import com.eshop4j.web.response.act.RedpacketTemplateListResponse;


 /**
 * 
 * @描述：ActRedpacketTemplateService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 20:13:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketTemplateService")
public class ActRedpacketTemplateServiceImpl extends GenericServiceImpl<ActRedpacketTemplate, Long> implements ActRedpacketTemplateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketTemplateServiceImpl.class);
	
	@Resource
	private ActRedpacketTemplateMapper actRedpacketTemplateMapper;
	
	@Resource
	private ActRepaymentRedpacketPoolService repaymentRedpacketPoolService;
	
	@Override
    public GenericDao<ActRedpacketTemplate, Long> getDao() {
        return actRedpacketTemplateMapper;
    }

	@Override
	public DataTableReturn getRedpacketTemplateList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<RedpacketTemplateListResponse> page = new Page<RedpacketTemplateListResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<RedpacketTemplateListResponse> list = actRedpacketTemplateMapper.getRedpacketTemplateList(page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insetRedpacketTemplate(RedPacketTemplateInfoRequest redPacketInfo) {
		Date date = new Date();
		ActRedpacketTemplate redpacketTemplate = createRedpacketTemplate(redPacketInfo, date);
		redpacketTemplate.setRedpacketTemplateId(StringUtils.getUUID());
		redpacketTemplate.setCreateTime(date);
		insert(redpacketTemplate);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateRedpacketTemplate(RedPacketTemplateInfoRequest infoRequest) throws Exception {
		ActRedpacketTemplate redpacketTemplate = new ActRedpacketTemplate();
		String redpacketTemplateId =infoRequest.getRedpacketId();
		redpacketTemplate.setRedpacketTemplateId(redpacketTemplateId);
		redpacketTemplate = selectOne(redpacketTemplate);
		if(null==redpacketTemplate)return;
		ActRedpacketTemplate update = createRedpacketTemplate(infoRequest, new Date());
		update.setId(redpacketTemplate.getId());
		update(update);
		
		ActRepaymentRedpacketPool repaymentRedpacketPool = new ActRepaymentRedpacketPool();
		repaymentRedpacketPool.setRedpacketTemplateId(redpacketTemplateId);
		//repaymentRedpacketPool.setStatus(0);
		List<ActRepaymentRedpacketPool> repaymentRedpacketPools =repaymentRedpacketPoolService.selectListByCondition(repaymentRedpacketPool);
		if(CollectionUtils.isEmpty(repaymentRedpacketPools))return;
		for (ActRepaymentRedpacketPool actRepaymentRedpacketPool : repaymentRedpacketPools) {
			infoRequest.setRedpacketId(actRepaymentRedpacketPool.getRedpacketId());
			repaymentRedpacketPoolService.updatePlatFormRedpacketRule(redpacketTemplateId, actRepaymentRedpacketPool.getPlatformId(), actRepaymentRedpacketPool.getModel(), infoRequest);
		}
	}
	
	private ActRedpacketTemplate createRedpacketTemplate(RedPacketTemplateInfoRequest redPacketInfo,Date date){
		
		ActRedpacketTemplate redpacketTemplate = new ActRedpacketTemplate();
		
		redpacketTemplate.setName(redPacketInfo.getName());
		redpacketTemplate.setExpiresDay(redPacketInfo.getDay());
		redpacketTemplate.setMoney(redPacketInfo.getMoney().longValue());
		redpacketTemplate.setRemark(redPacketInfo.getRemark());
		redpacketTemplate.setRepaymentAmt(redPacketInfo.getRepaymentAmt());
		redpacketTemplate.setMaxRepaymentAmt(redPacketInfo.getMaxRepaymentAmt());
		redpacketTemplate.setProductType(redPacketInfo.getProductType());
		redpacketTemplate.setType(redPacketInfo.getType());
		
		Integer limitProductCode = redPacketInfo.getLimitProduct();
		if(limitProductCode==1 && redPacketInfo.getOperator()!=null){
			limitProductCode = Integer.parseInt(org.apache.commons.lang.StringUtils.join(new Object[]{limitProductCode,redPacketInfo.getRelationalOperator()}));
		}
		ProductLimit productLimit = ProductLimit.getProductLimit(limitProductCode);		
		redpacketTemplate.setProductLimit(productLimit.getUseCondition());
		redpacketTemplate.setProductDeadline(redPacketInfo.getDeadline());
		redpacketTemplate.setAmountLimit(redPacketInfo.getLimitMoney());
		if(!ObjectUtils.equals(redPacketInfo.getLimitMoney(), 0))
			redpacketTemplate.setAmount(new BigDecimal(redPacketInfo.getInvestMoney()));
		redpacketTemplate.setInvestLimit(redPacketInfo.getInvestLlimit());
		
		redpacketTemplate.setUpdateTime(date);
		redpacketTemplate.setOperator(redPacketInfo.getOperator());
		return redpacketTemplate;
	}

	@Override
	public RedPacketTemplateInfoRequest getRedPacketTemplateInfo(String redPacketTemplateId) {
		if(StringUtils.isBlank(redPacketTemplateId))return null;
		ActRedpacketTemplate redpacketTemplate = new ActRedpacketTemplate();
		redpacketTemplate.setRedpacketTemplateId(redPacketTemplateId);
		redpacketTemplate = selectOne(redpacketTemplate);
		if(redpacketTemplate==null)return null;
		RedPacketTemplateInfoRequest templateInfoRequest = new RedPacketTemplateInfoRequest();
		templateInfoRequest.setRedpacketId(redPacketTemplateId);
		templateInfoRequest.setDay(redpacketTemplate.getExpiresDay());
		templateInfoRequest.setName(redpacketTemplate.getName());
		templateInfoRequest.setMoney(redpacketTemplate.getMoney().doubleValue());
		templateInfoRequest.setRemark(redpacketTemplate.getRemark());
		templateInfoRequest.setType(redpacketTemplate.getType());
		templateInfoRequest.setRepaymentAmt(redpacketTemplate.getRepaymentAmt());
		templateInfoRequest.setMaxRepaymentAmt(redpacketTemplate.getMaxRepaymentAmt());
		templateInfoRequest.setProductType(redpacketTemplate.getProductType());
		//产品限制
		ProductLimit productLimit = ProductLimit.getUseConditionLimit(redpacketTemplate.getProductLimit());
		int limitCode = productLimit.getLimitCode();
		templateInfoRequest.setLimitProduct(limitCode>=10?limitCode/10:limitCode);
		if(ProductLimit.eqProductDeadlineLimit==productLimit||ProductLimit.geProductDeadlineLimit==productLimit){
			templateInfoRequest.setRelationalOperator(limitCode%10);
			templateInfoRequest.setDeadline(redpacketTemplate.getProductDeadline());
		}
		templateInfoRequest.setLimitMoney(redpacketTemplate.getAmountLimit());
		if(!ObjectUtils.equals(templateInfoRequest.getLimitMoney(), 0))
			templateInfoRequest.setInvestMoney(redpacketTemplate.getAmount().doubleValue());
		templateInfoRequest.setInvestLlimit(redpacketTemplate.getInvestLimit());
		return templateInfoRequest;
	}

	
    


}
