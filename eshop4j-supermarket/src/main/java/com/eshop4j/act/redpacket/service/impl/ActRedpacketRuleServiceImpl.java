package com.eshop4j.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.act.redpacket.model.ActRedpacketDetail;
import com.eshop4j.act.redpacket.model.ActRedpacketRule;
import com.eshop4j.act.redpacket.model.ActRedpacketRuleDetail;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleDetailService;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleDetailService.ProductLimit;
import com.eshop4j.act.redpacket.service.ActRedpacketRuleService;
import com.eshop4j.core.exception.ServiceException;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.ActRedpacketRuleMapper;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.request.act.RedPacketInfoRequest;
import com.eshop4j.web.service.CimProductService;


 /**
 *
 * @描述：ActRedpacketRuleService 服务实现类
 *
 * @创建人： ch
 *
 * @创建时间：2016年08月19日 15:47:03
 *
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketRuleService")
public class ActRedpacketRuleServiceImpl extends GenericServiceImpl<ActRedpacketRule, Long> implements ActRedpacketRuleService{

  private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketRuleServiceImpl.class);

  @Autowired
  private ActRedpacketRuleMapper redpacketRuleMapper;

  @Autowired
  private ActRedpacketRuleDetailService redpacketRuleDetailService;

  @Autowired
  private CimProductService productService;

  @Override
    public GenericDao<ActRedpacketRule, Long> getDao() {
        return redpacketRuleMapper;
    }

  @Override
  public ActRedpacketDetail matcheRedPacket(InvestRecordWrapper investRecord,Map<String, ActRedpacketDetail> redpacketMpas) {
    //查询红包使用规则
    List<ActRedpacketRuleDetail> useRules = redpacketRuleMapper.getRedpacketUseRulesByRedPacketId(redpacketMpas.keySet());

    if(useRules==null||useRules.isEmpty())return null;
    Map<String, ActRedpacketRuleDetail> ruleMaps = Maps.newLinkedHashMap();
    //根据红包编号 映射红包使用规则 方便匹配红包
    for (ActRedpacketRuleDetail useRule : useRules) ruleMaps.put(useRule.getRedpacketId(), useRule);

    TreeSet<ActRedpacketDetail> matcheRedpackets = Sets.newTreeSet();
    //匹配红包
    for (Entry<String, ActRedpacketDetail> entry : redpacketMpas.entrySet()) {
      if(matcheRedPacket(investRecord, entry.getValue(),ruleMaps.get(entry.getKey()))){
        matcheRedpackets.add(entry.getValue());
      }
    }
    //选择最优红包
    return optimalMatcheRedPacket(matcheRedpackets);
  }

  /**
   * 匹配红包
   * @param investRecord
   * @param redpacketDetail
   * @return
   */
  private boolean matcheRedPacket(InvestRecordWrapper investRecord,ActRedpacketDetail redpacket,ActRedpacketRuleDetail useRule){
    boolean platformMatche = false;
    boolean investMatche = false;
    boolean userInvestMatche = false;
    boolean productMatche = false;

    //平台匹配
    if(useRule.isPlatformLimit())platformMatche = ObjectUtils.equals(investRecord.getProductOrgId(), useRule.getPlatformId());
    else platformMatche = true;
    LOGGER.debug("matcheRedPacket platformLimitResult = {}",platformMatche);

    //投资匹配
    if(ObjectUtils.equals(0, useRule.getInvestLlimit()))userInvestMatche=true;
    else if(ObjectUtils.equals(1, useRule.getInvestLlimit()))userInvestMatche=investRecord.isFirstInvest();
    else if(ObjectUtils.equals(2, useRule.getInvestLlimit()))userInvestMatche=investRecord.isPlatfromFirstInvest();

    LOGGER.debug("matcheRedPacket investLlimitResult = {},investLlimit = {}",new Object[]{userInvestMatche,investRecord.isPlatfromFirstInvest()});

    //投资金额匹配
    if(ObjectUtils.equals(0, useRule.getAmountLimit()))investMatche =true;
    else if(ObjectUtils.equals(1, useRule.getAmountLimit()))investMatche = investRecord.getInvestAmt().compareTo(useRule.getAmount())==1;
    else if(ObjectUtils.equals(2, useRule.getAmountLimit()))investMatche = investRecord.getInvestAmt().compareTo(useRule.getAmount())>=0;
    LOGGER.debug("matcheRedPacket investMatcheResult = {},amountLimit={},InvestAmt={},RuleAmount={}",new Object[]{investMatche,useRule.getAmountLimit(),investRecord.getInvestAmt(),useRule.getAmount()});


    if(ObjectUtils.equals(useRule.getProductLimit(), 1000))productMatche = true;

    //产品匹配
    else if(ObjectUtils.equals(useRule.getProductLimit(), 1001))productMatche = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(useRule.getProductId(), ",")).contains(investRecord.getProductId());
    else if(ObjectUtils.equals(useRule.getProductLimit(), 1002))productMatche = ObjectUtils.equals(useRule.getProductDeadline(), investRecord.getProductDays());
    else if(ObjectUtils.equals(useRule.getProductLimit(), 1003))productMatche = investRecord.getDeadLineMinValue().compareTo(useRule.getProductDeadline())>=0;

    LOGGER.debug("matcheRedPacket productLimitResult = {},productLimit={},deadLine={},useRuleDeadline={}",new Object[]{productMatche,useRule.getProductLimit(),investRecord.getDeadLineMinValue(),useRule.getProductDeadline()});


    LOGGER.debug("matcheRedPacket result = {}",platformMatche&userInvestMatche&investMatche&productMatche);
    return platformMatche&userInvestMatche&investMatche&productMatche;
  }

  /**
   * 获取最优红包
   * @param redpacketDetails
   * @return
   */
  private ActRedpacketDetail optimalMatcheRedPacket(TreeSet<ActRedpacketDetail> redpackets) {
    return redpackets.isEmpty()?null:redpackets.first();
  }

  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean insertRedpacketRule(RedPacketInfoRequest redPacketInfo,Date date)throws Exception {

    ActRedpacketRule redpacketRule = new ActRedpacketRule();
    redpacketRule.setRuleId(StringUtils.getUUID());
    redpacketRule.setRedpacketId(redPacketInfo.getRedpacketId());
    redpacketRule.setRuleType(2);
    redpacketRule.setCreateTime(date);
    redpacketRule.setOperator(redPacketInfo.getOperator());
    boolean result = insert(redpacketRule)>0;
    if(result){
      result = redpacketRuleDetailService.insertRedpacketRuleDetail(redpacketRule, redPacketInfo, date);
    }
    return result;
  }

  @Override
  public boolean updateRedpacketRule(RedPacketInfoRequest redPacketInfo,Date date) throws Exception{
    ActRedpacketRule redpacketRule = new ActRedpacketRule();
    redpacketRule.setRedpacketId(redPacketInfo.getRedpacketId());
    redpacketRule = selectOne(redpacketRule);
    if(redpacketRule==null)throw new ServiceException("红包规则不存在");
    return redpacketRuleDetailService.updateRedpacketRuleDetail(redpacketRule, redPacketInfo, date);
  }

  @Override
  public void getRedPacketRuleInfo(RedPacketInfoRequest redPacketInfo,String redpacketId) {
    List<ActRedpacketRuleDetail> useRules = redpacketRuleMapper.getRedpacketUseRulesByRedPacketId(Sets.newHashSet(redpacketId));
    Validate.notEmpty(useRules, "红包规则为空");
    ActRedpacketRuleDetail redpacketRule = useRules.get(0);
    //平台限制
    redPacketInfo.setLimitPlatform(redpacketRule.isPlatformLimit()? 1 : 0);
    redPacketInfo.setPlatformId(redpacketRule.getPlatformId());

    //投资限制
    redPacketInfo.setInvestLlimit(redpacketRule.getInvestLlimit());

    //金额限制
    redPacketInfo.setLimitMoney(redpacketRule.getAmountLimit());


    if(ObjectUtils.equals(redpacketRule.getAmountLimit(), 1)||ObjectUtils.equals(redpacketRule.getAmountLimit(), 2)){
      redPacketInfo.setInvestMoney(redpacketRule.getAmount()==null ?null:redpacketRule.getAmount().setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
    }

    //产品限制
    ProductLimit productLimit = ProductLimit.getUseConditionLimit(redpacketRule.getProductLimit());
    int limitCode = productLimit.getLimitCode();
    redPacketInfo.setLimitProduct(limitCode>=10?limitCode/10:limitCode);
    if(ProductLimit.eqProductDeadlineLimit==productLimit||ProductLimit.geProductDeadlineLimit==productLimit){
      redPacketInfo.setRelationalOperator(limitCode%10);
      redPacketInfo.setDeadline(redpacketRule.getProductDeadline());
    }
    redPacketInfo.setPids(redpacketRule.getProductId());
  }

}
