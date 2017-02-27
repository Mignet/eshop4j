package com.linkwee.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.model.ActRedpacketRule;
import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.act.redpacket.service.ActRedpacketRuleDetailService;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActRedpacketRuleDetailMapper;
import com.linkwee.web.request.act.RedPacketInfoRequest;
import com.linkwee.web.service.CimProductService;
import com.linkwee.xoss.util.Operation;


 /**
 * 
 * @描述：ActRedpacketRuleDetailService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketRuleDetailService")
public class ActRedpacketRuleDetailServiceImpl extends GenericServiceImpl<ActRedpacketRuleDetail, Long> implements ActRedpacketRuleDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketRuleDetailServiceImpl.class);
	
	@Autowired
	private ActRedpacketRuleDetailMapper redpacketRuleDetailMapper;
	
	@Autowired
	private CimProductService productService;
	
	@Override
    public GenericDao<ActRedpacketRuleDetail, Long> getDao() {
        return redpacketRuleDetailMapper;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean insertRedpacketRuleDetail(ActRedpacketRule redpacketRule,RedPacketInfoRequest req, Date date) throws Exception{
		ActRedpacketRuleDetail	redpacketRuleDetail = createRedpacketRuleDetail(redpacketRule, req, date);
		redpacketRuleDetail.setCreateTime(date);
		return insert(redpacketRuleDetail)>0;
	}
	
	
	@Override
	public boolean updateRedpacketRuleDetail(ActRedpacketRule redpacketRule,RedPacketInfoRequest req, Date date) throws Exception {
		ActRedpacketRuleDetail	redpacketRuleDetail = createRedpacketRuleDetail(redpacketRule, req, date);
		return redpacketRuleDetailMapper.updateRedpacketRuleDetail(redpacketRuleDetail)>0;
	}
	
	private ActRedpacketRuleDetail createRedpacketRuleDetail(ActRedpacketRule redpacketRule,RedPacketInfoRequest req, Date date){
		ActRedpacketRuleDetail redpacketRuleDetail = new ActRedpacketRuleDetail();
		//基础内容
		redpacketRuleDetail.setRuleId(redpacketRule.getRuleId());
		redpacketRuleDetail.setUpdateTime(date);
		redpacketRuleDetail.setOperator(req.getOperator());
		//平台限制
		redpacketRuleDetail.setPlatformLimit( ObjectUtils.equals(req.getLimitPlatform(), 1));
		if(redpacketRuleDetail.isPlatformLimit()){
			redpacketRuleDetail.setPlatformId(req.getPlatformId());
		}
		
		//金额限制
		redpacketRuleDetail.setAmountLimit(req.getLimitMoney());
		if(ObjectUtils.equals(redpacketRuleDetail.getAmountLimit(), 1)||ObjectUtils.equals(redpacketRuleDetail.getAmountLimit(), 2)){
			Validate.isTrue(Operation.GT.compare(req.getInvestMoney(), 0d));
			redpacketRuleDetail.setAmount(new BigDecimal(req.getInvestMoney()));
		}
		//投资限制
		redpacketRuleDetail.setInvestLlimit(req.getInvestLlimit());
		
		//产品限制
		Integer limitProductCode = req.getLimitProduct();
		if(limitProductCode==1 && req.getOperator()!=null){
			limitProductCode = Integer.parseInt(StringUtils.join(new Object[]{limitProductCode,req.getRelationalOperator()}));
		}
		
		ProductLimit productLimit = ProductLimit.getProductLimit(limitProductCode);
		
		Validate.notNull(productLimit,"投资产品限制有误");
		
		redpacketRuleDetail.setProductLimit(productLimit.getUseCondition());
		
		//期限限制
		if(productLimit==ProductLimit.eqProductDeadlineLimit||productLimit==ProductLimit.geProductDeadlineLimit){
			Validate.notNull(req.getDeadline(),"投资产品期限不能为空");
			redpacketRuleDetail.setProductDeadline(req.getDeadline());
		}else if(productLimit==ProductLimit.productIdLimit){//特定产品限制
			Validate.isTrue(StringUtils.isNotBlank(req.getPids()),"产品编号不能为空");
			String[] productIds = StringUtils.split(req.getPids(), ',');
			Validate.notEmpty(productIds,"产品不能为空");
			ProductDetailResponse detailResponse = null;
			for (String productId : productIds) {
				detailResponse = productService.queryProductDetail(productId);
				Validate.notNull(detailResponse,"产品不存在");
				if(redpacketRuleDetail.isPlatformLimit()){
					Validate.isTrue(ObjectUtils.equals(detailResponse.getOrgNumber(),redpacketRuleDetail.getPlatformId()),"产品机构与限制机构不一致");
				}
			}
			redpacketRuleDetail.setProductId(req.getPids());
		}
		return redpacketRuleDetail;
	}

	
    


}
