package com.eshop4j.web.service;


import java.util.List;

import com.eshop4j.core.base.ErrorCode;
import com.eshop4j.core.base.ReturnCode;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.model.news.SmAdvertisement;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月17日 15:18:02
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface AdvertisementService{
	public static enum Error implements ErrorCode{
		SESSION_EXPIRE(142001, "会话已过期，请重新登录"),
		DB_ERROR(142001, "更新失败，请联系管理员")
		;
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
	 * 查询banner广告内容
	 * @param appType 
	 * @return
	 */
	public List<SmAdvertisement> queryBanner(Integer appType);
	
	public SmAdvertisement findAdvDtl(String fid);
	public ReturnCode DeleteAdv(Integer fid);
	public ReturnCode SaveAdv(SmAdvertisement adv) ;
	public ReturnCode updateAdv(SmAdvertisement adv);
	public DataTableReturn findAdvList(SmAdvertisement pageRequest,DataTable dataTable) throws Exception;

	/**
	 * 分页查询
	 * @param req
	 * @return
	 */
//	public PaginatorSevResp<SmAdvertisement> queryAdvList(PaginatorSevReq req);
	/**
	 * 开屏广告查询
	 * @param adv
	 * @return
	 */
	public List<SmAdvertisement> findAdvertisementDtl(SmAdvertisement adv);
	
}
