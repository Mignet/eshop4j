package com.xiaoniu.mybatis.paginator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueProcessorDecoratorHandler implements DecoratorHandler {
	
	private static final Logger log =  LoggerFactory.getLogger(ValueProcessorDecoratorHandler.class);
	
	private ResultFormateConfig config = null;
	
	public ValueProcessorDecoratorHandler(ResultFormateConfig config){
		this.config = config;
	}

	@SuppressWarnings("unchecked")
	public Object invoke(Object data) {
		Map<String,Object> obj = null;
		if(!(data instanceof Map)){
			try {
				obj = MapUtils.convertBean(data);
			} catch (Exception e) {
				log.error("对象转map错误:",e);
			}
		}else{
			obj = (Map<String,Object>)data;
		}
		for(String key:obj.keySet()){
			obj.put(key, config.processor(obj.get(key)));
		}
		return obj;
	}

}
