/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * QuartZ任务调度
 * 
 * 1.实现Job接口
 * 2.重写execute方法
 * 
 * @version 2018年8月2日下午2:28:07
 * @author zhuwenbin
 */
public class MyJob implements Job {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info(">>>>>正在使用QuartZ任务调度框架");
	}

}
