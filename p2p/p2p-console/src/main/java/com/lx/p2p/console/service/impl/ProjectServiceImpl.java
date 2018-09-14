/*
 * Copyright (C) 2017 ShenZhen LiXiang Software Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市理想软件有限公司
 */
package com.lx.p2p.console.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.p2p.console.entity.Project;
import com.lx.p2p.console.mapper.ProjectMapper;
import com.lx.p2p.console.page.ProjectPage;
import com.lx.p2p.console.service.ProjectService;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * 借款项目服务接口实现类
 * 
 * @version 2018年7月31日下午2:33:26
 * @author zhuwenbin
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> getAllProject(ProjectPage page) {
		List<Project> projects = null;
		// 获取记录总数目
		int count = projectMapper.getProjectCount(page);
		// 判断
		if (count == 0) {
			return projects;
		}
		// 设置记录总数目，计算分页数据
		page.setTotalElements(count);
		// 分页查询
		projects = projectMapper.getAllProject(page);
		// 将分页数据设置到page
		page.setDatas(projects);

		return projects;
	}

	@Override
	public void exportExcel(int[] ids, HttpServletResponse response) throws IOException {
		// 查询项目列表
		List<Project> projects = projectMapper.getProjectByIds(ids);

		// 通过工具类创建writer，默认创建xls格式
		ExcelWriter writer = ExcelUtil.getWriter();
		// 一次性写出内容，使用默认样式
		writer.write(projects);
		// out为OutputStream，需要写出到的目标流，response为HttpServletResponse对象
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		// project.xlsx是弹出下载对话框的文件名，不能为中文，中文请自行编码
		response.setHeader("Content-Disposition", "attachment;filename=project.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out);
		// 关闭writer，释放内存
		writer.close();
	}

	@Override
	public Map<String, Object> getFullProjectData(int type) {
		Map<String, Object> dataSet = new HashMap<>();
		List<String> category = new ArrayList<>();
		List<Double> data = new ArrayList<>();

		//查询数据库
		List<Map<String, Object>> list = projectMapper.getFullProjectDataList(type);

		// 遍历
		for (Map<String, Object> map : list) {
			for (Entry<String, Object> entry : map.entrySet()) {
				// 获取借款金额
				if (entry.getKey().equals("borrowAmount")) {
					data.add((Double) entry.getValue());
				}
				// 获取满标时间
				if (entry.getKey().equals("releaseDate")) {
					category.add((String) entry.getValue());
				}
			}
		}

		// 存储
		dataSet.put("category", category);
		dataSet.put("data", data);

		return dataSet;
	}

}
