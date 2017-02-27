package com.linkwee.tc.fee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.tc.fee.model.TCFeeTypeConfig;
import com.linkwee.tc.fee.service.TCFeeTypeConfigService;
import com.linkwee.web.dao.TCFeeTypeConfigMapper;


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
