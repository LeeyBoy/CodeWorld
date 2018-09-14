package com.lx.p2p.console.service;

import com.lx.p2p.console.entity.SysUser;

/**
 * 
 * 系统用户服务接口
 * 
 * @version 2017年5月26日下午2:24:14
 * @author zhuwenbin
 */
public interface SysUserService {

	/**
	 * 
	 * 根据用户名查找用户
	 * 
	 * @version 2017年5月26日下午2:24:35
	 * @author zhuwenbin
	 * @param userName
	 * @return
	 */
	public SysUser findSysUserByName(String userName);
}
