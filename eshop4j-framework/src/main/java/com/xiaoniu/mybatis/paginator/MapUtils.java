package com.xiaoniu.mybatis.paginator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static DecimalFormat df = new DecimalFormat("###,###.##");
	
	public static void formateMap(Map<String,Object> map){
		for(String key:map.keySet()){
			Object obj = map.get(key);
			if(obj==null){
				continue;
			}
			if(obj instanceof Date){
				Date date = (Date)obj;
				map.put(key, sdf.format(date));
			}else if(obj instanceof BigDecimal||obj instanceof Double||obj instanceof Float){
				map.put(key, df.format(obj));
			}
		}
	}
	
	  public static Map<String,Object> convertBean(Object bean) throws Exception { 
	        Class<?> type = bean.getClass(); 
	        Map<String,Object> returnMap = new HashMap<String,Object>(); 
	        Field[] fields = type.getDeclaredFields();
	        for (Field field:fields) { 
	            String propertyName = field.getName();
	            if (!propertyName.equals("class")) { 
	            	field.setAccessible(true);
	                Object result = field.get(bean);
	                if (result != null) { 
	                    returnMap.put(propertyName, result); 
	                } else { 
	                    returnMap.put(propertyName, ""); 
	                } 
	                field.setAccessible(false);
	            } 
	        } 
	        return returnMap; 
	}
	  
	  
	
	public static Map<String,Object> getDefaultFormateMap(Map<String,Object> data){
		Map<String,Object> map = new HashMap<String,Object>();
		for(String key:data.keySet()){
			Object obj = data.get(key);
			if(obj==null){
				map.put(key,obj);
				continue;
			}
			if(obj instanceof Date||obj instanceof Timestamp){
				Date date = (Date)obj;
				map.put(key, sdf.format(date));
			}else if(obj instanceof BigDecimal||obj instanceof Double||obj instanceof Float){
				map.put(key, df.format(obj));
			}else{
				map.put(key,obj);
			}
		}
		return map;
	}
}
