<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
 <h2><a href="${ctx }/index">起始页</a></h2>
 <ul>
  <li>
   <dl>
    <dt>资金管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="product_list.html">投资列表</a></dd>
    <dd><a href="product_detail.html">充值列表</a></dd>
    <dd><a href="recycle_bin.html">提现列表</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>借款管理</dt>
    <dd><a href="order_list.html">借款申请列表</a></dd>
    <dd><a href="${ctx }/project">借款项目列表</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>公告管理</dt>
    <dd><a href="user_list.html">公告列表</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>会员管理</dt>
    <dd><a href="setting.html">会员列表</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>系统管理</dt>
    <dd><a href="express_list.html">配置管理</a></dd>
   </dl>
  </li>
 </ul>
</aside>
    