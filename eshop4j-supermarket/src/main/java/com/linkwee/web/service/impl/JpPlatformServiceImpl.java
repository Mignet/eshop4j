package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.JpPlatformMapper;
import com.linkwee.web.model.jpressPlatform.JpPlatform;
import com.linkwee.web.service.JpPlatformService;


 /**
 * 
 * @描述：JpPlatformService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月31日 11:31:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("jpPlatformService")
public class JpPlatformServiceImpl extends GenericServiceImpl<JpPlatform, Long> implements JpPlatformService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JpPlatformServiceImpl.class);
	
	@Value("${jpress_domain}")
	private String jpress_domain; //域名
	
	@Resource
	private JpPlatformMapper jpPlatformMapper;
	
	@Override
    public GenericDao<JpPlatform, Long> getDao() {
        return jpPlatformMapper;
    }

	public String getJpress_domain() {
		return jpress_domain;
	}

	public void setJpress_domain(String jpress_domain) {
		this.jpress_domain = jpress_domain;
	}

	@Override
	public PaginatorResponse<JpPlatform> queryPlatformList(Page<JpPlatform> page,Map<String,Object> conditions){
		PaginatorResponse<JpPlatform> paginatorResponse = new PaginatorResponse<JpPlatform>();
		List<JpPlatform> queryCimOrginfoList = jpPlatformMapper.queryPlatformList(page,conditions);
		for(JpPlatform item :queryCimOrginfoList){
			item.setDtlLinkUrl(jpress_domain +"/p/"+ item.getId() + ".html");
			item.setPlatformDetailImg(new StringBuffer(jpress_domain).append(item.getPlatformDetailImg()).toString()); //平台列表logo
		}
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		LOGGER.debug("queryPlatformList excute success");
		return paginatorResponse;
	}
    


}
