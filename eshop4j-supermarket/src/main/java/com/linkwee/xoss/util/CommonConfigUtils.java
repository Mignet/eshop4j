package com.linkwee.xoss.util;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.linkwee.core.util.ConfigFileUtil;

/**
 * 加载配置文件工具类
 * @author liqimoon
 *
 */
public class CommonConfigUtils {
	
	static Logger logger =  LoggerFactory.getLogger(CommonConfigUtils.class);
			
	public static InputStream getResourceAsStream(String classPathLocation){
		Resource addressConfig = new ClassPathResource(classPathLocation);
		try {
			String path = ConfigFileUtil.findFile(addressConfig.getFilename());
			if(StringUtils.isNotBlank(path)){
				Resource resource = new FileSystemResource(path);
				if (resource.exists()) {
					addressConfig = null;
					logger.info(String.format("########################Loading resource file: %s",path));
					return resource.getInputStream();
				}
			}
			logger.info(String.format("########################Loading resource file: %s",addressConfig));
			return addressConfig.getInputStream();	
		} catch (Exception e) {
			logger.info(String.format("########################加载配置文件%s异常: %s",addressConfig,e));
		}
		return null;
	}
	
	public static Resource getResource(Resource location){
		String path = ConfigFileUtil.findFile(location.getFilename());
		if(StringUtils.isNotBlank(path)){
			Resource resource = new FileSystemResource(path);
			if (resource.exists()) {
				location = null;
				logger.info(String.format("########################Loading resource file: %s",path));
				return resource;
			}
		}
		logger.info(String.format("########################Loading resource file: %s",location));
		return location;
	}
}
