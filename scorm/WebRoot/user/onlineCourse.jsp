<%@page import="com.scorm.utils.UserCommon"%>
<%@page import="com.scorm.vo.Scoinfo"%>
<%@page import="com.scorm.vo.Courseinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="keywords" content="zlms">
	<meta name="description" content="zlms">
	<meta name="author" content="zlms">
	<title>IT学源网-在线选课</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/ext.css">
	
	<link href="${pageContext.request.contextPath}/user/css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/commons.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.js"></script>
	<link href="${pageContext.request.contextPath}/user/css/idialog.css" rel="stylesheet"><script type="text/javascript" src="${pageContext.request.contextPath}/user/js/artDialog.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/user/js/iframeTools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/dialog-common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery_003.js"></script>
	<script type="text/javascript">var web_path=WEB_PATH="http://demo.zlms.org/lms/"; var web_ui_path="http://demo.zlms.org/lms/view/templates/silverlight/";</script><script type="text/javascript" src="${pageContext.request.contextPath}/user/js/portal.js"></script>
	<script type="text/javascript">var lang_loading="数据正在载入中,请稍候...";var lang_ConfirmOperation="确认操作";var total_top_menu_item=8;
	 var menus=new Array();menus[0]=1;menus[1]=8;menus[2]=3;menus[3]=4;menus[4]=2;menus[5]=6;menus[6]=5;menus[7]=7;</script>
	<link href="${pageContext.request.contextPath}/user/css/evabootstrap-full.css" rel="stylesheet" type="text/css">
	
	<link href="${pageContext.request.contextPath}/user/css/navbar-blue.css" rel="stylesheet" type="text/css"> 
	<link href="${pageContext.request.contextPath}/user/css/font-awesome.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap-hover-dropdown.js"></script>
	
	<script src="${pageContext.request.contextPath}/user/js/jquery.js" type="text/javascript"></script>
	
	<style>
	#scrollUp {
		bottom: 20px;
		right: 20px;
		height: 38px;
		width: 38px;
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
	.form select,.form textarea,.form input[type="text"],.form input[type="password"],.form input[type="submit"]{
		margin-bottom:0;
	}
	</style>
	<script type="text/javascript">
		var logout_uri = "/lms/?c=Index&m=logout&tc=lms3";
		var lang_ExitConfirm = "您确定要退出本平台吗?";
	</script>
	
	<script type="text/javascript">
	var G_NOTIFICATION_INTERVAL = 60000;
	var document_title="IT学源网";
	
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
						<td class="aui_c">
							<div class="aui_inner">
								<table class="aui_dialog">
									<tbody>
										<tr>
											<td colspan="2" class="aui_header">
												<div class="aui_titleBar">
													<div style="cursor: move; display: block;" class="aui_title"></div>
													<a style="display: block;" class="aui_close" href="javascript:/*artDialog*/;">×</a>
												</div>
											</td>
										</tr>
										<tr>
											<td style="display: none;" class="aui_icon">
												<div style="background: none repeat scroll 0% 0% transparent;" class="aui_iconBg"></div>
											</td>
											<td style="width: auto; height: auto;" class="aui_main">
												<div style="padding: 20px 25px;" class="aui_content"></div>
											</td>
										</tr>
										<tr>
											<td colspan="2" class="aui_footer">
												<div style="display: none;" class="aui_buttons"></div>
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

				<a href="${pageContext.request.contextPath }/user/user_index.jsp" class="brand">
					<img src="${pageContext.request.contextPath}/user/img/logo1.png" onload="fixPNG(this)" height="55">
				</a>

				<div class="nav-collapse collapse">
					<ul class="nav narbar-nav pull-left">
						<li class="">
							<a href="${pageContext.request.contextPath }/user/user_index.jsp">首页</a>
						</li>
						<li class="dropdown active">
							<a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								href="#">
								选课<strong class="caret"></strong>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath }/user/onlineCourse.action">在线课程选课与报名</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								href="#">
								学习<strong class="caret"></strong>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath }/user/studyCourse.action">我正在学习的课程</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath }/user/endCourse.action">我已完成的在线课程</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath }/user/lastCourse.action">我已过期的在线课程</a>
								</li>
							</ul>
						</li>

						

						<li class="dropdown">
							<a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								href="#">
								档案<strong class="caret"></strong>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="studyRecord.action">我的课程学习档案</a>
								</li>
								
							</ul>
						</li>

						<li class="dropdown">
							<a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								href="#">
								资讯<strong class="caret"></strong>
							</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath }/getNoticeList.action">系统公告</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								href="#">
								关于我<strong class="caret"></strong>
							</a>
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
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="clearfix"></div>
	<div style="top: 75px; margin-top: 75px;"></div>	
	<div class="container">
	
	<!-- V4.0 使用课程中心(新) -->
	<link href="${pageContext.request.contextPath}/user/css/base.css" rel="stylesheet" type="text/css" media="screen">
	<link href="${pageContext.request.contextPath}/user/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen">
	<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery_002.js"></script>

	<style>
		.box-cate {
			padding-bottom: 10px;
			border: 1px solid #D5D5D5;
			box-shadow: rgba(0, 0, 0, 0.2) 1px 1px 5px;
			border-radius: 5px;
		}
		
		.box-cate .list-cate {
			padding: 10px 10px 0px 10px;
			background: #fff;
			border-bottom: 0px solid #0ae;
			border-radius: 5px;
		}
		
		.box-cate .list-cate .cur {
			background: #25b9f0;
		}
		
		.box-cate .list-cate dl {
			background: url(images_v4/dashed_line.png) left bottom repeat-x;
			overflow: hidden;
			*zoom: 1;
			border-bottom: 1px dotted #EFEFEF
		}
		
		.box-cate .list-cate dl dt {
			float: left;
			margin-top: 5px; /*width: 80px;*/
			font-weight: 700;
			text-align: right;
		}
		
		.box-cate .list-cate dl dd {
			margin-bottom: -5px;
			overflow: hidden;
			zoom: 1;
		}
		
		.box-cate .list-cate dl dd em {
			display: inline-block;
			margin-bottom: 10px;
			width: 100px;
		}
		
		.box-cate .list-cate dl dd em a {
			color: #666;
		}
		
		.box-cate .list-cate dl dd em .cur {
			padding: 0 1px;
			background: #0ae;
			color: #fff;
		}
		
		.box-cate .list-cate ul {
			margin-bottom: 10px;
			padding-left: 0px;
			padding-bottom: 1px;
			background: url(images_v4/dashed_line.png) left bottom repeat-x;
			overflow: hidden;
			*zoom: 1;
		}
		
		.box-cate .list-cate ul li {
			float: left; /*width: 110px;*/
		}
		
		.box-cate .list-cate .last {
			margin: 0;
			padding-bottom: 0;
			background: none;
		}
		
		.nav-pills>li>a {
			padding-bottom: 4px;
			padding-top: 4px;
		}
		
		.nav-tabs>li>a,.nav-pills>li>a {
			padding-left: 6px;
			padding-right: 6px;
			border-radius: 4px
		}
	</style>
	
	
	<div class="container">
	
		<ul class="breadcrumb">
			<li><a href="user_index.jsp">首页</a><span class="divider">/</span></li>
			<li class="active"><a href="#">在线课程选课与报名</a></li>
		</ul>
	
		<div class="row-fluid">
			<div class="span3">
				<div class="well well-small" style="width: 99%; padding-bottom: 10px; padding-left: 10px;">
					<form action="${pageContext.request.contextPath }/courseSearch.action" method="get" class="form form-search" target="_self">
						<div class="input-append">
							<input name="courseName" class="span2 search-query" onfocus="this.select();" style="width: 90%;" type="text">
							<button class="btn" type="submit">搜 索</button>
						</div>
					</form>
				</div>
	
				<div class="clearfix"></div>
	
				<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.8.3.min.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/menu.js"></script>			
	            
				<div style="width:860px;margin:0 auto;">
					<!-- 左边导航条 -->
					<div class="sidebar" style="overflow:visible;">
						<div class="sidebar_top sidebar_top_tc">课程分类</div>
						
						<div class="sidebar_con">
							<!-- 课程类型 -->
							<s:iterator value="#request.coursetypeinfo" var="coursetype">
								<dl class="sidebar_item">
									<dt><i class="sidebar_item_icon01"></i><span>${coursetype.courseType }</span></dt>
									<!-- 课程 -->
									<s:iterator value="#request.courseinfo" var="course">
										<!-- 当前课程类型的课程 -->
										<s:if test="#course.courseType == #coursetype.courseType">
											<dd>
												<h3>
													<a href="${pageContext.request.contextPath }/user/lookCourseinfo.action?courseId=${course.courseId}&userId=${userinfo.userId}"><s:property value="#course.courseName"/></a>
												</h3>
												<s></s>
												<div class="sidebar_popup" style="display:none;">
													<div class="sidebar_popup_class clearfix">
														<!-- 课件 -->
														<s:iterator value="#request.scoinfo" var="sco">
															<!-- 属于当前课程的课件 -->
															<s:if test="#course.courseId == #sco.courseinfo.courseId">
																<div class="sidebar_popup_item"> 
																	<strong>
																		<span class="linesbg"><a href="#">${sco.scoName }</a></span>
																	</strong>
																	<p>
																		<span class="linesbg"><a href="#">作者：${sco.uploadAuthor }</a></span>
																		<span class="linesbg"><a href="#">发布时间：${sco.uploadTime }</a></span>
																	</p>
																</div>
															</s:if>
														</s:iterator>
													</div>
													<div class="sidebar_popup_all"><a href="${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=${course.courseId}&userId=${userinfo.userId}"><span class="linesbg">查看全部${course.courseName }</span></a></div>
												</div>
											</dd>
										</s:if>
									</s:iterator>
								</dl>
							</s:iterator>
						</div>
						<div class="sidebar_bottom"></div>
					</div><!--sidebar end-->
				</div>
			</div>
	
			<!-- 右边课程 -->
			<div class="span9">	
				<ul class="course-list-catalog">
					<s:iterator value="#request.rCourseinfo" var="course">
						<!-- 单个课程信息 -->
						<li class="course-item clearfix">
							<div class="course-left pull-left">
								<div class="course-logo">
									<a class="course-picture-link" href="${pageContext.request.contextPath }/user/lookCourseinfo.action?courseId=${course.courseinfo.courseId}&userId=${userinfo.userId}">
										<img class="course-picture" onerror="javascript:load_default_image(this);" src="${pageContext.request.contextPath }/${course.courseinfo.coursePicture }" alt="${course.courseinfo.courseName }">
									</a>
								</div>
				
								<div class="clearfix"></div>
								<div class="course-action">
									<a href="${pageContext.request.contextPath }/user/lookCourseinfo.action?courseId=${course.courseinfo.courseId}&userId=${userinfo.userId}" style="left: 16px; top: 115px; min-width: 60px; _width: 60px" class="btn btn-mini btn-primary">查看详情</a>							
								</div>
								
							</div>
									
							<div class="course-body pull-right">
								<div class="course-title">
									<div style="float: left; padding-right: 40px">
										<span class=""> <a href="${pageContext.request.contextPath }/user/lookCourseinfo.action?courseId=${course.courseinfo.courseId}&userId=${userinfo.userId}" title="${course.courseinfo.courseName }"> <strong>${course.courseinfo.courseName }</strong></a></span>
									</div>
									<div style="font-size: 11px;">课程编号: ${course.courseinfo.courseId }</div>
								</div>
								<div class="clearfix"></div>
								<div class="course-about ellipsis">
								
									<c:if test="${not empty course.courseinfo.courseContent }">
										<span class="_des">课程简介： ${fn:substring(course.courseinfo.courseContent,0,100) }...  </span> 
									</c:if>
								</div>
				
								<div class="course-footer clearfix">
									<table style="width: 100%; padding-left: 1px">
										<tbody>
											<tr>
												<td><span class="f-l">课程类别：${course.courseinfo.courseType }</span></td>
												<td><span class="f-l">发布时间：</span><span class="f-l">${course.courseinfo.uploadTime }</span></td>
											</tr>
											<tr>
												<td><span class="f-l">课程状态：
												
												<s:if test="#request.course.regFlag != false">
												<span class="label label-success">已报名</span>
												</s:if>
												<s:else>
													<span class="label label-fail">未报名</span>
												</s:else>
												</span></td>
												<td><span class="f-l">课程学分：4</span></td>
												<td><span class="f-l">注册人数：${course.courseinfo.registerNumber }</span></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</li>
					</s:iterator>
						
				</ul>
				<div class="clearfix"></div>
				<div class="pagination">
					<ul>
						<s:iterator value="new int[#request.page.pageCount]" status="i">
							<s:if test="#request.courseName == null">
								<li <%=(request.getAttribute("pageStart") == request.getAttribute("i")) ? "class='active'" : "" %>><a href="onlineCourse.action?pageStart=${i.index+1 }">${i.index + 1 }</a></li>
							</s:if>
							<s:else>
								<li <%=(request.getAttribute("pageStart") == request.getAttribute("i")) ? "class='active'" : "" %>><a href="courseSearch.action?pageStart=${i.index+1 }&courseName=${courseName}">${i.index + 1 }</a></li>
							</s:else>
						</s:iterator>
						<s:if test="#request.courseName == null">
							<li><a href="onlineCourse.action?pageStart=${pageStart + 1 }">下一页</a></li>				
						</s:if>
						<s:else>
							<s:if test="#request.page.currentPage > #request.pageStart">
								<li><a href="courseSearch.action?pageStart=${pageStart + 1 }&courseName=${courseName}">下一页</a></li>	
							</s:if>
							<s:else>
								<li><a href="courseSearch.action?pageStart=${pageStart }&courseName=${courseName}">下一页</a></li>
							</s:else>
						</s:else>
					</ul>
					<span class="f_l f6" style="margin-right: 10px; float: right;">课程总数:<b>${dataCount }</b> </span>
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