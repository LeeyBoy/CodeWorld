package com.lx.p2p.console.mapper;

import org.springframework.stereotype.Repository;

import com.lx.p2p.console.common.mapper.BaseMapper;
import com.lx.p2p.console.entity.SysUser;

/**
 * 
 * 系统用户数据库访问接口
 * 
 * @version 2017年5月26日下午2:13:40
 * @author zhuwenbin
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 
	 * 根据用户名查找用户
	 * 
	 * @version 2017年5月26日下午2:26:50
	 * @author zhuwenbin
	 * @param userName
	 * @return
	 */
	public SysUser findSysUserByName(String userName);
}