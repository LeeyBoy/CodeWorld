<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/style.css">
<!--[if lt IE 9]>
<script src="${ctx }/static/js/html5.js"></script>
<![endif]-->
<script src="${ctx }/static/js/jquery.js"></script>
</head>
<body>
	<!--header-->
	<jsp:include page="../include/header.jsp"></jsp:include>

	<!--aside nav-->
	<jsp:include page="../include/aside.jsp"></jsp:include>

	<section class="rt_wrap content mCustomScrollbar">

		<div class="rt_content">
			<!--开始：以下内容则可删除，仅为素材引用参考-->
			<h1
				style="color: red; font-size: 20px; font-weight: bold; text-align: center;">系统异常！</h1>
		</div>
	</section>
</body>
</html>