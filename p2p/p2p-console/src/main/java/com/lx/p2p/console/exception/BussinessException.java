/*
 * Copyright (C) 2015 ShenZhen QiHangChuangShi Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳起航创势科技有限公司 www.qihcs.com.
 */
package com.lx.p2p.console.exception;

/**
 * 业务异常
 * 
 * @version 2017年7月18日下午3:04:00
 * @author zhuwenbin
 */
public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 578416272067122079L;

	public BussinessException() {
		super();
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

}
