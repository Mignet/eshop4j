package com.eshop4j.xoss.helper;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.service.SysRejectedExecuteLogService;
import com.eshop4j.xoss.util.RejectedExecuteRetry;

@Aspect
@Component("rejectedExecuteRetryHelper")
public class RejectedExecuteRetryHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RejectedExecuteRetryHelper.class);
	
	private static final ThreadLocal<String> executeIdThreadLocal =  new ThreadLocal<String>();
	
	
	public static String getExecuteId() {
		return executeIdThreadLocal.get();
	}
	
	public static void setExecuteId(String executeId) {
		 executeIdThreadLocal.set(executeId);
	}
	
	public static void cleanExecuteId(){
		executeIdThreadLocal.remove();
	}
	
	

	@Autowired
	private SysRejectedExecuteLogService sysRejectedExecuteLogService;
	
	@AfterThrowing(value="execution(* com.eshop4j.act.*.service..*(..)) || execution(* com.eshop4j.tc.*.service..*(..)) || execution(* com.eshop4j.web.service..*(..))", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String serviceName="";
		String methodName="";
		try{
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			if (method.getDeclaringClass().isInterface()) {
		        try {
		            method= joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());	        
		        } catch ( Exception exception) {
		        	LOGGER.warn("获取实现方法异常", exception);
		        } 
		    }
			//没有 RejectedExecuteRetry 注解 或者 getExecuteId 不为空 则不处理
			if(method.getAnnotation(RejectedExecuteRetry.class)==null || StringUtils.isNotBlank(getExecuteId()))return;
			String exceptionMsg = ex.getMessage();
			exceptionMsg = StringUtils.isBlank(exceptionMsg) ? "未知异常": exceptionMsg;
			serviceName = joinPoint.getTarget().getClass().getAnnotation(Service.class).value();
			methodName = method.getName();
			Object[] args = joinPoint.getArgs();
			if(args==null||args.length==0){
				sysRejectedExecuteLogService.saveRejectedExecuteLog(exceptionMsg, serviceName, methodName);
			}else{
				ArgsMeta[] metas =new ArgsMeta[args.length];
				for (int i = 0; i < args.length; i++) {
					metas[i] = new ArgsMeta(JSON.toJSONString(args[i]), args[i].getClass().getName(),i);
				}
				sysRejectedExecuteLogService.saveRejectedExecuteLog(exceptionMsg, serviceName, methodName,metas);
			}
		}catch(Exception e){
			LOGGER.warn("保存 RejectedExecuteLog 异常 serviceName={},methodName={},args={},exceptionMsg={},e={}", new Object[]{serviceName,methodName,joinPoint.getArgs(),ex.getMessage(),e});
		}
	}
	
}
