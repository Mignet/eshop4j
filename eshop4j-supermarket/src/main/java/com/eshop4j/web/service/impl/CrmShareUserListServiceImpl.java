package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.CrmShareUserListMapper;
import com.eshop4j.web.model.crm.CrmShareUserList;
import com.eshop4j.web.service.CrmShareUserListService;


 /**
 * 
 * @描述：CrmShareUserListService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年01月03日 16:52:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmShareUserListService")
public class CrmShareUserListServiceImpl extends GenericServiceImpl<CrmShareUserList, Long> implements CrmShareUserListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmShareUserListServiceImpl.class);
	
	@Resource
	private CrmShareUserListMapper crmShareUserListMapper;
	
	@Override
    public GenericDao<CrmShareUserList, Long> getDao() {
        return crmShareUserListMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmShareUserList -- 排序和模糊查询 ");
		Page<CrmShareUserList> page = new Page<CrmShareUserList>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmShareUserList> list = this.crmShareUserListMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
