<%@page import="com.scorm.utils.UserCommon"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0089)http://xue.vision-info.com/lms/?c=Home.CourseCatalog&m=course_info&code=5SXIANCHANGGUANLI -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="zlms">
<meta name="description" content="zlms">
<meta name="author" content="zlms">
<title>IT学源网-查看课程信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/user/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/user/css/ext.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/commons.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/artDialog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/iframeTools.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/dialog-common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/jquery.wtooltip.js"></script>
<script type="text/javascript">var web_path=WEB_PATH="http://xue.vision-info.com/lms/"; var web_ui_path="http://xue.vision-info.com/lms/view/templates/silverlight/";</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/portal.js"></script>
<script type="text/javascript">var lang_loading="数据正在载入中,请稍候...";var lang_ConfirmOperation="确认操作";var total_top_menu_item=8;
 var menus=new Array();menus[0]=1;menus[1]=2;menus[2]=3;menus[3]=4;menus[4]=5;menus[5]=6;menus[6]=7;menus[7]=8;</script>
<link
	href="${pageContext.request.contextPath }/user/css/evabootstrap-full.css"
	rel="stylesheet" type="text/css">

<link
	href="${pageContext.request.contextPath }/user/css/navbar-blue.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/user/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/bootstrap-dropdown.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/user/js/bootstrap-hover-dropdown.js"></script>

<script
	src="${pageContext.request.contextPath }/user/js/jquery.scrollUp.min.js"
	type="text/javascript"></script>

<style>
#scrollUp {
	bottom: 20px;
	right: 20px;
	height: 38px;
	width: 38px;
	background: url("img/top.png") no-repeat;
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
	var logout_uri = "/lms/?c=Index&m=logout&tc=lms3";
	var lang_ExitConfirm = "您确定要退出本平台吗?";
</script>

<script type="text/javascript">
var G_NOTIFICATION_INTERVAL = 60000;
var document_title="微兴企业在线学习平台";

	function confirmExit() {
		top.location.href = logout_uri;
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

<body class="typocn yui-skin-sam">
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
													<div style="cursor: move; display: block;"
														class="aui_title"></div>
													<a style="display: block;" class="aui_close"
														href="javascript:/*artDialog*/;">×</a>
												</div></td>
										</tr>
										<tr>
											<td style="display: none;" class="aui_icon"><div
													style="background: none repeat scroll 0% 0% transparent;"
													class="aui_iconBg"></div></td>
											<td style="width: auto; height: auto;" class="aui_main"><div
													style="padding: 20px 25px;" class="aui_content"></div></td>
										</tr>
										<tr>
											<td colspan="2" class="aui_footer"><div
													style="display: none;" class="aui_buttons"></div></td>
										</tr>
									</tbody>
								</table>
							</div></td>
						<td class="aui_e"></td>
					</tr>
					<tr>
						<td class="aui_sw"></td>
						<td class="aui_s"></td>
						<td style="cursor: se-resize;" class="aui_se"></td>
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
					class="brand"> <img src="img/logo1.png" onload="fixPNG(this)"
					height="55"> </a>
 
				<div class="nav-collapse collapse">
					<ul class="nav narbar-nav pull-left">
						<li class=""><a
							href="${pageContext.request.contextPath }/user/user_index.jsp">首页</a>
						</li>
						<li   class="dropdown active"><a data-toggle="dropdown"
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

						<li class="dropdown"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"> 资讯<strong
								class="caret"></strong> </a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath }/user/sysNotice.jsp">系统公告</a></li>
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
	
	<div class="clearfix"></div>
	<div style="top: 75px; margin-top: 75px;"></div>
	<div class="container">
		<link href="${pageContext.request.contextPath }/user/css/tabview0.css" rel="stylesheet"
			type="text/css" media="screen">
		<script src="${pageContext.request.contextPath }/user/js/jquery.raty.min.js"
			type="text/javascript"></script>

		<script type="text/javascript">
		var CourseApplyTobeJudge = "您已提交该课程的选修申请,请等待审核!";
		var confirm_text = "确认操作";
		var regeistercursesuccess = "您已成功注册选修了该课程!";
		var Operation_failed = "操作失败，如有任何疑问，请联系系统管理员！";
		var UNotAllowedRegisterCourse = "你不允许选修该课程,请联系课程管理员!";
		var RUSureToApplyCourse = "您确认选修该门课程吗";

		$(document).ready(
				function() {
					$("body").addClass("yui-skin-sam");
					$(".yui-content > div").fadeOut('fast').eq(0).fadeIn(
							'normal');

					$('#tab li').click(
							function() {
								$(this).addClass("selected").siblings()
										.removeClass("selected");
								var cur_idx = $('#tab li').index(this);
								$(".yui-content > div").eq(cur_idx).addClass(
										"selected").siblings().removeClass(
										"selected");
								$(".yui-content > div").fadeOut('normal').eq(
										cur_idx).fadeIn('normal');
							});
				});
	</script>
		<script type="text/javascript">
	
		function subscribe2Course(code) {
			var txt = RUSureToApplyCourse+'?';
			art.dialog({content : txt,
						icon : "question",
						lock : "true",
						window : "top",
						title : confirm_text,
						ok : function() {
							$.post(web_path+ '?c=Home.MyCourses&m=ajax_subscribe_course',{
												course_code : code,
												course_class_id : 0
											},
											function(data) {
												//alert(data+"---"+data=="EnrollToCourseSuccess");									
												if ($.trim(data) == "EnrollToCourseSuccessful") {
													art.dialog({
																content : CourseApplyTobeJudge,
																icon : "question",
																lock : "true",
																window : "top",
																title : confirm_text,
																ok : function() {
																	top.location.href = web_path
																			+ "?c=Home.MyCourses&m=course_home&cidReq="
																			+ code;
																},
																cancel : function() {
																}
															});
												} else if ($.trim(data) == "EnrollToCourseSuccess") {
													art.dialog({
																content : regeistercursesuccess,
																icon : "question",
																lock : "true",
																window : "top",
																title : confirm_text,
																ok : function() {
																	top.location.href = web_path
																			+ "?c=Home.MyCourses&m=course_home&cidReq="
																			+ code;
																},
																cancel : function() {
																}
															});
												} else if ($.trim(data) == "ErrorContactPlatformAdmin") {
													dialog_alert(Operation_failed);
												} else if ($.trim(data) == "YouArtNotAllowedToSubTheCourse") {
													dialog_alert(UNotAllowedRegisterCourse);
												}
											}, 'text');
						},
						cancel : function() {
						}
					});
		}
	</script>
		<style>
#tab em,#tab div {
	font-size: 13px;
}

.yui-navset {
	margin: 0px;
}

.xkside {
	width: 100%
}

.tableindex4 td,.desc {
	background-color: #FFF;
}

.tableindex4 {
	border: 0px;
}

.tableindex4 th {
	background: none;
	text-align: center;
	font-size: 24px;
	border-bottom-width: 0px;
	padding-bottom: 12px
}

.tableindex4 img {
	border-width: 0px;
}

.course_info {
	width: 100%
}

.course_info td {
	padding: 0 0 0 10px;
	text-align: left;
}

.course_info .tditem {
	font-weight: bold;
	width: 100px;
}

.course_info .tdval {
	width: 200px;
}

.list1 {
	padding-left: 0px;
}

.list1 li {
	width: 98%;
	line-height: 20px;
	text-align: left;
	padding-left: 0px;
}

.kelist a {
	padding-left: 4px;
}

.panel-body {
	padding: 10px
}

.panel-heading {
	padding: 4px 15px;
}

.panel-title {
	font-size: 14px
}

.kelist li {
	float: left;
	font-size: 14px;
	line-height: 28px;
	padding-left: 10px;
	width: 100%;
}

.nav-tabs>li>a { /* padding-left:11px;
	padding-right:11px; */
	
}

.typo .dl-horizontal dt {
	width: 80px;
}

.typo .dl-horizontal dd {
	margin-left: 95px;
	margin-bottom: 10px;
}

.nav-list {
	padding-left: 5px;
	padding-right: 5px;
}

.nav-list .divider {
	margin: 1px;
}
</style>



		<div class="container">

			<ul class="breadcrumb">
				<li><a href="http://xue.vision-info.com/lms/?c=Home.Home">首页</a><span
					class="divider">/</span>
				</li>
				<li><a
					href="http://xue.vision-info.com/lms/?c=Home.CourseCatalog&m=index">选课</a><span
					class="divider">/</span>
				</li>
				<li class="active">${courseinfo.courseName }</li>
			</ul>
			<div class="cls"></div>

			<div class="row">


				<div class="span9">
					<table width="99%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td valign="top"><div class="xkdecon">

										<div class="xkde">
											<div class="xkside">
												<div class="xkdeone">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0" class="tableindex4">
														<tbody>
															<tr>
																<th colspan="3"><b>${courseinfo.courseName }</b> <!-- <span style="float:right;"><a href="?c=Home.CourseCatalog&m=index" target="_top" ><b>返回</b></a></span> -->
																</th>
															</tr>
															<tr>
																<td valign="top" width="120"><div class="brimg">
																		<img class="img-rounded"
																			src="${pageContext.request.contextPath }/${courseinfo.coursePicture}"
																			onerror="javascript:load_default_image(this);"
																			style="width: 138px; height: 123px;" width="138"
																			height="123">
																	</div>
																</td>
																<td valign="top">
																	<div class="typo">
																		<dl class="dl-horizontal">
																			<dt>课程类型</dt>
																			<dd>${courseinfo.courseType }</dd>
																			<dt>课程有效期</dt>
																			<dd>${courseinfo.uploadTime }</dd>
																			
																			
																		</dl>
																	</div></td>

																<td valign="top">
																	<div class="typo">
																		<dl class="dl-horizontal">
																			
																			<dt>角色</dt>
																			<dd>
																				<span class="label label-warning">
																				<c:if test="${flag eq true }">
																					未报名
																				</c:if>
																				<c:if test="${flag eq false }">
																					已报名
																				</c:if>
																				</span>
																			</dd>
																			<dt>学习人数</dt>
																			<dd>${courseinfo.registerNumber }</dd>
																		</dl>
																	</div></td>
															</tr>
														</tbody>
													</table>
													<div class="clearfix"></div>
													
												</div>
												<div class="xkdetwo">
													<div style="text-align: center">
														<!-- <a href="#"><img src="{$web_ui_path}assets/images/inkecheng.jpg" width="76" height="28" /></a> -->
														<!-- 进入课程 -->
														<!-- <button onclick="top.location.href='{$web_path}?c=Home.MyCourses&m=course_home&cidReq={$course_info.code}';" class="btn0" name="sm1" type="button">{$lang.EnterInCourse}</button> -->
														<a class="btn btn-primary btn-large"
															href="${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=${courseinfo.courseId}&userId=${userinfo.userId}"><i
															class="icon-signin"></i> 
															<c:if test="${flag eq true }">
																注册课程
															</c:if>
															<c:if test="${flag eq false }">
																进入课程
															</c:if>
															</a>
													</div>
												</div>
												<div style="clear: both;"></div>

												<ul class="nav nav-tabs" style="margin-bottom: 15px;">
													<li class="active"><a
														href="http://xue.vision-info.com/lms/?c=Home.CourseCatalog&m=course_info&code=5SXIANCHANGGUANLI#CourseIntroduce"
														data-toggle="tab">课程简介</a>
													</li>
													
												</ul>
												<div id="myTabContent" class="tab-content"
													style="padding-left: 10px">
													<div class="tab-pane fade active in" id="CourseIntroduce">
														<div class="desc">${courseinfo.courseContent }</div>
													</div>
													
												</div>




											</div>
										</div>
									</div>
								</td>

							</tr>
						</tbody>
					</table>
				</div>




				<div class="span3">

					<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">猜您喜欢的课程</h3>
							</div>
							<div class="panel-body">
								<c:forEach var="course" items="${recommendList }" begin="0" end="4">
									<div class="inner2 ref_courses">
										<i class="icon-book"></i> <a href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${course.courseId }&userId=${userinfo.userId  }" title="${course.courseName }" >
										${course.courseName }</a>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="cls"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">相关课程</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-list">
								<s:iterator value="#request.list" var="sco">		
								<li><a
									href="lookCourseinfo?courseId=${sco.courseId }&userId=${userId  }"
									 >${sco.courseName }</a>
								</li>
								</s:iterator>
								<li class="divider"></li>
							</ul>
						</div>
					</div>
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