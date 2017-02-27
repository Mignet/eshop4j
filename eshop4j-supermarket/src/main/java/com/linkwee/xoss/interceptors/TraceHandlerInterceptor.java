package com.linkwee.xoss.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.util.StringUtils;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.trace.Span;
import com.linkwee.xoss.trace.SpanBuider;
import com.linkwee.xoss.trace.Tracer;
import com.linkwee.xoss.util.AccessParamRecord;
import com.linkwee.xoss.util.RequestLogging;

public class TraceHandlerInterceptor extends HandlerInterceptorAdapter{
	
	private static final Class<RequestLogging> REQUEST_LOGGING_CLASS = RequestLogging.class;

	//private static final Logger LOGGER = LoggerFactory.getLogger(TraceHandlerInterceptor.class);
	
	private boolean isTrace( Object handler){
		if (handler != null && (handler instanceof HandlerMethod)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestLogging classAnnotation = AnnotationUtils.findAnnotation(handlerMethod.getBean().getClass(),REQUEST_LOGGING_CLASS);
			RequestLogging methodAnnotation = AnnotationUtils.findAnnotation(method, REQUEST_LOGGING_CLASS);
			
			return (classAnnotation != null && methodAnnotation != null) ;
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		try {
			Tracer.clean();
			if(isTrace(handler)){
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Subject currentUser = ThreadContext.getSubject();
				String id = StringUtils.getUUID();
				Span span = SpanBuider.buider().traceId(id).spanId(id).user(currentUser != null?JsonWebTokenHepler.getUserIdByToken(request.getParameter("token")):"guest").name(request.getRequestURI()).methodName(request.getMethod()).msg( AccessParamRecord.paramInfo(handlerMethod.getMethodParameters(), request)).startTime(System.currentTimeMillis()).build();
				Tracer.setCurrentSpan(span);
			}
		}finally {
			super.preHandle(request, response, handler);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			if(isTrace(handler) && Tracer.getCurrentSpan()!=null){
				Tracer.currentSpanEnd("成功");
			}
		}finally {
			Tracer.clean();
			super.afterCompletion(request, response, handler, ex);
		}
		
	}
	
	
}
