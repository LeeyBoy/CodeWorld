package com.lx.p2p.console.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lx.p2p.console.common.mapper.BaseMapper;
import com.lx.p2p.console.entity.SysFunction;


/**
 * 
 * 系统用户职能数据库访问接口
 * 
 * @version 2017年5月26日下午2:12:28
 * @author zhuwenbin
 */
@Repository
public interface SysFunctionMapper extends BaseMapper<SysFunction> {

	/**
	 * 
	 * 根据用户id查找权限
	 * 
	 * @version 2017年5月26日下午2:26:20
	 * @author zhuwenbin
	 * @param userId
	 * @return
	 */
	public List<SysFunction> findFunctionsByUserId(int userId);
}