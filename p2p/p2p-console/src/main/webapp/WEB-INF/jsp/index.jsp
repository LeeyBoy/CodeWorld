<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页-P2P管理后台</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/style.css">
<style>
.dataStatistic{width:400px;height:200px;border:1px solid #ccc;margin:0 auto;margin:10px;overflow:hidden}
#cylindrical{width:400px;height:200px;margin-top:10px}
#line{width:400px;height:200px;margin-top:10px}
#pie{width:400px;height:200px;margin-top:10px}
</style>
<!--[if lt IE 9]>
<script src="${ctx }/static/js/html5.js"></script>
<![endif]-->
<script src="${ctx }/static/js/jquery.js"></script>
</head>
<body>
<!--header-->
<jsp:include page="include/header.jsp"></jsp:include>

<!--aside nav-->
<jsp:include page="include/aside.jsp"></jsp:include>

<section class="rt_wrap content mCustomScrollbar">

 <div class="rt_content">
	<!--统计图-->
	<section style="overflow:hidden">
	    <!--柱状图-->
	    <div class="dataStatistic fl">
	     <div id="cylindrical">
	     </div>
	    </div>
	    <!--线性图-->
	    <div class="dataStatistic fl">
	     <div id="line">
	     </div>
	    </div>
	    <!--饼状图-->
	    <div class="dataStatistic fl">
	     <div id="pie">
	     </div>
	    </div>
	</section>
     <!--点击加载-->
     <script>
     $(document).ready(function(){
		$("#loading").click(function(){
			$(".loading_area").fadeIn();
             $(".loading_area").fadeOut(1500);
			});
		 });
     </script>
     <section class="loading_area">
      <div class="loading_cont">
       <div class="loading_icon"><i></i><i></i><i></i><i></i><i></i></div>
       <div class="loading_txt"><mark>数据正在加载，请稍后！</mark></div>
      </div>
     </section>
     <!--结束加载-->
 </div>
</section>
<script type="text/javascript" src="${ctx }/static/js/echarts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var line = echarts.init(document.getElementById('line'));
	// 异步加载数据
	$.get('${ctx }/project/getFullProjectData.json',{'type':7}).done(function (data) {
	    // 填入数据
	    line.setOption({
	    	title: {
	            text: '每日满标金额'
	        },
	        tooltip : {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'cross',
	                label: {
	                    backgroundColor: '#6a7985'
	                }
	            }
	        },
	        toolbox: {
	            feature: {
	                saveAsImage: {}
	            }
	        },
	        xAxis: {
	            type: 'category',
	            data: data.category
	        },
	        yAxis: {
	            type: 'value'
	        },
	        grid: {
	        	left: 55
	        },
	        series: [{
	            data: data.data,
	            type: 'line'
	        }]
	    });
	});
});

//柱状图
var cylindrical = echarts.init(document.getElementById('cylindrical'));
// 指定图表的配置项和数据
var option = {
    title: {
        text: '产品销量'
    },
    tooltip: {},
    legend: {
        data:['成交量']
    },
    xAxis: {
        data: ["车易贷","房易贷","赎楼贷","债权贷"]
    },
    yAxis: {},
    series: [{
        name: '成交量',
        type: 'bar',
        data: [5, 20, 36, 10]
    }]
};
// 使用刚指定的配置项和数据显示图表。
cylindrical.setOption(option);

/* //线性图
var line = echarts.init(document.getElementById('line33'));
// 指定图表的配置项和数据
var option = {
	title: {
        text: '成交额'
    },
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line',
        smooth: true
    }]
};
// 使用刚指定的配置项和数据显示图表。
line.setOption(option); */

//饼状图
var pie = echarts.init(document.getElementById('pie'));
// 指定图表的配置项和数据
var option = {
	title: {
        text: '来访分析'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'right',
        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series: [
        {
            name:'访问来源',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
pie.setOption(option);

</script>
</body>
</html>