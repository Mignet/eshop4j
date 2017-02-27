package com.eshop4j.web.service;

import java.util.Map;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.jpressPlatform.JpPlatform;
 /**
 * 
 * @描述： JpPlatformService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 11:31:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface JpPlatformService extends GenericService<JpPlatform,Long>{

	/**
	 * 网贷平台列表 分页
	 * @author chenchy 
	 * @date 2016年10月31日 下午2:28:45  
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<JpPlatform> queryPlatformList(Page<JpPlatform> page,Map<String,Object> conditions);
	
	
}
