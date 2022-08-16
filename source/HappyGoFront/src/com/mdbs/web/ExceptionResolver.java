/*
 * Created on : 2009/4/7
 */
package com.mdbs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 *
 * @author Ringo
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {
	//private static final Logger logger = Logger.getLogger(ExceptionResolver.class);

	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		//logger.error(request.getRequestURL() + (null == request.getQueryString() ? "" : "?" + request.getQueryString()), ex);
		request.setAttribute("ex",ex);
		return super.doResolveException(request, response, handler, ex);
	}
}
