<%@page import="com.scorm.service.CourseregService"%>
<%@page import="com.scorm.vo.Coursereg"%>
<%@page import="com.scorm.action.ActionSupport"%>
<%@page import="com.scorm.service.UsercourseinfoService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.scorm.utils.UserCommon"%>
<%@page import="com.scorm.vo.Usercourseinfo"%>
<%@page import="com.scorm.vo.Scoinfo"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<title>IT学源网-查看课程</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/ext.css">
		<script type="text/javascript" src="js/commons.js"></script>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" href="css/idialog-4.css"/>
		<script type="text/javascript" src="js/artDialog.js"></script>
		<script type="text/javascript" src="js/iframeTools.js"></script>
		<script type="text/javascript" src="js/dialog-common.js"></script>
		<script type="text/javascript" src="js/jquery.wtooltip.js"></script>
		<script type="text/javascript">
			var lang_loading="数据正在载入中,请稍候...";var lang_ConfirmOperation="确认操作";var total_top_menu_item=8;
			var menus=new Array();menus[0]=1;menus[1]=2;menus[2]=3;menus[3]=4;menus[4]=5;menus[5]=6;menus[6]=7;menus[7]=8;
		</script>
		<link href="css/evabootstrap-full.css" rel="stylesheet" type="text/css">

 		<link href="css/navbar-blue.css" rel="stylesheet" type="text/css"> 
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
		<script type="text/javascript" src="js/bootstrap-hover-dropdown.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/jquery.scrollUp.min.js" type="text/javascript"></script>
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
														<div class="aui_title" style="cursor: move; display: block;"></div>
														<a class="aui_close" href="javascript:/*artDialog*/;" style="display: block;">×</a>
													</div>
												</td>
											</tr>
											<tr>
												<td class="aui_icon" style="display: none;">
													<div class="aui_iconBg" style="background-image: none; background-position: initial initial; background-repeat: initial initial;"></div>
												</td>
												<td class="aui_main" style="width: 1071px; height: 622px;">
													<div class="aui_content" style="padding: 20px 25px;"></div>
												</td>
											</tr>
											<tr>
												<td colspan="2" class="aui_footer">
													<div class="aui_buttons" style="display: none;"></div>
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
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div>
				<div class="container">
	
					<a href="${pageContext.request.contextPath }/user/user_index.jsp" class="brand">
						<img src="img/logo1.png" onload="fixPNG(this)" height="55">
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
							<li class="dropdown active">
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
		<!-- header end-->
	
		<div class="clearfix"></div>
		<div style="top: 75px; margin-top: 75px;"></div>	
		<div class="container">
			<script type="text/javascript">
				var finishandcomment = "您确认完成该门课程,并立即参与课后评估吗？";
				var confirm_text ="确认操作";
				var finishicourseandstartexam ="您确认学完该门课程,并立即进行毕业考试吗?";
				var cancelcourse ="您确认申请取消该门课程学习吗? 一旦管理员同意，将会删除你与这门课程相关的所有学习数据信息，且不可恢复, 请谨慎操作!";
				var operationsuccessgotohomepage ="操作成功！正为跳转到学习中心首页……"; 
				var applysuccess = "您的申请已成功提交！请等待管理员审核。";
				var OperationFailedtoCancelCourse= "操作失败！可能原因是你不允许注销该课程的学习,如有疑问,请联系管理员.";
			</script>
			<script type="text/javascript">
				var attempt=1;
				var course_code="5SXIANCHANGGUANLI";
				var open_mode="_blank";
				var modal=false;var unsubscribe=0;
				var start_url="";
			</script>
			<script type="text/javascript">
				var wWidth = $(window).width();
				var wHeight = $(window).height();
				
				function openDialog(start_url,modal){
					/*if($.browser.msie){
						if(modal) LoadDialogWindow(start_url, 'cw', 0, 0, wWidth, wHeight+60);
						else openWin2(start_url,'cw');
					}else{*/
						openWin2(start_url, "cw");
					//}
				}
				
				
				$(document).ready(function() {
					$("a,img").wTooltip({fadeIn:"fast",offsetY:6,className:'wTooltip'});
					
					$("#StartEval").click(function(){
						var txt = finishandcomment ;
						art.dialog({content:txt,icon:"question",lock:"true",window:"top",title:confirm_text,
							ok: function(){
								if(survey_url!="")
									openDialog(survey_url,modal);
							},
							cancel:function(){}
						});
					});
					
					$("#applyFinish").click(function(){
						var txt = finishicourseandstartexam;
						art.dialog({content:txt,icon:"question",lock:"true",window:"top",title:confirm_text,
							ok: function(){
								if(exam_url!="")
								top.location.href=exam_url;
							},
							cancel:function(){}
						});
					});
					
					$("#unsubscribe").click(function(){
						var txt = cancelcourse;
						art.dialog({content:txt,icon:"question",lock:"true",window:"top",title:confirm_text,width:'280px',
							ok: function(){
								$.post(web_path+'?c=Home.MyCourses&m=ajax_unsubscribe_course',{course_code:course_code}, 
										function(data){
											//alert(data);									
											if($.trim(data)=='1') {
												dialog_alert( operationsuccessgotohomepage);
												top.location.href=web_path+"?c=Home.LearningCenter&m=index";
											}else if($.trim(data)==101){
												dialog_alert( OperationFailedtoCancelCourse);
											}else if($.trim(data)==110){
												dialog_alert( applysuccess);
												$("#unsubscribe").hide();
											}
										},'text');			
							},
							cancel:function(){}
						});
					});
					
					$("#startLearning").click(function(){
						//art.dialog({time:6,content: {$lang.OpeningScrom},lock:true,icon:"warning",title:{$lang.MyAlertMessages},window:"top"});
						//openDialog(start_url,modal);
						openDialog(web_path+"?c=Home.MyCourses&m=start_learning_cw",modal);
					});
				});
			</script>
			
			<style>
				.shuominglist li {
					width: 50%
				}
				
				.keyi {
					margin-top: 0px;
				}
				
				.tableindex th {
					border-width: 1px 1px 1px;
					border-bottom: 1px solid #E0E0E0;
				}
				
				.progress {
					margin-bottom: 4px;
				}
				
				.typocn blockquote {
					margin-left: 0;
					margin-right: 0;
					margin-top: 0;
				}
				
				em {
					font-size: 12px;
					font-style: italic;
				}
				
				.panel .kelist2 .tools {
					padding-left: 20px;
				}
				
				.typocn h3 {
					font-weight: bold;
				}
				
				.panel-body {
					padding: 10px
				}
				
				.panel-heading {
					padding: 9px 15px;
				}
				
				.panel-title {
					font-size: 14px;
				}
				
				.table {
					margin-bottom: 0
				}
			</style>
			<div class="cls"></div>
		
			<div class="container">
				<ul class="breadcrumb">
					<li><a href="${pageContext.request.contextPath }/user/user_index.jsp">首页</a><span class="divider">/</span></li>
					<li><a href="${pageContext.request.contextPath }/user/onlineCourse.action">学习</a><span class="divider">/</span></li>
					<li class="active"><a href="#">课程首页: ${courseinfo.courseName }</a></li>
				</ul>
				<div class="cls"></div>
			
				<div class="row">
					<div class="span9">
						<div class="daiwokaoshi" style="border-width: 0px;">
							<div class="kaoshicon2" style="margin-top: 0px">
								<div class="cls"></div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">
											<span style="font-size: 15px; font-weight: bold">正在学习的课程</span>
											
										</h3>
									</div>
									<div class="panel-body">
										<table class="table table-hover">
											<thead>
												<tr>
													<th style="width: 20px"></th>
													<th style="text-align: left; ">课程名称</th>
													<th style="text-align: left;  ">注册时间</th>
													<th style="text-align: left;  ">课程评分</th>
													<th style="text-align: center;  ">操作</th>
												</tr>
											</thead>
											<tbody>
													<tr>
														<td><img src="img/scorm.gif"
															class="title_icon" title="SCORM" alt="SCORM">
														</td>
														<td><a title="" target="_black"
															href="${pageContext.request.contextPath }/user/scoPlay.jsp?courseID=${sco.scoId}&cId=${courseinfo.courseId}"
															> <span
																style="text-align: center; padding-left: 4px">${courseinfo.courseName }</span>
														</a></td>
														<td style="text-align: left;">
															${coursereg.registerTime }
														</td>
														<td style="text-align: left;">
															<span style="text-align: center; padding-left: 4px" class="scoScore">
																<%
																	int userId = ActionSupport.getSessionUser().getUserId();
																	int courseId = Integer.parseInt(request.getAttribute("courseId").toString());
																	int scoId = Integer.parseInt(request.getAttribute("scoId").toString());
																	System.out.println("userId="+userId+",courseId="+courseId+",scoId="+scoId);
																	int scoScore = -1;
																	
																	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
																	UsercourseinfoService usercourseinfoService = (UsercourseinfoService)context.getBean("usercourseinfoService");
																	CourseregService courseregService = (CourseregService) context.getBean("courseregService");
																	List<Usercourseinfo> list = usercourseinfoService.getCourseStudy(userId, courseId);
																	if(list!=null&&list.size()>0){
																		scoScore = courseregService.findById(userId, courseId).get(0).getCourseScore();
																	}
																	System.out.println("scoScore="+scoScore);
																	request.setAttribute("scoScore", scoScore);
																%>
																<c:if test="${scoScore>-1 }">
																<c:forEach begin="1" end="${scoScore }" varStatus="status">
																	<a title="点击评分" href="userCourse_updateUserCouse.action?scoScore=${status.count }&userId=${userinfo.userId}&courseId=${courseId}">
																	<img src="${pageContext.request.contextPath }/user/img/star-on.png"/>
																	</a>
																</c:forEach>
																<c:forEach begin="${scoScore }" end="4" varStatus="status">
																	<a title="点击评分" href="userCourse_updateUserCouse.action?scoScore=${status.count+scoScore }&userId=${userinfo.userId}&courseId=${courseId}">
																	<img src="${pageContext.request.contextPath }/user/img/star-off.png"/>
																	</a>
																</c:forEach>
																</c:if>
																<c:if test="${scoScore==-1 }">
																	尚未观看该课程
																</c:if>
															</span>
														</td>
														
														<td style="text-align: center;">
														<c:if test="${fn:length(scoinfo) gt 0}">
														<a  href="${pageContext.request.contextPath }/user/studyView.jsp?cId=${courseinfo.courseId}"
														class="btn btn-success">进行学习</a>
														 </c:if>
														<c:if test="${fn:length(scoinfo) eq 0}">
														<a title="该课程尚未有资源" class="btn btn-fail">进行学习 </a>
														</c:if>
														</td>
													</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="cls"></div>
			
								<div class="keyi" style="margin-top: 20px; border: 0px;">
									<div class="kccon3text">
										<ul class="shuominglist">
										</ul>
									</div>
								</div>
								<div class="cls"></div>
							</div>
						</div>
					</div>
			
					<div class="span3">
						<div class="cls"></div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">您可以</h3>
							</div>
							<div class="panel-body">
								<ul class="kelist">
									<li><a href="lookCourseinfo?courseId=${courseinfo.courseId }&userId=${userId  }"  title=""><img src="${pageContext.request.contextPath }/user/img/fun2.gif"> 查看本课程信息</a></li>
									<li>
										<a href="#" onclick="javascript:dialog_open(&#39;getCourseNote.action?courseId=${courseinfo.courseId }&amp;userId=${userId  }&#39;,&#39;课程笔记&#39;,&#39;95%&#39;,&#39;80%&#39;);"><img src="${pageContext.request.contextPath }/user/img/fun3.gif"> 课程笔记</a>
									</li>
								</ul>
							</div>
						</div>
			
						<div class="cls"></div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">学习状态</h3>
							</div>
							<div class="panel-body">
								<ul class="kelist2" style="text-align: center">
									<li>
										总体学习状态: 
										<span class="label label-important">未通过</span>
									</li>
									<li>
										获得学分: <span class="badge badge-success">0</span>
									</li>
									
								</ul>
							</div>
						</div>
						<div class="cls"></div>
						
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
								<s:iterator value="#request.reCourseinfo" var="course">
									<div class="inner2 ref_courses">
										<i class="icon-book"></i> <a href="${pageContext.request.contextPath }/user/lookCourseinfo?courseId=${course.courseId }&userId=${userinfo.userId  }" title="${course.courseName }" >
										${course.courseName }</a>
									</div>
								</s:iterator>
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