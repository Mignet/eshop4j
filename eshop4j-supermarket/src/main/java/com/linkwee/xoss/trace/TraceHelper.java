package com.linkwee.xoss.trace;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.StringUtils;

@Aspect
@Component("traceHelper")
@Order(1) 
public class TraceHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TraceHelper.class);
	
	@Value("${APPLICATION_VERSION}")
	private String application_version; //应用版本

	@Around("execution(* com.linkwee.act.*.service..*(..)) || execution(* com.linkwee.tc.*.service..*(..)) || execution(* com.linkwee.web.service..*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{ 
			Span parentSpan =Tracer.getParentSpan();
	        if(parentSpan == null  ){
	        	return  pjp.proceed();
	        }
	        parentSpan.setVersion(application_version);
	        SpanBuider buider = SpanBuider.buider();
	        buider.traceId(parentSpan.getTraceId()).version(application_version).parentId(parentSpan.getSpanId()).spanId(StringUtils.getUUID()).user(parentSpan.getUser()).startTime(System.currentTimeMillis());	       
	        setNamaAndMethodName(pjp, buider);	
	        String resultMsg = null;
			try {  
				Tracer.setCurrentSpan(buider.build());
				Object result = pjp.proceed();
				resultMsg = "成功";
				return result;
	        } catch (Throwable e) {  
	        	resultMsg = "异常 : "+e;
	        	LOGGER.warn("Throwable current traceid ={},throwable={}",Tracer.getCurrentSpan().getTraceId(),e);
	        	throw e;
	        } finally{
	        	Tracer.currentSpanEnd(resultMsg);
	        	Tracer.setCurrentSpan(parentSpan);
	        }
	} 
	
	private void setNamaAndMethodName(ProceedingJoinPoint pjp,SpanBuider buider){
		try{
			Class<?> targetClass = pjp.getTarget().getClass();
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Method method = signature.getMethod();
			if (method.getDeclaringClass().isInterface()) {
		        try {
		            method= targetClass.getDeclaredMethod(signature.getName(), method.getParameterTypes());	        
		        } catch ( Exception exception) {
		        	LOGGER.warn("获取实现方法异常", exception);
		        } 
		    }
			buider.name(targetClass.getName()).methodName(method.getName());
		}catch(Exception e){
			LOGGER.warn("e={}", e);
		}
	}

}
