package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgMemberinfoMapper;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.service.CimOrgMemberinfoService;


 /**
 * 
 * @描述：CimOrgMemberinfoService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 18:52:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgMemberinfoService")
public class CimOrgMemberinfoServiceImpl extends GenericServiceImpl<CimOrgMemberInfo, Long> implements CimOrgMemberinfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgMemberinfoServiceImpl.class);
	
	@Resource
	private CimOrgMemberinfoMapper cimOrgMemberinfoMapper;
	
	@Override
    public GenericDao<CimOrgMemberInfo, Long> getDao() {
        return cimOrgMemberinfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgMemberinfo -- 排序和模糊查询 ");
		Page<CimOrgMemberInfo> page = new Page<CimOrgMemberInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgMemberInfo> list = this.cimOrgMemberinfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertBatch(List<CimOrgMemberInfo> teams) {
		cimOrgMemberinfoMapper.insertBatch(teams);
		
	}

	@Override
	public void updateBatchTeam(List<CimOrgMemberInfo> teams) {
		cimOrgMemberinfoMapper.updateBatchTeam(teams);
	}

}
