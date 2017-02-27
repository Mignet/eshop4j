package com.eshop4j.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.api.request.NewsPageListRequest;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.SmNewsMapper;
import com.eshop4j.web.model.SmNews;
import com.eshop4j.web.service.SmNewsService;


 /**
 * 
 * @描述：SmNewsService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smNewsService")
public class SmNewsServiceImpl extends GenericServiceImpl<SmNews, Long> implements SmNewsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmNewsServiceImpl.class);
	
	@Resource
	private SmNewsMapper smNewsMapper;
	
	@Override
    public GenericDao<SmNews, Long> getDao() {
        return smNewsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmNews -- 排序和模糊查询 ");
		Page<SmNews> page = new Page<SmNews>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmNews> list = this.smNewsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    /**
     * 根据id查询资讯记录
     */
	@Override
	public SmNews findNewsDtl(String fid) {
		long id = Long.parseLong(fid);
		return smNewsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询资讯分页
	 */
	@Override
	public PaginatorResponse<SmNews> queryNewsPageList(NewsPageListRequest newsPageListRequest, Page<SmNews> page) {
		PaginatorResponse<SmNews> paginatorResponse = new PaginatorResponse<SmNews>();
		List<SmNews> newsList = smNewsMapper.queryNewsPageList(newsPageListRequest, page);
		for(SmNews news : newsList){
			news.setContent(null);
		}
		paginatorResponse.setDatas(newsList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public SmNews queryNewest(Integer appType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appType", appType);
		return smNewsMapper.queryNewest(map);
	}

}
