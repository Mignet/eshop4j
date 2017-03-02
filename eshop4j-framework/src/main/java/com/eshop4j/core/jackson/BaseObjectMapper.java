package com.eshop4j.core.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * 描述：json null值 日期格式化处理
 * @author yalin
 * @date 2016年7月22日 下午3:05:39 
 * Copyright (c) 深圳市前海米格网络科技有限公司
 */
public class BaseObjectMapper extends ObjectMapper {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 7817732152721842655L;

	public BaseObjectMapper(){  
        super();  
        //设置null转换""  
        getSerializerProvider().setNullValueSerializer(new NullSerializer());  
        //设置日期转换yyyy-MM-dd HH:mm:ss  
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
    }  
      
    //null的JSON序列  
    private class NullSerializer extends JsonSerializer<Object> {  
        public void serialize(Object value, JsonGenerator jgen,SerializerProvider provider) throws IOException,JsonProcessingException {  
            jgen.writeString("");  
        }  
    }  
} 
