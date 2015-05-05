<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/user/css/style2.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/move.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
window.onload = function() {  
	/*
		注意document事件
		如果与网站有别的document事件有冲突记得叫我
	*/ 
	var oDiv = document.getElementById("boxDiv");
	var oDivX = oDiv.offsetLeft;
	var oDivY = oDiv.offsetTop;	
	var oTxt = document.getElementById("txt");		
	var oBoxHead = classLoader(oDiv, 'box_head')[0];		
	var oCloseBtn = classLoader(oDiv, 'closeBtn')[0];
	
	oDiv.onmouseover = function() {	
		//缓冲移动： 对象(oDiv), boxShadow变到20, 还有一个fn(function函数可自由添加)
		bufferMove(oDiv, {boxShadow:20}, {timer:0});
	};
	oDiv.onmouseout = function() {		
		bufferMove(oDiv, {boxShadow:0}, {timer:0});
	};
	oTxt.onclick = function() {
		startMove(oDiv, {height:240}, {sum:0, iSpeed:0, timer:0});
	};
	//div移动
	oBoxHead.onmousedown = function(ev) {
		var oEvent = event || ev;
		var disX = oEvent.clientX-oDiv.offsetLeft;
		var disY = oEvent.clientY-oDiv.offsetTop;
		document.onmousemove = function(ev) {
			var oEvent = event || ev;
			var x = oEvent.clientX-disX;
			var y = oEvent.clientY-disY;
			var e = document.documentElement;
			var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
			var scrollLeft = document.documentElement.scrollLeft||document.body.scrollLeft;
			if ( x <= 0 ) x = 0;
			else if ( x >= scrollLeft+e.clientWidth-oDiv.offsetWidth ) {
				x = scrollLeft+e.clientWidth-oDiv.offsetWidth;
			}
			if ( y <= 0 ) y = 0;
			else if ( y >= scrollTop+e.clientHeight-oDiv.offsetHeight ) {
				y = scrollTop+e.clientHeight-oDiv.offsetHeight;
			}
			oDiv.style.left = x+'px';
			oDiv.style.top = y+'px';						
		};
		document.onmouseup = function() {
			document.onmousemove = null;
			document.onmousemove = null;
			startMove(oDiv, {height:290}, {sum:0, iSpeed:0, timer:0});
			startMove(oTxt, {height:170}, {sum:0, iSpeed:0, timer:0});
			startMove(oDiv, {width:429}, {sum:0, iSpeed:0, timer:0});
			startMove(oTxt, {width:340}, {sum:0, iSpeed:0, timer:0});
			oCloseBtn.style.display = 'block';
		};
		return false;
	};
	oCloseBtn.onclick = function (ev){
		oDiv.style.display = "none";
		var oEvent = ev||event;
		if( ev && ev.stopPropagation ){
	 		ev.stopPropagation();
		}else event.cancelBubble = true;
		//弹性移动： 对象(oDiv), width变到379, 一个公共对象common可照抄
		startMove(oDiv, {width:379}, {sum:0, iSpeed:0, timer:0});
		startMove(oDiv, {height:190}, {sum:0, iSpeed:0, timer:0});	
		startMove(oTxt, {width:290}, {sum:0, iSpeed:0, timer:0});	
		startMove(oTxt, {height:120}, {sum:0, iSpeed:0, timer:0});	
		startMove(oDiv, {left:oDivX}, {sum:0, iSpeed:0, timer:0});
		startMove(oDiv, {top:oDivY}, {sum:0, iSpeed:0, timer:0});
		oCloseBtn.style.display = 'none';
	};
	document.onscroll = function() {
		var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
		if ( scrollTop >= oDivY ) {
			oDiv.style.position = 'fixed';
			oDiv.style.top = '0px';
		}else {
			oDiv.style.position = 'absolute';
			oDiv.style.top = oDivY+'px';
		}
	};	
	var note = document.getElementById("note");
	note.onclick = function(){
		oDiv.style.display = "";
	};
};
function clearWord() {
	var oTxt = document.getElementById("txt");
	oTxt.value = "";
};
</script>
</head>

<body style="height:2000px;">
<input type="button" value="我要写笔记" id="note" style="float:right" />
<div id="boxDiv" class="box" style="display:none">
	<div class="box_head">    	
    	<span class="headFont">我的笔记</span>
        <!--关闭按钮-->
        <a href="javascript:void(0)" class="closeBtn"><img src="${pageContext.request.contextPath}/user/img/btn1.png" width="22" height="22" /></a>
    </div>
    <div class="content">
   	  <form action="#" method="post" style="padding-top:10px;">
        <table>
                <tbody>
                	<tr>
                    	<th>内容</th>
                        <td colspan="2">
                        	<textarea style="margin-top:5px;" id="txt"></textarea>
                        </td>
                    </tr>
                    <tr style="text-align:center; vertical-align:bottom;">
                    <!--保存、清除按钮可换-->
                   		<th style="height:45px;"></th>
                    	<td><input class="btn" type="button" id="save" value="保存" /></td>   
                        <td><input class="btn" type="button" value="清除" onclick="clearWord()" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" value="${userinfo.userId }" id="userId" />
            <input type="hidden" value="${cId }" id="courseId" />
            <input type="hidden" value="${courseID }" id="scoId" />
        </form>
    </div>
</div>
<script type="text/javascript">

	$("#txt").focus(function(){
		if($("#txt").val()=="添加成功"){
			$("#txt").val("").css("color","black");
		}
	});
	 $("#save").click(function(){
		 var params = {
				 userId:$("#userId").val(),
				 courseId:$("#courseId").val(),
				 scoId:$("#scoId").val(),
				 scoNote:$("#txt").val()
		 };
		 $.ajax({
				type:"post",
				url:"uc_updateUsercourseinfo.action",
				data:params,
				dataType:"json",
				//async:false,
				success:function(data){
					$("#txt").val("添加成功").css("color","gray");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("系统异常，请稍后重试！");
				}
			});
	 });
</script>
</body>
</html>
