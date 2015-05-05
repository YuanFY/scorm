// JavaScript Document  onclick="shareNote(${n.noteId})"
$(function (){
	$(".updateDetail").click(function(){
		var node = $(this);
		var value = $(this).parent().find('#shareNoteId')[0] ; 
		$.ajax({
			type:"post",
			url:"note_updateShare.action?noteId="+$(value).val(),
			dataType:"json",
			success:function(data){
				if($(node).text().trim()=="分享")
					$(node).text("取消分享");
				else
					$(node).text("分享");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 
			}
		});
	});
	
});


function addNote(){
	var value = editor1.text();
	if(value==null || value==""){
		return false;
	}
	 
		var params = {
				 courseId:$("#courseId").val(),
				 noteContent:value
		};
		$.ajax({
			type:"post",
			url:"note_save.action",
			data:params,
			dataType:"json",
			success:function(data){
				var d = eval("("+data+")");
				for(var i in d){
					var datetime = (2000-100+d[i].noteTime.year)
                	+"-"+d[i].noteTime.month+'-'+d[i].noteTime.date+" "+
                	d[i].noteTime.hours+":"+d[i].noteTime.minutes+":"+d[i].noteTime.seconds;
				
					$('<li style="list-style:none"> '+
                        	'<div class="notelist_headpic"> '+
                        	'<a href="#"> '+
                            '	<img src="img/user_img.jpg" width="40" height="40">'+
                            '</a> '+
                        '</div> '+
                        '<div class="notelist_content"> '+
                        	'<div class="u_name"> '+
                            '	<a href="#">'+d[i].userName+'</a> '+
                            '</div> '+
                            '<input type="hidden"   name="courseId" value="${n.courseId }">'+
                            '<input type="hidden" id="noteId" name="noteId" value="${n.noteId }">'+
                            '<p class="noteContent">'+
                            d[i].noteContent+
                            '</p>'+   
                            '<div class="notelist-bottom clearfix">'+  
                            	'<span class="l timeago">'+datetime+'</span>'+ 
                                '<div class="notelist-actions">'+  
                                	'<a href="javascript:void(0)">'+
                                	'<input type="hidden" id="shareNoteId" name="shareNoteId" value='+d[i].noteId+'>'+
                        				'<span  class="updateDetail"  style="color:gray">分享 </span>'+
                        			'</a>'+
                                '</div>  '+
                                 
                            '</div> '+
                        '</div> '+
                    '</li> ').insertBefore("ul[class='note_']");
					 break;
				}
				editor1.html("");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 
			}
		});
	
}
function addDiscuss(){
	var value = editor.text();
	if(value==null || value==""){
		return false;
	}
	var params = {
			 courseId:$("#courseId").val(),
			 scoId:$("#scoId").val(),
			 discussContent:value
	};
	$.ajax({
		type:"post",
		url:"discuss_save.action",
		data:params,
		dataType:"json",
		//async:false,
		success:function(data){
			var d = eval("("+data+")");
			for(var i in d){
				$('<div class="answerlist ques-list"> '+
				'<a href="#" class="queHead l">'  +
						'<img src="img/user_img.jpg" width="40" height="40"> </a>'+ 
                        '<div class="queslist l"> '+
                        	'<span class="userName"><a href="#">' + d[i].userName +' </a></span>'+ 
                           ' <p class="userCommit"> ' + d[i].discussContent +'</p>   '+
                            '<div class="finaltime">'+
                            	'<span>发布时间：' + (2000-100+d[i].discussTime.year)
                            	+"-"+d[i].discussTime.month+'-'+d[i].discussTime.date+" "+
                            	d[i].discussTime.hours+":"+d[i].discussTime.minutes+":"+d[i].discussTime.seconds+'</span> '+
                                '<a href="javascript:void(0)">  <span class="replyDetail"><em class="replaynum"></em>回复 </span>'+
                                '</a>'+
                            '</div> ' +
                        '</div>'	+																			
                    '</div>').insertAfter("div[class='top']");
				 break;
			}
			editor.html("");
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("error");
		}
	});
}