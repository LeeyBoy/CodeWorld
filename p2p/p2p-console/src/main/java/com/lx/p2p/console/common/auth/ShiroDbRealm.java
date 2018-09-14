package com.lx.p2p.console.common.auth;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lx.p2p.console.common.dto.ShiroUser;
import com.lx.p2p.console.entity.SysFunction;
import com.lx.p2p.console.entity.SysRole;
import com.lx.p2p.console.entity.SysUser;
import com.lx.p2p.console.service.SysFunctionService;
import com.lx.p2p.console.service.SysRoleService;
import com.lx.p2p.console.service.SysUserService;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * shiro认证
 * 
 * @version 2017年5月26日下午2:00:20
 * @author zhuwenbin
 */
public class ShiroDbRealm extends AuthorizingRealm {

	// private static final String ALGORITHM = "MD5";

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysFunctionService sysFunctionService;
	@Autowired
	private JedisCluster jedisCluster;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 认证通过从认证信息里面获取身份信息
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		// 权限信息体
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 加载用户的roles
		List<SysRole> roles = sysRoleService.findRolesByUserId(shiroUser.getUserId());
		for (SysRole role : roles) {
			authorizationInfo.addRole(role.getRoleName());
		}
		// 加载用户的permissions
		List<SysFunction> permissions = sysFunctionService.findPermissionsByUserId(shiroUser.getUserId());
		Set<String> stringPermissions = new HashSet<String>(permissions.size());
		for (SysFunction sysFunction : permissions) {
			if (StringUtils.isNotBlank(sysFunction.getPermission())) {
				stringPermissions.add(sysFunction.getPermission());
			}
		}
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
	}

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 从前台获取的登陆信息，包括用户名，密码
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		// 根据用户名查询用户信息
		SysUser sysUser = sysUserService.findSysUserByName(token.getUsername());
		if (sysUser == null) {
			// 没找到帐号
			throw new UnknownAccountException();
		}
		if (sysUser.getStatus() == 1) {
			// 帐号锁定
			throw new LockedAccountException();
		}
		if (sysUser.getStatus() == 2) {
			// 账号禁用
			throw new DisabledAccountException();
		}

		// 验证密码输入错误次数
		String username = token.getUsername();
		String password = new String(token.getPassword());
		String key = "p2p-console.auth.error-msg:" + username;

		// 判断密码是否正确
		if (sysUser.getPassword().equals(password)) {
			// 登陆成功，删除失败缓存
			jedisCluster.del(key);
		} else {
			// 当错误次数大于三次，则提示登陆错误次数过多
			String errorCountStr = jedisCluster.hget(key, "errorCount");
			String errorTimeStr = jedisCluster.hget(key, "errorTime");
			if (StringUtils.isNotBlank(errorCountStr)) {
				int errorCount = Integer.parseInt(errorCountStr);
				// 当密码错误次数<3时
				if (errorCount < 3) {
					// 错误次数加1
					errorCount++;
					jedisCluster.hset(key, "errorCount", errorCount + "");
					jedisCluster.hset(key, "errorTime", new Date().getTime() + "");
					jedisCluster.expire(key, 24 * 60 * 60);
					throw new IncorrectCredentialsException();
				}
				// 当密码错误次数>=3并且<6时
				if (errorCount >= 3 && errorCount < 6) {
					long errorTime = Long.parseLong(errorTimeStr);
					long now = new Date().getTime();
					long interval = now - errorTime;
					// 当密码错误次数>=3并且<6时，但密码仍然输入错误则把缓存中的密码错误次数加1
					// 判断密码是否正确
					if (!sysUser.getPassword().equals(password)) {
						// 错误次数加1
						errorCount++;
						jedisCluster.hset(key, "errorCount", errorCount + "");
						jedisCluster.hset(key, "errorTime", new Date().getTime() + "");
						jedisCluster.expire(key, 24 * 60 * 60);
						throw new AuthenticationException(
								"你输入的密码错误次数过多，请等待" + (2 * 60 * 60 * 1000 - interval) / (60 * 60 * 1000) + "小时");
					}
					// 当密码错误次数>=3并且<6时，但等待时间不足2小时，提示用户等待
					if (interval <= 2 * 60 * 60 * 1000) {
						throw new AuthenticationException(
								"你输入的密码错误次数过多，请等待" + (2 * 60 * 60 * 1000 - interval) / (60 * 60 * 1000) + "小时");
					}
					throw new ExcessiveAttemptsException();
				}
				if (errorCount >= 6) {
					// TODO 将用户状态改为冻结状态
					throw new AuthenticationException("由于你输入密码错误次数过多，账户已冻结！");
				}
			} else {
				// 第一次输入密码错误时
				jedisCluster.hset(key, "errorCount", 1 + "");
				jedisCluster.hset(key, "errorTime", new Date().getTime() + "");
				jedisCluster.expire(key, 24 * 60 * 60);
				throw new IncorrectCredentialsException();
			}
		}

		// 保存身份信息（用户信息），凭证信息（密码）还有域的名字
		return new SimpleAuthenticationInfo(
				new ShiroUser(sysUser.getUserId(), sysUser.getUserName(), sysUser.getRealName()), sysUser.getPassword(), // 密码
				getName() // realm name
		);
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	/*
	 * @PostConstruct public void initCredentialsMatcher() {
	 * HashedCredentialsMatcher matcher = new
	 * HashedCredentialsMatcher(ALGORITHM); setCredentialsMatcher(matcher); }
	 */

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
}
