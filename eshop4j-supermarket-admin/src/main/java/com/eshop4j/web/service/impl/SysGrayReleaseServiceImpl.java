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
import com.eshop4j.web.dao.SysGrayReleaseMapper;
import com.eshop4j.web.model.SysGrayRelease;
import com.eshop4j.web.service.SysGrayReleaseService;


 /**
 * 
 * @描述：SysGrayReleaseService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysGrayReleaseService")
public class SysGrayReleaseServiceImpl extends GenericServiceImpl<SysGrayRelease, Long> implements SysGrayReleaseService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysGrayReleaseServiceImpl.class);
	
	@Resource
	private SysGrayReleaseMapper sysGrayReleaseMapper;
	
	@Override
    public GenericDao<SysGrayRelease, Long> getDao() {
        return sysGrayReleaseMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysGrayRelease -- 排序和模糊查询 ");
		Page<SysGrayRelease> page = new Page<SysGrayRelease>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysGrayRelease> list = this.sysGrayReleaseMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    @Override
	public SysGrayRelease selectByMobile(String mobile) {
		return sysGrayReleaseMapper.selectByMobile(mobile);
	}

}
