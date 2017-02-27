package com.eshop4j.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.SysNoticeMapper;
import com.eshop4j.web.model.mc.SysNotice;
import com.eshop4j.web.service.SysNoticeService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： 何源
 * 
 * @创建时间：2015年10月26日 20:05:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("noticeService")
public class SysNoticeServiceImpl extends GenericServiceImpl<SysNotice, Long>  implements SysNoticeService{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
	@Autowired
	private SysNoticeMapper sysNoticeMapper;
	
	
	@Override
    public GenericDao<SysNotice, Long> getDao() {
        return sysNoticeMapper;
    }
    @Override
	public DataTableReturn selectByDatatables(DataTable dt,Integer appType) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- Notice -- 排序和模糊查询 ");
		Page<SysNotice> page = new Page<SysNotice>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysNotice> list = sysNoticeMapper.selectBySearchInfo(dt,appType,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	
}
