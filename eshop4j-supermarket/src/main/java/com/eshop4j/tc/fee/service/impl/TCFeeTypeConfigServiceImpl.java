package com.eshop4j.tc.fee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.tc.fee.model.TCFeeTypeConfig;
import com.eshop4j.tc.fee.service.TCFeeTypeConfigService;
import com.eshop4j.web.dao.TCFeeTypeConfigMapper;


 /**
 * 
 * @描述：CimFeeTypeConfigService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月29日 10:49:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("tCFeeTypeConfigService")
public class TCFeeTypeConfigServiceImpl extends GenericServiceImpl<TCFeeTypeConfig, Long> implements TCFeeTypeConfigService{
	
	@Resource
	private TCFeeTypeConfigMapper feeTypeConfigMapper;
	
	@Override
    public GenericDao<TCFeeTypeConfig, Long> getDao() {
        return feeTypeConfigMapper;
    }
    


}
