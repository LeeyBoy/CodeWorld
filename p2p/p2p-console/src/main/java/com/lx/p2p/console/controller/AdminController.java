/*
 * Copyright (C) 2015 ShenZhen QiHangChuangShi Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳起航创势科技有限公司 www.qihcs.com.
 */
package com.lx.p2p.console.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lx.p2p.console.entity.SysUser;

/**
 * 首页控制器
 * 
 * @version 2017年7月25日下午6:47:55
 * @author zhuwenbin
 */
@Controller
public class AdminController {

	/**
	 * 
	 * 登陆页面
	 * 
	 * @version 2017年7月25日下午6:48:52
	 * @author zhuwenbin
	 * @return
	 */
	@GetMapping({ "/", "/login" })
	public String login() {
		return "login";
	}

	/**
	 * 
	 * 登陆
	 * 
	 * @version 2017年7月28日下午4:00:59
	 * @author zhuwenbin
	 * @return
	 */
	@PostMapping("/goLogin")
	public String goLogin(@Valid SysUser sysUser, String checkCode, String rememberMe, BindingResult bindingResult,
			HttpSession session, Model model) {
		// 错误信息
		String errorMsg = "";
		// 判断输入用户名、密码是否符合规则
		if (bindingResult.hasErrors()) {
			errorMsg = bindingResult.getFieldError().getDefaultMessage();
			// 返回错误信息，提示用户
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}
		// 获取登陆主体
		Subject subject = SecurityUtils.getSubject();
		// 组装口令
		UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword(),
				"1".equals(rememberMe) ? true : false);
		try {
			// 主体携带口令进行登陆
			subject.login(token);
		} catch (UnknownAccountException e) {
			errorMsg = "用户不存在";
		} catch (LockedAccountException e) {
			errorMsg = "用户已冻结";
		} catch (DisabledAccountException e) {
			errorMsg = "用户已禁用";
		} catch (IncorrectCredentialsException e) {
			errorMsg = "用户名/密码错误";
		} catch (ExpiredCredentialsException e) {
			errorMsg = "用户身份已过期";
		} catch (ExcessiveAttemptsException e) {
			errorMsg = "登录失败次数过多";
		} catch (AuthenticationException e) {
			errorMsg = e.getMessage();
		}
		if (StringUtils.isNotBlank(errorMsg)) {
			// 返回错误信息，提示用户
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}

		// 保存用户信息到session中
		sysUser.setPassword(null);
		session.setAttribute("user", sysUser);

		return "redirect:/index";
	}

	/**
	 * 
	 * 首页
	 * 
	 * @version 2017年7月28日下午12:27:03
	 * @author zhuwenbin
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	/**
	 * 
	 * 获得主体并退出
	 * 
	 * @version 2017年7月28日下午6:23:23
	 * @author zhuwenbin
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 获得主体并退出
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

}
