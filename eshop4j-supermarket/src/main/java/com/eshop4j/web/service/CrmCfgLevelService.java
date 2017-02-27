package com.eshop4j.web.service;

import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.crm.CrmCfgLevel;
 /**
 * 
 * @描述： CrmCfgLevelService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月26日 17:13:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfgLevelService extends GenericService<CrmCfgLevel,Long>{
	
	/**
	 * 计算职级
	 * @param userId
	 */
	void rankCalculation(String userId);
}
