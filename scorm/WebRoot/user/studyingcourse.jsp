<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>IT学源网</title>
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
	var web_path = WEB_PATH = "http://demo.zlms.org/lms/";
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
<!--[if lte IE 6]>
  <link rel="stylesheet" type="text/css" href="http://demo.zlms.org/lms/assets/bootstrap/css/bootstrap-ie6.css">
  <link rel="stylesheet" type="text/css" href="http://demo.zlms.org/lms/assets/bootstrap/css/ie.css">
   <script type="text/javascript" src="http://demo.zlms.org/lms/assets/bootstrap/js/bootstrap-ie.js"></script>
  <![endif]-->
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

<body class="typocn">
	<jsp:include page="commonIndex.jsp"></jsp:include>
	<!-- header end-->
	<div class="clearfix"></div>
	<div style="top: 75px; margin-top: 75px;"></div>
	<div class="container">
		<script type="text/javascript">
			var RUSureToDelay = "您确认要延期学习该课程吗?";
			var confirm_text = "";
			var ApplySuccessSubmit = "您的申请已成功提交！请等待管理员审核。";
		</script>

		<script type="text/javascript">
			$(document).ready(function() {
				$("a,img").wTooltip({
					fadeIn : "fast",
					offsetY : 6,
					className : 'wTooltip'
				});
			});

			function apply_delay_course(course_code) {
				var txt = RUSureToDelay;
				art.dialog({
					content : txt,
					icon : "question",
					lock : "true",
					window : "top",
					title : confirm_text,
					ok : function() {
						$.post(web_path
								+ '?c=Home.MyCourses&m=ajax_delay_course', {
							course_code : course_code
						}, function(data) {
							if ($.trim(data) == 110) {
								dialog_alert(ApplySuccessSubmit);
								$("#unsubscribe").hide();
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

.progress {
	margin-bottom: 4px;
}
</style>

		<div class="main">

			<ul class="breadcrumb">
				<li><a href="${pageContext.request.contextPath }/user/user_index.jsp">首页</a><span
					class="divider">/</span>
				</li>
				<li>学习<span class="divider">/</span>
				</li>
				<li class="active">我正在学习的课程</li>
			</ul>



			<div class="cls"></div>

			<div class="mcontent">

				<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					<li class="active"><a
						href="http://demo.zlms.org/lms/?c=Home.LearningCenter&amp;m=index&amp;learn_status=incomplete">我正在学习的课程</a>
					</li>
					<li><a
						href="http://demo.zlms.org/lms/?c=Home.LearningCenter&amp;m=index&amp;learn_status=completed">我已完成的在线课程</a>
					</li>
					<!-- <li {if $learn_status== 'passed'}class="selected"{/if}><a href="{$web_path}?c=Home.LearningCenter&m=index&learn_status=passed"><em>{$lang.MyPassedCourses}</em></a></li>
				<li {if $learn_status== 'failed'}class="active"{/if}><a href="{$web_path}?c=Home.LearningCenter&m=index&learn_status=failed"><em>{$lang.MyFailedCourses}</em></a></li> -->
					<li><a
						href="http://demo.zlms.org/lms/?c=Home.LearningCenter&amp;m=index&amp;learn_status=expire">我已过期的在线课程</a>
					</li>
					<li><a
						href="http://demo.zlms.org/lms/?c=Home.MyTraining&amp;m=training_my_list">我需参加的面授培训</a>
					</li>
				</ul>

				<div id="myTabContent" class="tab-content" style="padding-left:0px">
					<div class="tab-pane fade active in" id="lc">
						<div class="well well-small">
							<form action="http://demo.zlms.org/lms/" method="get"
								class="form form-inline">
								<input name="c" value="Home.LearningCenter" type="hidden">
								<input name="m" value="index" type="hidden"><input
									name="keyword" class="inputText span3" onfocus="this.select();"
									type="text"> <span><select name="reqcrs"
									class="input-medium">
										<option selected="selected" value="">--课程类型--</option>
										<option value="1">必修课</option>
										<option value="0">选修课</option>
								</select>
								</span>&nbsp;<span><select name="category_code"
									class="input-medium">
										<option value="" selected="selected">--- 所有分类 ---</option>
										<option value="69">Demo演示课程</option>
										<option value="2">3分屏课件</option>
										<option value="75">用户使用指导</option>
										<option value="1">flash课件</option>
										<option value="3">Scorm标准课件</option>
										<option value="4">Scorm非标准课件</option>
										<option value="62">Articulate课件</option>
										<option value="63">串流大师课件</option>
										<option value="5">视频格式课件</option>
										<option value="70">上锁挂牌LOTO在线培训</option>
										<option value="71">宽学网课程</option>
								</select>
								</span> <input name="learn_status" value="incomplete" type="hidden">
								<input name="" value="搜 索" class="btn btn-primary" type="submit">
							</form>
						</div>

						<div class="cls"></div>
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th class="tricon"></th>
									<th>课程名称</th>
									<th>所属分类</th>
									<th>学习进度</th>
									<th>课程类型</th>
									<th>有效期</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=DP1000"
										title="">高效能人士的7个习惯</a>&nbsp;</td>
									<td><a href="#" title="/Scorm标准课件">Scorm标准课件</a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="29%">
											<div class="bar" style="width: 29%">29%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>选修课</td>
									<td>2014-04-17 至 2015-04-17</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=LINGSHOUYINXINGXIAOSHOUGONGJU"
										title="销售陈述 (LINGSHOUYINXINGXIAOSHOUGONGJU)">销售陈述</a>&nbsp;</td>
									<td><a href="#" title="">Scorm标准课件</a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="33%">
											<div class="bar" style="width: 33%">33%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>选修课</td>
									<td>2014-02-26 至 2015-02-26</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=LISHIRENWUJIESHAOQINSHIHUANG"
										title="">历史人物介绍－秦始皇</a>&nbsp;</td>
									<td><a href="#" title="">mp4格式课件</a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="100%">
											<div class="bar" style="width: 100%">100%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>选修课</td>
									<td>2013-09-21 至 2014-09-21</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=DEMOYANSHIKECHENG"
										title="">Demo演示课程</a>&nbsp;</td>
									<td><a href="#" title="/Demo演示课程">Demo演示课程</a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="80%">
											<div class="bar" style="width: 80%">80%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>选修课</td>
									<td>2014-04-24 至 2015-12-31</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=JIAOLIANJIQIAO"
										title="">教练技巧</a>&nbsp;</td>
									<td><a href="#" title=""></a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="100%">
											<div class="bar" style="width: 100%">100%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>必修课</td>
									<td>2014-04-24 至 2015-12-31</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=XIAOSHOUJIQIAO"
										title="">销售技巧</a>&nbsp;</td>
									<td><a href="#" title=""></a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="100%">
											<div class="bar" style="width: 100%">100%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>必修课</td>
									<td>2014-04-24 至 2015-12-31</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
								<tr>
									<td><img src="${pageContext.request.contextPath }/user/img/course.png"
										style="vertical-align: middle">
									</td>
									<td style="text-align: left; padding-left: 5px">&nbsp;<a
										class="de1"
										href="http://demo.zlms.org/lms/?c=Home.MyCourses&amp;m=course_home&amp;cidReq=ARTICULATESTORYLINEJIANDANJIESHA"
										title="">课件制作工具 Articulate Storyline简单介...</a>&nbsp;</td>
									<td><a href="#" title="/Scorm非标准课件">Scorm非标准课件</a>
									</td>
									<td>
										<div class="progress progress-striped active"
											style="margin-top: 6px" title="100%">
											<div class="bar" style="width: 100%">100%</div>
										</div> <!-- <div class="tdbg">
									<div
										style="background: #064ca1; height: 12px; float: left; width: {$v.progress}%"></div>
								</div>
								<div class="tdnum">{$v.progress}%</div> --></td>
									<td>必修课</td>
									<td>2014-04-24 至 2015-12-31</td>
									<td><span class="label label-warning">学习中</span>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="cls"></div>
						<div class="pagination">
							<ul>
							</ul>
							<span class="f_l f6" style="margin-right: 10px; float: right">
								记录总数: <b>7</b> </span>
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