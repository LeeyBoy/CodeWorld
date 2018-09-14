package com.lx.p2p.console.common.dto;

import java.io.Serializable;

/**
 * 
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * 
 * @version 2017年5月26日下午2:06:07
 * @author zhuwenbin
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 7466170063138844543L;

	private Integer userId;

	private String userName;

	private String realName;

	public ShiroUser() {
	}

	public ShiroUser(Integer userId, String userName, String realName) {
		this.userId = userId;
		this.userName = userName;
		this.realName = realName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return realName;
	}
}
