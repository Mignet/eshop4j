package com.eshop4j.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;

import com.eshop4j.web.model.CrmOrgAcctRel;
import com.eshop4j.web.dao.CrmOrgAcctRelMapper;
import com.eshop4j.web.service.CrmOrgAcctRelService;
import com.eshop4j.web.service.impl.CrmOrgAcctRelServiceImpl;


 /**
 * 
 * @描述：CrmOrgAcctRelService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年10月12日 14:52:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmOrgAcctRelService")
public class CrmOrgAcctRelServiceImpl extends GenericServiceImpl<CrmOrgAcctRel, Long> implements CrmOrgAcctRelService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmOrgAcctRelServiceImpl.class);
	
	@Resource
	private CrmOrgAcctRelMapper crmOrgAcctRelMapper;
	
	@Override
    public GenericDao<CrmOrgAcctRel, Long> getDao() {
        return crmOrgAcctRelMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmOrgAcctRel -- 排序和模糊查询 ");
		Page<CrmOrgAcctRel> page = new Page<CrmOrgAcctRel>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmOrgAcctRel> list = this.crmOrgAcctRelMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
