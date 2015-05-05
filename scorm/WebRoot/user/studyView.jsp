<%@page import="com.scorm.vo.Coursereg"%>
<%@page import="com.scorm.service.CourseregService"%>
<%@page import="com.scorm.service.UserinfoService"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@page import="com.scorm.vo.Noteinfo"%>
<%@page import="com.scorm.service.NoteinfoService"%>
<%@page import="com.scorm.vo.Discussinfo"%>
<%@page import="com.scorm.service.DiscussinfoService"%>
<%@page import="com.scorm.vo.Courseinfo"%>
<%@page import="com.scorm.service.CourseinfoService"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.scorm.vo.Scoinfo"%>
<%@page import="com.scorm.vo.Viewcourse"%>
<%@page import="com.scorm.service.ScoinfoService"%>
<%@page import="com.scorm.service.ViewcourseService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.scorm.action.ActionSupport"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <%
  //断点播放
  		int cId = Integer.parseInt(request.getParameter("cId"));//课程Id
  		int userId = ActionSupport.getSessionUser().getUserId();
  		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
  		ViewcourseService viewcourseService = (ViewcourseService) context.getBean("viewcourseService");
  		ScoinfoService scoinfoService = (ScoinfoService) context.getBean("scoinfoService");
  		UserinfoService userinfoService = (UserinfoService)context.getBean("userinfoService");
  		List<Viewcourse> viewList = viewcourseService.findBysql("from Viewcourse where userId = "+userId +" and courseId = "+cId);
  		int courseID = -1;
  		String str = request.getParameter("scoId");
  		System.out.println("---scoId="+str);
  		//如果上次观看了 就取上次看的章节
  		if(str == null){
  			if(viewList!=null&&viewList.size()>0){
  	  			courseID = viewList.get(0).getScoId();
  	  		}
  			if(courseID == -1){
	  			List<Scoinfo> list = scoinfoService.findByCourseId(cId);
	  			if(list!=null && list.size()>0){
	  				courseID = list.get(0).getScoId();
	  						
	  			}
  			}
  		}else{
  			courseID = Integer.parseInt(str);
  		}
  		//修改打分限制
  		CourseregService courseregService = (CourseregService)context.getBean("courseregService");
  		List<Coursereg> regList = courseregService.findById(userId, cId);
  		if(regList!=null && regList.size()>0){
  			Coursereg reg = regList.get(0);
  			reg.setCourseScore(0);
  			courseregService.update(reg);
  		}
  		System.out.println("courseId="+cId+",scoId="+courseID);
  		//添加进去
  		Viewcourse viewcourse = null;
  		if(viewList!=null && viewList.size()>0){
  			viewcourse = viewList.get(0);
  			viewcourse.setScoId(courseID);
  			viewcourseService.update(viewcourse);
  		}
  		if(viewcourse == null){
  			viewcourse = new Viewcourse();
  			viewcourse.setUserId(userId);
  			viewcourse.setCourseId(cId);
  			viewcourse.setViewTime(new Timestamp(System.currentTimeMillis()));
  			viewcourse.setScoId(courseID);
  	  		viewcourseService.save(viewcourse);
  		}
		String requestedSCO = (String)request.getParameter( "scoID" );
		String buttonType = (String)request.getParameter( "button" );
		
		CourseinfoService courseinfoService = (CourseinfoService) context.getBean("courseinfoService");
		Courseinfo courseinfo = courseinfoService.findByCourseId(cId).get(0);
	    List<Scoinfo> list = scoinfoService.findByCourseId(cId);
	    request.setAttribute("list", list);
		
		
		
		request.getSession().setAttribute("cId", cId);
		request.getSession().setAttribute("courseID", courseID);
		request.getSession().setAttribute("requestedSCO", requestedSCO);
		request.getSession().setAttribute("buttonType", buttonType);
  %>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    	<title>IT学源网-正在观看<%=courseinfo.getCourseName() %></title>
        
        <link rel="stylesheet" type="text/css" href="css/studyView.css">
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/discuss.js"></script>
        
        <link rel="stylesheet" type="text/css" href="css/default.css">
		<script charset="utf-8" src="js/kindeditor.js"></script>
		<script charset="utf-8" src="js/zh_CN.js"></script>
		<script charset="utf-8" src="js/prettify.js"></script>
		<script>
			var update = false;
			var editor, editor1;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="discussContent"]', {
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
				
				editor1 = K.create('textarea[name="message"]', {
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
		</script>
	</head>
    <body>
    	<header id='header' style="">
        	<div class='back'><a href='${pageContext.request.contextPath }/user/courseScoinfo.action?courseId=<%=cId %>&userId=${userinfo.userId}'>&lt;返回</a></div>
            <div class='scoName'>正在观看<%=courseinfo.getCourseName() %></div>
        </header>
        
        <div class='view'>
        	<!-- 课程 课件 -->
        	<div class='view_left' id='view_left'>
            	<div class='menu'>
            	
            	<%=courseinfo.getCourseName() %>
            	</div>
            	<ul>
            		<c:forEach var="m" items="${list }" varStatus="c">
						<c:if test="${m.scoId eq sessionScope.courseID }">
							<li class='current'>
							<a href="${pageContext.request.contextPath }/user/studyView.jsp?cId=<%=courseinfo.getCourseId() %>&scoId=${m.scoId}">
							${c.count }.${m.scoName }
							</a>
							</li> 
						</c:if>
						<c:if test="${m.scoId ne sessionScope.courseID }">
							<li>
							<a href="${pageContext.request.contextPath }/user/studyView.jsp?cId=<%=courseinfo.getCourseId() %>&scoId=${m.scoId}">
							${c.count }.${m.scoName }
							</a>
							</li>
						</c:if>
					</c:forEach> 
                </ul>
            </div>
            
            <!-- 课件视频 -->
            <div class='view_right' id='video'>
            	<iframe  src="${pageContext.request.contextPath}/user/play.jsp" style="width:100%; height: 100%"></iframe>
            	<div class='full_view' style='z-index:1000;'><a href='javascript:void(0)' id='fullView'>全屏观看</a></div>
            </div>
        </div>
        
        <div class='content' style="margin-left: 50px;">
        	<!-- 评论、笔记 -->
            <div class='content_left'>
            	<ul class='content_top'>
                	<li class='current'><a href='javascript:void(0)'>讨论</a></li>
                    <li class='fg' style='background: #ccc; width:1px; height: 18px; margin-right:25px; margin-left:-25px; margin-top: 18px;'></li>
                    <li><a href='javascript:void(0)'>我的笔记</a></li>
                    <li class='fg' style='background: #ccc; width:1px; height: 18px; margin-right:25px; margin-left:-25px; margin-top: 18px;'></li>
                    <li><a href='javascript:void(0)'>笔记分享</a></li>
                </ul>
                <%
                DiscussinfoService discussinfoService = (DiscussinfoService)context.getBean("discussinfoService");
                List<Discussinfo> disList = discussinfoService.findSql("from Discussinfo where userId="+userId+" and courseId="+cId+" order by discussId desc");
                request.setAttribute("disList",disList);
                %>
                <!-- 评论 -->
                <div class='comment'>
                	<div class='editor'>
                		<input type="hidden" name="courseId" id="courseId" value="<%=cId %>">
                		<input type="hidden" name="scoId" id="scoId" value="<%=courseID %>">
                        <textarea id="discussContent" name='discussContent' style='width: 700px; height: 200px; resize: none;' class='userEditor'></textarea>
                        <div class='submit'>
                            <a href='javascript:void(0)' onclick="addDiscuss()">发布</a>
                        </div>
                    </div>
                	<div class='top'>同学们的讨论</div>
                	<c:forEach var="m" items="${disList }">
                    <div class="answerlist ques-list"> 
                        <a href="#" class="queHead l"> 
                        	<img src="img/user_img.jpg" width="40" height="40">
                        </a> 
                        <div class="queslist l"> 
                        	<span class='userName'><a href="#">${m.userName } </a></span> 
                            <p class='userCommit'> ${m.discussContent }</p>   
                            <div class="finaltime">
                            	<span>发布时间：${m.discussTime}</span> 
                                <a href="javascript:void(0)">
                                	<span class="replyDetail"><em class="replaynum"></em>回复 </span>
                                </a>
                            </div>  
                        </div>																						
                    </div>
                    </c:forEach>
                </div>
                <%
                NoteinfoService noteinfoService = (NoteinfoService)context.getBean("noteinfoService");
                userId = ActionSupport.getSessionUser().getUserId();
                List<Noteinfo> noteList = noteinfoService.findSql("from Noteinfo where userId = "+userId+" and courseId = "+cId+" order by noteId desc");
                request.setAttribute("noteList",noteList);
                
                %>
                <!-- 笔记 -->
                <div class='note' style='display:none;'>
                	<div class='editor'>
                        <textarea name='message' style='width: 700px; height: 200px;'></textarea>
                        <div class='submit'>
                            <a href='javascript:void(0)' onclick="addNote()">发布</a>
                        </div>
                    </div>
                    
                    <div id="course_note" class="course_note">
                    	<ul class="note_">   
                    	<c:forEach var="n" items="${noteList}">
                        	<li> 
                            	<div class="notelist_headpic"> 
                                	<a href="#"> 
                                    	<img src="img/user_img.jpg" width="40" height="40">
                                    </a> 
                                </div> 
                                <div class="notelist_content"> 
                                	<div class="u_name"> 
                                    	<a href="#">${userinfo.userName}</a> 
                                    </div> 
                                    <input type="hidden"   name="courseId" value="${n.courseId }">
                                    <input type="hidden" id="noteId" name="noteId" value="${n.noteId }">
                                    <p class="noteContent">
                                    	${n.noteContent }
                                    </p>   
                                    <div class="notelist-bottom clearfix">  
                                    	<span class="l timeago">${n.noteTime }</span> 
                                        <div class="notelist-actions">  
                                        	<a href="javascript:void(0)">
                                        	<input type="hidden" id="shareNoteId" name="shareNoteId" value="${n.noteId }">
                                        		<c:if test="${n.noteTitle eq '0' }">
                                				<span  class="updateDetail"  style="color:gray">分享 </span>
                                				</c:if>
                                				<c:if test="${n.noteTitle eq '1' }">
                                				<span class="updateDetail"    style="color:gray">取消分享 </span>
                                				</c:if>
                                			</a>
                                        </div>  
                                         
                                    </div> 
                                </div> 
                            </li> 
                           </c:forEach>
                        </ul>
                    </div>
                </div>
                <%
                List<Noteinfo> shareList = noteinfoService.findSql("from Noteinfo where noteTitle = '1' and courseId = "+cId+" order by noteId desc");
                request.setAttribute("shareList",shareList);
                List<Userinfo> ulist = userinfoService.findSql("from Userinfo");
                request.setAttribute("ulist",ulist);
                
                %>
                <div class='otherNote' style='display:none'>
                	<div id="course_note" class="course_note">
                    	<ul class="note_">   
                    	<c:forEach var="n" items="${shareList}">
                        	<li> 
                            	<div class="notelist_headpic"> 
                                	<a href="#"> 
                                    	<img src="img/user_img.jpg" width="40" height="40">
                                    </a> 
                                </div> 
                                <div class="notelist_content"> 
                                	<div class="u_name"> 
                                    	<a href="#">
                                    	<c:forEach var="u" items="${ulist}">
	                                    	<c:if test="${n.userId eq u.userId }">
	                                    		${u.userName }
	                                    	</c:if>
                                    	</c:forEach>
                                    	</a> 
                                    </div> 
                                    <input type="hidden"   name="courseId" value="${n.courseId }">
                                    <input type="hidden" id="noteId" name="noteId" value="${n.noteId }">
                                    <p class="noteContent">
                                    	${n.noteContent }
                                    </p>   
                                    <div class="notelist-bottom clearfix">  
                                    	<span class="l timeago">${n.noteTime }</span> 
                                        <div class="notelist-actions">  
                                        	<a href="javascript:void(0)">
                                			</a>
                                        </div>  
                                         
                                    </div> 
                                </div> 
                            </li> 
                           </c:forEach>
                        </ul>
                    </div>
                </div>
                
            </div>
            <%
            	List<Userinfo> userList = userinfoService.findSql("from Userinfo where userId in ( select a.id.userinfo.userId from Coursereg as a where  a.id.courseinfo.courseId = "+cId+")");
            	request.setAttribute("userList", userList);
            %>
            <!-- 正在学习的人 -->
            <div class='content_right'>
            	<div class="sidebar">
                    <dl>
                      <dt> <span style="color:#be3948"><%=userList.size() %></span> 位同学学习过</dt>
                      <dd>
                        <ul class="users">
                        	<c:forEach var="user" items="${userList }">                          
                        	<li>
                            	<a href="#">
                                	<img src="img/user_img.jpg" width="40" height="40">
                                </a>
                                <h3>
                                	<a href="#">${user.userName }</a>
                                </h3>
                                <em>${user.userSex }</em>
                            </li>
                            </c:forEach>  
                        </ul>
                      </dd>
                    </dl>
                   <br class="clear">
                </div>
            </div>
        </div>
        
        <footer>
        </footer>
    </body>
    
    <script>
		<!-- 评论 li -->
		var ul = $('.content_left').find('ul')[0] ;
		$(ul).find('li').mouseover(function() {
			$(this).css('border-bottom', '3px solid rgb(201, 57, 74)') ;
		}) ;
		
		$(ul).find('li').mouseout(function() {
			if(this.className.indexOf('current') < 0) {
				$(this).css('border-bottom', '') ;
			}
		}) ;
		
		var aLi = $(ul).find('li') ;
		for( var i = 0 ; i < aLi.length ; i ++ ) {
			aLi[i].index = i ;
			aLi[i].onclick = function() {
				if( this.index != 1 ) {
					if( this.index == 0 ) {
						$('.comment').css('display', 'block') ;
						$('.note').css('display', 'none') ;
						$('.otherNote').css('display', 'none') ;
					} else if( this.index == 2 ) {
						$('.comment').css('display', 'none') ;
						$('.note').css('display', 'block') ;
						$('.otherNote').css('display', 'none') ;
					} else if( this.index == 4 ) {
						$('.comment').css('display', 'none') ;
						$('.note').css('display', 'none') ;
						$('.otherNote').css('display', 'block') ;
					}
					$(this).siblings().removeClass('current').css('border-bottom', '');
					$(this).addClass('current');
				}
			}
		}
	
		var full = false ;
		$('#fullView').click(function() {
			full = !full ;
			if( full ) {
				$('#header').stop().animate({top:-55});
				$('#view_left').stop().animate({left:-250});
				$('#video').stop().animate({top:-55,left:-250},'normal','swing',function() {
					$('.view_right').css('width', '1366px').css('height', '595px') ;
					//$('.full_view').stop().animate({top:545}) ;
				}) ;
				this.innerHTML = "<a href='javascript:void(0)' id='fullView'>退出全屏</a>" ;
			} else {
				$('#header').stop().animate({top:0});
				$('#view_left').stop().animate({left:0});
				$('#video').stop().animate({top:0,left:0},'normal','swing',function() {
					$('.view_right').css('width', '1115px').css('height', '540px') ;
					//$('.full_view').stop().animate({top:500}) ;
				}) ;
				this.innerHTML = "<a href='javascript:void(0)' id='fullView'>全屏观看</a>" ;
			}
		}) ;
		
		$('.replyDetail').click(function() {
			var userName = $(this).parent().parent().parent().find('.userName')[0] ;
			var userCommit = $(this).parent().parent().parent().find('.userCommit')[0] ;
			var userEditor = $(document).find('.ke-edit')[0] ;
			
			editor.html('<b>回复 ' + $(userName).text() + ' - ' + $(userCommit).text() + ' : </b><br / >');
		}); 
		
		
	</script>
</html>