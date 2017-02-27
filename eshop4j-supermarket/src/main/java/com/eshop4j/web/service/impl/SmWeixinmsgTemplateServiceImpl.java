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
import com.eshop4j.web.dao.SmWeixinmsgTemplateMapper;
import com.eshop4j.web.model.weixin.SmWeixinmsgTemplate;
import com.eshop4j.web.service.SmWeixinmsgTemplateService;


 /**
 * 
 * @描述：SmWeixinmsgTemplateService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月22日 19:11:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smWeixinmsgTemplateService")
public class SmWeixinmsgTemplateServiceImpl extends GenericServiceImpl<SmWeixinmsgTemplate, Long> implements SmWeixinmsgTemplateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmWeixinmsgTemplateServiceImpl.class);
	
	@Resource
	private SmWeixinmsgTemplateMapper smWeixinmsgTemplateMapper;
	
	@Override
    public GenericDao<SmWeixinmsgTemplate, Long> getDao() {
        return smWeixinmsgTemplateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmWeixinmsgTemplate -- 排序和模糊查询 ");
		Page<SmWeixinmsgTemplate> page = new Page<SmWeixinmsgTemplate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmWeixinmsgTemplate> list = this.smWeixinmsgTemplateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
