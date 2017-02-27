package com.linkwee.web.dao;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.news.News;
import com.linkwee.web.request.NewsRequest;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* 描述： Dao接口
*
* @创建人： chenchy
*
* @创建时间：2016年05月10日 14:22:39
*
* Copyright (c) 深圳市小牛科技有限公司-版权所有
*/
public interface NewsDao extends BasePageDao<News>{

   public PageList<News> query(PageRequest req);

   /**
    * 新版本资讯列表
    * @param newsRequest
    * @param bounds
    * @return
    * @throws Exception
     */
   public List<NewsRequest> findNewsList(@Param("query") NewsRequest newsRequest , RowBounds bounds) throws Exception;

}
