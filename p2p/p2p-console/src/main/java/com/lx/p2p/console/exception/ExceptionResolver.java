/*
 * Copyright (C) 2015 ShenZhen QiHangChuangShi Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳起航创势科技有限公司 www.qihcs.com.
 */
package com.lx.p2p.console.exception;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常解析器
 * 
 * @version 2017年7月18日下午4:21:27
 * @author zhuwenbin
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		// 从request获取所有的参数名
		Enumeration<String> enumeration = request.getParameterNames();
		// 遍历参数名列表
		for (Enumeration<String> e = enumeration; e.hasMoreElements();) {
			String key = e.nextElement();
			// 根据key从request获取value
			String value = request.getParameter(key);
			System.out.println(key + " = " + value);
		}
		System.out.println("异常信息：" + ex.getMessage());
		if (ex instanceof BussinessException) {
			modelAndView.setViewName("error/bussinessError");
			modelAndView.addObject("errorMsg", "请联系客服");
		} else if (ex instanceof NullPointerException) {
			modelAndView.setViewName("error/500");
			modelAndView.addObject("errorMsg", "请联系管理员");
		}
		return modelAndView;
	}

}
