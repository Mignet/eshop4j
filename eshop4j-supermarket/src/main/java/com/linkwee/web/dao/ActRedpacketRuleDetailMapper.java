package com.linkwee.web.dao;

import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketRuleDetailMapper extends GenericDao<ActRedpacketRuleDetail,Long>{
	
	/**
	 * 更新红包规则
	 * @param redpacketRuleDetail
	 * @return
	 */
	int updateRedpacketRuleDetail(ActRedpacketRuleDetail redpacketRuleDetail);
}
