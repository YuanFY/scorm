<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	function toLogout(){
		var sure = window.confirm("确定要退出吗？");
		if(sure){
			//window.location.href="${pageContext.request.contextPath }/admin_adminLogout.action";
			$("#logout").attr("href","admin_adminLogout.action");
		}
	}
</script>
</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" 
background="images/header_bg.jpg" border=0>
  <TR height=56>
    <TD width=260><IMG height=56 src="images/header_left.jpg" 
    width=260></TD>
    <TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-RIGHT: 20px "  align=right> 当前用户：${userinfo.userName } &nbsp;&nbsp; <A style="COLOR: #fff;cursor:pointer" 
      onclick="toLogout()"  target="_parent" id="logout">退出系统</A> 
    </TD>
   </TR></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TR bgColor=#1c5db6 height=4>
    <TD></TD></TR></TABLE></BODY></HTML>
