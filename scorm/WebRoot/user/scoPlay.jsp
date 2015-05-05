<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'scoPlay.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
    <%
  		int cId = Integer.parseInt(request.getParameter("cId"));
		String courseID = (String)request.getParameter( "courseID" );
		String requestedSCO = (String)request.getParameter( "scoID" );
		String buttonType = (String)request.getParameter( "button" );
		request.getSession().setAttribute("cId", cId);
		request.getSession().setAttribute("courseID", courseID);
		request.getSession().setAttribute("requestedSCO", requestedSCO);
		request.getSession().setAttribute("buttonType", buttonType);
  %>
  
  <frameset border="0"  cols="67%, *"  >
		<frame name="play" src="${pageContext.request.contextPath }/user/play.jsp" >
		<frame name="note" src="${pageContext.request.contextPath }/user/note.jsp" >
	</frameset>
  
</html>
