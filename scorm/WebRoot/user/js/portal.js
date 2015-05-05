function subscribe2Course(code) {
	var txt = '您确认报名学习该门课程吗?';
	art.dialog({
		content : txt,
		icon : "question",
		lock : "true",
		window : "top",
		title : '确认操作',
		ok : function() {
			art.dialog.tips("正在处理中，请稍候...", 60);
			$.post(web_path + '?c=Home.MyCourses&m=ajax_subscribe_course', {
				course_code : code,
				course_class_id : 0
			}, function(data) {
				// alert(data+"---"+data=="EnrollToCourseSuccess");
				if ($.trim(data) == "SubmitedApplySuccess") {
					art.dialog({
						content : '您已提交该课程的选修申请,请等待审核!',
						icon : "question",
						lock : "true",
						window : "top",
						title : '确认操作',
						ok : function() {
							location.href = web_path
									+ "?c=Home.CourseCatalog&m=course_catalog";
						},
						cancel : function() {
						}
					});
				} else if ($.trim(data) == "EnrollToCourseSuccess") {
					art.dialog({
						content : "您已成功报名学习该课程!",
						icon : "question",
						lock : "true",
						window : "top",
						title : '确认操作',
						ok : function() {
							// top.location.href=web_path+"?c=Home.MyCourses&m=course_home&cidReq="+code;
							location.href = web_path
									+ "?c=Home.CourseCatalog&m=course_catalog";
						},
						cancel : function() {
						}
					});
				} else if ($.trim(data) == "ErrorContactPlatformAdmin") {
					dialog_alert("操作失败，如有任何疑问，请联系系统管理员！");
				} else if ($.trim(data) == "YouArtNotAllowedToSubTheCourse") {
					dialog_alert("你不允许报名学习该课程,请联系课程管理员!");
				} else if ($.trim(data) == "YouHaveSubmittedApplyBefore") {
					dialog_alert("你已提交过报名申请，请不要重复提交！!");
					art.dialog.close();
				}

				var list = art.dialog.list;
				for ( var i in list) {
					// list[i].close();
				}
			}, 'text');
		},
		cancel : function() {
			var list = art.dialog.list;
			for ( var i in list) {
				list[i].close();
			}
		}
	});
}

function load_default_image(img) {
	var pic = parseInt(Math.random() * 24 + 1);
	img.src = web_path + 'assets/img/course/' + pic + '.jpg';
	//img.className='lowersrc';
}

var G_UNREAD_NOTIFICATION = 0;
var G_UNREAD_PM=0;
function check_notifications() {
	var d = new Date()
	$.get(web_path + '?c=Home.Message&m=ajax_notifications&time='+d.getTime(),
	function(result) {
		last_unread_notification = G_UNREAD_NOTIFICATION;
		G_UNREAD_NOTIFICATION = Number(result.rsm.notifications_num);
		G_UNREAD_PM=Number(result.rsm.inbox_num);
		$('#inbox_unread').html(G_UNREAD_PM);
		$('#msg_unread').html(G_UNREAD_NOTIFICATION+G_UNREAD_PM);
		if (G_UNREAD_NOTIFICATION > 0) {
			if (G_UNREAD_NOTIFICATION != last_unread_notification) {
				$('#notifications_unread').html(G_UNREAD_NOTIFICATION);
			}
		} else {
			if ($('#header_notification_list').length > 0) {
				$("#header_notification_list").html('<p style="padding: 0" align="center">没有未读通知</p>');
			}

			if ($("#index_notification").length > 0) {
				$("#index_notification").fadeOut();
			}
		}

		if (G_UNREAD_NOTIFICATION > 0) {
			document.title = '(' + (G_UNREAD_NOTIFICATION + G_UNREAD_PM)+ ') ' + document_title;
			$('#notifications_unread').show();
		} else {
			$('#notifications_unread').hide();
		}

		if (G_UNREAD_PM > 0) {
			$('#inbox_unread').show();
		} else {
			$('#inbox_unread').hide();
		}

		if ((G_UNREAD_NOTIFICATION + G_UNREAD_PM) > 0) {
			document.title = '('+ (G_UNREAD_NOTIFICATION + G_UNREAD_PM)+ ') ' + document_title;
			$('#msg_unread').show();
		} else {
			document.title = document_title;
			$('#msg_unread').hide();
		}
	}, 'json');
}

function addToMyFavorite(code,type){
	$.post(web_path+"?c=Home.MyProfile&m=ajax_add_to_my_favorite",{item_id:code,item_type:type},
			function(data){
			if(data=="1"){ art.dialog.tips("收藏成功!");	}
			else if(data=="101"){ art.dialog.tips("已存在的收藏项，不允许重复添加!");}
			else{art.dialog.tips("收藏失败!");}
	},'text');
}

function removeFromMyFavorite(code,type){
	art.dialog({
		title:"确认执行该操作",
		content : "您确认移除该收藏项吗?",
		icon : "question",
		lock : "true",
		window : "top",
		ok : function() {
			$.get(web_path+"?c=Home.MyProfile&m=ajax_remove_my_favorite&item_id="+code+"&item_type="+type,
					function(data){
					if(data=="1"){
						location.reload();
						art.dialog.tips("移除收藏成功!");	}
					else{art.dialog.tips("移除收藏失败!");}
			},'text');
		},
		cancel : function() {
		}
	});
}

function addToErrorQuestionSet(question_id,track_id){
	$.post(web_path+"?c=Home.MyProfile&m=ajax_add_to_my_error_questions",{question_id:question_id,track_id:track_id},
			function(data){
			if(data=="1"){ art.dialog.tips("添加成功!");	}
			else if(data=="101"){ art.dialog.tips("已存在的错题，不允许重复添加!");}
			else{art.dialog.tips("操作失败!");}
	},'text');
}


function removeFromMyErrorQuestionSet(id){
	art.dialog({
		title:"确认执行该操作",
		content : "您确认移除该错题吗?",
		icon : "question",
		lock : "true",
		window : "top",
		ok : function() {
			$.get(web_path+"?c=Home.MyProfile&m=ajax_remove_my_error_question&id="+id,
					function(data){
					if(data=="1"){
						location.reload();
						art.dialog.tips("移除错题成功!");	}
					else{art.dialog.tips("移除错题失败!");}
			},'text');
		},
		cancel : function() {
		}
	});
}