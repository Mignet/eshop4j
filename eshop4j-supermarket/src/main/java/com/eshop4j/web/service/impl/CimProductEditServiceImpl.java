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
import com.eshop4j.web.dao.CimProductEditMapper;
import com.eshop4j.web.model.CimProductEdit;
import com.eshop4j.web.service.CimProductEditService;


 /**
 * 
 * @描述：CimProductEditService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月25日 17:32:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductEditService")
public class CimProductEditServiceImpl extends GenericServiceImpl<CimProductEdit, Long> implements CimProductEditService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductEditServiceImpl.class);
	
	@Resource
	private CimProductEditMapper cimProductEditMapper;
	
	@Override
    public GenericDao<CimProductEdit, Long> getDao() {
        return cimProductEditMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductEdit -- 排序和模糊查询 ");
		Page<CimProductEdit> page = new Page<CimProductEdit>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductEdit> list = this.cimProductEditMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
