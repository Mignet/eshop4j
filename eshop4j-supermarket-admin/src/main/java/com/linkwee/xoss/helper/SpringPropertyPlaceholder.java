package com.linkwee.xoss.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.linkwee.core.util.ConfigFileUtil;

public class SpringPropertyPlaceholder extends PropertyPlaceholderConfigurer  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringPropertyPlaceholder.class);

	/**
	 * 生产环境加载{server}/config/xxx.properties文件
	 * 开发环境加载classpath的xxx.properties文件
	 * 
	 * @param location
	 *            资源文件
	 */
	@Override
	public void setLocation(Resource location) {
		super.setLocation(getResource(location));
	}
	
	private Resource getResource(Resource location){
		String path = ConfigFileUtil.findFile(location.getFilename());
		LOGGER.info(path);
		if(path!=null){
			Resource resource = new FileSystemResource(path);
			if (resource.exists()) {
				location = null;
				LOGGER.info(String.format("########################Loading resource file: %s",path));
				return resource;
			}
		}
		return location;
	}

	@Override
	public void setLocations(Resource[] locations) {
		for(int i=0;i<locations.length;i++){
			locations[i] = getResource(locations[i]);
		}
		super.setLocations(locations);
	}
}