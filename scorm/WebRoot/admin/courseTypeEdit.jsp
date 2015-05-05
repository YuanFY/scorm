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
		<s:elseif test="#session.updateResult == -1">
			<script>
				alert('修改失败，存在修改的课程类型');
			</script>
			<%
				request.getSession().removeAttribute("updateResult");
			 %>
		</s:elseif>

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
														<span class="STYLE3">当前位置</span>：[课程管理]-[课程类型管理]
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
    
    	var name = $('#courseType').val();
    	if(name == null||""==name)
    	{
    		$('#courseTypeTip').html("*课程类型不能为空");
    		$('#courseTypeTip').css("color",'red');
    		return false;
    	}
    	else
    	{
    		$('#courseTypeTip').html("");
    	}

    	return true;
    }
    
    );
    });
</script>
		<s:form action="updateCourseType" method="post">
			<div id="errorlist">
			</div>
			<table border="0px" style="font-size: 12px" width="100%">
				<tr>
					<td align="right" width="135">
						课程编号：
					</td>
					<td width="265">
						<input type="text" size="26" name="courseTypeId" value="${courseTypeinfo.courseTypeId}" readOnly/>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						课程类型：
					</td>
					<td width="265">
						<input type="text" size="26" id="courseType" name="courseType" title="课程类型名字不能为空" value="${courseTypeinfo.courseType}"  />
					</td>
					<td width="330">
						<p id="courseTypeTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
			</table>
			
			<input type="submit" name="button" id="button"  value="修改"/>
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
