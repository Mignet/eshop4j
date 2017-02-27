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
import com.linkwee.web.dao.CimProductExtendsMapper;
import com.linkwee.web.model.CimProductExtends;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.service.CimProductExtendsService;


 /**
 * 
 * @描述：CimProductExtendsService 服务实现类
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 17:02:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductExtendsService")
public class CimProductExtendsServiceImpl extends GenericServiceImpl<CimProductExtends, Long> implements CimProductExtendsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductExtendsServiceImpl.class);
	
	@Resource
	private CimProductExtendsMapper cimProductExtendsMapper;
	
	@Override
    public GenericDao<CimProductExtends, Long> getDao() {
        return cimProductExtendsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductExtends -- 排序和模糊查询 ");
		Page<CimProductExtends> page = new Page<CimProductExtends>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductExtends> list = this.cimProductExtendsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CimProductExtends selectByProductId(String productId) {
		// TODO Auto-generated method stub
		CimProductExtends cimProductExtends = new CimProductExtends();
		cimProductExtends.setProductId(productId);
		cimProductExtends = cimProductExtendsMapper.selectOneByCondition(cimProductExtends);
		return cimProductExtends;
	}

	@Override
	public ShareContent selectShareContentByProductId(String productId) {
		// TODO Auto-generated method stub
		ShareContent shareContent = cimProductExtendsMapper.selectShareContentByProductId(productId);
		return shareContent;
	}

}
