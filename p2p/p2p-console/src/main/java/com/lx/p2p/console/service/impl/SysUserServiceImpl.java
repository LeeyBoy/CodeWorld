package com.lx.p2p.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.p2p.console.entity.SysUser;
import com.lx.p2p.console.mapper.SysUserMapper;
import com.lx.p2p.console.service.SysUserService;

/**
 * 
 * 系统用户服务接口实现类
 * 
 * @version 2017年5月26日下午2:24:53
 * @author zhuwenbin
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserDao;

	@Override
	public SysUser findSysUserByName(String userName) {
		return sysUserDao.findSysUserByName(userName);
	}
}
