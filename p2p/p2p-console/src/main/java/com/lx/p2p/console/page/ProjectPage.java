/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.page;

import com.lx.p2p.console.common.page.Page;

/**
 * 借款项目分页类
 * 
 * @version 2018年7月31日下午2:30:27
 * @author zhuwenbin
 */
public class ProjectPage extends Page {

	// 借款状态
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
