<%@page import="com.scorm.vo.UserFinish"%>
<%@page import="com.scorm.utils.UserUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'common.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/ext.css">
	
	<link href="${pageContext.request.contextPath }/user/css/idialog.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/user/css/tabview0.css" rel="stylesheet" type="text/css"
	media="screen">
	
<link href="${pageContext.request.contextPath }/user/css/evabootstrap-full.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath }/user/css/navbar-blue.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/user/css/font-awesome.css" rel="stylesheet" type="text/css">
 
 							<style>
ol,ul {
	list-style: none outside none;
}

.extend-foot {
	padding: 0px;
	margin-left: 2px;
}
/*群组列表*/
.group-list {
	margin: 0 -20px 0 0;
	_margin: 0;
}

.group-list li {
	float: left;
	/*width: 315px;*/
	width: 276px;
	height: 138px;
	padding: 15px 0;
	margin: 0 18px 0 0;
	*width: 275px;
	_margin: 0 30px 0 0;
	overflow: hidden;
	*zoom: 1;
}

.group-list .group-pic {
	float: left;
	width: 110px;
	height: 110px;
}

.group-list .group-info {
	margin: 0 0 0 124px;
	text-align: left
}

.group-list .group-info .crstitle {
	font-weight: bold;
	font-size: 14px;
}

.group-list .group-info h3 {
	font-size: 14px;
	margin: 0 5px 5px 0;
	display: inline-block;
}

.group-list .group-info p {
	line-height: 21px;
	color: #999;
	word-break: break-all;
}

/*群组分类*/
.group-type dl {
	border-bottom: 1px dotted #ddd;
	padding: 15px 0 5px 0;
}

.group-type dt {
	margin: 0 0 10px 0;
	font-weight: bold;
	border-left: 2px solid #aaa;
}

.group-type dt a {
	margin: 0 0 0 5px;
}

.group-type dd a {
	margin: 0 10px 10px 0;
	display: inline-block;
}

.course_pic {
	width: 110px;
	height: 110px;
}
</style>
  </head>
  
  <body>
  <div id="testTab" style="display:none">
    <div class="extend-foot">
		<div class="group-list">
			<ul id="ulId">
			<%
				UserUtils utils = new UserUtils();
				List<UserFinish> l = utils.getTestCourse();
				request.setAttribute("testList", l);
				request.setAttribute("testTotal", l.size());
			%>
				<c:forEach var="m" items='${testList }' begin="0" end="6"> 
				<li>
					<div class="group-pic">
						<a
							href="${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=${m.courseId}&userId=${userinfo.userId}"
							title=""
							> <img class="course_pic img-rounded"
							src="${pageContext.request.contextPath }/${m.picture}"
							onerror="javascript:load_default_image(this);" height="110"
							width="110"> </a>
					</div>
					<div class="group-info">
						<div class="crstitle">
							<a
								href="${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=${m.courseId}&userId=${userinfo.userId}"
								title=""
								>${m.courseName } </a>
						</div>
						<p>类型: ${m.courseType }</p>
						<p>过期时间: ${m.time }</p>
						<p>状态： ${m.finish }</p>
						<p>
							<a 
								href="${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=${m.courseId}&userId=${userinfo.userId}"
								title="用户使用指导" style="color: #666"
								>查看课程介绍 </a>
						</p>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>

		<div class="cls"></div>
		<div class="pagination"
			style="float: right; margin-right: 20px;">
			<span class="f_l f6"
				style="margin-right: 10px; margin-left: 10px">总计 <b>${requestScope.testTotal}
			</b> 门课程</span> <c:if test="${request.testTotal>6}">
				<a
				href="#"
				class="btn btn-mini">更多… </a>
				</c:if>
		</div>
		<div class="cls"></div>
	</div>
	</div>
  </body>
</html>
