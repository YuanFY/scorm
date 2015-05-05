<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
request.setCharacterEncoding("UTF-8");

%>

<!DOCTYPE html>
<!-- saved from url=(0065)http://xue.vision-info.com/lms/?c=Home.InfoCenter&m=bulletin_list -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="zlms">
<meta name="description" content="zlms">
<meta name="author" content="zlms">
<title>IT学源网-系统公告</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/ext.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/commons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/idialog-4.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/artDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/iframeTools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/dialog-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/jquery.wtooltip.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/portal.js"></script>
<script type="text/javascript">var lang_loading="数据正在载入中,请稍候...";var lang_ConfirmOperation="确认操作";var total_top_menu_item=8;
 var menus=new Array();menus[0]=1;menus[1]=2;menus[2]=3;menus[3]=4;menus[4]=5;menus[5]=6;menus[6]=7;menus[7]=8;</script>
<link href="${pageContext.request.contextPath}/user/css/evabootstrap-full.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/user/css/navbar-blue.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/user/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/bootstrap-hover-dropdown.js"></script>

<script src="${pageContext.request.contextPath }/user/js/jquery.scrollUp.min.js" type="text/javascript"></script>
<style>
#scrollUp {
	bottom: 20px;
	right: 20px;
	height: 38px;
	width: 38px;
	background:
		url("http://xue.vision-info.com/lms/assets/js/jquery-plugins/top.png")
		no-repeat;
}

.breadcrumb li {
	display: inline;
}

.typocn button {
	line-height: 1.4;
}

.table thead th {
	background-color: #F5F5F5;
	text-align: center;
}

.navbar .brand {
	padding: 6px 20px;
}

.form select,.form textarea,.form input[type="text"],.form input[type="password"],.form input[type="submit"]
	{
	margin-bottom: 0;
}
</style>
<script type="text/javascript">
	var logout_uri = "#";
	var lang_ExitConfirm = "您确定要退出本平台吗?";
</script>

<script type="text/javascript">
var G_NOTIFICATION_INTERVAL = 60000;
var document_title="IT学源网";

	function confirmExit() {
		top.location.href = logout_uri;
		/* art.dialog({
			content : lang_ExitConfirm,
			icon : "question",
			lock : "true",
			window : "top",
			title : lang_ConfirmOperation,
			ok : function() {
				top.location.href = logout_uri;
			},
			cancel : function() {
			}
		}); */
	}

	
	$(document).ready(function() {
		$('.dropdown-toggle').dropdown();
		$("#confirmExit").click(function() {confirmExit();});

		 if (typeof (G_NOTIFICATION_INTERVAL) != 'undefined'){
		        check_notifications();
		        setInterval('check_notifications()', G_NOTIFICATION_INTERVAL);
		 }
	});
</script>

<script>
	$(document).ready(function() {
		$.scrollUp({
			scrollName : 'scrollUp', // Element ID
			topDistance : '300', // Distance from top before showing element (px)
			topSpeed : 300, // Speed back to top (ms)
			animation : 'fade', // Fade, slide, none
			animationInSpeed : 200, // Animation in speed (ms)
			animationOutSpeed : 200, // Animation out speed (ms)
			scrollText : '', // Text for element
			activeOverlay : false
		// Set CSS color to display scrollUp active point, e.g '#00FFFF'
		});
	});
</script>
</head>

<body class="typocn">
	<div class="" style="display: none; position: absolute;">
		<div class="aui_outer">
			<table class="aui_border">
				<tbody>
					<tr>
						<td class="aui_nw"></td>
						<td class="aui_n"></td>
						<td class="aui_ne"></td>
					</tr>
					<tr>
						<td class="aui_w"></td>
						<td class="aui_c"><div class="aui_inner">
								<table class="aui_dialog">
									<tbody>
										<tr>
											<td colspan="2" class="aui_header"><div
													class="aui_titleBar">
													<div class="aui_title"
														style="cursor: move; display: block;"></div>
													<a class="aui_close" href="javascript:/*artDialog*/;"
														style="display: block;">×</a>
												</div>
											</td>
										</tr>
										<tr>
											<td class="aui_icon" style="display: none;"><div
													class="aui_iconBg"
													style="background-image: none; background-position: initial initial; background-repeat: initial initial;"></div>
											</td>
											<td class="aui_main" style="width: auto; height: auto;"><div
													class="aui_content" style="padding: 20px 25px;"></div>
											</td>
										</tr>
										<tr>
											<td colspan="2" class="aui_footer"><div
													class="aui_buttons" style="display: none;"></div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>
						<td class="aui_e"></td>
					</tr>
					<tr>
						<td class="aui_sw"></td>
						<td class="aui_s"></td>
						<td class="aui_se" style="cursor: se-resize;"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- header begin-->
		<div class="clearfix"></div>
	<!-- 加上 navbar-inverse,去掉navbar-inner即为blue风格-->
	<div class="navbar   navbar-inverse navbar-fixed-top">
		<div>
			<div class="container">

				<a href="${pageContext.request.contextPath }/user/user_index.jsp"
					class="brand"> <img src="${pageContext.request.contextPath }/user/img/logo1.png" onload="fixPNG(this)"
					height="55"> </a>

				<div class="nav-collapse collapse">
					<ul class="nav narbar-nav pull-left">
						<li class=""><a
							href="${pageContext.request.contextPath }/user/user_index.jsp">首页</a>
						</li>
						<li class="dropdown"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 选课<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath }/user/onlineCourse.action">在线课程选课与报名</a>
								</li>
							</ul></li>
						<li class="dropdown"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 学习<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath }/user/studyCourse.action">我正在学习的课程</a>
								</li>
								<li><a
									href="${pageContext.request.contextPath }/user/endCourse.action">我已完成的在线课程</a>
								</li>
								<li><a
									href="${pageContext.request.contextPath }/user/lastCourse.action">我已过期的在线课程</a>
								</li>
							</ul></li>

						

						<li class="dropdown"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 档案<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li><a href="studyRecord.action">我的课程学习档案</a></li>
							</ul></li>

						<li class="dropdown active"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 资讯<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath }/getNoticeList.action">系统公告</a></li>
							</ul></li>
						<li class="dropdown"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 关于我<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath }/myCenterUserinfo.action?userId=${userinfo.userId}">
										<i class="icon-user"></i> 
										个人资料
									</a>
								</li>
								
								<li>
									<a href="${pageContext.request.contextPath }/myCenterPwdUpdate.action?userId=${userinfo.userId}">
										<i class="icon-random"></i> 
										修改密码
									</a>
								</li>
								<s:if test="#session.userinfo.isAdmin == 1">
									<li><a href="${pageContext.request.contextPath }/myCenterScoUpload.action" >
										<i class="icon-picture"></i> 
										上传课件
										</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/myCenterSco.action"  >
										<i class="icon-picture"></i> 
										课件管理
										</a>
									</li>
								
								</s:if>
								<li>
									<a id="confirmExit" href="javascript:window.opener=null;window.open('','_self');window.close();">
										<i class="icon-power-off"></i> 
										退出
									</a>
								</li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
		<!-- header end-->
	<div class="clearfix"></div>
	<div style="top: 75px; margin-top: 75px;"></div>
	<div class="container">
		<div class="main">

			<ul class="breadcrumb">
				<li><a href="user_index.jsp">首页</a><span
					class="divider">/</span>
				</li>
				<li>资讯<span class="divider">/</span>
				</li>
				<li class="active">系统公告</li>
			</ul>
			<div class="cls"></div>


			<div class="mcontent">

				<div class="well well-small">
					<form action="noticeSearch" method="post" class="form form-search" name="theForm" id="theForm">
						<div class="input-append">
							<input type="text" name="noticeTitle" class="span3 search-query"
								onfocus="this.select();" value="">
							<button class="btn" type="submit">搜 索</button>
						</div>
					</form>
				</div>
				<div class="cls"></div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 20px"></th>
							<th>标题</th>
							<th>发布时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.noticeinfo" var="notice">
							<tr>
								<td><img src="${pageContext.request.contextPath}/user/img/announcement_22.png" class="title_icon2">
								</td>
								<td style="text-align:center;">
									<a target="_black" href="getNoticeInfo.action?noticeId=${notice.noticeId }" title="${notice.noticeTitle }">${notice.noticeTitle }</a>
								</td>
								<td style="text-align:center;">${fn:substring(notice.noticeTime, 0, 19) }</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="cls"></div>
				<div class="pagination">
					<ul>
					</ul>
					<span class="f_l f6" style="margin-right: 10px; float: right">记录总数:<b>${noticeSize }</b>
					</span>
				</div>




			</div>



		</div>
	</div>
	<div class="clearfix"></div>
	<!--footer start-->
		<footer class="footer">
		<div class="container" style="text-align: center;">


			<ul class="footer-links">
				<li>技术支持:BFS技术团队</li>
				<li>邮箱:1018654313@qq.com</li>
				<li>电话:18874442871</li>
			</ul>

			<p>©2014 All IT学源网 Rights Reserved 版权所有</p>
		</div>
		<div class="clearfix"></div>

	</footer>

	<a id="scrollUp"
		href="http://xue.vision-info.com/lms/?c=Home.CourseCatalog&m=course_info&code=5SXIANCHANGGUANLI#top"
		title="" style="display: none; position: fixed; z-index: 2147483647;"></a>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"></div>
</body>
</html>