package com.lx.p2p.console.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lx.p2p.console.common.mapper.BaseMapper;
import com.lx.p2p.console.entity.SysRole;


/**
 * 
 * 系统用户角色数据库访问接口
 * 
 * @version 2017年5月26日下午2:12:54
 * @author zhuwenbin
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 
	 * 根据用户id查找角色
	 * 
	 * @version 2017年5月26日下午2:26:29
	 * @author zhuwenbin
	 * @param userId
	 * @return
	 */
	public List<SysRole> findRolesByUserId(int userId);
}