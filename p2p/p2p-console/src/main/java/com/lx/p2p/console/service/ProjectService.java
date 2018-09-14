/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lx.p2p.console.entity.Project;
import com.lx.p2p.console.page.ProjectPage;

/**
 * 借款项目服务接口
 * 
 * @version 2018年7月31日下午2:31:36
 * @author zhuwenbin
 */
public interface ProjectService {

	/**
	 * 
	 * 分页获取借款项目列表
	 * 
	 * @version 2018年7月31日下午2:32:53
	 * @author zhuwenbin
	 * @param page
	 * @return
	 */
	List<Project> getAllProject(ProjectPage page);

	/**
	 * 
	 * 导出指定的id列表的数据到excel文件
	 * 
	 * @version 2018年8月1日上午9:41:05
	 * @author zhuwenbin
	 * @param ids
	 * @param response
	 */
	void exportExcel(int[] ids, HttpServletResponse response) throws IOException;

	/**
	 * 
	 * 根据指定时间获取满标的项目数据
	 * 
	 * @version 2018年8月1日下午12:19:17
	 * @author zhuwenbin
	 * @param type
	 * @return
	 */
	Map<String, Object> getFullProjectData(int type);
}
