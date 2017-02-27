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
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.dao.CimOrgPictureMapper;
import com.linkwee.web.service.CimOrgPictureService;
import com.linkwee.web.service.impl.CimOrgPictureServiceImpl;


 /**
 * 
 * @描述：CimOrgPictureService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月27日 14:51:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgPictureService")
public class CimOrgPictureServiceImpl extends GenericServiceImpl<CimOrgPicture, Long> implements CimOrgPictureService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgPictureServiceImpl.class);
	
	@Resource
	private CimOrgPictureMapper cimOrgPictureMapper;
	
	@Override
    public GenericDao<CimOrgPicture, Long> getDao() {
        return cimOrgPictureMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgPicture -- 排序和模糊查询 ");
		Page<CimOrgPicture> page = new Page<CimOrgPicture>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgPicture> list = this.cimOrgPictureMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgPicture> queryOrgPictureList(String orgNumber,int pictureType,int source) {
		return cimOrgPictureMapper.queryOrgPictureList(orgNumber,pictureType,source);
	}

}
