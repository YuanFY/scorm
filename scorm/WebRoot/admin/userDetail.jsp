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
														<span class="STYLE3">当前位置</span>：[人员管理]-[学员管理]
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
    
    	var name = $('#userName').val();
    	if(name == null||""==name)
    	{
    		$('#userNameTip').html("*学员名字不能为空");
    		$('#userNameTip').css("color",'red');
    		return false;
    	}
    	    	//alert(name);
   		var name4 = $('#userPhone').val();
    	if(name4 == null||""==name4)
    	{
    		$('#userPhoneTip').html("*请填入学员电话");
    		$('#userPhoneTip').css("color",'red');
    		return false;
    	}
    	var reg1 = "^([0-9]{11})?$";     
          var r = name4.match(reg1);     
         if(r==null) {   
            $('#userPhoneTip').html("*格式不对"); 
            $('#userPhoneTip').css("color",'red'); 
            return false;
        }  
    	 
    	var name5 = $('#userEmail').val();
    	if(name5 == null||""==name5)
    	{
    		$('#userAddressTip').html("*请填入学员邮箱");
    		$('#userAddressTip').css("color",'red');
    		return false;
    	}
    	var reg = "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$";     
        var r = name5.match(reg);     
        if(r==null) {   
            $('#userAddressTip').html("*格式不对"); 
            $('#userAddressTip').css("color",'red'); 
            return false;
        }  

    	return true;
    }
    
    );
    });
</script>
<s:form action="changeUser" namespace="/" method="post">
			<div id="errorlist">
			</div>
			<table border="0px" style="font-size: 12px" width="100%">
				<tr>
					<td align="right" width="135">
						学员编号：
					</td>
					<td width="265">
						<input type="hidden" size="26" name="userId" value="${ user.userId}" />
						<s:property value="user.userId" />
					</td>
					<td width="330">
						<p id="problemNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						学员姓名：
					</td>
					<td width="265">
						<input type="text" id="userName" name="userName" title="学员名字不能为空"
							value="${user.userName}"  />
					</td>
					<td width="330">
						<p id="userNameTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="135">
						学员性别：
					</td>
					<td width="265">
						<input type="hidden" id="userSex" name="villageId" 
							value="${user.userSex}" style="width: 280px" />
						<s:property value="user.userSex" />
					</td>
				</tr>
				<tr>
					<td align="right" width="135">
						学员号码：
					</td>
					<td width="265">
						<input type="text" size="26" name="userPhone" value="<s:property value="user.userPhone " />" id="userPhone" title="学员号码不能为空"/>
					</td>
					<td  width="330">
						<p id="userPhoneTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 
			    <tr>
					<td align="right" width="135">
						学员邮箱：
					</td>
					<td width="265">
						<input type="text" size="26" name="userEmail" value="<s:property value="user.userEmail " />" id="userEmail" title="学员邮箱不能为空"/>
					</td>
					<td  width="330">
						<p id="userAddressTip" style="width: 280px"><font color="red"></font></p>
					</td>
				</tr> 

			</table>
			
			<input type="submit" name="button" id="button"  value="提交"/>
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
