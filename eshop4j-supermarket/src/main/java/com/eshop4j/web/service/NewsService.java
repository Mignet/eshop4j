package com.eshop4j.web.service;

import com.eshop4j.core.base.ErrorCode;
import com.eshop4j.core.base.PaginatorSevReq;
import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.base.ReturnCode;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.model.news.News;
import com.eshop4j.web.request.NewsRequest;

/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月10日 14:22:39
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface NewsService{
	public static enum Error implements ErrorCode{
		SESSION_EXPIRE(142001, "会话已过期，请重新登录"),DB_ERROR(142001, "更新失败，请联系管理员");
		Error(int code,String message){
			this.code = code;
			this.message = message;
		}
		private int code = 0;
		private String message = "";
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
	}
	
	/**
	 * 新版本资讯列表
	 * @param newsRequest
	 * @param dataTable
	 * @return
	 * @throws Exception
     */
	 public DataTableReturn findNewsList(NewsRequest newsRequest, DataTable dataTable) throws Exception;

	public News findNewsDtl(String fid);
	public ReturnCode DeleteNews(Integer fid);
	public ReturnCode SaveNews(News adv) ;
	public ReturnCode updateNews(News adv);
	
	
}
