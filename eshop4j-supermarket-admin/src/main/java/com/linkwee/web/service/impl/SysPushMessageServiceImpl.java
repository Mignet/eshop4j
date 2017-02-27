package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SysPushMessageMapper;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.service.SysPushMessageService;


 /**
 * 
 * @描述：SysPushMessageService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 16:17:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysPushMessageService")
public class SysPushMessageServiceImpl extends GenericServiceImpl<SysPushMessage, Long> implements SysPushMessageService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPushMessageServiceImpl.class);
	
	@Resource
	private SysPushMessageMapper sysPushMessageMapper;
	
	@Override
    public GenericDao<SysPushMessage, Long> getDao() {
        return sysPushMessageMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysPushMessage -- 排序和模糊查询 ");
		Page<SysPushMessage> page = new Page<SysPushMessage>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysPushMessage> list = this.sysPushMessageMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<SysPushMessage> querySysPushMessageList(
			Page<SysPushMessage> page, Map<String, Object> conditions) {
		PaginatorResponse<SysPushMessage> paginatorResponse = new PaginatorResponse<SysPushMessage>();
		List<SysPushMessage> queryCimOrginfoList = sysPushMessageMapper.querySysPushMessageList(page,conditions);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer renewBatch(List<SysPushMessage> list) {
		return sysPushMessageMapper.updateBatch(list);				
	}

	@Override
	public Integer saveBatch(List<SysPushMessage> sysPushMessage) {
		return sysPushMessageMapper.saveBatch(sysPushMessage);		
	}

	@Override
	public Integer deleteBatch(List<SysPushMessage> list) {
		return sysPushMessageMapper.deleteBatch(list);
	}

}
