<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款列表-P2P后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/style.css">
<!--[if lt IE 9]>
<script src="${ctx }/static/js/html5.js"></script>
<![endif]-->
<script src="${ctx }/static/js/jquery.js"></script>
<script src="${ctx }/static/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>
	(function($) {
		$(window).load(
				function() {

					$("a[rel='load-content']").click(
							function(e) {
								e.preventDefault();
								var url = $(this).attr("href");
								$.get(url, function(data) {
									$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
									//scroll-to appended content 
									$(".content").mCustomScrollbar("scrollTo",
											"h2:last");
								});
							});

					$(".content").delegate(
							"a[href='top']",
							"click",
							function(e) {
								e.preventDefault();
								$(".content").mCustomScrollbar("scrollTo",
										$(this).attr("href"));
							});

				});
	})(jQuery);
	
	$(document).ready(function(){
		//导出excel
		$("#exportProject").click(function(){
			//获取选择的项目id
			var ids = getIds();
			if(ids != null){
				document.location = "${ctx}/project/exportProject?ids=" + ids;
			}
		});
		//全选
		$("#ids").change(function(){
			$("input[name='ids']").each(function(index, item){
				if($("#ids").prop("checked")){
					$(item).prop("checked","checked");
				}else{
					$(item).prop("checked",false);
				}
			});
		});
	});
	
	//获取选择的项目
	function getIds(){
		var ids = $("input[name='ids']:checked");
		if(ids.length == 0){
			alert("请选择导出的项目！");
			return null;
		}
		var array = new Array();
		//遍历
		ids.each(function(index, item){
			array[index] = $(item).val();
		});
		return array;
	}
</script>
</head>
<body>
	<!-- 页头 -->
	<jsp:include page="../include/header.jsp"></jsp:include>
	<!-- 侧边栏 -->
	<jsp:include page="../include/aside.jsp"></jsp:include>
	<!-- 主要内容 -->
	<section class="rt_wrap content mCustomScrollbar">
		<div class="rt_content">
			<form action="${ctx }/project" method="post" name="form1">
				<div class="page_title">
					<h2 class="fl">借款项目列表</h2>
					<a href="${ctx }/project/addProject" class="fr top_rt_btn add_icon">添加借款项目</a>
					<a href="javascript:void(0)" id="exportProject" class="fr top_rt_btn add_icon">导出借款项目</a>
				</div>
				<section class="mtb">
					<input type="text" class="textbox textbox_225"
						placeholder="输入借款状态" name="status" value="${page.status }"/> 
					<input type="submit" value="查询"
						class="group_btn" />
				</section>
				<table class="table">
					<tr>
						<th>选择 <input type="checkbox" id="ids"/></th>
						<th>标题</th>
						<th>借款金额</th>
						<th>年利率</th>
						<th>借款期限</th>
						<th>回款方式</th>
						<th>借款进度</th>
						<th>状态</th>
						<th>项目类型</th>
						<th>发标日期</th>
						<th>操作</th>
					</tr>
					<c:forEach var="project" items="${page.datas }">
						<tr>
							<td>
								<input type="checkbox" name="ids" value="${project.projectId }"/>
							</td>
							<td class="center">${project.title }</td>
							<td class="center"><fmt:formatNumber type="CURRENCY" value="${project.borrowingAmount }"/></td>
							<td class="center"><fmt:formatNumber type="PERCENT" value="${project.yearRate }"/></td>
							<td class="center"><strong>${project.loanLife }</strong></td>
							<td class="center">${project.returnType }</td>
							<td class="center"><fmt:formatNumber type="PERCENT" value="${project.loanSchedule }"/></td>
							<td class="center">${project.status }</td>
							<td class="center">${project.investmentType }</td>
							<td class="center"><fmt:formatDate type="both" value="${project.releaseDate }"/></td>
							<td class="center"><a href="http://www.deathghost.cn"
								title="预览" class="link_icon" target="_blank">&#118;</a> <a
								href="${ctx }/project/updateProject" title="编辑" class="link_icon">&#101;</a>
								<a href="${ctx }/project/deleteProject" title="删除" class="link_icon">&#100;</a></td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="../include/pagination.jsp"></jsp:include>
			</form>
		</div>
	</section>
</body>
</html>