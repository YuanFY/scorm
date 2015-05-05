<%@page import="com.scorm.action.UserinfoAction"%>
<%@page import="com.scorm.action.ActionSupport"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@page import="com.scorm.utils.UserCommon"%>
<%@page import="com.scorm.service.UserinfoService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TR height=28>
    <TD background=images/title_bg1.jpg>当前位置: </TD></TR>
  <TR>
    <TD bgColor=#b1ceef height=1></TD></TR>
  <TR height=20>
    <TD background=images/shadow_bg.jpg></TD></TR></TABLE>
    <%
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    	UserinfoService service = (UserinfoService)context.getBean("userinfoService");
    	Userinfo userinfo =  (Userinfo)session.getAttribute(UserCommon.USERINFO);
    	System.out.println(userinfo);
    	List<Userinfo> list = service.findByUserId(userinfo.getUserId());
    	Userinfo uinfo = null;
    	if(list!=null && list.size()>0)
    		uinfo = list.get(0);
    	
    %>
<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
  <TR height=100>
    <TD align=middle width=100><IMG height=100 src="images/admin_p.gif" 
      width=90></TD>
    <TD width=60>&nbsp;</TD>
    <TD>
      <TABLE height=100 cellSpacing=0 cellPadding=0 width="100%" border=0>
        
        <TR>
          <TD>当前时间：<%=(new Timestamp(System.currentTimeMillis())) %></TD></TR>
        <TR>
          <TD style="FONT-WEIGHT: bold; FONT-SIZE: 16px"><%=uinfo.getUserName() %></TD></TR>
        <TR>
          <TD>欢迎进入网站管理中心！</TD></TR></TABLE></TD></TR>
  <TR>
    <TD colSpan=3 height=10></TD></TR></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
  <TR height=20>
    <TD></TD></TR>
  <TR height=22>
    <TD style="PADDING-LEFT: 20px; FONT-WEIGHT: bold; COLOR: #ffffff" 
    align=middle background=images/title_bg2.jpg>您的相关信息</TD></TR>
  <TR bgColor=#ecf4fc height=12>
    <TD></TD></TR>
  <TR height=20>
    <TD></TD></TR></TABLE>
    
<TABLE cellSpacing=0 cellPadding=2 width="95%" align=center border=0>
  <TR>
    <TD align=right width=100>登陆帐号：</TD>
    <TD style="COLOR: #880000"><%=uinfo.getUserName() %></TD></TR>
  <TR>
    <TD align=right>性别：</TD>
    <TD style="COLOR: #880000"><%=uinfo.getUserSex() %></TD></TR>
  <TR>
  <TR>
    <TD align=right>邮箱：</TD>
    <TD style="COLOR: #880000"><%=uinfo.getUserEmail() %></TD></TR>
  <TR>
 
  <TR>
    <TD align=right>上次登录时间：</TD>
    <TD style="COLOR: #880000"><%=uinfo.getLoginTime() %></TD></TR>
  <TR>
    </TABLE></BODY></HTML>