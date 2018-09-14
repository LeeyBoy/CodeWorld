/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.p2p.console.page.ProjectPage;
import com.lx.p2p.console.service.ProjectService;

/**
 * 借款项目业务控制器
 * 
 * @version 2018年7月31日下午2:45:58
 * @author zhuwenbin
 */
@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	/**
	 * 
	 * 借款项目列表
	 * 
	 * @version 2018年8月1日上午9:33:38
	 * @author zhuwenbin
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/project")
	public String getAllProject(ProjectPage page, Model model) {
		// 分页查询
		projectService.getAllProject(page);
		// 返回page
		model.addAttribute("page", page);
		// 返回视图
		return "project/project";
	}

	/**
	 * 
	 * 导出借款项目列表
	 * 
	 * @version 2018年8月1日上午9:33:51
	 * @author zhuwenbin
	 * @param ids
	 * @throws IOException
	 */
	@RequestMapping("/project/exportProject")
	public void exportProject(@RequestParam(value = "ids") int[] ids, HttpServletResponse response) throws IOException {
		// 导出到响应流中
		projectService.exportExcel(ids, response);
	}

	/**
	 * 
	 * 获取每日满标数据
	 * 
	 * @version 2018年8月1日下午12:42:31
	 * @author zhuwenbin
	 * @param type
	 *            比如三天、七日、一个月
	 * @return
	 */
	@RequestMapping("/project/getFullProjectData")
	@ResponseBody
	public Map<String, Object> getFullProjectData(int type) {
		return projectService.getFullProjectData(type);
	}
}
