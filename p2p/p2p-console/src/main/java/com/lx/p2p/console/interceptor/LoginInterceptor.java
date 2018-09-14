/*
 * Copyright (C) 2015 ShenZhen QiHangChuangShi Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳起航创势科技有限公司 www.qihcs.com.
 */
package com.lx.p2p.console.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆拦截器
 * 
 * @version 2017年7月18日下午5:42:49
 * @author zhuwenbin
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 在执行原请求之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("执行LoginInterceptor的preHandle方法");
		String url = request.getRequestURI();
		System.out.println("请求url = " + url);
		// 先判断session是否有用户信息，如果没有则跳到登陆页面
		HttpSession httpSession = request.getSession();
		Object object = httpSession.getAttribute("user");
		if (object == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		// 如果返回true继续执行原请求，否则终止请求
		return true;
	}

	/**
	 * 在执行原请求没返回ModelAndView之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("执行LoginInterceptor的postHandle方法");

	}

	/**
	 * 在执行原请求完成后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("执行LoginInterceptor的afterCompletion方法");

	}

}
