package com.linkwee.openapi.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoRequest.class);
	
	public static <T> T getAttribute(HttpServletRequest request,String key,Class<T> valueType){
		Object o = request.getAttribute(key);
		if(o==null){
			o = request.getParameter(key);
			if(o==null){
				return null;
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(o.toString(), valueType);
		} catch (JsonParseException e) {
			LOGGER.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException: ", e);
		} catch (IOException e) {
			LOGGER.error("IOException: ", e);
		}
		return null;
	}
}
