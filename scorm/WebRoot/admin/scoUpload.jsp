<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>jQuery formValidator</title>
		<script
			src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"
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
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
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
			$("#problemNameTip").html("*课程名不能为空");
			$("#problemNameTip").css("color",'red');
			flag = false;
		}
		if ( courseInfo.coursezipfile.value == "" ) 
		{
			$("#userNameTip").html("*上传的课件不能为空");
			$("#userNameTip").css("color",'red');
			flag = false;
		}
		var str = courseInfo.coursezipfile.value;
		if ( str.substr(str.length-3,3) != "zip"  ) 
		{
			$("#userNameTip").html("*后缀名必须为zip");
			$("#userNameTip").css("color",'red');
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
	</head>

	<body>



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
														<span class="STYLE3">当前位置</span>：[上传课件]-[管理员管理]
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
		
	
<s:form method="post" action="LMSCourseImport.jsp" name="courseInfo" onSubmit="return checkValues()"
       enctype="multipart/form-data">
			<div id="errorlist">
			</div>
			<table border="0px" style="font-size: 12px" width="100%">
				<tr>
					<td align="right" width="135">
						课程名：
					</td>
					<td width="265">
						<select id="coursename" name="coursename" style="width:150px">
							<option value="">--请选择--</option>
							<c:forEach var="m" items='${requestScope.typeList}'> 
								<option value="${m.courseId}">${m.courseName}</option>
							</c:forEach>
						</select>
					</td>
					<td width="330">
						<p id="problemNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						选择课件：
					</td>
					<td width="265">
						<input id="coursezipfile" name="coursezipfile" type=file>
					</td>
					<td width="330">
						<p id="userNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						是否为测试课件：
					</td>
					<td width="265">
						<input id="isTest1" name="isTest1" type="radio" value="1" >是
						<input id="isTest1" name="isTest1" type="radio" value="0" checked="checked">否
					</td>
					<td width="330">
						<p id="userNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
			</table>
			
			<input type="submit" name="button" id="button"  value="提交"/>
			<input type=hidden name="theManifest">
   			<input type=hidden name="theZipFile">
   			<input type=hidden name="isTest">
		</s:form>
		<div id="blank" style="height:100px"></div>
	</body>
	
	
	
</html>
