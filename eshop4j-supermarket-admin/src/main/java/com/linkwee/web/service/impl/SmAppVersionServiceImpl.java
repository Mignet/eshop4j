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

import com.linkwee.web.model.SmAppVersion;
import com.linkwee.web.dao.SmAppVersionMapper;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.service.SmAppVersionService;
import com.linkwee.web.service.impl.SmAppVersionServiceImpl;


 /**
 * 
 * @描述：SmAppVersionService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月26日 20:22:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smAppVersionService")
public class SmAppVersionServiceImpl extends GenericServiceImpl<SmAppVersion, Long> implements SmAppVersionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmAppVersionServiceImpl.class);
	
	@Resource
	private SmAppVersionMapper smAppVersionMapper;
	
	@Override
    public GenericDao<SmAppVersion, Long> getDao() {
        return smAppVersionMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmAppVersion -- 排序和模糊查询 ");
		Page<SmAppVersion> page = new Page<SmAppVersion>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmAppVersion> list = this.smAppVersionMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    /**
     * 根据平台类型与应用类别查询版本信息
     */
	@Override
	public SmAppVersion getAppVersion(PlatformEnum platForm, Integer appType) {
		// TODO Auto-generated method stub
		List<SmAppVersion> list = smAppVersionMapper.queryNewAppVersion(platForm.getValue(),appType);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询最新版本信息
	 */
	@Override
	public List<SmAppVersion> queryNewAppVersion(Integer appType) {
		// TODO Auto-generated method stub
		return smAppVersionMapper.queryNewAppVersion(null,appType);
	}
    
}
