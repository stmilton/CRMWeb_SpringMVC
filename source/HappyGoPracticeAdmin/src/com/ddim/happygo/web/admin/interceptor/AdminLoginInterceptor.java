package com.ddim.happygo.web.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ddim.happygo.Constants;
import com.ddim.happygo.model.Manager;
import com.ddim.happygo.util.ManagerUtil;
import com.mdbs.util.WebUtil;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.web.admin.interceptor<P> 
 * 類別名稱：AdminLoginInterceptor.java<P>
 * 程式內容說明：Interceptor<P>
 * @author Yvonne
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ManagerUtil managerUtil;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestUri = request.getServletPath();
		if (requestUri.startsWith(Constants.LOGIN_PAGE)) {
			return true;
		}

		Manager manager = managerUtil.getManager(request);
		if (null != manager) {
			return true;
		} else {
			String loginUrl = ("/".equals(request.getContextPath()) ? "" : request.getContextPath()) + Constants.LOGIN_PAGE;
			response.sendRedirect(WebUtil.removeControlCharacter(loginUrl));
			return false;
		}
	}

}
