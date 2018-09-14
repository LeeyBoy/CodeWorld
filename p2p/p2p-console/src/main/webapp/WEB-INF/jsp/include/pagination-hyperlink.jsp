<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<c:set var="ctx" value="${requestPath }" />

<aside class="paging">
	<c:choose>
		<c:when test="${page.firstPage }">
			<a href="javascript:void(0)" aria-label="Previous" class="disabled">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="${ctx }?number=${page.number - 1 }" aria-label="Previous"
				class="disabled"> <span aria-hidden="true">&laquo;</span>
			</a>
		</c:otherwise>
	</c:choose>
	<c:forEach var="x" begin="${page.beginPage}" end="${page.endPage}">
		<c:choose>
			<c:when test="${page.number == x}">
				<a class="current">${x }</a>
			</c:when>
			<c:otherwise>
				<a href="${ctx }?number=${x }">${x }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${page.lastPage }">
			<a href="javascript:void(0)" aria-label="Next" class="disabled">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="${ctx }?number=${page.number + 1 }" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
			</a>
		</c:otherwise>
	</c:choose>
</aside>
