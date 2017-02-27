package com.eshop4j.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefectUtils {
	private static final Logger logger = LoggerFactory.getLogger(RefectUtils.class);

	public static boolean isPrimitive(Class<?> type) {
		return type.isPrimitive() || type == String.class
				|| type == Character.class || type == Boolean.class
				|| type == Byte.class || type == Short.class
				|| type == Integer.class || type == Long.class
				|| type == Float.class || type == Double.class
				|| type == Object.class;
	}
	
	
	private static String getGetName(Field field){
		String fieldName = field.getName();
		if(field.getType().isPrimitive()&&field.getType() == Boolean.class){
			return "is"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		}else{
			return "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		}
	}
	
	private static String getSetName(Field field){
		String fieldName = field.getName();
		return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	
	public static Class<?> getFieldClass(Object obj,String fieldName){
		Class<?> clazz = obj.getClass();
		Field field;
			try {
				field = clazz.getDeclaredField(fieldName);
				if(field!=null){
					return field.getType();
				}
			} catch (NoSuchFieldException e) {
			} catch (SecurityException e) {
			}
		return null;
	}
	
	public static Map<String,Field> getFields(Object obj){
		Class<?> clazz = obj.getClass();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		while(clazz!=null){
			Field[]  fields = clazz.getDeclaredFields();
			clazz = clazz.getSuperclass();
			for(Field field:fields){
				fieldMap.put(field.getName(),field);
			}
		}
		return fieldMap;
	}
	public static Map<String,Method> getGetMethods(Object obj){
		Class<?> clazz = obj.getClass();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		Map<String,String> fieldGetMethodMap = new HashMap<String,String>();
		Map<String,Method> methodMap = new HashMap<String,Method>();
		while(clazz!=null){
			Field[]  fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			for(Field field:fields){
				fieldMap.put(field.getName(),field);
				fieldGetMethodMap.put(getGetName(field), field.getName());
			}
			for(Method method:methods){
				String filedName = fieldGetMethodMap.get(method.getName());
				if(!StringUtils.isBlank(filedName)){
					methodMap.put(filedName, method);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return methodMap;
	}
	
	public static Map<String,Method> getSetMethods(Object obj){
		Class<?> clazz = obj.getClass();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		Map<String,String> fieldSetMethodMap = new HashMap<String,String>();
		Map<String,Method> methodMap = new HashMap<String,Method>();
		while(clazz!=null){
			Field[]  fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			for(Field field:fields){
				fieldMap.put(field.getName(),field);
				fieldSetMethodMap.put(getSetName(field), field.getName());
			}
			for(Method method:methods){
				String filedName = fieldSetMethodMap.get(method.getName());
				if(!StringUtils.isBlank(filedName)){
					methodMap.put(filedName, method);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return methodMap;
	}
	
	
	public static void copyObj(Object src,Object target,FieldDecorator decorator) {
		if(target==null||src==null){
			return ;
		}else{
			 Map<String,Method> setMap = getSetMethods(target);
			 Map<String,Field> fieldMap = getFields(target);
			 Map<String,Method> getMap = getGetMethods(src);
			 for(String fieldName:setMap.keySet()){
				 Method get =  getMap.get(fieldName);
				 try{
					 if(get!=null){
						 Object value = get.invoke(src,new Object[]{});
						 Field field = fieldMap.get(fieldName);
						 if(value==null&&field.getType()==String.class){
							 setMap.get(fieldName).invoke(target,"");
							 continue;
						 }
						 if(decorator!=null&&value!=null&&value.getClass()!=field.getType()){
		    				  value= decorator.doInvoke(fieldName, value);
		    			 }
						 if(value!=null&&value.getClass()==field.getType()){
							 setMap.get(fieldName).invoke(target,value);
						 }
					 }	
	    		  }catch(Exception e){
	    			  logger.warn("对象copy错误 fieldName={}",fieldName,e);
	    		  }
			 }
		}
	}
	
	public interface FieldDecorator{
		public Object doInvoke(String fieldName,Object value);
	}
}
