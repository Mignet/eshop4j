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

import com.linkwee.web.model.mc.SmWeixinmsgTemplate;
import com.linkwee.web.dao.SmWeixinmsgTemplateMapper;
import com.linkwee.web.service.SmWeixinmsgTemplateService;
import com.linkwee.web.service.impl.SmWeixinmsgTemplateServiceImpl;


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
