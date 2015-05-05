<%@page import="com.scorm.vo.Coursetypeinfo"%>
<%@page import="com.scorm.vo.Courseinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>jQuery formValidator</title>
		<script
			src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"
			type="text/javascript"></script>
	</head>
	
		
	<style type="text/css">
body,div {
	font:  14px "微软雅黑","Consolas", "Monaco", "Bitstream Vera Sans Mono", "Courier New", Courier, monospace;
}

tr {
	height: 32px;
}

#button {
	margin-left: 200px;
}
</style>
	</head>

	<body>
	
		<s:if test="#session.updateResult == 1">
			<script>
				alert('修改成功');
			</script>
			<%
				request.getSession().removeAttribute("updateResult");
			 %>
		</s:if>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30"
					background="${pageContext.request.contextPath }/admin/images/tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img
									src="${pageContext.request.contextPath }/admin/images/tab_03.gif"
									width="12" height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="46%" valign="middle">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="5%">
														<div align="center">
															<img
																src="${pageContext.request.contextPath }/admin/images/tb.gif"
																width="16" height="16" />
														</div>
													</td>
													<td width="40%" class="STYLE1">
														<s:url action="enterProblemManageList.action"
															var="problemManageListUrl">
														</s:url>
														<span class="STYLE3">当前位置</span>：[课程管理]-[课程信息管理]
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td width="16">
					<img
						src="${pageContext.request.contextPath }/admin/images/tab_07.gif"
						width="16" height="30" />
				</td>
			</tr>
		</table>
		
	<script>
jQuery(document).ready(function($) {
    $('#button').click(function empty()
    {
    	var name = $('#courseName').val();
//     	    	alert(name);
    	if(name == null||""==name)
    	{
    		$('#courseNameTip').html("*课程名称不能为空");
    		$('#courseNameTip').css("color",'red');
    		return false;
    	}
    	else
    	{
    		$('#courseNameTip').html("");
    	}
    	
    	return true;
    }
    
    );
    });
</script>
<s:form action="addCourseInfo" namespace="/" encType="multipart/form-data" method="post">
			<div id="errorlist">
			</div>
			<table border="0px" style="font-size: 12px" width="100%">
				
				<tr>
					<td align="right" width="135">
						课程名称：
					</td>
					<td width="265">
						<input type="text" size="26" id="courseName" name="courseName" title="课程名称不能为空"  />
					</td>
					<td width="330">
						<p id="courseNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						课程类型：
					</td>
					<td width="265">
						<select size="1" name="courseType">
							<s:iterator value="#session.coursetypeinfoList" var="course">
								<option value=<s:property value="#course.courseTypeId"/>>
									<s:property value="#course.courseType"/>
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						课程封面：
					</td>
					<td width="265">
						<s:file name="coursePicture" accept="image/bmp,image/jpeg" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="135">
						课程简介：
					</td>
					<td width="265">
						<textarea name="courseContent" rows="10" cols="24" style="resize:none"></textarea>
					</td>
				</tr>
			</table>
			
			<input type="submit" name="button" id="button"  value="添加"/>
		</s:form>
		<div id="blank" style="height:100px"></div>
	</body>
	
	
	<!-- kindeditor Start -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugin/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath }/resource/plugin/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath }/resource/plugin/kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath }/resource/plugin/kindeditor/kindeditor.js"></script>
			
		<!-- kindeditor End -->
	
	<!-- JQuery UI depend
  ================================================== -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/style/jquery-ui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/script/jquery-ui.js"></script>
	
</html>
