package com.linkwee.core.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * Common Exception Resolver
 *
 * @Author Mignet
 * @Date 2016/6/28
 */
public class CommonHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    	logger.error("CommonHandlerException:"+httpServletRequest.getRequestURI(),e);
        return null;
    }
}
