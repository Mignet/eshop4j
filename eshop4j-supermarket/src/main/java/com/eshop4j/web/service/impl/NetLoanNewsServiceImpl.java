package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eshop4j.web.dao.NetLoanNewsMapper;
import com.eshop4j.web.model.news.HomepageNetNewsListResp;
import com.eshop4j.web.service.NetLoanNewsService;
import com.eshop4j.xoss.util.jpress.JsoupUtils;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("netLoanNewsService")
public class NetLoanNewsServiceImpl implements NetLoanNewsService{
	
	@Resource
	private NetLoanNewsMapper netLoanNewsMapper;
	
	@Override
	public List<HomepageNetNewsListResp> queryHomepageNetNewsList(String type) {
		List<HomepageNetNewsListResp> list = netLoanNewsMapper.queryHomepageNetNewsList(type);
		for (int i = 0; i < list.size(); i++) {
			HomepageNetNewsListResp bo = list.get(i);
			bo.setImage(JsoupUtils.getFirstImageSrc(bo.getContent()));
			String content = JsoupUtils.getText(bo.getContent());
			bo.setContent(content);
			bo.setUrl("c/" + bo.getUrl() + ".html");
			list.set(i, bo);
		}
		return list;
	}

	
}
