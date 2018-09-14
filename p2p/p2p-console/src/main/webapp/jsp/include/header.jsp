<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!--header-->
<header>
 <h1><img src="${ctx }/static/images/admin_logo.png"/></h1>
 <ul class="rt_nav">
  <li><a href="${ctx }" target="_blank" class="website_icon">站点首页</a></li>
  <li><a href="#" class="clear_icon">清除缓存</a></li>
  <li><a href="#" class="admin_icon">${sessionScope.user.userName }</a></li>
  <li><a href="#" class="set_icon">账号设置</a></li>
  <li><a href="${ctx }/logout" class="quit_icon">安全退出</a></li>
 </ul>
</header>