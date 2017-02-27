package com.eshop4j.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.base.ReturnCode;
import com.eshop4j.core.base.SuccessCode;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;

import com.eshop4j.web.model.SmDynamicNews;
import com.eshop4j.web.request.DynamicNewsRequest;
import com.eshop4j.web.request.NewsRequest;
import com.eshop4j.web.response.DynamicNewsResponse;
import com.eshop4j.web.dao.SmDynamicNewsMapper;
import com.eshop4j.web.service.SmDynamicNewsService;
import com.eshop4j.web.service.NewsService.Error;
import com.eshop4j.web.service.impl.SmDynamicNewsServiceImpl;


 /**
 * 
 * @描述：SmDynamicNewsService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月18日 19:01:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smDynamicNewsService")
public class SmDynamicNewsServiceImpl extends GenericServiceImpl<SmDynamicNews, Long> implements SmDynamicNewsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmDynamicNewsServiceImpl.class);
	
	@Resource
	private SmDynamicNewsMapper smDynamicNewsMapper;
	
	@Override
    public GenericDao<SmDynamicNews, Long> getDao() {
        return smDynamicNewsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmDynamicNews -- 排序和模糊查询 ");
		Page<SmDynamicNews> page = new Page<SmDynamicNews>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmDynamicNews> list = this.smDynamicNewsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findNewsList(DynamicNewsRequest dynamicNewsRequest, DataTable dataTable) {
		// TODO Auto-generated method stub
		Page<DynamicNewsRequest> page = new Page<DynamicNewsRequest>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<DynamicNewsResponse> newsRequestList = smDynamicNewsMapper.findDynamicNewsList(dynamicNewsRequest,page);
		 for(DynamicNewsResponse dnr : newsRequestList){
			 if(dnr.getAppType().equals("1")){
				 dnr.setAppType("猎财大师");
			 }else if(dnr.getAppType().equals("2")){ 
				 dnr.setAppType("T呗");
			 }
		 }
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(newsRequestList);
		 return dataTableReturn;
	}

	@Override
	public ReturnCode updateDynamicNews(SmDynamicNews convertToDynamicNews) {
		// TODO Auto-generated method stub
		try {
			smDynamicNewsMapper.updateByPrimaryKeySelective(convertToDynamicNews);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("newsDao.update invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode SaveDynamicNews(SmDynamicNews convertToDynamicNews) {
		// TODO Auto-generated method stub
		try {
			smDynamicNewsMapper.insertSelective(convertToDynamicNews);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("newsDao.add invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}		
	}

}
