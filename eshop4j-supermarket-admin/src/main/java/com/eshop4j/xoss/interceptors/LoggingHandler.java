package com.eshop4j.xoss.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.NetUtils;
import com.eshop4j.xoss.util.AccessParamRecord;
import com.eshop4j.xoss.util.RequestLogging;


/**
 * brief logger
 * @author Mignet
 *
 */
public class LoggingHandler extends HandlerInterceptorAdapter {
	
	private static final Class<RequestLogging> REQUEST_LOGGING_CLASS = RequestLogging.class;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandler.class);

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try {
			if (handler != null && (handler instanceof HandlerMethod)) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				RequestLogging classAnnotation = AnnotationUtils.findAnnotation(handlerMethod.getBean().getClass(),REQUEST_LOGGING_CLASS);
				RequestLogging methodAnnotation = AnnotationUtils.findAnnotation(method, REQUEST_LOGGING_CLASS);
				Subject currentUser = ThreadContext.getSubject();
				if (classAnnotation != null && methodAnnotation != null ) {
					String classValue = classAnnotation.value();
					String methodValue = methodAnnotation.value();
					String paramStr = AccessParamRecord.paramInfo(handlerMethod.getMethodParameters(), request);
					//ctor log info
					StringBuilder logs = new StringBuilder();
					logs.append(NetUtils.getIpAddress(request));
					logs.append("|");
					logs.append(currentUser != null?JSON.toJSONString(currentUser.getPrincipal()):null);
					logs.append("|");
					logs.append(DateUtils.getNow(DateUtils.FORMAT_LONG));
					logs.append("|"+classValue+"->"+methodValue);
					logs.append(paramStr);
					LOGGER.info(logs.toString());
				}
			}
		}finally {
			super.postHandle(request, response, handler, modelAndView);
		}
	}
	
}
