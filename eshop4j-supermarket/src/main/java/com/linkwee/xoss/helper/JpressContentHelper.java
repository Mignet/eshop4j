package com.linkwee.xoss.helper;

import java.text.SimpleDateFormat;
import java.util.List;

import com.linkwee.web.model.JpContent;
import com.linkwee.web.model.JpOption;
import com.linkwee.web.model.JpTaxonomy;

public class JpressContentHelper {
	
	public static final String TYPE_STATIC_MODULE_SLUG = "_static_module_slug"; // 模型SLUG
	public static final String TYPE_STATIC_MODULE_ID = "_static_module_id"; // 静态模型ID
	public static final String TYPE_STATIC_DATE_SLUG = "_static_date_slug"; // 静态日期slug
	public static final String TYPE_STATIC_DATE_ID = "_static_date_id"; // 静态日期id
	public static final String TYPE_STATIC_PREFIX_SLUG = "_static_prefix_slug"; // 静态slug
	public static final String TYPE_STATIC_PREFIX_ID = "_static_prefix_id"; // 静态ID
	public static final String TYPE_DYNAMIC_ID = "_dynamic_id"; // 动态类型
	public static final String TYPE_DYNAMIC_SLUG = "_dynamic_slug"; // 动态类型
	public static final String SLASH = "/"; // 动态类型
	public static final String ROUTER_CONTENT = "/c";
	
	public static final String URL_PARA_SEPARATOR = "-";//默认的jfinal参数分隔符,如在jpress中修改需要修改此处
	
	public static final String DEFAULT_TYPE = TYPE_STATIC_PREFIX_SLUG;

	public static String getRouter(JpContent content,List<JpOption> option) {

		String url = getRouterWithoutSuffix(content,option);

		String settingType = getRouterType(option);
		if (TYPE_DYNAMIC_ID.equals(settingType) || TYPE_DYNAMIC_SLUG.equals(settingType)) {
			return url;
		}

		if (enalbleFakeStatic(option)) {
			url += getFakeStaticSuffix(option);
		}
		return url;
	}

	private static String getRouterWithoutSuffix(JpContent content,List<JpOption> option) {
		
		String settingType = getRouterType(option);
		// 模型SLUG
		if (TYPE_STATIC_MODULE_SLUG.equals(settingType)) {
			return SLASH + content.getModule() + SLASH + content.getSlug();
		}
		// 模型ID
		else if (TYPE_STATIC_MODULE_ID.equals(settingType)) {
			return SLASH + content.getModule() + SLASH + content.getId();
		}
		// 日期SLUG
		else if (TYPE_STATIC_DATE_SLUG.equals(settingType)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return SLASH + sdf.format(content.getCreated()) + SLASH + content.getSlug();
		}
		// 日期ID
		else if (TYPE_STATIC_DATE_ID.equals(settingType)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return SLASH + sdf.format(content.getCreated()) + SLASH + content.getId();
		}
		// 前缀SLUG
		else if (TYPE_STATIC_PREFIX_SLUG.equals(settingType)) {
			String prefix = getRouterPrefix(option);
			return SLASH + prefix + SLASH + content.getSlug();
		}
		// 前缀ID
		else if (TYPE_STATIC_PREFIX_ID.equals(settingType)) {
			String prefix = getRouterPrefix(option);
			return SLASH + prefix + SLASH + content.getId();
		}
		// 动态ID
		else if (TYPE_DYNAMIC_ID.equals(settingType)) {
			String prefix = getRouterPrefix(option);
			return SLASH + prefix + "?id=" + content.getId();
		} 
		// 动态SLUG
		else if (TYPE_DYNAMIC_SLUG.equals(settingType)) {
			String prefix = getRouterPrefix(option);
			return SLASH + prefix + "?slug=" + content.getSlug();
		}
		else {
			return ROUTER_CONTENT + "?id=" + content.getId();
		}
	}
		
	public static String getRouterType(List<JpOption> optionList) {
		String type = null;
		for(JpOption jpOption : optionList){
			if(jpOption.getOptionKey().equals("router_content_type")){
				type = jpOption.getOptionValue();
			}
		}
		if (StringUtils.isBlank(type))
			return DEFAULT_TYPE;

		return type;
	}
		
	protected static boolean enalbleFakeStatic(List<JpOption> optionList) {
		Boolean fakeStaticEnable = false;
		for(JpOption jpOption : optionList){
			if(jpOption.getOptionKey().equals("router_fakestatic_enable")){
				fakeStaticEnable = Boolean.parseBoolean(jpOption.getOptionValue());
			}
		}
		return fakeStaticEnable != null && fakeStaticEnable == true;
	}
	
	protected static String getFakeStaticSuffix(List<JpOption> optionList) {
		String fakeStaticSuffix = null;
		for(JpOption jpOption : optionList){
			if(jpOption.getOptionKey().equals("router_fakestatic_suffix")){
				fakeStaticSuffix = jpOption.getOptionValue();
			}
		}
		if (StringUtils.isNotBlank(fakeStaticSuffix)) {
			return fakeStaticSuffix.trim();
		}
		return ".html";
	}
	
	public static String getRouterPrefix(List<JpOption> optionList) {
		String prefix = null;
		for(JpOption jpOption : optionList){
			if(jpOption.getOptionKey().equals("router_content_prefix")){
				prefix = jpOption.getOptionValue();
			}
		}
		if (StringUtils.isBlank(prefix))
			prefix = ROUTER_CONTENT.substring(1);
		return prefix;
	}
	
	public static String getRouter(JpTaxonomy taxonomy,List<JpOption> option) {
		String url = getRouterWithoutSuffix(taxonomy);
		if (enalbleFakeStatic(option)) {
			url += getFakeStaticSuffix(option);
		}
		return url;
	}
	
	private static String getRouterWithoutSuffix(JpTaxonomy taxonomy) {
		String url = SLASH + taxonomy.getContentModule() + URL_PARA_SEPARATOR
				+ (taxonomy.getSlug() == null ? taxonomy.getId() : taxonomy.getSlug()) + URL_PARA_SEPARATOR + 1;

		return url;
	}
}
