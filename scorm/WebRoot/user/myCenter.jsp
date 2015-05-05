<%@page import="com.scorm.utils.UserCommon"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>IT学源网-个人资料</title>
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

		<link type="text/css" href="${pageContext.request.contextPath}/user/css/style_center" />
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
									<li><a href="${pageContext.request.contextPath }/getNoticeList.action">系统公告</a></li>
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
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/home.css">
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
							<li><a data-section="info" href="${pageContext.request.contextPath }/myCenterUserinfo.action?userId=${userinfo.userId}" class="pushstate-link option tap-settings-info current "><i class="icon-user"></i> 我的资料</a></li>
							<li><a data-section="accounts" href="${pageContext.request.contextPath }/myCenterPwdUpdate.action?userId=${userinfo.userId }" class="pushstate-link option tap-settings-accounts "><i class="icon-random"></i> 修改密码</a></li>
							<s:if test="#session.userinfo.isAdmin == 1">
								<li><a data-section="avatar" href="${pageContext.request.contextPath }/myCenterScoUpload.action" class="pushstate-link option tap-settings-info "><i class="icon-picture"></i> 上传课件</a></li>
								<li><a data-section="avatar" href="${pageContext.request.contextPath }/myCenterSco.action" class="pushstate-link option tap-settings-avatar "><i class="icon-picture"></i> 课件管理</a></li>
								
							</s:if>
										
						</ul>
					</div>
					<div class="right span12">
		
						<script type="text/javascript">
							var PleaseInsertRealName = "请输入真实姓名";
							var RealNameWithoutBlanks = " 真实姓名不能为空";
							var RealNameInputForamt = " 真实姓名输入合法";
							var RealNmaeWithoutBlanksTwosides = "真实姓名两边不能有空符号";
							var RealNameLengthNotConsitent = "真实姓名长度不合要求,请确认";
							var InpputIdNumber = "请输入身份证号码";
							var InsertCorrect = "输入正确";
							var UIdNumberIllegal = "你输入的身份证号码非法,请确认";
							var idcard = "idcard";
							var PwDataType = "enum";
							var IdCardFormatIncorrect = "身份证号码格式不正确";
							var InputFixNumber = "请输入固定电话号码";
							var tel = "tel";
							var FixNumberFormatIncorrect = "固定电话号码格式不正确";
							var InputMobileNumber = "请输入手机号码";
							var mobile = "mobile";
							var MobileNumberFormatIncorrect = "手机号码格式不正确";
							
							$('#button').click(function empty() {
								var name=""						
							});
							
							$(document).ready(function() {
								$.formValidator.initConfig({
									theme : "126",
									submitOnce : true,
									formID : "theForm",
									onError : function(msg) {
										dialog_alert(msg);
									}
								});
						
								$("#firstname").formValidator({
									onshow : PleaseInsertRealName,
									onfocus : RealNameWithoutBlanks,
									oncorrect : RealNameInputForamt
								}).inputValidator({
									min : 1,
									empty : {
										leftempty : false,
										rightempty : false,
										emptyerror : RealNmaeWithoutBlanksTwosides
									},
									onerror : RealNameLengthNotConsitent
								});
						
								$("#credential_no").formValidator({
									empty : true,
									onshow : InpputIdNumber,
									onfocus : InpputIdNumber,
									oncorrect : InsertCorrect
								}).inputValidator({
									min : 10,
									max : 30,
									onerror : UIdNumberIllegal
								}).regexValidator({
									regExp : idcard,
									dataType : PwDataType,
									onerror : IdCardFormatIncorrect
								});
						
								$("#phone").formValidator({
									empty : true,
									onshow : InputFixNumber,
									onfocus : InputFixNumber,
									oncorrect : InsertCorrect
								}).regexValidator({
									regExp : tel,
									dataType : PwDataType,
									onerror : FixNumberFormatIncorrect
								});
						
								$("#mobile").formValidator({
									empty : true,
									onshow : InputMobileNumber,
									onfocus : InputMobileNumber,
									oncorrect : InsertCorrect
								}).regexValidator({
									regExp : mobile,
									dataType : PwDataType,
									onerror : MobileNumberFormatIncorrect
								});
							});
						</script>
		
						<article class="settings-info current">
							<h3>我的资料</h3>
							<form class="form form-horizontal" action="updateMyUserinfo" method="post" name="theForm" id="theForm">
								<fieldset>
									<input value="${userinfo.userId }" name="userId" type="hidden">
									<div class="control-group">
										<label for="" class="control-label"><span style="color: red"></span> 用户名</label>
										<div class="controls">
											<input class="g-ipt" name="userName" placeholder="请输入名字" id="firstname" required type="text" value="${userinfo.userName }" readOnly>
											<div id="firstnameTip" style="display: inline; margin: 0px; padding: 0px; background: none repeat scroll 0% 0% transparent;"></div>
										</div>
									</div>
									<div class="control-group">
										<label for="" class="control-label"> 邮箱</label>
										<div class="controls">
											<input class="inputText" name="userEmail" placeholder="请输入Email地址" id="Email" value="${userinfo.userEmail }" type="text">
											<div id="Email_noTip" style="display: inline;"></div>
										</div>
									</div>
									<div class="control-group">
										<label for="" class="control-label">移动电话</label>
										<div id="url-setting" class="controls">
											<input class="g-ipt" name="userPhone" id="credential_no" value="${userinfo.userPhone }" type="text">
											<div id="credential_noTip" style="display: inline; margin: 0px; padding: 0px; background: none repeat scroll 0% 0% transparent;"></div>
										</div>
									</div>
									<div class="control-group">
										<label for="" class="control-label">用户级别</label>
										<div id="url-setting" class="controls">
											<s:if test="#session.userinfo.isAdmin == 0">
												<input class="g-ipt" title="用户权限" name="isAdminChar" id="mobile" value="学生" type="text" readOnly>
											</s:if>
											<s:elseif test="#session.userinfo.isAdmin == 1">
												<input class="g-ipt" title="用户权限" name="isAdminChar" id="mobile" value="老师" type="text" readOnly>
											</s:elseif>
											<s:elseif test="#session.userinfo.isAdmin == 2">
												<input class="g-ipt" title="用户权限" name="isAdminChar" id="mobile" value="管理员" type="text" readOnly>
											</s:elseif>
											<div id="mobileTip" style="display: inline; margin: 0px; padding: 0px; background: none repeat scroll 0% 0% transparent;"></div>
										</div>
									</div>
									<div class="control-group">
										<label for="" class="control-label">登录时间</label>
										<div id="url-setting" class="controls">
											<input class="g-ipt" name="loginTime" id="phone" value="${fn:substring(userinfo.loginTime,0,19) }" type="text" readOnly>
											<div id="phoneTip" style="display: inline; margin: 0px; padding: 0px; background: none repeat scroll 0% 0% transparent;"></div>
										</div>
									</div>
									<div class="control-group">
										<label for="" class="control-label">性别</label>
										<div id="url-setting" class="controls">
											<ul class="sex">
												<select size="1" name="userSex">
													<option value="男" <%=((Userinfo)request.getSession().getAttribute("userinfo")).getUserSex().equals("男") ? "selected" : "" %>>男</option>
													<option value="女" <%=((Userinfo)request.getSession().getAttribute("userinfo")).getUserSex().equals("女") ? "selected" : "" %>>女</option>
												</select>
											</ul>
										</div>
									</div>
									<div class="form-actions">
										<button class="btn btn-primary" name="sm1" type="submit" data-original-title="已保存" data-loading-text="正在保存">
											保 存 
										</button>
									</div>
									<div class="cls"></div>
								</fieldset>
							</form>
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