/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * 
 * @version 2018年7月13日下午6:37:34
 * @author zhuwenbin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 
	 * 处理授权异常
	 * 
	 * @version 2018年7月13日下午6:39:47
	 * @author zhuwenbin
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public String handleUnauthorizedException(UnauthorizedException exception) {
		return "error/unauthorized";
	}

	/**
	 * 
	 * 处理异常
	 * 
	 * @version 2018年7月13日下午6:39:47
	 * @author zhuwenbin
	 * @return
	 */
	@ExceptionHandler
	public String handleException(Exception exception) {
		log.error("系统异常：" + ExceptionUtils.getRootCauseMessage(exception));
		return "error/500";
	}
}
