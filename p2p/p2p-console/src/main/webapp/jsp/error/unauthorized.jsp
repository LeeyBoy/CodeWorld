<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>P2P后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/style.css">
<!--[if lt IE 9]>
<script src="${ctx }/static/js/html5.js"></script>
<![endif]-->
<style>
.dataStatistic {
	width: 400px;
	height: 200px;
	border: 1px solid #ccc;
	margin: 0 auto;
	margin: 10px;
	overflow: hidden
}

#cylindrical {
	width: 400px;
	height: 200px;
	margin-top: -15px
}

#line {
	width: 400px;
	height: 200px;
	margin-top: -15px
}

#pie {
	width: 400px;
	height: 200px;
	margin-top: -15px
}
</style>
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
</script>
</head>
<body>
	<!--header-->
	<header>
		<h1>
			<img src="${ctx }/static/images/admin_logo.png" />
		</h1>
		<ul class="rt_nav">
			<li><a href="http://www.deathghost.cn" target="_blank"
				class="website_icon">站点首页</a></li>
			<li><a href="#" class="clear_icon">清除缓存</a></li>
			<li><a href="#" class="admin_icon">${user.userName }</a></li>
			<li><a href="#" class="set_icon">账号设置</a></li>
			<li><a href="${ctx }/logout" class="quit_icon">安全退出</a></li>
		</ul>
	</header>
	<!--aside nav-->
	<!--aside nav-->
	<aside class="lt_aside_nav content mCustomScrollbar">
		<h2>
			<a href="index.html">起始页</a>
		</h2>
		<ul>
			<li>
				<dl>
					<dt>常用布局示例</dt>
					<!--当前链接则添加class:active-->
					<dd>
						<a href="product_list.html" class="active">商品列表示例</a>
					</dd>
					<dd>
						<a href="product_detail.html">商品详情示例</a>
					</dd>
					<dd>
						<a href="recycle_bin.html">商品回收站示例</a>
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>订单信息</dt>
					<dd>
						<a href="order_list.html">订单列表示例</a>
					</dd>
					<dd>
						<a href="order_detail.html">订单详情示例</a>
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>会员管理</dt>
					<dd>
						<a href="user_list.html">会员列表示例</a>
					</dd>
					<dd>
						<a href="user_detail.html">添加会员（详情）示例</a>
					</dd>
					<dd>
						<a href="user_rank.html">会员等级示例</a>
					</dd>
					<dd>
						<a href="adjust_funding.html">会员资金管理示例</a>
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>基础设置</dt>
					<dd>
						<a href="setting.html">站点基础设置示例</a>
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>配送与支付设置</dt>
					<dd>
						<a href="express_list.html">配送方式</a>
					</dd>
					<dd>
						<a href="pay_list.html">支付方式</a>
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>在线统计</dt>
					<dd>
						<a href="discharge_statistic.html">流量统计</a>
					</dd>
					<dd>
						<a href="sales_volume.html">销售额统计</a>
					</dd>
				</dl>
			</li>
			<li>
				<p class="btm_infor">© DeathGhost.cn 版权所有</p>
			</li>
		</ul>
	</aside>

	<section class="rt_wrap content mCustomScrollbar">

		<div class="rt_content">
			<!--开始：以下内容则可删除，仅为素材引用参考-->
			<h1 style="color: red; font-size: 20px; font-weight: bold; text-align: center;">没有该权限，请联系管理员！</h1>
		</div>
	</section>
</body>
</html>