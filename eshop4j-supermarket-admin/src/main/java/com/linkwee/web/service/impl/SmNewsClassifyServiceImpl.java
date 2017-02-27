package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmNewsClassify;
import com.linkwee.web.dao.SmNewsClassifyMapper;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.SmNewsClassifyService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.impl.SmNewsClassifyServiceImpl;


 /**
 * 
 * @描述：SmNewsClassifyService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 13:45:44
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smNewsClassifyService")
public class SmNewsClassifyServiceImpl extends GenericServiceImpl<SmNewsClassify, Long> implements SmNewsClassifyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmNewsClassifyServiceImpl.class);
	
	@Resource
	private SmNewsClassifyMapper smNewsClassifyMapper;
	
	@Override
    public GenericDao<SmNewsClassify, Long> getDao() {
        return smNewsClassifyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmNewsClassify -- 排序和模糊查询 ");
		Page<SmNewsClassify> page = new Page<SmNewsClassify>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmNewsClassify> list = this.smNewsClassifyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findNewsClassifyList(NewsRequest newsRequest,DataTable dataTable) {
		Page<NewsRequest> page = new Page<NewsRequest>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		List<SmNewsClassify> newsRequestList = smNewsClassifyMapper.findNewsClassifyList(newsRequest,page);
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(newsRequestList);
		return dataTableReturn;
	}

	@Override
	public ReturnCode updateNewsClassify(SmNewsClassify classify) {
		try {
			smNewsClassifyMapper.updateByPrimaryKeySelective(classify);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("smNewsClassifyMapper.update invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode saveNewsClassify(SmNewsClassify classify) {
		try {
			smNewsClassifyMapper.insertSelective(classify);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("smNewsClassifyMapper.add invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode DeleteNewsClassify(int parseInt) {
		try {
			smNewsClassifyMapper.deleteByPrimaryKey(Long.parseLong(String.valueOf(parseInt)));
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("newsDao.deleteByPrimaryKey invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

}
