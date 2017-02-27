package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;

import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.dao.ActActivityPrizeCaseMapper;
import com.linkwee.web.service.ActActivityPrizeCaseService;
import com.linkwee.web.service.impl.ActActivityPrizeCaseServiceImpl;


 /**
 * 
 * @描述：ActActivityPrizeCaseService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:50:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actActivityPrizeCaseService")
public class ActActivityPrizeCaseServiceImpl extends GenericServiceImpl<ActActivityPrizeCase, Long> implements ActActivityPrizeCaseService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityPrizeCaseServiceImpl.class);
	
	@Resource
	private ActActivityPrizeCaseMapper actActivityPrizeCaseMapper;
	
	@Override
    public GenericDao<ActActivityPrizeCase, Long> getDao() {
        return actActivityPrizeCaseMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActActivityPrizeCase -- 排序和模糊查询 ");
		Page<ActActivityPrizeCase> page = new Page<ActActivityPrizeCase>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActActivityPrizeCase> list = this.actActivityPrizeCaseMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
