<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0031)http://xue.vision-info.com/lms/ -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Language" content="zh-cn">

<meta name="Description" content="">
<meta name="Keywords" content="">
<meta name="author" content="">
<meta name="robots" content="all">
<title>IT学源网-登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/login/css/layout.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/jquery.XYTipsWindow.min.2.8.js"></script>
<script src="${pageContext.request.contextPath }/login/js/formValidator_min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/login/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<link href="${pageContext.request.contextPath }/login/css/box_style.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/commons.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/jquery.formtips.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/login/css/blue.css"><script type="text/javascript" src="${pageContext.request.contextPath }/login/js/artDialog.js"></script><script type="text/javascript" src="${pageContext.request.contextPath }/login/js/iframeTools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/dialog-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/login/js/jquery.blockui.min.js"></script>

<script type="text/javascript">
		var checkBrowser=true;
		var browserName="Chrome"; var browserVersion="28.0"; var isIE=false;</script>
<link href="${pageContext.request.contextPath }/login/css/evabootstrap-full.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/login/css/font-awesome.min.css" rel="stylesheet" type="text/css">


<script type="text/javascript">
	$(document)
			.ready(
					function() {
						/*if (!isIE && checkBrowser) {
							var alert_txt = ('平台自动检测您当前使用的浏览器为:'
									+ (browserName + " " + browserVersion) + ', 为获得更好的使用体验,我们强烈建议您使用 IE7/IE8/IE9 访问本系统。');
							//dialog_alert(alert_txt);
							art.dialog.tips(alert_txt, 5);
						}*/

						$('.carousel').carousel();

						$("form input.help, form textarea.help").formtips({
							tippedClass : "tipped"
						});

						if ($("#j_username").val().length > 0) {
							$("#j_password").focus();
						} else {
							$("#j_username").focus();
						}

						$("#form1")
								.submit(
										function() {
											$("#errorMsg").html("");
											if ($("#j_username").val() == "") {
												art.dialog.tips("请输入用户名！");
												$("#errorMsg").show();
												$("#j_username").focus();
												return false;
											}
											if ($("#tenant_code").val() == "") {
												$("#errorMsg").html("请输入企业ID!");
												$("#errorMsg").show();
												$("#tenant_code").focus();
												return false;
											}
											if ($("#j_password").val() == "") {
												art.dialog.tips("请输入密码");
												$("#errorMsg").show();
												$("#j_password").focus();
												return false;
											}

											$("#errorMsg").html("正在登录中，请稍候...");
											//art.dialog.tips("正在登录中，请稍候...",15);
											$
													.blockUI({
														message : '<h2><div style="padding:4px;">正在登录中，请稍候...</div></h2>'
													});//<img src="${pageContext.request.contextPath }/login/http://xue.vision-info.com/lms/assets/js/jquery-plugins/busy.gif" />
											return true;
										});
					});

	function keyPressInUser() {
		var keyValue;
		keyValue = window.event.keyCode;
		if (keyValue == 13)
			document.all.j_password.focus();
	}

	function keyPressInPassword() {
		var keyValue;
		keyValue = window.event.keyCode;
		if (keyValue == 13)
			G("form1").submit();
		// submitForm();
	}

	function dialog_alert(txt) {
		art.dialog({
			title : "友情提醒",
			content : txt,
			lock : false,
			icon : "face-smile",
			time : 15,
			window : "top",
			left : '100%',
			top : '100%',
			fixed : true,
			drag : false,
			resize : false,
			width : 320,
			height : 100
		})
	};

	function addBookMark() {
		var nome_sito = "网络学习平台";
		var url_sito = "#";
		if ((navigator.appName == "Microsoft Internet Explorer")
				&& (parseInt(navigator.appVersion) >= 4))
			window.external.AddFavorite(url_sito, nome_sito);
		else if (navigator.appName == "Netscape")
			window.sidebar.addPanel(nome_sito, url_sito, '');
		else
			parent.Dialog.alert("您的浏览器不允许脚本访问收藏夹，请手动添加！");
	}

	function setMyHomepage(url) { //  设置首页 
		if (!!(window.attachEvent && !window.opera)) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(url);
		} else {
			if (window.clipboardData && clipboardData.setData) {
				clipboardData.setData('text', url);
			} else {
				parent.Dialog.alert('您的浏览器不允许脚本访问剪切板，请手动设置！');
			}
		}
	}
</script>
<style type="text/css">
.nav {
	margin-left: auto;
}

.login h2 {
	font-size: 16px;
}
</style>
</head>
<body id="index" class="typocn"><div class="" style="display: none; position: absolute;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move; display: none;"></div><a class="aui_close" href="javascript:/*artDialog*/;" style="display: none;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background-image: none; background-position: initial initial; background-repeat: initial initial;"></div></td><td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
	<div class="container">

		<div class="head">
			<h1>
				<a href="#"><img alt="网络学习平台" src="${pageContext.request.contextPath }/login/image/88.png" onload="fixPNG(this)"></a>
			</h1>
		</div>
		<div class="clear"></div>
		<div class="top_bg">
			<div class="nav" style="background:url(image/1.png) repeat-x; color:black">
				<span class="left"> <a href="#">首页</a> 
				</span> <span class="right"> 
					<a href="#" class="forget">立即注册</a>  <a href="#">请登录</a>
				</span>
			</div>
		</div>
		<div class="wrapper">
			<div class="main">
				<div class="snsidea">
					<div class="carousel slide" id="myCarousel">
						<ol class="carousel-indicators">
							<li data-slide-to="0" data-target="#myCarousel" class="active"></li>
							<li data-slide-to="1" data-target="#myCarousel" class=""></li>
							<li data-slide-to="2" data-target="#myCarousel" class=""></li>
							<li data-slide-to="3" data-target="#myCarousel" class=""></li>
							<li data-slide-to="4" data-target="#myCarousel" class=""></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img src="${pageContext.request.contextPath }/login/image/slide01.jpg">
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath }/login/image/slide02.jpg">
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath }/login/image/slide03.jpg">
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath }/login/image/slide04.jpg">
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath }/login/image/slide05.jpg">
							</div>
						</div>
						<a data-slide="prev" href="#" class="left carousel-control">‹</a> <a data-slide="next" href="#" class="right carousel-control">›</a>
					</div>
				</div>
				<div class="login">
					<h2>
						学员登录 <span id="loadingmsg"></span>
					</h2>
					<form id="form1" name="form1" method="post" action="login.action">
						<input type="hidden" name="testcookie" value="1"> <input type="hidden" name="indexPage" value="?c=Home.Home">
						<p>
							<label>登录名： <span id="emailmsg" class="red"></span></label><input class="input" name="userName" id="j_username" type="text" title="登录帐号" onblur="this.style.borderColor=&#39;#dcdcdc&#39;" onkeypress="keyPressInUser()" onmouseover="this.focus()" onfocus="this.select();this.style.borderColor=&#39;#239fe3&#39;" autocomplete="on" value="student" style="border-color: rgb(220, 220, 220);">
						</p>
						<p>
							<label>密码： <span id="pwdmsg" class="red"></span></label><input class="pwd" name="userPwd" id="j_password" type="password" value="666666" onkeypress="keyPressInPassword()" onmouseover="this.focus()" onfocus="this.select();this.style.borderColor=&#39;#239fe3&#39;" onblur="this.style.borderColor=&#39;#dcdcdc&#39;" style="border-color: rgb(220, 220, 220);">
						</p>
						<p class="chk">
						
						</p>
						<p>
							 <span><a id="reg" style="cursor: pointer;" class="forget" >立即注册？</a></span>							<input class="btn btn-info btn-large" name="loginsubm" id="loginsubm" value="登 录" type="submit" style="margin-top: 15px; ">
						</p>
						<c:if test="${requestScope.flag==false }">
							<p>
								<span id="spanId" style=" color: red">用户名或密码错误</span>
							</p>
						</c:if>
						<c:if test="${requestScope.flag==true }">
							<c:if test="${requestScope.validateFlag==false }">
							<p>
								<span id="spanId" style=" color: blue">注册成功</span>
							</p>
						</c:if>
						</c:if>
					</form>
					<c:if test="${requestScope.validateFlag==true }">
						
						
						 <script type="text/javascript">
						 	$("#form1").attr("action","${pageContext.request.contextPath}/user/user_index.jsp");
						 	$("#form1").submit();
						 </script>
					</c:if>
				</div>

			</div>
			<div class="clear"></div>
			<div class="index_bottom"></div>
		</div>
		
	
		<script type="text/javascript">
			function set_cookie_lp(lp_str) {
				document.cookie = "lp_name=" + escape(lp_str);
				window.location.reload();
			};
			$(".forget").click(function(){
				$.XYTipsWindow({
					___title:"注册",
					___content:"iframe:${pageContext.request.contextPath}/login/reg.jsp",
					___width:"460",
					___height:"350",
					___showbg:true,
					___drag:"___boxTitle"
				});
		    });
			var userNameFlag = null;
			function validateUserName(params){
				$.ajax({
					type:"post",
					url:"userinfoAction_validateUserName.action",
					data:params,
					dataType:"json",
					async:false,
					success:function(data){
						var d = eval("("+data+")");//eval函数是将括号中的东西解析成jquery对象
						userNameFlag = d.flag;
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert("系统异常，请稍后重试！");
					}
				});
			}
			var codeFlag = null;
			function codeValidate(params){
				$.ajax({
					type:"post",
					url:"userinfoAction_validateCode.action",
					data:params,
					dataType:"json",
					async:false,
					success:function(data){
						var d = eval("("+data+")");//eval函数是将括号中的东西解析成jquery对象
						codeFlag = d.flag;
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert("系统异常，请稍后重试！");
					}
				});
			}
			function closeReg(){
				$.XYTipsWindow.removeBox();
			}
		</script>
		<div class="foot" style="text-align: center">
			 <strong><b>IT学习网</b>&nbsp;&nbsp; <!--{$lang.CompanyName}--></strong>
			©BFS 版权所有		</div>
		<div style="display: none;" class="emBg" id="face_list_menu"></div>
		<div id="append_parent"></div>
	</div>


<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"></div></body></html>