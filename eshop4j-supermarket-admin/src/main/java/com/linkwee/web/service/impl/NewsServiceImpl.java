package com.linkwee.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.NewsDao;
import com.linkwee.web.model.news.News;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.NewsService;


/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月10日 14:22:39
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private NewsDao newsDao;


/*	@Override
	public PaginatorSevResp<News> queryNewsList(PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		PageList<News> datas = newsDao.query(req);
		return  PaginatorUtil.toPaginatorSevResp(datas);
	}*/

	 /**
	  * 新版本资讯列表
	  *
	  * @param newsRequest
	  * @param dataTable
	  * @return
	  * @throws Exception
	  */
	 @Override
	 public DataTableReturn findNewsList(NewsRequest newsRequest, DataTable dataTable) throws Exception {
		 Page<NewsRequest> page = new Page<NewsRequest>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<NewsRequest> newsRequestList = newsDao.findNewsList(newsRequest,page);
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(newsRequestList);
		 return dataTableReturn;
	 }

	 @Override
	public News findNewsDtl(String fid) {
		return newsDao.getByPrimaryKey(fid);
	}

	@Override
	public ReturnCode DeleteNews(Integer fid) {
		try {
			newsDao.deleteByPrimaryKey(fid);
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("newsDao.deleteByPrimaryKey invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}

	@Override
	public ReturnCode SaveNews(News adv) {
		 try {
			 newsDao.add(adv);
			return new SuccessCode();
		} catch (Exception e) {
			logger.error("newsDao.add invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateNews(News adv) {
		 try {
			 newsDao.update(adv);
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("newsDao.update invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}
	


}
