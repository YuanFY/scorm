<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>管理中心登陆 V1.0</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
</HEAD>
<BODY>

<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" bgColor=#002779 
border=0>
  <TR>
    <TD align=middle>
      <TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
        <TR>
          <TD><IMG height=23 src="images/login_1.jpg" 
          width=468></TD></TR>
        <TR>
          <TD><IMG height=147 src="images/login_2.jpg" 
            width=468></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=468 bgColor=#ffffff border=0>
        <TR>
          <TD width=16><IMG height=122 src="images/login_3.jpg" 
            width=16></TD>
          <TD align=middle>
            <TABLE cellSpacing=0 cellPadding=0 width=230 border=0>
              <FORM name=form1 method=post id="form1">
              
              <TR height=36>
                <TD></TD>
                <TD width=100>用户名</TD>
                <TD><INPUT id="userName"
                  style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid" 
                  maxLength=30 size=24 value="" name=name></TD></TR>
              <TR height=36>
                <TD>&nbsp; </TD>
                <TD>密　码</TD>
                <TD><INPUT id="userPwd"
				  style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid" 
                  type=password maxLength=30 size=24 value="" 
                name=pass></TD></TR>
                <TR height=36>
                <TD>&nbsp; </TD>
                <TD>验证码</TD>
                <TD>
					<INPUT id="code" onblur="validateCode()"
				  style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid" 
                  type=text maxLength=4 size=24 value="" 
                name=pass>
				</TD></TR><TR height=36>
                <TD>&nbsp; </TD>
                <TD> </TD>
                <TD>
					<a href="javascript:change()" title="点击换一张"> <img id="img"
						class="img"
						src="${pageContext.request.contextPath }/userinfoAction_createImage.action" />
					</a> <span id="t_CheckCodeTip" class="reg_m"></span>
					
		</script>
				</TD></TR>
              <TR height=5>
                <TD colSpan=3></TD></TR>
              <TR>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD><INPUT id="btnSubmit" type="image" height=18 width=70  
                  src="images/bt_login.gif"  onclick="return false;"><span id="t_spanid" style="color:red;display:none">用户名或密码错误</span>
                  <span id="t_spanid1" style="color:red;display:none">验证码错误</span></TD></TR></FORM></TABLE></TD>
          <TD width=16><IMG height=122 src="images/login_4.jpg" 
            width=16></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
        <TR>
          <TD><IMG height=16 src="images/login_5.jpg" 
          width=468></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
        <TR>
          <TD align=right><A href="javascript:;" target=_blank><IMG 
            height=26 src="images/login_6.gif" width=165 
            border=0></A></TD></TR></TABLE></TD></TR></TABLE>
            
	<script type="text/javascript">
		function change() {
			var imgObj = document.getElementById("img");
			imgObj.src = imgObj.src + "?" + new Date().getTime();
		}
		var f  = true;
		function validateCode(){
			var params = {
					code : $("#code").val()
				};
				$.ajax({
					type:"post",
					url:"userinfoAction_validateCode.action",
					data:params,
					dataType:"json",
					async:false,
					success:function(data){
						var d = eval("("+data+")");//eval函数是将括号中的东西解析成jquery对象
						if (d.flag == false) {
							$("#t_spanid").css("display","none");
							$("#t_spanid1").css("display","");
							f = false;
						} else{
							$("#t_spanid1").css("display","none");
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						f = false;
						alert("errror");
					}
				});
				return f;
		}
		$("#btnSubmit").click(function(){
			
			if(f)
				validateCode();
			
			if(f==false){
				f = true;
				return false;
			}
			$.ajax({
				type:"post",
				url:"admininfoAction_loginValidate.action",
				data:{
					userName:$("#userName").val(),
					userPwd:$("#userPwd").val()
				},
				dataType:"json",
				success:function(data){
					var d = eval("("+data+")");//eval函数是将括号中的东西解析成jquery对象
					if(d.flag){
						$("#form1").attr("action","${pageContext.request.contextPath}/admin/index.jsp");
						$("#form1").submit();
					}else{
						$("#t_spanid").css("display","");
						//t_spanid("用户名或密码错误");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("系统异常，请稍后重试！");
				}
			});
			//return false;
		});
	</script>            
        
            
</BODY>      
</HTML>
