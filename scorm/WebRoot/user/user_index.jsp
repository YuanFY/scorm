<%@page import="com.scorm.vo.Courseinfo"%>
<%@page import="com.scorm.utils.UserUtils"%>
<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="zlms">
<meta name="description" content="zlms">
<meta name="author" content="zlms">
<title>IT学源网-首页</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/ext.css">
<script type="text/javascript" src="js/commons.js"></script>
<script type="text/javascript" src="js/jquery-1.js"></script>
<link href="css/idialog.css" rel="stylesheet">
<script type="text/javascript" src="js/artDialog.js"></script>
<script type="text/javascript" src="js/iframeTools.js"></script>
<script type="text/javascript" src="js/dialog-common.js"></script>
<script type="text/javascript" src="js/jquery_002.js"></script>
<script type="text/javascript">
	var web_path = WEB_PATH = " ";
	var web_ui_path = "http://demo.zlms.org/lms/view/templates/silverlight/";
</script>
<script type="text/javascript" src="js/portal.js"></script>
<script type="text/javascript">
	var lang_loading = "数据正在载入中,请稍候...";
	var lang_ConfirmOperation = "确认操作";
	var total_top_menu_item = 8;
	var menus = new Array();
	menus[0] = 1;
	menus[1] = 8;
	menus[2] = 3;
	menus[3] = 4;
	menus[4] = 2;
	menus[5] = 6;
	menus[6] = 5;
	menus[7] = 7;
</script>
<link href="css/tabview0.css" rel="stylesheet" type="text/css"
	media="screen">

<link href="css/evabootstrap-full.css" rel="stylesheet" type="text/css">

<link href="css/navbar-blue.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
<!--[if IE 7]>
  <link rel="stylesheet" href="http://demo.zlms.org/lms/assets/bootstrap/font-awesome/css/font-awesome-ie7.min.css">
<![endif]-->
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="js/bootstrap-hover-dropdown.js"></script>

<script src="js/jquery.js" type="text/javascript"></script>
<style>
#scrollUp {
	bottom: 20px;
	right: 20px;
	height: 38px;
	width: 38px;
	background:
		url("http://demo.zlms.org/lms/assets/js/jquery-plugins/top.png")
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
	var logout_uri = "/lms/?c=Index&m=logout&tc=lms3";
	var lang_ExitConfirm = "您确定要退出本平台吗?";
</script>

<script type="text/javascript">
	var G_NOTIFICATION_INTERVAL = 60000;
	var document_title = "微兴企业在线学习平台";

	function confirmExit() {
		top.location.href = logout_uri;
	}

	$(document).ready(function() {
		$('.dropdown-toggle').dropdown();
		$("#confirmExit").click(function() {
			confirmExit();
		});

		if (typeof (G_NOTIFICATION_INTERVAL) != 'undefined') {
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
					class="brand"> <img src="${pageContext.request.contextPath }/user/img/logo1.png" onload="fixPNG(this)"
					height="55"> </a>


				<div class="nav-collapse collapse">
					<ul class="nav narbar-nav pull-left">
						<li class="active"><a
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

						<li class="dropdown"><a data-toggle="dropdown"
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
		<link href="css/tabview0.css" rel="stylesheet" type="text/css"
			media="screen">
		<style>
.loading_txt {
	font-style: italic;
	font-size: 16px;
	padding: 8px 0 0 10px
}

.list1 {
	margin-top: 0px;
	margin-left: 0px;
	padding-bottom: 15px;
}

.list1 li {
	width: 96%;
	overflow: hidden;
}

.panel-body {
	padding: 10px
}

.panel-heading {
	padding: 4px 15px;
}

.panel-title {
	font-size: 14px;
}

.nav {
	margin-bottom: 10px;
}

.nav-tabs>li {
	margin-bottom: 0;
}

.nav-tabs>li>a { /* padding-left:11px;
	padding-right:11px; */
	
}

.panel-more {
	float: right;
	padding-right: 5px;
	font-size: 12px;
	display: inline;
}

.table {
	margin-bottom: 0;
}

.nav-pills .open .dropdown-toggle,.nav>li.dropdown.open.active>a:hover {
	color: #000;
}
</style>

		<script type="text/javascript">
			$(document).ready(function() {
				$("body").addClass("yui-skin-sam");
				$("a,img").wTooltip({
					fadeIn : "fast",
					offsetY : 6,
					className : 'wTooltip'
				});
			});

			$(document)
					.ready(
							function() {
								var default_url = "/lms/?c=Home.Home&m=ajax_course_list";
								$("#tab-content > div").eq(0).load(default_url);

								$('#tab li')
										.click(
												function() {
													var cur_idx = $('#tab li')
															.index(this);
													cur_idx = 0;
													$("#tab" + cur_idx)
															.html(
																	'<div class="loading_txt">'
																			+ lang_loading
																			+ '</div>');
													$("#tab-content div")
															.fadeOut('fast');
													$(this).addClass("active")
															.siblings()
															.removeClass(
																	"active");

													//alert($(this).attr("url"));
													if ($(this).attr("url") != undefined) {
														var load_url = $(this)
																.attr("url")
																+ "&rand="
																+ Math.random();
														$("#tab" + cur_idx)
																.load(load_url);
													}
													$("#tab" + cur_idx).fadeIn(
															'slow');
												});
							});

			$(document).ready(
					function() {
						var default_url = $('#tab2 li').eq(0).attr("url");
						$("#tab2-content > div").eq(0).load(
								web_path + default_url);
						$("#tab2 li").eq(0).addClass("active");

						$('#tab2 li').click(
								function() {
									cur_idx = 0;
									$("#tab2" + cur_idx).html(
											'<div class="loading_txt">'
													+ lang_loading + '</div>');
									$("#tab2-content div").fadeOut('fast');
									$(this).addClass("active").siblings()
											.removeClass("active");

									//alert($(this).attr("url"));
									if ($(this).attr("url") != undefined) {
										var load_url = web_path
												+ $(this).attr("url")
												+ "&rand=" + Math.random();
										$("#tab2" + cur_idx).load(load_url);
									}
									$("#tab2" + cur_idx).fadeIn('slow');
								});
					});
		</script>


		<div class="container">
			<div class="row">
				<div class="span9" style="margin-top: 1px;">
					<ul class="nav nav-tabs" id="tab">
						<li class="active"><a
							href="${pageContext.request.contextPath }/user/study.jsp"
							data-toggle="tab">最近学习课程</a></li>

						<li><a
							href="${pageContext.request.contextPath }/user/testTab.jsp"
							data-toggle="tab">待参加考试</a></li>


					</ul>
					<script type="text/javascript">
					$(function(){
						$("#tab li").click(function(){
							var num = $('#tab li').index(this);
							if(num==0){
								$("#studyTab").css("display","");
								$("#testTab").css("display","none");
							}
							if(num==1){
								$("#studyTab").css("display","none");
								$("#testTab").css("display","");
							}
							
							
						});
					});
					
					
					
				</script>

					<jsp:include page="studyTab.jsp" flush="true" />
					<jsp:include page="testTab.jsp" flush="true" />
					<div class="cls"></div>




					<div style="width: 100%; margin-top: 20px; margin-bottom: 20px">
						<div class="cls"></div>

						<div style="margin-top: 1px">
							<ul class="nav nav-tabs" style="margin-bottom: 15px;">
								<li  class="active"> <a href="#recommend_courses" data-toggle="tab">猜您喜欢的课程</a></li>
								<li> <a href="#hot_courses" data-toggle="tab">热门课程</a></li>
								<li><a href="#latest_courses" data-toggle="tab">最新课程</a></li>

							</ul>
							<%
								UserUtils utils = new UserUtils();
								List<Courseinfo> list =  utils.recommend();
								request.setAttribute("recommendList", list);
							%>
							<div id="myTabContent" class="tab-content" style="padding-left: 10px">
								<div class="tab-pane fade active in" id="recommend_courses">
									<!-- 热门课程 -->
									<h4>
										<ul class="cells cells-middle">
											<c:forEach var="m" items='${requestScope.recommendList }' varStatus="c">
												<li class="cell">
													<div class="course-item">
														<div class="thumb">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${m.courseId }&userId=${userinfo.userId  }">
																<img alt="查看课程"
																title="查看课程"
																src="${pageContext.request.contextPath}/${m.coursePicture}"
																onerror="javascript:load_default_image(this);"
																height="125" width="150"> </a>
														</div>
														<p class="title">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${m.courseId }&userId=${userinfo.userId  }">
																${m.courseName }
																
																</a>
														</p>
														<!-- <div class="summary"></div> -->
														<p class="metas clearfix">
															<span title="学员人数" class="fr learn">学员人数:${m.registerNumber
																}</span>
														</p>
													</div></li>
											</c:forEach>
										</ul>
									</h4>
									<div class="clearfix"></div>

								</div>
								<div class="tab-pane fade" id="hot_courses">
									<!-- 热门课程 -->
									<h4>
										<ul class="cells cells-middle">
											<%
												request.setAttribute("hostList", utils.getHotCourse());
											%>
											<c:forEach var="m" items='${hostList }' varStatus="c">
											<c:if test="${c.count lt 11}">
												<li class="cell">
													<div class="course-item">
														<div class="thumb">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${m.courseId }&userId=${userinfo.userId  }">
																<img alt="查看课程"
																title="查看课程"
																src="${pageContext.request.contextPath}/${m.coursePicture}"
																onerror="javascript:load_default_image(this);"
																height="125" width="150"> </a>
														</div>
														<p class="title">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${m.courseId }&userId=${userinfo.userId  }">
																---${m.courseName}</a>
														</p>
														<!-- <div class="summary"></div> -->
														<p class="metas clearfix">
															<span title="学员人数" class="fr learn">学员人数:${m.registerNumber
																}</span>
														</p>
													</div></li>
													</c:if>
											</c:forEach>
										</ul>
									</h4>
									<div class="clearfix"></div>

								</div>
								<div class="tab-pane fade" id="latest_courses">
									<!-- 最新课程 -->
									<h4>
										<ul class="cells cells-middle">
											<%
												request.setAttribute("newList", utils.getNewCourse());
											%>
											<c:forEach var="c" items='${newList }' varStatus="t">
											<c:if test="${t.count lt 11}">
												<li class="cell">
													<div class="course-item">
														<div class="thumb">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${c.courseId }&userId=${userinfo.userId  }">
																<img   src="${pageContext.request.contextPath}/${c.coursePicture}" 
																height="125" width="150"> </a>
														</div>
														<p class="title">
															<a
																href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${c.courseId }&userId=${userinfo.userId  }">
																${c.courseName }</a>
														</p>
														<!-- <div class="summary"></div> -->
														<p class="metas clearfix">
															<span title="注册时间" class="fr learn">${c.uploadTime
																}</span>
														</p>
													</div></li>
													</c:if>
											</c:forEach>
										</ul>
									</h4>
									<div class="cls"></div>

								</div> 
							</div>
						</div>



					</div>
				</div>


				<div class="span3">

					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${userinfo.userName}
							</h3>
						</div>
						<div class="panel-body">
							<table style="width: 100%">
								<tbody>
									<tr>
										<td valign="top"><div style="padding-left: 0px;">
												 
												<p>
													<span class="f14 b">上次登录：</span><span class="hei7">
														<%
														request.setAttribute("loginTime", utils.getLoginTime());
														%> ${requestScope.loginTime } </span>
												</p>
												<p>
													<span class="f14 b">课程平均得分：</span><span class="hei7">
														<%
														request.setAttribute("examScore", utils.getExamScore());
														%> ${requestScope.examScore } </span>
												</p>
												<p>
													<span class="f14 b">已完成课程：</span><span class="hei7">
														<%
															
															request.setAttribute("isFinish", utils.getIsFinish());
														%> ${requestScope.isFinish } </span>
												</p>
											</div></td>
										<td align="right" valign="top">
											<div style="float: right">
												<a
													href="${pageContext.request.contextPath }/user/mycenter.htm"><img
													class="img-rounded"
													src="${pageContext.request.contextPath }/user/img/user_img.jpg"
													alt="" height="95" width="84"> </a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>

							<div class="clearfix"></div>
							<ul class="nav nav-pills">
								<li class="dropdown"><a class="dropdown-toggle"
									style="background-color:#DFF0D8" data-toggle="dropdown"
									href="#"> <i class="icon-tasks"></i> 我的学习任务<span
										class="caret"></span> <span class="badge badge-important">
											<%
											request.setAttribute("taskNum", utils.getTaskNum());
										%> ${requestScope.taskNum } </span> </a>
									<ul class="dropdown-menu">
										<li><a
											href="${pageContext.request.contextPath }/user/studyCourse.action"><i
												class="icon-book"></i> 需学习课程 <span class="my_task_num">
													${requestScope.taskNum } </span> </a></li>
										<li><a><i class="icon-edit"></i> 课程考试 <span
												class="my_task_num"> <%
													
													request.setAttribute("examCourse", utils.getTestCourse().size());
												%> ${requestScope.examCourse } </span> </a></li>

									</ul></li>
							</ul>
						</div>
					</div>


					<div class="clearfix"></div>

					<div class="clearfix"></div>
					<!-- 获得最多学分的学员 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">学分榜</h3>
						</div>
						<div class="panel-body">
							<ul class="list1" >
								<%
									request.setAttribute("map", utils.MaxFinish());
								%>

								<c:forEach var="m" items='${map }' varStatus="c">
									<c:if test="${c.count lt 13 }">
									<li style="height:23">
									
										<div style="width: 120px; float:left">
										<img class="img-rounded" src="${pageContext.request.contextPath }/user/img/avatar.jpg"
													alt="" height="23" width="23">${m.userName}</div>
										
										<div style="margin-right: 80px;">${20 - c.count }</div>
									</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>



					<div class="clearfix"></div>

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