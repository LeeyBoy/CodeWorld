package com.lx.p2p.console.service;

import java.util.List;

import com.lx.p2p.console.entity.SysRole;

/**
 * 
 * 系统用户角色服务接口
 * 
 * @version 2017年5月26日下午2:23:31
 * @author zhuwenbin
 */
public interface SysRoleService {

	/**
	 * 
	 * 根据用户id查找用户角色
	 * 
	 * @version 2017年5月26日下午2:23:44
	 * @author zhuwenbin
	 * @param userId
	 * @return
	 */
	public List<SysRole> findRolesByUserId(int userId);
}
