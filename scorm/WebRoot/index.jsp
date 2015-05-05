<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$("button").click(function(){
				var params = {
						userName:$("input[name=userName]").val(),
						userPwd:$("input[name=userPwd]").val()
				};
				$.ajax({
					type:"post",
					url:"login.action",
					data:params,
					dataType:"json",
					success:function(data){
						//var d = eval("("+data+")");//eval函数是将括号中的东西解析成jquery对象
						alert(1);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(XMLHttpRequest.status);
						alert("系统异常，请稍后重试！");
					}
				});
			});
			
		});
	</script>
  </head>
  
  <body>
  <img alt="" src="upload/coursePicture/2.jpg">
  
</html>
