<%@page import="com.scorm.vo.Noteinfo"%>
<%@page import="com.scorm.vo.Usercourseinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>

<html style="">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="author" content="xiaohe">
		<title>IT学源网-课件笔记</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/ext.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/commons.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/user/css/idialog-4.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/artDialog.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/iframeTools.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/dialog-common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/jquery.wtooltip.js"></script>
		<script type="text/javascript">
			var lang_loading="数据正在载入中,请稍候...";
			var lang_ConfirmOperation="确认操作";
			var total_top_menu_item=8;
 			var menus=new Array();menus[0]=1;menus[1]=2;menus[2]=3;menus[3]=4;menus[4]=5;menus[5]=6;menus[6]=7;menus[7]=8;</script><link href="css/evabootstrap-full.css" rel="stylesheet" type="text/css">
	 	<link href="${pageContext.request.contextPath }/user/css/navbar-blue.css" rel="stylesheet" type="text/css"> 
		<link href="${pageContext.request.contextPath }/user/css/font-awesome.min.css" rel="stylesheet" type="text/css">

		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/bootstrap-dropdown.js"></script>

		<style>
			.breadcrumb li { display:inline; }
			.typocn button{ line-height:1.4; }
			.table thead th { background-color:#F5F5F5;text-align:center; }
		</style>
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
			.tableindex3 td {
				height: 30px;
				border: 0px solid #e0e0e0;
				padding-left: 3px;
			}
		</style>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/user/css/default.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/user/css/prettify.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath }/user/js/kindeditor.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath }/user/js/zh_CN.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath }/user/js/prettify.js"></script>
		<script>
			KindEditor.ready(function(K) {
				var editor1 = K.create('textarea[name="noteContent"]', {
					cssPath : 'css/prettify.css',
					allowFileManager : true,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
					}
				});
				prettyPrint();
			});
		</script>
	</head>

	<body class="typocn">
		<div style="display: none; position: absolute; " class="">
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
														<div class="aui_title" style="cursor: move; display: block; "></div>
														<a class="aui_close" href="javascript:/*artDialog*/;" style="display: block; ">×</a>
													</div>
												</td>
											</tr>
											<tr>
												<td class="aui_icon" style="display: none; ">
													<div class="aui_iconBg" style="background-image: none; background-position: initial initial; background-repeat: initial initial; "></div>
												</td>
												<td class="aui_main" style="width: auto; height: auto; ">
													<div class="aui_content" style="padding: 20px 25px; "></div>
												</td>
											</tr>
											<tr>
												<td colspan="2" class="aui_footer">
													<div class="aui_buttons" style="display: none; "></div>
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
							<td class="aui_se" style="cursor: se-resize; "></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="xuankeright" style="width: 99.8%; border: #FFF 1px solid; min-height: 520px">
			<div class="kaoshicon4">
				<div class="cls"></div>
				<form action="saveScoNote" method="post" name="theForm" id="theForm" class="form">
					
					
					<input type="hidden" value="${noteinfo.noteId}" name="noteId" />
					<textarea id="description" name="noteContent" style="resize:none;float: left; width: 99%; height: 410px; padding-left: 10px; display: none; ">
						<%=htmlspecialchars(((Noteinfo)request.getAttribute("noteinfo")).getNoteContent()) %>
					</textarea>
					<br>
					<div class="cls"></div>
					<div style="color: #aaa; font-size: 12px; float: right">上次保存时间: ${noteinfo.noteTime }</div>
					<div class="cls"></div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary" style="float:right" onclick="javascript:art.dialog.tips(&#39;正在保存中，请稍候...&#39;);">
							<i class="icon-save"></i> 修 改</button>
					</div>
				</form>
				<div class="cls"></div>
			</div>
		</div>
		<%!
			private String htmlspecialchars(String str) {
				str = str.replaceAll("&", "&amp;");
				str = str.replaceAll("<", "&lt;");
				str = str.replaceAll(">", "&gt;");
				str = str.replaceAll("\"", "&quot;");
				return str;
		}
		%>
		<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial; "></div>
	</body>
</html>
