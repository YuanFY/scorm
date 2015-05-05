<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="author" content="xiaohe">
		<title>IT学源网-笔记</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/ext.css">
		<script type="text/javascript" src="js/commons.js"></script>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" href="#" />
		<script type="text/javascript" src="js/artDialog.js"></script>
		<script type="text/javascript" src="js/iframeTools.js"></script>
		<script type="text/javascript" src="js/dialog-common.js"></script>
		<script type="text/javascript" src="js/jquery.wtooltip.js"></script>
		<script type="text/javascript">
			var lang_loading="数据正在载入中,请稍候...";
			var lang_ConfirmOperation="确认操作";
			var total_top_menu_item=8;
			var menus=new Array();menus[0]=1;menus[1]=2;menus[2]=3;menus[3]=4;menus[4]=5;menus[5]=6;menus[6]=7;menus[7]=8;
		</script>
		<link href="css/evabootstrap-full.css" rel="stylesheet" type="text/css">
		<link href="css/navbar-blue.css" rel="stylesheet" type="text/css"> 
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
		<style>
			.breadcrumb li { display:inline; }
			.typocn button{ line-height:1.4; }
			.table thead th { background-color:#F5F5F5;text-align:center; }
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
			<link href="css/default.css" rel="stylesheet" type="text/css" media="screen">
			<script type="text/javascript" src="js/kindeditor.js"></script>
			<script type="text/javascript" src="js/zh_CN.js"></script>
			<script type="text/javascript">
				var editor;
				KindEditor.ready(function(K) {
					editor = K.create("#description",{
						/*themeType : "simple",*/
						resizeType: 1,
					 	newlineTag : "br",
						allowPreviewEmoticons : false,
						allowUpload : true,
						allowFileManager : false,
						items : ["source","|","fontname", "fontsize", "|", "bold", "italic", "underline",
						"removeformat", "|", "justifyleft", "justifycenter", "justifyright", "insertorderedlist",
						"insertunorderedlist", "|", "image", "link"]
					});
				});
			</script>
		</head>
		
		<body class="typocn">
			<div class=" aui_state_focus" style="position: absolute; left: -9999em; top: 206px; display: block; width: auto; z-index: 1987;">
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
															<div class="aui_title" style="cursor: move; display: block;">消息</div>
															<a class="aui_close" href="javascript:/*artDialog*/;" style="display: block;">×</a>
														</div>
													</td>
												</tr>
												<tr>
													<td class="aui_icon" style="display: none;">
														<div class="aui_iconBg" style="background-image: none; background-position: initial initial; background-repeat: initial initial;"></div>
													</td>
													<td class="aui_main" style="width: auto; height: auto;">
														<div class="aui_content" style="padding: 20px 25px;">
															<div class="aui_loading"><span>Loading...</span></div>
														</div>
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

			<div class="xuankeright" style="width: 99.8%; border: #FFF 1px solid; min-height: 520px">
				<div class="kaoshicon4">
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>课程</th>
								<th>最后更新时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.noteinfo" var="note">
								<tr>
									<td><img src="img/fun3.gif"></td><td style="text-align:center">${courseName}</td> 
									<td style="text-align:center">${note.noteTime  }</td>
									<td style="text-align:center"><a href="#" onclick="javascript:dialog_open_yesno(&#39;getScoNote.action?noteId=${note.noteId }#&#39;,&#39;课程笔记&#39;,&#39;84%&#39;,&#39;80%&#39;);">查看笔记</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="cls"></div>
				</div>
			</div>
		</body>
</html>