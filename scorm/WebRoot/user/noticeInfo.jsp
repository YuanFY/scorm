<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!-- saved from url=(0078)http://xue.vision-info.com/lms/?c=Home.InfoCenter&m=iframe_bulletin_view&id=51 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="xiaohe">
<title>${noticeinfo.noticeTitle }</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/ext.css">
<script type="text/javascript" src="js/commons.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="css/idialog-4.css">
<script type="text/javascript" src="js/artDialog.js"></script>
<script type="text/javascript" src="js/iframeTools.js"></script>
<script type="text/javascript" src="js/dialog-common.js"></script>
<script type="text/javascript" src="js/jquery.wtooltip.js"></script>
<script type="text/javascript">
	var lang_loading = "数据正在载入中,请稍候...";
	var lang_ConfirmOperation = "确认操作";
	var total_top_menu_item = 8;
	var menus = new Array();
	menus[0] = 1;
	menus[1] = 2;
	menus[2] = 3;
	menus[3] = 4;
	menus[4] = 5;
	menus[5] = 6;
	menus[6] = 7;
	menus[7] = 8;
</script>
<link href="css/evabootstrap-full.css" rel="stylesheet" type="text/css">
<link href="css/navbar-blue.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
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
</style>
<script src="js/jquery.scrollUp.min.js" type="text/javascript"></script>
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
<style>
.table th,.table td {
	border-top-width: 0;
}
</style>
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


	<div class="container">
		<div class="page-header intitle3">${noticeinfo.noticeTitle }</div>
		<table width="99%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td valign="top">
						<table class="table">
							<tbody>
								<tr>
									<td valign="top">
										<div style="margin: 1px 20px 10px 0; float: right">发布时间
											: ${fn:substring(noticeinfo.noticeTime, 0, 19) }</div>
										<div class="clearfix"></div> <span style="font-size:18px;">&nbsp;&nbsp;&nbsp;&nbsp;${noticeinfo.noticeContent }</span><br>
									</td>
								</tr>
							</tbody>
						</table></td>

				</tr>
			</tbody>
		</table>
	</div>

	<a id="scrollUp"
		href="http://xue.vision-info.com/lms/?c=Home.InfoCenter&m=iframe_bulletin_view&id=51#top"
		title="" style="display: none; position: fixed; z-index: 2147483647;"></a>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"></div>
</body>
</html>