package com.lx.p2p.console.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lx.p2p.console.entity.Project;
import com.lx.p2p.console.page.ProjectPage;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

	List<Project> getAllProject(ProjectPage page);

	int getProjectCount(ProjectPage page);

	List<Project> getProjectByIds(int[] ids);

	List<Map<String, Object>> getFullProjectDataList(@Param("type") int type);

}