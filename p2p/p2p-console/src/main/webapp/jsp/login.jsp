<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆-P2P管理后台</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="${ctx }/static/js/jquery.js"></script>
<script src="${ctx }/static/js/verificationNumbers.js"></script>
<script src="${ctx }/static/js/Particleground.js"></script>
<script src="${ctx }/static/js/login.js"></script>
<script src="${ctx }/static/js/md5.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		/* //验证码
		createCode();
		//测试提交，对接程序删除即可
		$(".submit_btn").click(function(){
		  location.href="index.html";
		  }); */
	});
</script>
</head>
<body>
	<form id="login-form" action="${ctx }/goLogin" method="post">
		<dl class="admin_login">
			<dt>
				<strong>P2P后台管理系统</strong> <em>Management System</em>
			</dt>
			<dd class="user_icon">
				<input type="text" placeholder="账号" class="login_txtbx"
					name="userName" id="userName" />
			</dd>
			<dd class="pwd_icon">
				<input type="password" placeholder="密码" class="login_txtbx"
					name="password" id="password" />
			</dd>
			<dd class="val_icon">
				<div class="checkcode">
					<input type="text" id="J_codetext" placeholder="验证码" maxlength="4"
						class="login_txtbx" name="checkCode">
					<canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
				</div>
				<input type="button" value="验证码核验" class="ver_btn"
					onClick="validate();">
			</dd>
			<dd style="height: inherit;">
				<input type="checkbox" name="rememberMe" value="1"> 记住密码
			</dd>
			<c:if test="${errorMsg != null }">
				<dd style="height: inherit; color: red">${errorMsg }</dd>
			</c:if>
			<dd>
				<input type="submit" value="立即登陆" class="submit_btn" />
			</dd>
			<dd>
				<p>© 2017-2018 Zhuwenbin 版权所有</p>
				<p>深B2-20180224-1</p>
			</dd>
		</dl>
	</form>
</body>
</html>