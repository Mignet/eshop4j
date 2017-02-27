package com.eshop4j.xoss.spring;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;

import com.eshop4j.core.util.ConfigFileUtil;

/***
 * 加载所有初始化bean之前的占位符属性文件
 * 
 * @author Mignet
 *
 */
public class CustomerApplicationContextInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerApplicationContextInitializer.class);

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ResourcePropertySource propertySource = getResource("classpath:config/elastic-job.properties");
		applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
	}

	private ResourcePropertySource getResource(String location) {
		ResourcePropertySource resource = null;
		String path = ConfigFileUtil.findFile(location);
		//如果没有自定义文件，那就使用自带的classpath文件
		if (StringUtils.isBlank(path)) {
			path = location;
		}
		try {
			resource = new ResourcePropertySource(path);
			LOGGER.info("########################Loading resource file: {}", path);
		} catch (IOException e) {
			LOGGER.error("Loading resource file ERROR: {}", path, e);
		}
		return resource;
	}

}
