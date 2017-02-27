package com.linkwee.web.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.linkwee.act.redpacket.model.ActRedpacketRule;
import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:47:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketRuleMapper extends GenericDao<ActRedpacketRule,Long>{
	
	/**
	 * 根据红包编号获取红包使用规则 
	 * @param redpacketIds
	 * @return
	 */
	List<ActRedpacketRuleDetail> getRedpacketUseRulesByRedPacketId(@Param("redpacketIds")Set<String> redpacketIds);
}
