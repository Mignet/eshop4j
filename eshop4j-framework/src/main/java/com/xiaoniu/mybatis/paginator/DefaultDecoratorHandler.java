package com.xiaoniu.mybatis.paginator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDecoratorHandler implements DecoratorHandler {
	
	private static final Logger log =  LoggerFactory.getLogger(DefaultDecoratorHandler.class);

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
		return MapUtils.getDefaultFormateMap(obj);
	}

}
