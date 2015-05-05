<%@page import="com.scorm.vo.Userinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
														<span class="STYLE3">当前位置</span>：[信息管理]-[个人信息管理]
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
	    	
	   		var name4 = $('#userPhone').val();
	    	var reg1 = "^([0-9]{11})?$";     
	        var r = name4.match(reg1);  
	    	if(name4 == null||""==name4)
	    	{
	    		$('#userPhoneTip').html("*用户号码不能为空");
	    		$('#userPhoneTip').css("color",'red');
	    		return false;
	    	}   
	        else if(r==null) 
	        {   
	            $('#userPhoneTip').html("*用户号码格式不对"); 
	            $('#userPhoneTip').css("color",'red'); 
	            return false;
	        }  
	        else
	        {
	        	$('#userPhoneTip').html(""); 
	        }
// 	        alert(name4);
	    	 
	    	var name5 = $('#userEmail').val();
	    	var reg = "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$";     
	        var r = name5.match(reg);  
	    	if(name5 == null||""==name5)
	    	{
	    		$('#userEmailTip').html("*用户邮箱不能为空");
	    		$('#userEmailTip').css("color",'red');
	    		return false;
	    	}   
	        else if(r==null) 
	        {   
	            $('#userAddressTip').html("*用户邮箱格式不对"); 
	            $('#userAddressTip').css("color",'red'); 
	            return false;
	        }  
	        else
	        {
	        	$('#userEmailTip').html("");
	        }
// 	        alert(name5);
	
	    	return true;
	    }
	    
	    );
	});
</script>
<s:form action="updateUserInfo" namespace="/" method="post">
			<div id="errorlist">
			</div>
			<table border="0px" style="font-size: 12px" width="100%">
				<tr>
					<td align="right" width="135">
						用户编号：
					</td>
					<td width="265">
						<input type="text" size="26" name="userId" value="${ userinfo.userId}" readOnly/>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						用户名称：
					</td>
					<td width="265">
						<input type="text" size="26" id="userName" name="userName" title="用户名字不能为空" value="${userinfo.userName}" readOnly />
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						用户性别：
					</td>
					<td width="265">
						<select size="1" id="userSex" name="userSex">
							<option value="男" <%=((Userinfo)session.getAttribute("userinfo")).getUserSex().equals("男") ? "selected" : "" %>>男</option>
							<option value="女" <%=((Userinfo)session.getAttribute("userinfo")).getUserSex().equals("女") ? "selected" : "" %>>女</option>
						</select>
					</td>
				</tr>
				
			    <tr>
					<td align="right" width="135">
						用户号码：
					</td>
					<td width="265">
						<input type="text" size="26" name="userPhone" value="${userinfo.userPhone }" id="userPhone" title="用户号码不能为空"/>
					</td>
					<td  width="330">
						<p id="userPhoneTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 
				
				<tr>
					<td align="right" width="135">
						用户邮箱：
					</td>
					<td width="265">
						<input type="text" size="26" name="userEmail" value="${userinfo.userEmail }" id="userEmail" title="用户邮箱不能为空"/>
					</td>
					<td  width="330">
						<p id="userEmailTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 
				
				<tr>
					<td align="right" width="135">
						激动状态：
					</td>
					<td width="265">
						<select size="1" id="userActive" name="userActive">
							<option value="0">未激活</option>
							<option value="1">已激动</option>
						</select>
						<script>
							document.getElementById('userActive').value = ${userinfo.userActive} ;
						</script>
					</td>
					<td  width="330">
						<p id="userActiveTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 
				
				<tr>
					<td align="right" width="135">
						用户权限：
					</td>
					<td width="265">
						<select size="1" id="isAdmin" name="isAdmin">
							<option value="0">学生</option>
							<option value="1">老师</option>
							<option value="2">管理员</option>
						</select>
						<script>
							document.getElementById('isAdmin').value = ${userinfo.isAdmin} ;
						</script>
					</td>
					<td  width="330">
						<p id="isAdminTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 
				
				<tr>
					<td align="right" width="135">
						登录时间：
					</td>
					<td width="265">
						<input type="text" size="26" name="loginTime" value="${fn:substring(userinfo.loginTime,0,19) }" id="loginTime" readOnly/>
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
