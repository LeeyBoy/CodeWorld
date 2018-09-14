package com.lx.p2p.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.p2p.console.entity.SysRole;
import com.lx.p2p.console.mapper.SysRoleMapper;
import com.lx.p2p.console.service.SysRoleService;

/**
 * 
 * 系统用户角色服务接口实现类
 * 
 * @version 2017年5月26日下午2:25:21
 * @author zhuwenbin
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleDao;

	@Override
	public List<SysRole> findRolesByUserId(int userId) {
		return sysRoleDao.findRolesByUserId(userId);
	}
}
