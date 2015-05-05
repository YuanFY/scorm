<%@page import="com.scorm.utils.UserCommon"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		<title>IT学源网-上传课件</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/ext.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/commons.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.js"></script>
		<link href="${pageContext.request.contextPath}/user/css/idialog.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/artDialog.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/iframeTools.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/dialog-common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery_002.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/portal.js"></script>
		<script type="text/javascript">
			var lang_loading="数据正在载入中,请稍候...";
			var lang_ConfirmOperation="确认操作";var total_top_menu_item=8; 
			var menus=new Array();menus[0]=1;menus[1]=8;menus[2]=3;menus[3]=4;menus[4]=2;menus[5]=6;menus[6]=5;menus[7]=7;
		</script>
		<link href="${pageContext.request.contextPath}/user/css/evabootstrap-full.css" rel="stylesheet" type="text/css">

		<link href="${pageContext.request.contextPath}/user/css/navbar-blue.css" rel="stylesheet" type="text/css"> 
		<link href="${pageContext.request.contextPath}/user/css/font-awesome.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap-dropdown.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/bootstrap-hover-dropdown.js"></script>

		<script src="${pageContext.request.contextPath}/user/js/jquery.js" type="text/javascript"></script>
		<style>
			
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.8.2.js"></script>
  <script language="JavaScript">

   /****************************************************************************
   **
   ** Function:  MM_reloadPage()
   ** Input:   init - boolean
   ** Output:  boolean
   **
   ** Description:  This function reloads the window if Nav4 is resized
   **
   ** Issues:  This method is not in use in Version 1.2.2 due to the lack of
   **          Netscape support.
   **
   ***************************************************************************/
   function MM_reloadPage( init )
   { 
      if (init == true) with (navigator)
      {               
         if ( (appName == "Netscape") && (parseInt(appVersion) == 4) )
         {
            document.MM_pgW = innerWidth;
            document.MM_pgH = innerHeight;
            onresize = MM_reloadPage;
         }
      }
      else if (innerWidth != document.MM_pgW || innerHeight != document.MM_pgH)
      {
         location.reload();
      }
   }
   MM_reloadPage(true);
   
   /****************************************************************************
   **
   ** Function:  checkValues()
   ** Input:   none
   ** Output:  boolean
   **
   ** Description:  This function ensures that there are values in each text
   **               box before submitting
   **
   ***************************************************************************/
	function checkValues()
    {
		var flag = true;
		if ( courseInfo.coursename.value == "" ) 
		{
			$("#firstnameTip").html("*课程名不能为空");
			$("#firstnameTip").css("color",'red');
			flag = false;
		}
		if ( courseInfo.coursezipfile.value == "" ) 
		{
			$("#coursezipfile_noTip").html("*上传的课件不能为空");
			$("#coursezipfile_noTip").css("color",'red');
			flag = false;
		}
		var str = courseInfo.coursezipfile.value;
		if ( str.substr(str.length-3,3) != "zip"  ) 
		{
			$("#coursezipfile_noTip").html("*后缀名必须为zip");
			$("#coursezipfile_noTip").css("color",'red');
			flag = false;
		}
		if(!flag)
		 	return flag;	
		courseInfo.theZipFile.value = courseInfo.coursezipfile.value;
		courseInfo.isTest.value = $("input[name='isTest1']:checked").val();
	
		
		return flag;
   }
   
   /****************************************************************************
   **
   ** Function:  newWindow()
   ** Input:   pageName
   ** Output:  none
   **
   ** Description:  This function opens the help window
   **
   ***************************************************************************/
   function newWindow( pageName )
   {
      window.open(pageName, 'Help', 
      "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=500,height=500");
   }
   
   </script>
		<script type="text/javascript">
			var logout_uri = "#";
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

		<link type="text/css" href="${pageContext.request.contextPath}/user/css/style_mycenter.css" />
	</head>

	<body class="typocn">
		<s:if test="#request.updateResult == 1">
			<script>
				alert('修改个人信息成功');
			</script>
			<%
				request.removeAttribute("updateResult");
			 %>
		</s:if>
	
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
							<li class="dropdown">
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
									<li><a href="${pageContext.request.contextPath }/user/sysNotice.jsp">系统公告</a></li>
								</ul>
							</li>
							<li class="dropdown active">
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
		<!-- header end-->
	
	<div class="clearfix"></div>
	<div style="top: 75px; margin-top: 75px;"></div>	
	<div class="container">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/home.css" />
		<style>
			.form label {
				display: inline;
			}
			
			.form-horizontal .form-actions {
				padding-left: 0;
			}
			
			#settings .right .control-group {
				margin-bottom: 20px
			}
			
			#settings .sex li {
				width: 80px;
				float: left
			}
		</style>
	
		<script src="${pageContext.request.contextPath}/user/js/formValidator-4.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/user/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
	
		<div class="container" id="main">
			<div class="row" id="settings">
				<div class="left span3">
					<div class="top">
						<div class="avatar">
							<a title="设置头像" href="#" class="pushstate-link"> <img alt="" src="${pageContext.request.contextPath}/user/img/avatar.jpg">
							</a>
						</div>
						<h1>
							<a href="#" class="pushstate-link"></a>
						</h1>
						<h2>
							<i class="icon-cog"></i>${userinfo.userName }			
						</h2>
					</div>
					<ul class="menu">
							<li><a data-section="info" href="${pageContext.request.contextPath }/myCenterUserinfo.action?userId=${userinfo.userId}" class="pushstate-link option tap-settings-avatar "><i class="icon-user"></i> 我的资料</a></li>
							<li><a data-section="accounts" href="${pageContext.request.contextPath }/myCenterPwdUpdate.action?userId=${userinfo.userId }" class="pushstate-link option tap-settings-accounts "><i class="icon-random"></i> 修改密码</a></li>
							<s:if test="#session.userinfo.isAdmin == 1">
								<li><a data-section="avatar" href="${pageContext.request.contextPath }/myCenterScoUpload.action" class="pushstate-link option tap-settings-info "><i class="icon-picture"></i> 上传课件</a></li>
								<li><a data-section="avatar" href="${pageContext.request.contextPath }/myCenterSco.action" class="pushstate-link option tap-settings-avatar current "><i class="icon-picture"></i> 课件管理</a></li>
								
							</s:if>
										
					</ul>
				</div>                                  
				<div class="right span12">
	
					<article class="settings-info current">
						<h3>课件管理</h3>
						<table class="table table-hover">
				 			<thead>
								<tr>
									<th style="width:30px">#</th>
									<th style="text-align: center">课件名称</th>
									<th style="text-align: center">课程名称</th>
									<th style="text-align: center">课程类型</th>
									<th style="text-align: center">上传时间</th>
									<th style="text-align: center">学习人数</th>
									<th style="text-align: center">操作</th>
								</tr>
							</thead> 
							<tbody>
								<c:forEach var="sco" items="${scoList }" varStatus="c">
								<tr>
									<td style="text-align: center">${c.count }</td>
									<td style="text-align: center">${sco.scoName }</td>
									<td style="text-align: center">${sco.courseName }</td>
									<td style="text-align: center">${sco.courseType }</td>
									<td style="text-align: center">${sco.uploadTime }</td>
									<td style="text-align: center">${sco.likeNum }</td>
									<td style="text-align: center">
										<a href="${pageContext.request.contextPath}/delSco.action?scoId=${sco.scoId}" onclick="return window.confirm('与该课件相关的记录有可能清除，您确定要删除？')">删除</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>

					</article>  
				</div>
			</div>
		</div>
		<div style="margin-top: 20px;"></div>
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