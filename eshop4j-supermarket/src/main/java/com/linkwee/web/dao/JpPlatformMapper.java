package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.jpressPlatform.JpPlatform;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 11:31:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface JpPlatformMapper extends GenericDao<JpPlatform,Long>{
	
	/**
	 * 平台列表
	 * @author yalin 
	 * @date 2016年7月15日 上午10:28:16  
	 * @param page
	 * @return
	 */
	public List<JpPlatform> queryPlatformList(Page<JpPlatform> page,Map<String,Object> map);
}
