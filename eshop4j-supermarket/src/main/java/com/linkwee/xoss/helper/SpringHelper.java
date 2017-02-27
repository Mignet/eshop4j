package com.linkwee.xoss.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringHelper implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringHelper.applicationContext = applicationContext;
		
	}
	
	

}
