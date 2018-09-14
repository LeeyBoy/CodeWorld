package com.lx.p2p.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.p2p.console.entity.SysFunction;
import com.lx.p2p.console.mapper.SysFunctionMapper;
import com.lx.p2p.console.service.SysFunctionService;

/**
 * 
 * 系统用户权限服务接口实现类
 * 
 * @version 2017年5月26日下午2:25:45
 * @author zhuwenbin
 */
@Service
public class SysFunctionServiceImpl implements SysFunctionService {

	@Autowired
	private SysFunctionMapper sysFunctionDao;

	@Override
	public List<SysFunction> findPermissionsByUserId(int userId) {
		return sysFunctionDao.findFunctionsByUserId(userId);
	}
}
