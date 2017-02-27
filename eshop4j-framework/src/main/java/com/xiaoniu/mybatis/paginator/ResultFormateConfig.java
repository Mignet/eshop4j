package com.xiaoniu.mybatis.paginator;

import java.util.HashMap;
import java.util.Map;

public class ResultFormateConfig {
	private Map<Class<?>,ResultValueProcessor<?>> processors =  new HashMap<Class<?>,ResultValueProcessor<?>>();
	
	
	public void registerResultValueProcessor(Class<?> clazz,ResultValueProcessor<?> resultValueProcessor){
		processors.put(clazz,resultValueProcessor);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processor(Object obj ){
		if(obj==null){
			return obj;
		}
		for(Class<?> clazz:processors.keySet()){
			if(clazz.isInstance(obj)){
				ResultValueProcessor processor = processors.get(clazz);
				if(processor!=null){
					return processor.process(obj);
				}
			}
		}
		return obj;
	}
}
