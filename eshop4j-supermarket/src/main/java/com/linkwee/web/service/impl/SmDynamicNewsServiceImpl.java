package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.NetloanNewsResponse;
import com.linkwee.api.response.mc.MsgResp;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.JpContent;
import com.linkwee.web.model.JpOption;
import com.linkwee.web.model.JpTaxonomy;
import com.linkwee.web.model.SmDynamicNews;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.dao.SmDynamicNewsMapper;
import com.linkwee.web.service.SmDynamicNewsService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.impl.SmDynamicNewsServiceImpl;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.JpressContentHelper;
import com.linkwee.xoss.util.HtmlFilterUtil;
import com.linkwee.xoss.util.JsoupUtils;


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
	@Resource
	private SysConfigService systemConfigService; 
	
	@Override
    public GenericDao<SmDynamicNews, Long> getDao() {
        return smDynamicNewsMapper;
    }

	@Override
	public PaginatorResponse<SmDynamicNews> queryDynamicNews(Page<SmDynamicNews> page, Map<String, Object> conditions) {
		// TODO Auto-generated method stub
		List<SmDynamicNews> resultList = smDynamicNewsMapper.queryDynamicNews(page,conditions);
		for(SmDynamicNews smDynamicNews : resultList){
    		if(smDynamicNews != null && StringUtils.isNotBlank(smDynamicNews.getContent()) ){
    			String content = HtmlFilterUtil.filterHtml(smDynamicNews.getContent()).replace("&nbsp;", "").replaceAll("\\s*", "");
    			if(StringUtils.isNotBlank(content) && content.length() > 100){
    				smDynamicNews.setContent(content.substring(0, 100));
    			}else{
    				smDynamicNews.setContent(content);
    			}   			
    		}    		
    	}
		PaginatorResponse<SmDynamicNews> paginatorResponse = new PaginatorResponse<SmDynamicNews>();
        paginatorResponse.setDatas(resultList);
        paginatorResponse.setValuesByPage(page);
        return paginatorResponse;
	}

	@Override
	public Map<String, Object> findNearNews(SmDynamicNews smDynamicNews) {
		// TODO Auto-generated method stub
		SmDynamicNews beforeNews = smDynamicNewsMapper.beforeOneNews(smDynamicNews);
		SmDynamicNews nextNews = smDynamicNewsMapper.nextOneNews(smDynamicNews);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("beforOne", beforeNews);
		resultMap.put("nextOne", nextNews);
		return resultMap;
	}

	@Override
	public PaginatorResponse<NetloanNewsResponse> queryNetloanNews(Page<NetloanNewsResponse> page, Map<String, Object> conditions) {
		// TODO Auto-generated method stub
		List<NetloanNewsResponse> resultList = smDynamicNewsMapper.queryNetloanNewsPageList(page,conditions);
		List<JpOption> optionList = smDynamicNewsMapper.queryAllJpOption();
		JpTaxonomy taxonomy = smDynamicNewsMapper.queryTaxonomy((String)conditions.get("title"));
		String jpressServerUrl = systemConfigService.getValuesByKey(WebConstants.JPRESS_SERVER_URL);
		for(NetloanNewsResponse netloanNewsResponse : resultList){
			
			//如果缩略图不存在，设置缩略图为内容的的第一张图片
			if(netloanNewsResponse!= null && StringUtils.isBlank(netloanNewsResponse.getThumbnail())){
				if(netloanNewsResponse != null && StringUtils.isNotBlank(netloanNewsResponse.getContent()) ){
	    			String thumbnail = JsoupUtils.getFirstImageSrc(netloanNewsResponse.getContent());	    		
	    			netloanNewsResponse.setThumbnail(thumbnail);
	    		}  
			}
			//如果简介不存在，设置简介为内容的去掉空字符后的前50个字
			if(netloanNewsResponse!= null && StringUtils.isBlank(netloanNewsResponse.getSummary())){
				if(netloanNewsResponse != null && StringUtils.isNotBlank(netloanNewsResponse.getContent()) ){
	    			String content = HtmlFilterUtil.filterHtml(netloanNewsResponse.getContent()).replace("&nbsp;", "").replaceAll("\\s*", "");
	    			if(StringUtils.isNotBlank(content) && content.length() > 50){
	    				netloanNewsResponse.setSummary(content.substring(0, 50));
	    			}else{
	    				netloanNewsResponse.setSummary(content);
	    			}   			
	    		}  
			} 
			//设置内容为空
			netloanNewsResponse.setContent("");
			//网贷新闻详情页
			JpContent jpContent = new JpContent();
			jpContent.setModule(netloanNewsResponse.getModule());
			jpContent.setSlug(netloanNewsResponse.getSlug());
			jpContent.setId(Long.parseLong(String.valueOf(netloanNewsResponse.getId())));
			jpContent.setCreated(netloanNewsResponse.getCreated());
			String detailUrl = JpressContentHelper.getRouter(jpContent, optionList);
			netloanNewsResponse.setDetailUrl(jpressServerUrl + detailUrl);
			//更多链接
			String moreUrl = JpressContentHelper.getRouter(taxonomy, optionList);
			netloanNewsResponse.setMoreUrl(jpressServerUrl + moreUrl);
			//处理缩略图路径格式
			netloanNewsResponse.setThumbnail(netloanNewsResponse.getThumbnail() != null ? (jpressServerUrl + netloanNewsResponse.getThumbnail().replace("\\", "/")) : "");
			
    	}
		PaginatorResponse<NetloanNewsResponse> paginatorResponse = new PaginatorResponse<NetloanNewsResponse>();
        paginatorResponse.setDatas(resultList);
        paginatorResponse.setValuesByPage(page);
        return paginatorResponse;
	}

	@Override
	public NetloanNewsResponse queryNetloanNewsDetail(
			Map<String, Object> conditions) {
		NetloanNewsResponse result = smDynamicNewsMapper.queryNetloanNewsDetail(conditions);
		List<JpOption> optionList = smDynamicNewsMapper.queryAllJpOption();
		String jpressServerUrl = systemConfigService.getValuesByKey(WebConstants.JPRESS_SERVER_URL);			
		//如果缩略图不存在，设置缩略图为内容的的第一张图片
		if(result!= null && StringUtils.isBlank(result.getThumbnail())){
			if(result != null && StringUtils.isNotBlank(result.getContent()) ){
    			String thumbnail = JsoupUtils.getFirstImageSrc(result.getContent());	    		
    			result.setThumbnail(thumbnail);
    		}  
		}
		//如果简介不存在，设置简介为内容的去掉空字符后的前50个字
		if(result!= null && StringUtils.isBlank(result.getSummary())){
			if(result != null && StringUtils.isNotBlank(result.getContent()) ){
    			String content = HtmlFilterUtil.filterHtml(result.getContent()).replace("&nbsp;", "").replaceAll("\\s*", "");
    			if(StringUtils.isNotBlank(content) && content.length() > 50){
    				result.setSummary(content.substring(0, 50));
    			}else{
    				result.setSummary(content);
    			}   			
    		}  
		} 
		//设置内容为空
		result.setContent("");
		//网贷新闻详情页
		JpContent jpContent = new JpContent();
		jpContent.setModule(result.getModule());
		jpContent.setSlug(result.getSlug());
		jpContent.setId(Long.parseLong(String.valueOf(result.getId())));
		jpContent.setCreated(result.getCreated());
		String detailUrl = JpressContentHelper.getRouter(jpContent, optionList);
		result.setDetailUrl(jpressServerUrl + detailUrl);
		//处理缩略图路径格式
		result.setThumbnail(result.getThumbnail() != null ? (jpressServerUrl + result.getThumbnail().replace("\\", "/")) : "");
			
        return result;
	}
    
}
